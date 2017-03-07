package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.LabelApi;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

public class Label {

    private LabelApi api;
    private com.helium.resource.Label model;

    private Label(LabelApi api, com.helium.resource.Label model) {
        this.api = api;
        this.model = model;
    }

    public static Label createLabel(LabelApi api, String labelName) throws IOException {
        com.helium.resource.Label newModel =
                api.createLabel(com.helium.resource.Label.newLabel(labelName))
                .execute().body().get();
        return new Label(api, newModel);
    }

    public static List<Label> getLabels(LabelApi api) throws IOException {
        List<com.helium.resource.Label> labels = api.labels().execute().body().get();
        List<Label> clientLabels = new ArrayList<>();
        for (com.helium.resource.Label label : labels) {
            clientLabels.add(new Label(api, label));
        }
        return clientLabels;
    }

    public static Optional<Label> lookupLabel(LabelApi api, String labelId) throws IOException {
        Response<JSONAPIDocument<com.helium.resource.Label>> labelResponse = api.label(labelId).execute();
        if (labelResponse.isSuccessful()) {
            return Optional.of(new Label(api, labelResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    public void delete() throws IOException {
        api.deleteLabel(model.id()).execute();
    }

    public String id() {
        return model.id();
    }

    @Override
    public String toString() {
        return model.toString();
    }


}
