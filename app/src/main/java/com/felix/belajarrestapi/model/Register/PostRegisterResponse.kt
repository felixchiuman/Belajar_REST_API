package com.felix.belajarrestapi.model.Register


import com.google.gson.annotations.SerializedName

data class PostRegisterResponse(
    @SerializedName("data")
    val `data`: Data,
    @SerializedName("success")
    val success: Boolean
)