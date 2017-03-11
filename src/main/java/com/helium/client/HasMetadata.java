package com.helium.client;

import com.helium.model.Metadata;

import java.io.IOException;

public interface HasMetadata<T> {

    public Metadata metadata() throws IOException;

    public T updateMetadata(Metadata metadata) throws IOException;

    public T replaceMetadata(Metadata metadata) throws IOException;
}
