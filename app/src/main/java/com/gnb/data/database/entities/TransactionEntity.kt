package com.gnb.data.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.gnb.domain.model.Transaction

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var sku: String,
    var amount: Double,
    var currency: String
)


fun Transaction.toDatabase() = TransactionEntity(sku = sku, amount = amount, currency = currency)