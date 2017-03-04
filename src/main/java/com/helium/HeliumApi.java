package com.helium;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.Label;
import com.helium.resource.Organization;
import com.helium.resource.Sensor;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface HeliumApi {
    @GET("sensor")
    Call<JSONAPIDocument<List<Sensor>>> sensors();

    @GET("sensor/{sensorId}")
    Call<JSONAPIDocument<Sensor>> sensor(@Path("sensorId")String sensorId);

    @POST("sensor")
    Call<JSONAPIDocument<Sensor>> createSensor(@Body Sensor sensor);

    @GET("organization")
    Call<JSONAPIDocument<Organization>> organization();

    @GET("label")
    Call<JSONAPIDocument<List<Label>>> labels();
}
