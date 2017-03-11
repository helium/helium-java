package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.DataPoint;
import com.helium.resource.Metadata;
import com.helium.resource.Sensor;
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
}
