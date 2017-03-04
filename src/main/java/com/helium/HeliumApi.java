package com.helium;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.DataPoint;
import com.helium.resource.Label;
import com.helium.resource.Organization;
import com.helium.resource.Sensor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface HeliumApi {

    @GET("label")
    Call<JSONAPIDocument<List<Label>>> labels();

    @POST("label")
    Call<JSONAPIDocument<Label>> createLabel(@Body Label label);

    @GET("label/{labelId}")
    Call<JSONAPIDocument<Label>> label(@Path("labelId")String labelId);

    @DELETE("label/{labelId}")
    Call<ResponseBody> deleteLabel(@Path("labelId")String labelId);


    @GET("sensor")
    Call<JSONAPIDocument<List<Sensor>>> sensors();

    @GET("sensor/{sensorId}")
    Call<JSONAPIDocument<Sensor>> sensor(@Path("sensorId")String sensorId);

    @DELETE("sensor/{sensorId}")
    Call<ResponseBody> deleteSensor(@Path("sensorId")String sensorId);

    @POST("sensor")
    Call<JSONAPIDocument<Sensor>> createSensor(@Body Sensor sensor);

    @GET("organization")
    Call<JSONAPIDocument<Organization>> organization();

    @GET("sensor/{sensorId}/timeseries")
    Call<JSONAPIDocument<List<DataPoint>>> timeseries(@Path("sensorId")String sensorId);
}

