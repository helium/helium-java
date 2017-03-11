package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.HeliumApi;
import com.helium.resource.DataPoint;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Element {

    private HeliumApi api;
    private com.helium.resource.Element model;

    protected Element(HeliumApi api, com.helium.resource.Element model) {
        this.api = api;
        this.model = model;
    }

    public static List<Element> getElements(HeliumApi api) throws IOException {
        List<com.helium.resource.Element> elements =
                api.element.elements().execute().body().get();
        List<Element> clientElements = new ArrayList<Element>();
        for (com.helium.resource.Element element : elements) {
            clientElements.add(new Element(api, element));
        }
        return clientElements;
    }

    public static Optional<Element> lookupElement(HeliumApi api, String elementId) throws IOException {
        Response<JSONAPIDocument<com.helium.resource.Element>> elementResponse = api.element.element(elementId).execute();
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

    public String id() {
        return model.id();
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
