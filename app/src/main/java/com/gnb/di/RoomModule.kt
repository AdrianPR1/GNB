package com.gnb.di

import android.content.Context
import androidx.room.Room
import com.gnb.data.database.GnbDatabase
import com.gnb.data.database.dao.RatesDao
import com.gnb.data.database.dao.TransactionsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class RoomModule {
    companion object {
        private const val DATABASE_NAME = "gnb-database"
    }

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, GnbDatabase::class.java, DATABASE_NAME).build()

    @Singleton
    @Provides
    fun providerRatesDao(db: GnbDatabase): RatesDao = db.ratesDao

    @Singleton
    @Provides
    fun providerTransactionsDao(db: GnbDatabase): TransactionsDao = db.transactionsDao
}