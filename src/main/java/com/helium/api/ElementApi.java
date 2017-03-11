package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.DataPoint;
import com.helium.resource.Element;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ElementApi {

    @GET("element")
    Call<JSONAPIDocument<List<Element>>> elements();

    @GET("element/{elementId}")
    Call<JSONAPIDocument<Element>> element(@Path("elementId")String elementId);

    @GET("element/{elementId}/timeseries")
    Call<JSONAPIDocument<List<DataPoint>>> elementTimeseries(@Path("elementId")String elementId);

    @POST("element/{elementId}/timeseries")
    Call<JSONAPIDocument<DataPoint>> createElementDataPoint(@Path("elementId")String elementId, @Body DataPoint dataPoint);

    @PATCH("element/{elementId}")
    Call<JSONAPIDocument<Element>> updateLabel(@Body Element element);
}
