package com.toolbox.app.managers.network

import android.content.Context
import android.net.ConnectivityManager
import android.util.Log
import com.google.gson.Gson
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import com.toolbox.app.App
import com.toolbox.utils.Constants
import java.io.File
import java.io.IOException
import java.util.concurrent.TimeUnit

object NetworkManager {

    private const val HEADER_CACHE_CONTROL = "Cache-Control"
    private const val HEADER_PRAGMA = "Pragma"

    var serviceApi: ServiceApi

    init {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(responseInterceptor()!!)
            .addInterceptor(offlineCacheInterceptor()!!)
            .addNetworkInterceptor(cacheInterceptor()!!)
            .readTimeout(5000, TimeUnit.SECONDS)
            .connectTimeout(5000, TimeUnit.SECONDS)
            .cache(provideCache())
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(Constants.URL_BASE)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(httpClient.build())
            .build()
        serviceApi = retrofit.create(ServiceApi::class.java)
    }

    private fun responseInterceptor(): HttpLoggingInterceptor? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.setLevel(HttpLoggingInterceptor.Level.HEADERS)
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return interceptor
    }

    private fun offlineCacheInterceptor(): Interceptor? {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                var request = chain.request()
                if (!isConnected()) {
                    val cacheControl = CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                    request = request.newBuilder()
                        .removeHeader(HEADER_PRAGMA)
                        .removeHeader(HEADER_CACHE_CONTROL)
                        .cacheControl(cacheControl)
                        .build()
                }
                return chain.proceed(request)
            }
        }
    }

    private fun cacheInterceptor(): Interceptor? {
        return object : Interceptor {
            @Throws(IOException::class)
            override fun intercept(chain: Interceptor.Chain): Response {
                val response = chain.proceed(chain.request())
                val cacheControl: CacheControl = if (isConnected()) {
                    CacheControl.Builder()
                        .maxAge(0, TimeUnit.SECONDS)
                        .build()
                } else {
                    CacheControl.Builder()
                        .maxStale(7, TimeUnit.DAYS)
                        .build()
                }
                return response.newBuilder()
                    .removeHeader(HEADER_PRAGMA)
                    .removeHeader(HEADER_CACHE_CONTROL)
                    .header(HEADER_CACHE_CONTROL, cacheControl.toString())
                    .build()
            }
        }
    }

    private fun isConnected(): Boolean {
        try {
            val e = App.getAppContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetwork = e.activeNetworkInfo
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting
        } catch (e: Exception) {
            Log.e("isConnected", "error: ${e.message}")
        }
        return false
    }

    private fun provideCache(): Cache? {
        var cache: Cache? = null
        try {
            cache = Cache(
                File(App.getAppContext().cacheDir, "http-cache"),
                10 * 1024 * 1024
            )
        } catch (e: Exception) {
            Log.e("provideCache", "Could not create Cache: ${e.message}")
        }
        return cache
    }

}