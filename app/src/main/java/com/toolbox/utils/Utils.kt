package com.toolbox.utils

import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.toolbox.R
import com.toolbox.app.App
import java.util.*

object Utils {

    fun getStringResource(string: Int): String =
        App.getAppContext().getString(string)

    fun showShortToast(message: String?) =
        Toast.makeText(App.getAppContext(), message, Toast.LENGTH_SHORT).show()

    fun loadImageUrl(url: String, view: ImageView) =
        Glide.with(App.getAppContext())
            .load(url)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.placeholder)
                    .error(R.drawable.error)
            )
            .into(view)

}