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
 * Convenience class to used explore the Helium API. This class gets the Helium API token from the environment variable
 * `HELIUM_API_KEY` to set up a connection to the Helium API. Use {@link com.helium.Client#client(String)} if setting
 * the Helium API token another way.
 */
public class Helium {

    private static final String heliumApiKey = System.getenv("HELIUM_API_KEY");
    private static final Client instance = Client.client(heliumApiKey);


    public static Organization organization() throws IOException {
        return instance.organization();
    }

    public static List<Element> elements() throws IOException {
        return instance.elements();
    }

    public static List<Sensor> sensors() throws IOException {
        return instance.sensors();
    }

    public static List<Label> labels() throws IOException {
        return instance.labels();
    }

    public static Optional<Element> lookupElement(String elementId) throws IOException {
        return instance.lookupElement(elementId);
    }

    public static Optional<Label> lookupLabel(String labelId) throws IOException {
        return instance.lookupLabel(labelId);
    }

    public static Label createLabel(String labelName) throws IOException {
        return instance.createLabel(labelName);
    }

    public static Optional<Sensor> lookupSensor(String sensorId) throws IOException {
        return instance.lookupSensor(sensorId);
    }

    public static Sensor createVirtualSensor(String sensorName) throws IOException {
        return instance.createVirtualSensor(sensorName);
    }

    public static User user() throws IOException {
        return instance.user();
    }
}
