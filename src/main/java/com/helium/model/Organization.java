package com.helium.model;


import com.github.jasminb.jsonapi.annotations.Relationship;
import com.github.jasminb.jsonapi.annotations.Type;

import java.util.List;

@Type("organization")
public class Organization extends Resource {

    @Relationship("user")
    private List<User> orgUsers;

    private String timezone;

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public List<User> getUsers() {
        return orgUsers;
    }

    @Override
    public String toString() {
        return "Organization{" +
                "orgUsers=" + orgUsers +
                ", timezone='" + timezone + '\'' +
                "} " + super.toString();
    }
}
