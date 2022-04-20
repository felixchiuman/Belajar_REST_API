package com.felix.belajarrestapi

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class RegisterRequest (
    @SerializedName("email")
    val email : String? = null,
    @SerializedName("username")
    val username : String? = null,
    @SerializedName("password")
    val password : String? = null
):Parcelable