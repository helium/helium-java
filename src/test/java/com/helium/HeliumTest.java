package com.helium;

import com.helium.client.Sensor;
import com.helium.resource.DataPoint;
import com.helium.resource.Label;
import com.helium.resource.Organization;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Optional;

public class HeliumTest {

    private Helium helium;

    @Before
    public void setUp() throws Exception {
        helium = new Helium("https://api.helium.com/v1");
    }

    @Test
    public void organization() throws Exception {
        Organization org = helium.organization();
        System.out.println(org.toString());
    }

    @Test
    public void sensors() throws Exception {
        List<Sensor> sensors = helium.sensors();
        System.out.println(sensors.toString());
    }

    @Test
    public void createSensor() throws Exception {
        Sensor sensor = helium.createVirtualSensor("Test Sensor A");
        System.out.println(sensor.toString());
        
        Optional<Sensor> someSensor = helium.lookupSensor(sensor.id());
        System.out.println(someSensor.toString());
        
        sensor.delete();
    }

    @Test
    public void labels() throws Exception {
        List<Label> labels = helium.labels();
        System.out.println(labels.toString());
    }
    
    @Test
    public void createLabel() throws Exception {
        Label label = helium.createLabel("Test Label A");
        System.out.println(label.toString());

        Optional<Label> someLabel = helium.lookupLabel(label.id());
        System.out.println(label.toString());

        helium.deleteLabel(label.id());
    }

    @Test
    public void timeseries() throws Exception {
        Sensor sensor = helium.lookupSensor("6887943b-852b-4ab5-a71a-12f0ce31cf75").get();
        List<DataPoint> timeseries = sensor.timeseries();
        System.out.println(timeseries.toString());
    }


}