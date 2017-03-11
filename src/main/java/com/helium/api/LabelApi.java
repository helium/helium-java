package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.model.Element;
import com.helium.model.Label;
import com.helium.model.Metadata;
import com.helium.model.Sensor;
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

    @PATCH("label/{labelId}")
    Call<JSONAPIDocument<Label>> updateLabel(@Body Label element);

    @DELETE("label/{labelId}")
    Call<ResponseBody> deleteLabel(@Path("labelId")String labelId);
    
    @GET("label/{labelId}/metadata")
    Call<Metadata> labelMetadata(@Path("labelId")String labelId);

    @PATCH("label/{labelId}/metadata")
    Call<Metadata> updateLabelMetadata(@Path("labelId")String labelId, @Body Metadata metadata);

    @PUT("label/{labelId}/metadata")
    Call<Metadata> replaceLabelMetadata(@Path("labelId")String labelId, @Body Metadata metadata);

    @GET("label/{labelId}/sensor")
    Call<JSONAPIDocument<List<Sensor>>> labelSensors(@Path("labelId")String labelId);

    @POST("label/{labelId}/relationships/sensor")
    Call<JSONAPIDocument<List<Sensor>>> addSensors(@Path("labelId")String labelId, @Body List<Sensor> sensor);

    @PATCH("label/{labelId}/relationships/sensor")
    Call<JSONAPIDocument<List<Sensor>>> replaceSensors(@Path("labelId")String labelId, @Body List<Sensor> sensor);

    @DELETE("label/{labelId}/relationships/sensor")
    Call<JSONAPIDocument<List<Sensor>>> removeSensors(@Path("labelId")String labelId, @Body List<Sensor> sensor);

    @GET("label/{labelId}/element")
    Call<JSONAPIDocument<List<Element>>> labelElements(@Path("labelId")String labelId);
    
    @POST("label/{labelId}/relationships/element")
    Call<JSONAPIDocument<List<Element>>> addElements(@Path("labelId")String labelId, @Body List<Element> element);

    @PATCH("label/{labelId}/relationships/element")
    Call<JSONAPIDocument<List<Element>>> replaceElements(@Path("labelId")String labelId, @Body List<Element> element);

    @DELETE("label/{labelId}/relationships/element")
    Call<JSONAPIDocument<List<Element>>> removeElements(@Path("labelId")String labelId, @Body List<Element> element);

}
