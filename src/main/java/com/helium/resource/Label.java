package com.helium.resource;

import com.github.jasminb.jsonapi.annotations.Type;


@Type("label")
public class Label extends Resource {

    public static Label newLabel(String name) {
        Label label = new Label();
        label.setName(name);
        return label;
    }

    @Override
    public String toString() {
        return "Label{} " + super.toString();
    }

}
