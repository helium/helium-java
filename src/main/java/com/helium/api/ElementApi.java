package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.model.DataPoint;
import com.helium.model.Element;
import com.helium.model.Metadata;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ElementApi {

    @GET("element")
    Call<JSONAPIDocument<List<Element>>> elements();

    @GET("element/{elementId}")
    Call<JSONAPIDocument<Element>> element(@Path("elementId")String elementId);

    @PATCH("element/{elementId}")
    Call<JSONAPIDocument<Element>> updateElement(@Body Element element);

    @GET("element/{elementId}/timeseries")
    Call<JSONAPIDocument<List<DataPoint>>> elementTimeseries(@Path("elementId")String elementId);

    @POST("element/{elementId}/timeseries")
    Call<JSONAPIDocument<DataPoint>> createElementDataPoint(@Path("elementId")String elementId, @Body DataPoint dataPoint);

    @GET("element/{elementId}/metadata")
    Call<Metadata> elementMetadata(@Path("elementId")String elementId);

    @PATCH("element/{elementId}/metadata")
    Call<Metadata> updateElementMetadata(@Path("elementId")String elementId, @Body Metadata metadata);

    @PUT("element/{elementId}/metadata")
    Call<Metadata> replaceElementMetadata(@Path("elementId")String elementId, @Body Metadata metadata);
}
