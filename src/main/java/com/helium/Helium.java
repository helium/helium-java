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
 * <code>HELIUM_API_KEY</code> to set up a connection to the Helium API. Use {@link com.helium.Client#client(String)} if setting
 * the Helium API token another way.
 */
public class Helium {

    private static final String heliumApiKey = System.getenv("HELIUM_API_KEY");
    private static final Client instance = Client.client(heliumApiKey);

    private Helium() {}

    /**
     * Get the {@link com.helium.client.Organization organization} associated with your account.
     * @throws IOException
     */
    public static Organization organization() throws IOException {
        return instance.organization();
    }

    /**
     * Get the current {@link com.helium.client.User user}.
     * @throws IOException
     */
    public static User user() throws IOException {
        return instance.user();
    }

    /**
     * Retrieve {@link com.helium.client.Element Elements} in the account.
     * @throws IOException
     */
    public static List<Element> elements() throws IOException {
        return instance.elements();
    }

    /**
     * Retrieve {@link com.helium.client.Sensor Sensors} in the account.
     * @throws IOException
     */
    public static List<Sensor> sensors() throws IOException {
        return instance.sensors();
    }

    /**
     * Retrieve {@link com.helium.client.Label Labels} in the account.
     * @throws IOException
     */
    public static List<Label> labels() throws IOException {
        return instance.labels();
    }

    /**
     * Lookup an {@link com.helium.client.Element Element} by ID.
     * @param elementId Element ID, a UUID
     * @throws IOException
     */
    public static Optional<Element> lookupElement(String elementId) throws IOException {
        return instance.lookupElement(elementId);
    }

    /**
     * Lookup a {@link com.helium.client.Sensor Sensor} by ID.
     * @param sensorId Sensor ID, a UUID
     * @throws IOException
     */
    public static Optional<Sensor> lookupSensor(String sensorId) throws IOException {
        return instance.lookupSensor(sensorId);
    }

    /**
     * Lookup a {@link com.helium.client.Label Label} by ID.
     * @param labelId Label ID, a UUID
     * @throws IOException
     */
    public static Optional<Label> lookupLabel(String labelId) throws IOException {
        return instance.lookupLabel(labelId);
    }

    /**
     * Create a new virtual {@link com.helium.client.Sensor Sensor}.
     * @param sensorName The name of the new Sensor.
     * @throws IOException
     */
    public static Sensor createVirtualSensor(String sensorName) throws IOException {
        return instance.createVirtualSensor(sensorName);
    }

    /**
     * Lookup a {@link com.helium.client.Label Label} by UUID.
     * @param labelName The name of the new Label
     * @throws IOException
     */
    public static Label createLabel(String labelName) throws IOException {
        return instance.createLabel(labelName);
    }
}
