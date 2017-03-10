package com.helium.resource;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.ArrayList;
import java.util.List;


@Type("label")
public class Label extends Resource {

    @Relationship("sensor")
    private List<Sensor> labelSensors;

    public static Label newLabel(String name) {
        Label label = new Label();
        label.setName(name);
        return label;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelSensors=" + labelSensors +
                "} " + super.toString();
    }
}
