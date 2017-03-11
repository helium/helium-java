package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.model.DataPoint;
import com.helium.model.Element;
import com.helium.model.Metadata;
import com.helium.model.Sensor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface SensorApi {

    @GET("sensor")
    Call<JSONAPIDocument<List<Sensor>>> sensors();

    @GET("sensor/{sensorId}")
    Call<JSONAPIDocument<Sensor>> sensor(@Path("sensorId")String sensorId);

    @DELETE("sensor/{sensorId}")
    Call<ResponseBody> deleteSensor(@Path("sensorId")String sensorId);

    @POST("sensor")
    Call<JSONAPIDocument<Sensor>> createSensor(@Body Sensor sensor);

    @GET("sensor/{sensorId}/timeseries")
    Call<JSONAPIDocument<List<DataPoint>>> timeseries(@Path("sensorId")String sensorId);

    @POST("sensor/{sensorId}/timeseries")
    Call<JSONAPIDocument<DataPoint>> createDataPoint(@Path("sensorId")String sensorId, @Body DataPoint dataPoint);

    @GET("sensor/{sensorId}/metadata")
    Call<Metadata> sensorMetadata(@Path("sensorId")String sensorId);

    @PATCH("sensor/{sensorId}/metadata")
    Call<Metadata> updateSensorMetadata(@Path("sensorId")String sensorId, @Body Metadata metadata);

    @PUT("sensor/{sensorId}/metadata")
    Call<Metadata> replaceSensorMetadata(@Path("sensorId")String sensorId, @Body Metadata metadata);

    @GET("sensor/{sensorId}/element")
    Call<JSONAPIDocument<List<Element>>> sensorElements(@Path("sensorId")String sensorId);
}
