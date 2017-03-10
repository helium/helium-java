package com.helium.client;

import com.helium.api.HeliumApi;

import java.io.IOException;

public class Organization {

    private HeliumApi api;
    private com.helium.resource.Organization model;


    private Organization(HeliumApi api, com.helium.resource.Organization model) {
        this.api = api;
        this.model = model;
    }

    public static Organization organization(HeliumApi api) throws IOException {
        return new Organization(api, api.org.organization().execute().body().get());
    }
}
