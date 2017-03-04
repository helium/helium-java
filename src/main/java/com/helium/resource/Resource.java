package com.helium.resource;


import com.github.jasminb.jsonapi.annotations.Id;


public abstract class Resource {

    @Id
    private String id;
    private String name;

    public String id() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
