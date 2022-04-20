package com.felix.belajarrestapi.service

import com.felix.belajarrestapi.LoginRequest
import com.felix.belajarrestapi.RegisterRequest
import com.felix.belajarrestapi.model.Auth.GetAuthResponse
import com.felix.belajarrestapi.model.Login.PostLoginResponse
import com.felix.belajarrestapi.model.Register.PostRegisterResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface ApiService {
    @POST("api/v1/auth/login")
    fun getLoginResponse(@Body request: LoginRequest): Call<PostLoginResponse>

    @POST("api/v1/auth/register")
    fun getRegisterPost(@Body request: RegisterRequest): Call<PostRegisterResponse>

    @GET("api/v1/auth/me")
    fun getAuthResponse(@Header("Authorization")token: String): Call<GetAuthResponse>
}