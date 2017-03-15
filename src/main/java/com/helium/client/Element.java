package com.helium.client;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.HeliumApi;
import com.helium.exception.HeliumException;
import com.helium.model.DataPoint;
import com.helium.model.Metadata;
import retrofit2.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Element implements HasLabels, HasMetadata, HasSensors {

    private HeliumApi api;
    private com.helium.model.Element model;

    protected Element(HeliumApi api, com.helium.model.Element model) {
        this.api = api;
        this.model = model;
    }

    /**
     * List all Elements in the account.
     * @return
     * @throws IOException
     */
    public static List<Element> getElements(HeliumApi api) throws IOException {
        List<com.helium.model.Element> elements =
                api.element.elements().execute().body().get();
        List<Element> clientElements = new ArrayList<Element>();
        for (com.helium.model.Element element : elements) {
            clientElements.add(new Element(api, element));
        }
        return clientElements;
    }

    /**
     * Lookup an Element by ID.
     * @param elementId ID of the Element
     * @return {@link java.util.Optional#of Optional.of} the Element if one exists with the given ID, otherwise {@link Optional#empty() Optional.empty}
     * @throws IOException
     */
    public static Optional<Element> lookupElement(HeliumApi api, String elementId) throws IOException {
        Response<JSONAPIDocument<com.helium.model.Element>> elementResponse = api.element.element(elementId).execute();
        if (elementResponse.isSuccessful()) {
            return Optional.of(new Element(api, elementResponse.body().get()));
        }
        else {
            return Optional.empty();
        }
    }

    /**
     * Change the name of an Element
     * @param name Name to be set
     * @return Element with new name
     * @throws IOException
     */
    public Element setName(String name) throws IOException {
        model.setName(name);
        return new Element(api, api.element.updateElement(model).execute().body().get());
    }

    /**
     * Get the Element's 'timeseries', a list of DataPoints.
     * @param Name to be set
     * @return Element with new name
     * @throws IOException
     */
    public List<DataPoint> timeseries() throws IOException {
        JSONAPIDocument<List<DataPoint>> dataPoints =
                api.element.elementTimeseries(model.id()).execute().body();
        return dataPoints.get();
    }

    /**
     * Create a new DataPoint associated with the Element's 'timeseries'
     * @param dataPoint
     * @return
     * @throws IOException
     */
    public DataPoint createDataPoint(DataPoint dataPoint) throws IOException {
        JSONAPIDocument<DataPoint> newDataPoint =
                api.element.createElementDataPoint(model.id(), dataPoint).execute().body();
        return newDataPoint.get();
    }

    /**
     * Retrieve the Element's Metadata.
     * @return
     * @throws IOException
     */
    @Override
    public Metadata metadata() throws IOException {
        return api.element.elementMetadata(id()).execute().body();
    }

    /**
     * Update the Element's Metadata. This will merge the current Metadata attributes and attributes in the new Metadata.
     * @param metadata
     * @return
     * @throws IOException
     */
    @Override
    public Element updateMetadata(Metadata metadata) throws IOException {
        api.element.updateElementMetadata(id(), metadata).execute().body();
        return this;
    }

    /**
     * Replace the element's current Metadata with the provided one. This will overwrite the current Metadata attributes.
     * This can be used to clear the Metadata attributes.
     * @param metadata
     * @return
     * @throws IOException
     */
    @Override
    public Element replaceMetadata(Metadata metadata) throws IOException {
        api.element.replaceElementMetadata(id(), metadata).execute().body();
        return this;
    }

    /**
     * Give the list of Sensors currently connected to the Element.
     * @return
     * @throws IOException
     */
    @Override
    public List<Sensor> sensors() throws IOException {
        List<com.helium.model.Sensor> sensorModels =
                api.element.elementSensors(id()).execute().body().get();
        List<Sensor> sensors = new ArrayList<>();
        for (com.helium.model.Sensor model : sensorModels) {
            sensors.add(new Sensor(api, model));
        }
        return sensors;
    }

    /**
     * Give the list of Labels currently applied to the Element.
     * @return
     * @throws IOException
     */
    @Override
    public List<Label> labels() throws IOException {
        List<com.helium.model.Label> labelModels =
                api.element.elementLabels(id()).execute().body().get();
        List<Label> labels = new ArrayList<>();
        for (com.helium.model.Label model : labelModels) {
            labels.add(new Label(api, model));
        }
        return labels;
    }

    /**
     * Get the ID of the Element.
     * @return The ID of the element, a UUID
     */
    public String id() {
        return model.id();
    }

    com.helium.model.Element model() {
        return model;
    }

    @Override
    public String toString() {
        return model.toString();
    }
}
