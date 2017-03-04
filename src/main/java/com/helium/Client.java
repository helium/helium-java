package com.helium;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.github.jasminb.jsonapi.retrofit.JSONAPIConverterFactory;
import com.helium.resource.Organization;
import com.helium.resource.Sensor;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


public class Client {

    private HeliumApi service;

    public Client() {
        String baseUrl = System.getenv("HELIUM_API_URL");
        String apiKey = System.getenv("HELIUM_API_KEY");
        new Client(baseUrl, apiKey);
    }

    public Client(String baseUrl, final String apiToken) {
        ObjectMapper objectMapper = new ObjectMapper();
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().addInterceptor(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request originalRequest = chain.request();

                Request.Builder builder = originalRequest.newBuilder().header("Authorization", apiToken);

                Request newRequest = builder.build();
                return chain.proceed(newRequest);
            }
        }).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl+"/")
                .client(okHttpClient)
                .addConverterFactory(
                    new JSONAPIConverterFactory(objectMapper,
                        Sensor.class, Organization.class
                    ))
                .build();
        service = retrofit.create(HeliumApi.class);
    }

    public Organization organization() throws IOException {
        return service.organization().execute().body().get();
    }

    public List<Sensor> sensors() throws IOException {
        return service.sensors().execute().body().get();
    }


    public Optional<Sensor> lookupSensor(String sensorId) throws IOException {
        Response<JSONAPIDocument<Sensor>> sensorResponse = service.sensor(sensorId).execute();
        if (sensorResponse.isSuccessful()) {
            return Optional.of(sensorResponse.body().get());
        }
        else {
            return Optional.empty();
        }
    }

    public Sensor createVirtualSensor(String sensorName) throws IOException {
        return service.createSensor(Sensor.newVirtualSensor(sensorName)).execute().body().get();

    }
}
