package com.demo.git5.api

import com.demo.git5.BuildConfig
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object APIWorker {

    /**
     * Config URL, setup Gson converter to POJO, etc.
     * @param URL
     * @return Retrofit
     */
    fun retrofitClient(url: String = BuildConfig.BASE_URL): Retrofit {
        return Retrofit.Builder()
            .baseUrl(url)
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
    }

    /**
     * Config timeout, host, etc.
     * @return OkHttpClient
     */
    private fun okHttpClient() : OkHttpClient {
        val certificatePinner = CertificatePinner.Builder()
            .add(
                BuildConfig.HOST_NAME,
                BuildConfig.CERTIFICATE_PIN_1,
                BuildConfig.CERTIFICATE_PIN_2,
                BuildConfig.CERTIFICATE_PIN_3,
            )
            .build()

        return OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .addInterceptor(createLoggingInterceptor())
            .pingInterval(30, TimeUnit.SECONDS)
            .readTimeout(1, TimeUnit.MINUTES)
            .connectTimeout(1, TimeUnit.MINUTES)
            .certificatePinner(certificatePinner)
            .build()
    }

    /**
     * Create logger to check log in Http request/response
     */
    private fun createLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
}
