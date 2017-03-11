package com.helium.client;

import com.helium.model.Metadata;

import java.io.IOException;

public interface HasMetadata<T> {

    Metadata metadata() throws IOException;

    T updateMetadata(Metadata metadata) throws IOException;

    T replaceMetadata(Metadata metadata) throws IOException;
}
