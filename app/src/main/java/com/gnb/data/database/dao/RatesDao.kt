package com.gnb.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.gnb.data.database.entities.RateEntity
import com.gnb.data.database.entities.TransactionEntity

@Dao
interface RatesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(rates:List<RateEntity>)

    @Query("SELECT * FROM rates")
    suspend fun getAll(): List<RateEntity>

    @Query("DELETE FROM rates")
    suspend fun deleteAll()

}