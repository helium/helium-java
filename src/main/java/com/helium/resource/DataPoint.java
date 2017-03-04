package com.helium.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ValueNode;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;


@Type("data-point")
public class DataPoint {


    @Id
    private String id;

    private String port;
    private String timestamp;
    private JsonNode value;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public JsonNode getValue() {
        return value;
    }

    public void setValue(JsonNode value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataPoint{" +
                "id='" + id + '\'' +
                ", port='" + port + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", value=" + value +
                '}';
    }
}
