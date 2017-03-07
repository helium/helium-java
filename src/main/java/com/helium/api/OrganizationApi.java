package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.resource.Organization;
import retrofit2.Call;
import retrofit2.http.GET;

public interface OrganizationApi {

    @GET("organization")
    Call<JSONAPIDocument<Organization>> organization();
}
