package com.helium;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.api.SensorApi;
import com.helium.resource.DataPoint;
import com.helium.resource.Label;
import com.helium.resource.Organization;
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

    @GET("organization")
    Call<JSONAPIDocument<Organization>> organization();
}

