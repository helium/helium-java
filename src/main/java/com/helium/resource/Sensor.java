package com.helium.resource;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.ArrayList;
import java.util.List;


@Type("sensor")
public class Sensor extends Resource {

    @Relationship("label")
    private List<Label> sensorLabels;

    public static Sensor newVirtualSensor(String name) {
        Sensor vSensor = new Sensor();
        vSensor.setName(name);
        return vSensor;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "sensorLabels=" + sensorLabels +
                "} " + super.toString();
    }
}
