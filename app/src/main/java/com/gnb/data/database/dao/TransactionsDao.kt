package com.gnb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gnb.data.database.entities.TransactionEntity

@Dao
interface TransactionsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(transactions: List<TransactionEntity>)

    @Query("SELECT * FROM transactions")
    suspend fun getAll(): List<TransactionEntity>

    @Query("SELECT distinct(sku) FROM transactions")
    suspend fun getDifferentProducts(): List<String>

    @Query("SELECT * FROM transactions WHERE sku = :sku")
    suspend fun getFilterTransactions(sku: String): List<TransactionEntity>


    @Query("DELETE FROM transactions")
    suspend fun deleteAll()

}