package com.helium.client;

import java.io.IOException;
import java.util.List;

public interface HasSensors {

    /**
     * Give the list of associated Sensors.
     * @return A list of Elements
     * @throws IOException
     */
    List<Sensor> sensors() throws IOException;
}
