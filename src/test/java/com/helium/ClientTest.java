package com.helium;

import com.helium.client.Sensor;
import com.helium.resource.DataPoint;
import com.helium.resource.Label;
import com.helium.resource.Organization;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

public class ClientTest {

    private Client client;

    @Before
    public void setUp() throws Exception {
        client = new Client("https://api.helium.com/v1");
    }

    @Test
    public void organization() throws Exception {
        Organization org = client.organization();
        System.out.println(org.toString());
    }

    @Test
    public void sensors() throws Exception {
        List<Sensor> sensors = client.sensors();
        System.out.println(sensors.toString());
    }

    @Test
    public void createSensor() throws Exception {
        Sensor sensor = client.createVirtualSensor("Test Sensor A");
        System.out.println(sensor.toString());
        
        Optional<Sensor> someSensor = client.lookupSensor(sensor.id());
        System.out.println(someSensor.toString());
        
        sensor.delete();
    }

    @Test
    public void labels() throws Exception {
        List<Label> labels = client.labels();
        System.out.println(labels.toString());
    }
    
    @Test
    public void createLabel() throws Exception {
        Label label = client.createLabel("Test Label A");
        System.out.println(label.toString());

        Optional<Label> someLabel = client.lookupLabel(label.id());
        System.out.println(label.toString());

        client.deleteLabel(label.id());
    }

    @Test
    public void timeseries() throws Exception {
        Sensor sensor = client.lookupSensor("6887943b-852b-4ab5-a71a-12f0ce31cf75").get();
        List<DataPoint> timeseries = sensor.timeseries();
        System.out.println(timeseries.toString());
    }


}