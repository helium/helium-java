package com.helium.client;

import java.io.IOException;
import java.util.List;

public interface HasSensors {

    public List<Sensor> sensors() throws IOException;
}
