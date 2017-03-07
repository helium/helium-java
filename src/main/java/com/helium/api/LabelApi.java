package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.Label;
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
}
