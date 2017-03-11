package com.helium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.SerializationFeature;
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


public class Helium {

    private HeliumApi heliumApi;

    private static final String HELIUM_API_URL = "https://api.helium.com/v1";
    private static String heliumApiKey = System.getenv("HELIUM_API_KEY");

    private static Helium instance = new Helium(HELIUM_API_URL, heliumApiKey);


    private Helium(String baseUrl, final String apiToken) {
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
        converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);
        JSONAPIConverterFactory converterFactory = new JSONAPIConverterFactory(converter);

        ObjectMapper objectMapper = new ObjectMapper();

        JacksonConverterFactory jacksonConverterFactory = JacksonConverterFactory.create(objectMapper);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl+"/")
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

    public static Organization organization() throws IOException {
        return Organization.organization(instance.heliumApi);
    }

    public static List<Element> elements() throws IOException {
        return Element.getElements(instance.heliumApi);
    }

    public static List<Sensor> sensors() throws IOException {
        return Sensor.getSensors(instance.heliumApi);
    }

    public static List<Label> labels() throws IOException {
        return Label.getLabels(instance.heliumApi);
    }

    public static Optional<Element> lookupElement(String elementId) throws IOException {
        return Element.lookupElement(instance.heliumApi, elementId);
    }

    public static Optional<Label> lookupLabel(String labelId) throws IOException {
        return Label.lookupLabel(instance.heliumApi, labelId);
    }

    public static Label createLabel(String labelName) throws IOException {
        return Label.createLabel(instance.heliumApi, labelName);
    }

    public static Optional<Sensor> lookupSensor(String sensorId) throws IOException {
        return Sensor.lookupSensor(instance.heliumApi, sensorId);
    }

    public static Sensor createVirtualSensor(String sensorName) throws IOException {
        return Sensor.createSensor(instance.heliumApi, sensorName);
    }

    public static User user() throws IOException {
        return User.getUser(instance.heliumApi);
    }
}
