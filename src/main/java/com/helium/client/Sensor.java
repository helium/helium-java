package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.HeliumApi;
import com.helium.resource.DataPoint;
import com.helium.resource.Metadata;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sensor {

    private HeliumApi api;
    private com.helium.resource.Sensor model;

    protected Sensor(HeliumApi api, com.helium.resource.Sensor model) {
        this.api = api;
        this.model = model;
    }

    public static Sensor createSensor(HeliumApi api, String sensorName) throws IOException {
        return new Sensor(
                api,
                api.sensor.createSensor(com.helium.resource.Sensor.newVirtualSensor(sensorName))
            .execute().body().get());
    }

    public static List<Sensor> getSensors(HeliumApi api) throws IOException {
        List<com.helium.resource.Sensor> sensors =
                api.sensor.sensors().execute().body().get();
        List<Sensor> clientSensors = new ArrayList<Sensor>();
        for (com.helium.resource.Sensor sensor : sensors) {
            clientSensors.add(new Sensor(api, sensor));
        }
        return clientSensors;
    }

    public static Optional<Sensor> lookupSensor(HeliumApi api, String sensorId) throws IOException {
        Response<JSONAPIDocument<com.helium.resource.Sensor>> sensorResponse = api.sensor.sensor(sensorId).execute();
        if (sensorResponse.isSuccessful()) {
            return Optional.of(new Sensor(api, sensorResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    public List<DataPoint> timeseries() throws IOException {
        JSONAPIDocument<List<DataPoint>> dataPoints =
                api.sensor.timeseries(model.id()).execute().body();
        return dataPoints.get();
    }

    public DataPoint createDataPoint(DataPoint dataPoint) throws IOException {
        JSONAPIDocument<DataPoint> newDataPoint =
                api.sensor.createDataPoint(model.id(), dataPoint).execute().body();
        return newDataPoint.get();
    }

    public void delete() throws IOException {
        api.sensor.deleteSensor(model.id()).execute();
    }

    public Metadata sensorMetadata() throws IOException {
        return api.sensor.sensorMetadata(id()).execute().body();
    }

    public String id() {
        return model.id();
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
