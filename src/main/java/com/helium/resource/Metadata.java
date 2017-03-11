package com.helium.resource;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Metadata {

    private Data data;

    public void setData(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public ObjectNode getAttibutes(){
        return data.attributes;
    }

    public void setAttibutes(ObjectNode attibutes){
        data.attributes = attibutes;
    }

    public Metadata addAttribute(String fieldName, String fieldValue) {
        data.attributes.put(fieldName, fieldValue);
        return this;
    }

    public Metadata addAttribute(String fieldName, int fieldValue) {
        data.attributes.put(fieldName, fieldValue);
        return this;
    }

    public Metadata addAttribute(String fieldName, double fieldValue) {
        data.attributes.put(fieldName, fieldValue);
        return this;
    }

    public Metadata addAttribute(String fieldName, JsonNode fieldValue) {
        data.attributes.replace(fieldName, fieldValue);
        return this;
    }

    @Override
    public String toString() {
        return "Metadata{" + data + '}';
    }

    public class Data {
        private ObjectNode attributes;
        private String id;
        private String type;

        public ObjectNode getAttributes() {
            return attributes;
        }

        public void setAttributes(ObjectNode attributes) {
            this.attributes = attributes;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        @Override
        public String toString() {
            return "attributes=" + attributes +
                    ", id='" + id + '\'' +
                    ", type='" + type + '\'';
        }
    }
}
