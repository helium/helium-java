package com.helium.client;

import com.helium.api.HeliumApi;

import java.io.IOException;

public class User {
    private HeliumApi api;
    private com.helium.resource.User model;

    protected User(HeliumApi api, com.helium.resource.User model) {
        this.api = api;
        this.model = model;
    }

    public static User getUser(HeliumApi api) throws IOException {
        return new User(api, api.user.getUser().execute().body().get());
    }

    public User setName(String name) throws IOException {
        model.setName(name);
        return new User(api, api.user.updateUser(model).execute().body().get());
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
