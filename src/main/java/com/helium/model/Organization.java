package com.helium.model;


import com.github.jasminb.jsonapi.annotations.Type;

@Type("organization")
public class Organization extends Resource {

    private String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "timezone='" + timezone + '\'' +
                "} " + super.toString();
    }
}
