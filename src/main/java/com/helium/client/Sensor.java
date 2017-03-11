package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.HeliumApi;
import com.helium.model.DataPoint;
import com.helium.model.Metadata;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sensor {

    private HeliumApi api;
    private com.helium.model.Sensor model;

    protected Sensor(HeliumApi api, com.helium.model.Sensor model) {
        this.api = api;
        this.model = model;
    }

    public static Sensor createSensor(HeliumApi api, String sensorName) throws IOException {
        return new Sensor(
                api,
                api.sensor.createSensor(com.helium.model.Sensor.newVirtualSensor(sensorName))
            .execute().body().get());
    }

    public static List<Sensor> getSensors(HeliumApi api) throws IOException {
        List<com.helium.model.Sensor> sensors =
                api.sensor.sensors().execute().body().get();
        List<Sensor> clientSensors = new ArrayList<Sensor>();
        for (com.helium.model.Sensor sensor : sensors) {
            clientSensors.add(new Sensor(api, sensor));
        }
        return clientSensors;
    }

    public static Optional<Sensor> lookupSensor(HeliumApi api, String sensorId) throws IOException {
        Response<JSONAPIDocument<com.helium.model.Sensor>> sensorResponse = api.sensor.sensor(sensorId).execute();
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

    public Metadata metadata() throws IOException {
        return api.sensor.sensorMetadata(id()).execute().body();
    }

    public Sensor updateMetadata(Metadata metadata) throws IOException {
        api.sensor.updateSensorMetadata(id(), metadata).execute().body();
        return this;
    }

    public Sensor replaceMetadata(Metadata metadata) throws IOException {
        api.sensor.replaceSensorMetadata(id(), metadata).execute().body();
        return this;
    }

    public String id() {
        return model.id();
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
