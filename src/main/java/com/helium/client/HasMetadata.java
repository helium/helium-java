package com.helium.client;

import com.helium.model.Metadata;

import java.io.IOException;

public interface HasMetadata<T> {

    /**
     * Retrieve the associated Metadata.
     * @return A list of Labels
     * @throws IOException
     */
    Metadata metadata() throws IOException;

    /**
     * Update the associated Metadata. This will merge the current Metadata attributes and attributes in the new Metadata.
     * @return A list of Labels
     * @throws IOException
     */
    T updateMetadata(Metadata metadata) throws IOException;

    /**
     * Replace the associated Metadata. This will overwrite the current Metadata attributes.
     * This can be used to clear the Metadata attributes.
     * @param metadata
     * @return
     * @throws IOException
     */
    T replaceMetadata(Metadata metadata) throws IOException;
}
