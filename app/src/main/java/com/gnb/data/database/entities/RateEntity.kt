package com.gnb.data.database.entities

import androidx.room.Entity
import com.gnb.domain.model.Rate


@Entity(primaryKeys = ["from", "to"], tableName = "rates")
data class RateEntity(
    var from: String,
    var to: String,
    var rate: Double
)

fun Rate.toDatabase() = RateEntity(from = from, to = to, rate = rate)