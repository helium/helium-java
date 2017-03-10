package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.HeliumApi;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Label {

    private final HeliumApi api;
    private com.helium.resource.Label model;

    protected Label(HeliumApi api, com.helium.resource.Label model) {
        this.api = api;
        this.model = model;
    }

    public static Label createLabel(HeliumApi api, String labelName) throws IOException {
        com.helium.resource.Label newModel =
                api.label.createLabel(com.helium.resource.Label.newLabel(labelName))
                .execute().body().get();
        return new Label(api, newModel);
    }

    public static List<Label> getLabels(HeliumApi api) throws IOException {
        List<com.helium.resource.Label> labels = api.label.labels().execute().body().get();
        List<Label> clientLabels = new ArrayList<>();
        for (com.helium.resource.Label label : labels) {
            clientLabels.add(new Label(api, label));
        }
        return clientLabels;
    }

    public static Optional<Label> lookupLabel(HeliumApi api, String labelId) throws IOException {
        Response<JSONAPIDocument<com.helium.resource.Label>> labelResponse = api.label.label(labelId).execute();
        if (labelResponse.isSuccessful()) {
            return Optional.of(new Label(api, labelResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    public List<Sensor> getRelatedSensors() throws IOException {
        Response<JSONAPIDocument<List<com.helium.resource.Sensor>>> response =
                api.label.labelRelationshipSensors(model.id()).execute();
        List<com.helium.resource.Sensor> sensorModels = response.body().get();
        List<Sensor> sensors = new ArrayList<>();
        for (com.helium.resource.Sensor model : sensorModels) {
            sensors.add(new Sensor(api, model));
        }
        return sensors;
    }

    public void delete() throws IOException {
        api.label.deleteLabel(model.id()).execute();
    }

    public String id() {
        return model.id();
    }

    @Override
    public String toString() {
        return model.toString();
    }


}
