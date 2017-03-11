package com.helium.api;

import com.github.jasminb.jsonapi.JSONAPIDocument;
import com.helium.model.Metadata;
import com.helium.model.Organization;
import retrofit2.Call;
import retrofit2.http.*;

public interface OrganizationApi {

    @GET("organization")
    Call<JSONAPIDocument<Organization>> organization();

    @PATCH("organization")
    Call<JSONAPIDocument<Organization>> updateOrganization(@Body Organization organization);

    @GET("organization/metadata")
    Call<Metadata> organizationMetadata();

    @PATCH("organization/metadata")
    Call<Metadata> updateOrganizationMetadata(@Body Metadata metadata);

    @PUT("organization/metadata")
    Call<Metadata> replaceOrganizationMetadata(@Body Metadata metadata);
}
