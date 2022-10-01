package com.engage.maitri.api;



import com.engage.maitri.SubjectResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface API {


//    @POST("auth/login")
//    Call<LoginResponse> userLogin(
//            @Body LoginRequest loginRequest
//            );

    @GET("subjects/{subject}.json")
    Call<SubjectResponse> getSubjectResponse(@Path("subject") String subjects);

}
