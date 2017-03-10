package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.User;
import retrofit2.Call;
import retrofit2.http.*;

public interface UserApi {

    @GET("user")
    Call<JSONAPIDocument<User>> getUser();

    @PATCH("user")
    Call<JSONAPIDocument<User>> updateUser(@Body User user);
}
