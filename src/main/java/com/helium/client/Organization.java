package com.helium.client;

import com.helium.api.OrganizationApi;

import java.io.IOException;

public class Organization {

    private OrganizationApi api;
    private com.helium.resource.Organization model;


    private Organization(OrganizationApi api, com.helium.resource.Organization model) {
        this.api = api;
        this.model = model;
    }

    public static Organization organization(OrganizationApi api) throws IOException {
        return new Organization(api, api.organization().execute().body().get());
    }
}
