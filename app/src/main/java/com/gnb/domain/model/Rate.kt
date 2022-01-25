package com.gnb.domain.model

import com.gnb.data.database.entities.RateEntity
import com.gnb.data.model.RateModel

class Rate(val from: String, val to: String, val rate: Double)

fun RateModel.toDomain() = Rate(from, to, rate)
fun RateEntity.toDomain() = Rate(from, to, rate)

