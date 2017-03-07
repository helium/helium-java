package com.helium;

import com.helium.client.Label;
import com.helium.client.Sensor;
import com.helium.resource.DataPoint;
import com.helium.client.Organization;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.Optional;

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

}