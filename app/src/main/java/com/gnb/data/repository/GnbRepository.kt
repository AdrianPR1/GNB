package com.gnb.data.repository

import com.gnb.data.database.dao.RatesDao
import com.gnb.data.database.dao.TransactionsDao
import com.gnb.data.database.entities.toDatabase
import com.gnb.data.model.TransactionModel
import com.gnb.data.network.GnbService
import com.gnb.domain.model.Rate
import com.gnb.domain.model.Transaction
import com.gnb.domain.model.toDomain
import javax.inject.Inject

class GnbRepository @Inject constructor(
    private val service: GnbService,
    private val ratesDao: RatesDao,
    private val transactionsDao: TransactionsDao
) {

    suspend fun getRemoteRates(): List<Rate> {

        return service.getRates().map { it.toDomain() }
    }

    suspend fun getRemoteTransactions(): List<Transaction> {
        val response: List<TransactionModel> = service.getTransactions()
        return response.map { it.toDomain() }
    }

    suspend fun getLocalTransactionsFilter(sku: String): List<Transaction> {

        return transactionsDao.getFilterTransactions(sku).map { it.toDomain() }
    }

    suspend fun getLocalTransactions(): List<Transaction> {

        return transactionsDao.getAll().map { it.toDomain() }
    }


    suspend fun getLocalRates(): List<Rate> {

        return ratesDao.getAll().map { it.toDomain() }
    }


    suspend fun insertRates(rates: List<Rate>) {
        ratesDao.deleteAll()
        ratesDao.insertAll(rates.map { it.toDatabase() })
    }

    suspend fun insertTransactions(transactions: List<Transaction>) {
        transactionsDao.deleteAll()
        transactionsDao.insertAll(transactions.map { it.toDatabase() })
    }

    suspend fun getDifferentsProducts(): List<String> {
        return transactionsDao.getDifferentProducts()
    }
}