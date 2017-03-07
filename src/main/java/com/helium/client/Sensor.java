package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.SensorApi;
import com.helium.resource.DataPoint;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sensor {

    private SensorApi api;
    private com.helium.resource.Sensor model;

    private Sensor(SensorApi api, com.helium.resource.Sensor model) {
        this.api = api;
        this.model = model;

    }

    public static Sensor createSensor(SensorApi api, String sensorName) throws IOException {
        return new Sensor(
                api,
                api.createSensor(com.helium.resource.Sensor.newVirtualSensor(sensorName))
            .execute().body().get());
    }

    public static List<Sensor> getSensors(SensorApi api) throws IOException {
        List<com.helium.resource.Sensor> sensors =
                api.sensors().execute().body().get();
        List<Sensor> clientSensors = new ArrayList<Sensor>();
        for (com.helium.resource.Sensor sensor : sensors) {
            clientSensors.add(new Sensor(api, sensor));
        }
        return clientSensors;
    }

    public static Optional<Sensor> lookupSensor(SensorApi api, String sensorId) throws IOException {
        Response<JSONAPIDocument<com.helium.resource.Sensor>> sensorResponse = api.sensor(sensorId).execute();
        if (sensorResponse.isSuccessful()) {
            return Optional.of(new Sensor(api, sensorResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    public List<DataPoint> timeseries() throws IOException {
        JSONAPIDocument<List<DataPoint>> dataPoints =
                api.timeseries(model.id()).execute().body();
        return dataPoints.get();
    }

    public DataPoint createDataPoint(DataPoint dataPoint) throws IOException {
        JSONAPIDocument<DataPoint> newDataPoint =
                api.createDataPoint(model.id(), dataPoint).execute().body();
        return newDataPoint.get();
    }

    public void delete() throws IOException {
        api.deleteSensor(model.id()).execute();
    }

    public String id() {
        return model.id();
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
