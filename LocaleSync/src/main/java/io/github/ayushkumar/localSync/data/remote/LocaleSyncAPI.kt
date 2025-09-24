package io.github.ayushkumar.localSync.data.remote

import retrofit2.http.GET
import retrofit2.http.Url

interface LocaleSyncAPI {
    @GET
    suspend fun getLocalization(@Url url:String) : Map<String,Map<String,String>>
}