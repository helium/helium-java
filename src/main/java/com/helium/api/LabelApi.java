package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.Label;
import com.helium.resource.Sensor;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface LabelApi {

    @GET("label")
    Call<JSONAPIDocument<List<Label>>> labels();

    @POST("label")
    Call<JSONAPIDocument<Label>> createLabel(@Body Label label);

    @GET("label/{labelId}")
    Call<JSONAPIDocument<Label>> label(@Path("labelId")String labelId);

    @DELETE("label/{labelId}")
    Call<ResponseBody> deleteLabel(@Path("labelId")String labelId);

    @GET("label/{labelId}/sensor")
    Call<JSONAPIDocument<List<Sensor>>> labelRelationshipSensors(@Path("labelId")String labelId);

    @POST("label/{labelId}/relationships")
    Call<JSONAPIDocument<List<Sensor>>> addSensorLabels(@Path("labelId")String labelId, @Body List<Sensor> sensor);

    @PATCH("label/{labelId}/relationships")
    Call<JSONAPIDocument<List<Sensor>>> replaceLabelSensors(@Path("labelId")String labelId, @Body List<Sensor> sensor);

    @DELETE("label/{labelId}/relationships")
    Call<JSONAPIDocument<List<Sensor>>> removeLabelSensors(@Path("labelId")String labelId, @Body List<Sensor> sensor);
}
