package com.helium;

import com.helium.resource.Organization;
import com.helium.resource.Sensor;
import org.junit.Before;
import org.junit.Test;

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
    public void sensor() throws Exception {
        Optional<Sensor> sensor = client.lookupSensor("16e50465-9b0e-4a84-8989-e7f4b706e417");
        System.out.println(sensor.toString());

    }

    @Test
    public void createSensor() throws Exception {
        Sensor sensor = client.createVirtualSensor("Test Sensor A");
        System.out.println(sensor.toString());
    }

}