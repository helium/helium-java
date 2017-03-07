package com.helium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.ResourceConverter;
import com.github.jasminb.jsonapi.SerializationFeature;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;
import com.helium.api.LabelApi;
import com.helium.api.SensorApi;
import com.helium.client.Label;
import com.helium.client.Sensor;
import com.helium.resource.DataPoint;
import com.helium.resource.Organization;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class Helium {

    private HeliumApi service;
    private SensorApi sensorApi;
    private LabelApi labelApi;

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
                com.helium.resource.Label.class,
                com.helium.resource.Sensor.class,
                Organization.class
            );

        converter.enableSerializationOption(SerializationFeature.INCLUDE_RELATIONSHIP_ATTRIBUTES);

        JSONAPIConverterFactory converterFactory = new JSONAPIConverterFactory(converter);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl+"/")
                .client(okHttpClient)
                .addConverterFactory(converterFactory)
                .build();

        service = retrofit.create(HeliumApi.class);
        sensorApi = retrofit.create(SensorApi.class);
        labelApi = retrofit.create(LabelApi.class);
    }

    public static Organization organization() throws IOException {
        return instance.service.organization().execute().body().get();
    }

    public static List<Sensor> sensors() throws IOException {
        return Sensor.getSensors(instance.sensorApi);
    }

    public static List<Label> labels() throws IOException {
        return Label.getLabels(instance.labelApi);
    }

    public static Optional<Label> lookupLabel(String labelId) throws IOException {
        return Label.lookupLabel(instance.labelApi, labelId);
    }

    public static Label createLabel(String labelName) throws IOException {
        return Label.createLabel(instance.labelApi, labelName);
    }

    public static Optional<Sensor> lookupSensor(String sensorId) throws IOException {
        return Sensor.lookupSensor(instance.sensorApi, sensorId);
    }

    public static Sensor createVirtualSensor(String sensorName) throws IOException {
        return Sensor.createSensor(instance.sensorApi, sensorName);
    }
}
