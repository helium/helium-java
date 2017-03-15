package com.helium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;
import com.helium.api.*;
import com.helium.client.*;
import com.helium.model.DataPoint;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

/**
 * Sets up connections and de-serialization to the Helium API. Provide an API
 * token to {@link #client(String)} to set up a connection to the API.
 * <p>
 * This is the preferred method of establishing a connection if the environment
 * variable <code>HELIUM_API_KEY</code> is not set or multiple clients are required.
 */
public class Client {

    private static final String HELIUM_API_URL = "https://api.helium.com/v1/";
    private HeliumApi heliumApi;

    private Client(String baseUrl, final String apiToken) {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization", apiToken);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        ResourceConverter converter =
                new ResourceConverter(
                        DataPoint.class,
                        com.helium.model.Element.class,
                        com.helium.model.Label.class,
                        com.helium.model.Sensor.class,
                        com.helium.model.Organization.class,
                        com.helium.model.User.class
                );
        JSONAPIConverterFactory converterFactory = new JSONAPIConverterFactory(converter);

        ObjectMapper objectMapper = new ObjectMapper();

        JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .addConverterFactory(jacksonConverterFactory)
                .build();

        heliumApi = new HeliumApi(
                retrofit.create(ElementApi.class),
                retrofit.create(LabelApi.class),
                retrofit.create(OrganizationApi.class),
                retrofit.create(SensorApi.class),
                retrofit.create(UserApi.class)
        );
    }

    /**
     * Create a new Helium client using the given API key. This is the preferred method to create a Helium instance
     * if the environment variable HELIUM_API_KEY is not set.
     * @param heliumApiKey Helium API key.
     * @return new Helium client instance.
     */
    public static Client client(String heliumApiKey) {
        return new Client(HELIUM_API_URL, heliumApiKey);
    }

    public static Client client(String baseUrl, String heliumApiKey) {
        return new Client(baseUrl, heliumApiKey);
    }

    /**
     * Get the {@link com.helium.client.Organization organization} associated with your account.
     * @throws IOException
     */
    public Organization organization() throws IOException {
        return Organization.organization(heliumApi);
    }

    /**
     * Get the current {@link com.helium.client.User user}.
     * @throws IOException
     */
    public User user() throws IOException {
        return User.getUser(heliumApi);
    }

    /**
     * Retrieve {@link com.helium.client.Element Elements} in the account.
     * @throws IOException
     */
    public List<Element> elements() throws IOException {
        return Element.getElements(heliumApi);
    }

    /**
     * Retrieve {@link com.helium.client.Sensor Sensors} in the account.
     * @throws IOException
     */
    public List<Sensor> sensors() throws IOException {
        return Sensor.getSensors(heliumApi);
    }

    /**
     * Retrieve {@link com.helium.client.Label Labels} in the account.
     * @throws IOException
     */
    public List<Label> labels() throws IOException {
        return Label.getLabels(heliumApi);
    }

    /**
     * Lookup an {@link com.helium.client.Element Element} by ID.
     * @param elementId Element ID, a UUID
     * @throws IOException
     */
    public Optional<Element> lookupElement(String elementId) throws IOException {
        return Element.lookupElement(heliumApi, elementId);
    }

    /**
     * Lookup a {@link com.helium.client.Sensor Sensor} by ID.
     * @param sensorId Sensor ID, a UUID
     * @throws IOException
     */
    public Optional<Sensor> lookupSensor(String sensorId) throws IOException {
        return Sensor.lookupSensor(heliumApi, sensorId);
    }

    /**
     * Lookup a {@link com.helium.client.Label Label} by ID.
     * @param labelId Label ID, a UUID
     * @throws IOException
     */
    public Optional<Label> lookupLabel(String labelId) throws IOException {
        return Label.lookupLabel(heliumApi, labelId);
    }

    /**
     * Create a new virtual {@link com.helium.client.Sensor Sensor}.
     * @param sensorName The name of the new Sensor.
     * @throws IOException
     */
    public Sensor createVirtualSensor(String sensorName) throws IOException {
        return Sensor.createSensor(heliumApi, sensorName);
    }

     /**
     * Lookup a {@link com.helium.client.Label Label} by UUID.
     * @param labelName The name of the new Label
     * @throws IOException
     */
    public Label createLabel(String labelName) throws IOException {
        return Label.createLabel(heliumApi, labelName);
    }
}
