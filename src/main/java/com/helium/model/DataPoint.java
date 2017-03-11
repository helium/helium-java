package com.helium.model;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.jasminb.jsonapi.annotations.Id;
import com.github.jasminb.jsonapi.annotations.Type;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


@Type("data-point")
public class DataPoint {

    @Id
    private String id;

    private String port;
    private String timestamp;
    private JsonNode value;

    private static DateFormat dateFormatter() {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
        dateFormat.setTimeZone(tz);
        return dateFormat;
    }

    public static DataPoint jsonDataPoint(JsonNode val, String port, Date timestamp){
        DataPoint dp = new DataPoint();
        dp.setValue(val);
        dp.setPort(port);
        dp.setTimestamp(dateFormatter().format(timestamp));
        return dp;
    }

    public static DataPoint numericDataPoint(Double numVal, String port, Date timestamp) {
        return jsonDataPoint(JsonNodeFactory.instance.numberNode(numVal), port, timestamp);
    }

    public static DataPoint textDataPoint(String val, String port, Date timestamp) {
        return jsonDataPoint(JsonNodeFactory.instance.textNode(val), port, timestamp);
    }

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
