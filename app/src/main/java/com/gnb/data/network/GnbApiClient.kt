package com.gnb.data.network

import com.gnb.data.model.RateModel
import com.gnb.data.model.TransactionModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers

interface GnbApiClient {

    @Headers("Accept: application/json")
    @GET("rates")
    suspend fun getRates(): Response<List<RateModel>>

    @Headers("Accept: application/json")
    @GET("transactions")
    suspend fun getAllTransactions(): Response<List<TransactionModel>>


}