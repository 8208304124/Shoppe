package com.example.e_commerce.ui.ApiService

import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

interface CommonApi {

    @GET
    fun get(@Url url: String): Call<ResponseBody>

    @POST
    fun post(@Url url: String, @Body body: RequestBody): Call<ResponseBody>

    @PUT
    fun put(@Url url: String, @Body body: RequestBody): Call<ResponseBody>

    @PATCH
    fun patch(@Url url: String, @Body body: RequestBody): Call<ResponseBody>

    @DELETE
    fun delete(@Url url: String): Call<ResponseBody>
}

class BaseApiService(private val baseUrl: String) {

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val api: CommonApi = retrofit.create(CommonApi::class.java)

    fun get(url: String): Call<ResponseBody> = api.get(url)

    fun post(url: String, body: RequestBody): Call<ResponseBody> = api.post(url, body)

    fun put(url: String, body: RequestBody): Call<ResponseBody> = api.put(url, body)

    fun patch(url: String, body: RequestBody): Call<ResponseBody> = api.patch(url, body)

    fun delete(url: String): Call<ResponseBody> = api.delete(url)

    fun <T> createService(serviceClass: Class<T>): T {
        return retrofit.create(serviceClass)
    }
}

