package com.helium.client;

import java.io.IOException;
import java.util.List;

public interface HasLabels {

    /**
     * Give the list of associated Labels.
     * @return A list of Labels
     * @throws IOException
     */
    List<Label> labels() throws IOException;
}
