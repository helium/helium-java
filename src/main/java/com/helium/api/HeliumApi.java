package com.helium.api;


public class HeliumApi {

    public final LabelApi label;
    public final OrganizationApi org;
    public final SensorApi sensor;
    public final UserApi user;

    public HeliumApi(LabelApi label, OrganizationApi org, SensorApi sensor, UserApi user) {
        this.label = label;
        this.org = org;
        this.sensor = sensor;
        this.user = user;
    }
}
