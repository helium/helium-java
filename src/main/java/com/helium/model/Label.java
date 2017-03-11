package com.helium.model;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.List;


@Type("label")
public class Label extends Resource {

    @Relationship("sensor")
    private List<Sensor> labelSensors;

    @Relationship("element")
    private List<Element> labelElements;

    public static Label newLabel(String name) {
        Label label = new Label();
        label.setName(name);
        return label;
    }

    @Override
    public String toString() {
        return "Label{" +
                "labelSensors=" + labelSensors +
                ", labelElements=" + labelElements +
                "} " + super.toString();
    }
}
