package com.toolbox.models.auth

import com.google.gson.annotations.SerializedName

data class ResponseAuth(

    @SerializedName("sub") val sub: String?,
    @SerializedName("token") val token: String?,
    @SerializedName("type") val type: String?

)
