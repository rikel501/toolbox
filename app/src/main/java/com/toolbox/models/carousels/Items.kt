package com.toolbox.models.carousels

import com.google.gson.annotations.SerializedName

data class Items(

    @SerializedName("title") val title: String?,
    @SerializedName("imageUrl") val imageUrl: String?,
    @SerializedName("videoUrl") val videoUrl: String?,
    @SerializedName("description") val description: String?

)