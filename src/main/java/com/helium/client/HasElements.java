package com.helium.client;

import java.io.IOException;
import java.util.List;

public interface HasElements {

    /**
     * Give the list of associated Elements.
     * @return A list of Elements
     * @throws IOException
     */
    List<Element> elements() throws IOException;
}
