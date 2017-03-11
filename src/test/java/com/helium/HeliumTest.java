package com.helium;

import com.helium.client.Element;
import com.helium.client.Label;
import com.helium.client.Organization;
import com.helium.client.Sensor;
import com.helium.client.User;
import com.helium.model.*;
import com.helium.model.Metadata;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertTrue;

public class HeliumTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void organization() throws Exception {
        Organization org = Helium.organization();
        System.out.println(org.toString());
    }

    @Test
    public void sensors() throws Exception {
        List<Sensor> sensors = Helium.sensors();
        System.out.println(sensors.toString());
    }

    @Test
    public void createSensor() throws Exception {
        Sensor sensor = Helium.createVirtualSensor("Test Sensor A");
        System.out.println(sensor.toString());

        Optional<Sensor> someSensor = Helium.lookupSensor(sensor.id());
        System.out.println(someSensor.toString());

        sensor.delete();
    }

    @Test
    public void labels() throws Exception {
        List<Label> labels = Helium.labels();
        System.out.println(labels.toString());
        for (Label label : labels) {
            for (Sensor sensor : label.sensors()) {
                System.out.println(sensor.toString());
            }
        }
    }

    @Test
    public void createLabel() throws Exception {
        Label label = Helium.createLabel("Test Label A");
        System.out.println(label.toString());

        Optional<Label> someLabel = Helium.lookupLabel(label.id());
        System.out.println(label.toString());

        label.delete();
    }

    @Test
    public void timeseries() throws Exception {
        Sensor sensor = Helium.lookupSensor("6887943b-852b-4ab5-a71a-12f0ce31cf75").get();
        List<DataPoint> timeseries = sensor.timeseries();
        System.out.println(timeseries.toString());
    }

    @Test
    public void createDatapoint() throws Exception {
        Sensor sensor = Helium.createVirtualSensor("DataPoint test");
        DataPoint newDp = DataPoint.numericDataPoint(42.0, "universe", new Date());
        DataPoint createdDp = sensor.createDataPoint(newDp);
        System.out.println(createdDp.toString());
        sensor.delete();
    }

    @Test
    public void user() throws Exception {
        User user = Helium.user();
        System.out.println(user.toString());
    }

    @Test
    public void elements() throws Exception {
        List<Element> elements = Helium.elements();
        System.out.println(elements.toString());
        for(Element element : elements) {
            assertTrue(Helium.lookupElement(element.id()).isPresent());
        }

    }

    @Test
    public void sensorMetadata() throws IOException {
        Sensor sensor = Helium.lookupSensor("6887943b-852b-4ab5-a71a-12f0ce31cf75").get();
        Metadata metadata = sensor.metadata();
        System.out.println(metadata.toString());
        sensor.updateMetadata(metadata.addAttribute("foo", "bar"));
        System.out.println(metadata.toString());
        sensor.replaceMetadata(metadata.clearAttributes());
        System.out.println(metadata.toString());
    }

    @Test
    public void elementMetadata() throws IOException {
        Element element = Helium.lookupElement("417da104-3414-4fa3-a919-c6c9908e3368").get();
        Metadata metadata = element.metadata();
        System.out.println(metadata.toString());
        element.updateMetadata(metadata.addAttribute("foo", "bar"));
        System.out.println(metadata.toString());
        element.replaceMetadata(metadata.clearAttributes());
        System.out.println(metadata.toString());
    }
    
    
}
