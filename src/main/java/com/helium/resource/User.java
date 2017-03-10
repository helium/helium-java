package com.helium.resource;

import com.github.jasminb.jsonapi.annotations.Type;

@Type("user")
public class User extends Resource {

    @Override
    public String toString() {
        return "User{} " + super.toString();
    }

}
