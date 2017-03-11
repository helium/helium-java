package com.helium.client;

import java.io.IOException;
import java.util.List;

public interface HasElements {

    List<Element> elements() throws IOException;
}
