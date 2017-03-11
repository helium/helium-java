package com.helium.model;

import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.List;


@Type("element")
public class Element extends Resource {

    @Relationship("label")
    private List<Label> elementLabels;

    @Override
    public String toString() {
        return "Element{" +
                "elementLabels=" + elementLabels +
                "} " + super.toString();
    }
}
