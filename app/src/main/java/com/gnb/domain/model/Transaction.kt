package com.gnb.domain.model

import com.gnb.data.database.entities.TransactionEntity
import com.gnb.data.model.TransactionModel


data class Transaction(var sku: String, var amount: Double, var currency: String)


    fun TransactionModel.toDomain() = Transaction(sku, amount, currency)
    fun TransactionEntity.toDomain() = Transaction(sku, amount, currency)


