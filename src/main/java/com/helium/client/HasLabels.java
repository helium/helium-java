package com.helium.client;

import java.io.IOException;
import java.util.List;

public interface HasLabels {

    List<Label> labels() throws IOException;
}
