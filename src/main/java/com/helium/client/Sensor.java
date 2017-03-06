package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.HeliumApi;
import com.helium.api.SensorApi;
import com.helium.resource.DataPoint;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Sensor {

    private SensorApi service;
    private com.helium.resource.Sensor sensor;

    public static Sensor createSensor(SensorApi service, String sensorName) throws IOException {
        return new Sensor(
                service,
                service.createSensor(com.helium.resource.Sensor.newVirtualSensor(sensorName))
            .execute().body().get());
    }

    public static List<Sensor> getSensors(SensorApi service) throws IOException {
        List<com.helium.resource.Sensor> sensors =
            service.sensors() .execute().body().get();
        List<Sensor> clientSensors = new ArrayList<Sensor>();
        for (com.helium.resource.Sensor sensor : sensors) {
            clientSensors.add(new Sensor(service, sensor));
        }
        return clientSensors;
    }

    public static Optional<Sensor> lookupSensor(SensorApi service, String sensorId) throws IOException {
        Response<JSONAPIDocument<com.helium.resource.Sensor>> sensorResponse = service.sensor(sensorId).execute();
        if (sensorResponse.isSuccessful()) {
            return Optional.of(new Sensor(service, sensorResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    private Sensor(SensorApi service, com.helium.resource.Sensor sensor) {
        this.service = service;
        this.sensor = sensor;

    }

    public List<DataPoint> timeseries() throws IOException {
        JSONAPIDocument<List<DataPoint>> dataPoints =
                service.timeseries(sensor.id()).execute().body();
        return dataPoints.get();
    }

    public DataPoint createDataPoint(DataPoint dataPoint) throws IOException {
        JSONAPIDocument<DataPoint> newDataPoint =
                service.createDataPoint(sensor.id(), dataPoint).execute().body();
        return newDataPoint.get();
    }

    public void delete() throws IOException {
        service.deleteSensor(sensor.id()).execute();
    }

    public String id() {
        return sensor.id();
    }

    @Override
    public String toString() {
        return sensor.toString();
    }
}
