package com.felix.belajarrestapi.model.Auth


import com.google.gson.annotations.SerializedName

data class GetAuthResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
)