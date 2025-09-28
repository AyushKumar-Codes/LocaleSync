package io.github.ayushkumar.localSync.data.remote

import javax.inject.Inject

class LocaleSyncRemoteDataSource @Inject constructor( val api: LocaleSyncAPI) {

    suspend fun downloadLocalisation(fullURL: String) : Map<String , Map<String,String>>{
        return api.getLocalization(fullURL)
    }

}