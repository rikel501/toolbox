package com.toolbox.models.carousels

import com.google.gson.annotations.SerializedName

data class ResponseCarousels (

	@SerializedName("title") val title : String?,
	@SerializedName("type") val type : String?,
	@SerializedName("items") val items : MutableList<Items>?

)