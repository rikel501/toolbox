package com.toolbox.models.auth

import com.google.gson.annotations.SerializedName

data class Auth(

    @SerializedName("sub") val sub: String

)
