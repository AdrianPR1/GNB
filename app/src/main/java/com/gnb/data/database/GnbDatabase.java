package com.gnb.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.gnb.data.database.dao.RatesDao;
import com.gnb.data.database.dao.TransactionsDao;
import com.gnb.data.database.entities.RateEntity;
import com.gnb.data.database.entities.TransactionEntity;

@Database(entities = {RateEntity.class, TransactionEntity.class}, version = 1)
public abstract class GnbDatabase extends RoomDatabase {
    public abstract RatesDao getRatesDao();

    public abstract TransactionsDao getTransactionsDao();

}
