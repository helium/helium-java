package com.helium.client;

import com.helium.api.HeliumApi;
import com.helium.model.Metadata;

import java.io.IOException;

public class Organization implements HasMetadata {

    private HeliumApi api;
    private com.helium.model.Organization model;


    private Organization(HeliumApi api, com.helium.model.Organization model) {
        this.api = api;
        this.model = model;
    }

    public static Organization organization(HeliumApi api) throws IOException {
        return new Organization(api, api.org.organization().execute().body().get());
    }

    public Organization setName(String name) throws IOException {
        return setAttributes(name, model.getTimezone());
    }

    public Organization setTimezone(String timezone) throws IOException {
        return setAttributes(model.getName(), timezone);
    }

    public Organization setAttributes(String name, String timezone) throws IOException {
        model.setName(name);
        model.setTimezone(timezone);
        return new Organization(api, api.org.updateOrganization(model).execute().body().get());
    }

    @Override
    public Metadata metadata() throws IOException {
        return api.org.organizationMetadata().execute().body();
    }

    @Override
    public Organization updateMetadata(Metadata metadata) throws IOException {
        api.org.updateOrganizationMetadata(metadata).execute().body();
        return this;
    }

    @Override
    public Organization replaceMetadata(Metadata metadata) throws IOException {
        api.org.replaceOrganizationMetadata(metadata).execute().body();
        return this;
    }

    @Override
    public String toString() {
        return model.toString();
    }

}
