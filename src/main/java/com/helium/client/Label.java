package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.HeliumApi;
import com.helium.model.Metadata;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Label implements HasMetadata, HasSensors {

    private final HeliumApi api;
    private com.helium.model.Label model;

    protected Label(HeliumApi api, com.helium.model.Label model) {
        this.api = api;
        this.model = model;
    }

    public static Label createLabel(HeliumApi api, String labelName) throws IOException {
        com.helium.model.Label newModel =
                api.label.createLabel(com.helium.model.Label.newLabel(labelName))
                .execute().body().get();
        return new Label(api, newModel);
    }

    public static List<Label> getLabels(HeliumApi api) throws IOException {
        List<com.helium.model.Label> labels = api.label.labels().execute().body().get();
        List<Label> clientLabels = new ArrayList<>();
        for (com.helium.model.Label label : labels) {
            clientLabels.add(new Label(api, label));
        }
        return clientLabels;
    }

    public static Optional<Label> lookupLabel(HeliumApi api, String labelId) throws IOException {
        Response<JSONAPIDocument<com.helium.model.Label>> labelResponse = api.label.label(labelId).execute();
        if (labelResponse.isSuccessful()) {
            return Optional.of(new Label(api, labelResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    public Label setName(String name) throws IOException {
        model.setName(name);
        return new Label(api, api.label.updateLabel(model).execute().body().get());
    }

    @Override
    public List<Sensor> sensors() throws IOException {
        Response<JSONAPIDocument<List<com.helium.model.Sensor>>> response =
                api.label.labelRelationshipSensors(model.id()).execute();
        List<com.helium.model.Sensor> sensorModels = response.body().get();
        List<Sensor> sensors = new ArrayList<>();
        for (com.helium.model.Sensor model : sensorModels) {
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


    @Override
    public Metadata metadata() throws IOException {
        return api.label.labelMetadata(id()).execute().body();
    }

    @Override
    public Label updateMetadata(Metadata metadata) throws IOException {
        api.label.updateLabelMetadata(id(), metadata).execute().body();
        return this;
    }

    @Override
    public Label replaceMetadata(Metadata metadata) throws IOException {
        api.label.replaceLabelMetadata(id(), metadata).execute().body();
        return this;
    }
}
