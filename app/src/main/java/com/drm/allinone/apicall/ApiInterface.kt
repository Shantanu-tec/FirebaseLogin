package com.drm.allinone.apicall

import com.drm.allinone.models.ResponseData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiInterface {
    @GET(Api.GET_POST)
    fun getAllPost():Call<List<ResponseData>>

    @GET(Api.GET_POST)
    fun getPostByUserId(@Query("userId") userId:String):Call<List<ResponseData>>

    @GET(Api.GET_POST_BY_PATH)
    fun getPostByPath(@Path("userId") userId:String):Call<ResponseData>

    @GET(Api.GET_COMMENTS)
    fun getComments():Call<List<ResponseData>>
}