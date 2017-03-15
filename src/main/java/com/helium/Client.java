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

    public Organization organization() throws IOException {
        return Organization.organization(heliumApi);
    }

    public List<Element> elements() throws IOException {
        return Element.getElements(heliumApi);
    }

    public List<Sensor> sensors() throws IOException {
        return Sensor.getSensors(heliumApi);
    }

    public List<Label> labels() throws IOException {
        return Label.getLabels(heliumApi);
    }

    public Optional<Element> lookupElement(String elementId) throws IOException {
        return Element.lookupElement(heliumApi, elementId);
    }

    public Optional<Label> lookupLabel(String labelId) throws IOException {
        return Label.lookupLabel(heliumApi, labelId);
    }

    public Label createLabel(String labelName) throws IOException {
        return Label.createLabel(heliumApi, labelName);
    }

    public Optional<Sensor> lookupSensor(String sensorId) throws IOException {
        return Sensor.lookupSensor(heliumApi, sensorId);
    }

    public Sensor createVirtualSensor(String sensorName) throws IOException {
        return Sensor.createSensor(heliumApi, sensorName);
    }

    public User user() throws IOException {
        return User.getUser(heliumApi);
    }
}
