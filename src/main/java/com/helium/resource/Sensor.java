package com.helium.resource;

import com.github.jasminb.jsonapi.annotations.Type;


@Type("sensor")
public class Sensor extends Resource {

    public static Sensor newVirtualSensor(String name) {
        Sensor vSensor = new Sensor();
        vSensor.setName(name);
        return vSensor;
    }

    @Override
    public String toString() {
        return "Sensor{} " + super.toString();
    }
}
