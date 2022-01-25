package com.gnb.data.network

import com.gnb.data.model.RateModel
import com.gnb.data.model.TransactionModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GnbService @Inject constructor(private val api: GnbApiClient) {

    suspend fun getRates(): List<RateModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getRates()
            response.body() ?: emptyList()
        }
    }

    suspend fun getTransactions(): List<TransactionModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllTransactions()
            response.body() ?: emptyList()
        }
    }
}