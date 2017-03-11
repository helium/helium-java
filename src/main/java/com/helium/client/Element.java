package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.HeliumApi;
import com.helium.model.DataPoint;
import com.helium.model.Metadata;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Element {

    private HeliumApi api;
    private com.helium.model.Element model;

    protected Element(HeliumApi api, com.helium.model.Element model) {
        this.api = api;
        this.model = model;
    }

    public static List<Element> getElements(HeliumApi api) throws IOException {
        List<com.helium.model.Element> elements =
                api.element.elements().execute().body().get();
        List<Element> clientElements = new ArrayList<Element>();
        for (com.helium.model.Element element : elements) {
            clientElements.add(new Element(api, element));
        }
        return clientElements;
    }

    public static Optional<Element> lookupElement(HeliumApi api, String elementId) throws IOException {
        Response<JSONAPIDocument<com.helium.model.Element>> elementResponse = api.element.element(elementId).execute();
        if (elementResponse.isSuccessful()) {
            return Optional.of(new Element(api, elementResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    public Element setName(String name) throws IOException {
        model.setName(name);
        return new Element(api, api.element.updateElement(model).execute().body().get());
    }

    public List<DataPoint> timeseries() throws IOException {
        JSONAPIDocument<List<DataPoint>> dataPoints =
                api.element.elementTimeseries(model.id()).execute().body();
        return dataPoints.get();
    }

    public DataPoint createDataPoint(DataPoint dataPoint) throws IOException {
        JSONAPIDocument<DataPoint> newDataPoint =
                api.element.createElementDataPoint(model.id(), dataPoint).execute().body();
        return newDataPoint.get();
    }
    
    public Metadata metadata() throws IOException {
        return api.element.elementMetadata(id()).execute().body();
    }

    public Element updateMetadata(Metadata metadata) throws IOException {
        api.element.updateElementMetadata(id(), metadata).execute().body();
        return this;
    }

    public Element replaceMetadata(Metadata metadata) throws IOException {
        api.element.replaceElementMetadata(id(), metadata).execute().body();
        return this;
    }

    public List<Sensor> sensors() throws IOException {
        List<com.helium.model.Sensor> sensorModels =
                api.element.elementSensors(id()).execute().body().get();
        List<Sensor> sensors = new ArrayList<>();
        for (com.helium.model.Sensor model : sensorModels) {
            sensors.add(new Sensor(api, model));
        }
        return sensors;
    }

    public List<Label> labels() throws IOException {
        List<com.helium.model.Label> labelModels =
                api.element.elementLabels(id()).execute().body().get();
        List<Label> labels = new ArrayList<>();
        for (com.helium.model.Label model : labelModels) {
            labels.add(new Label(api, model));
        }
        return labels;
    }

    public String id() {
        return model.id();
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
