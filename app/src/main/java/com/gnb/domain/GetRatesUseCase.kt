package com.gnb.domain

import com.gnb.data.repository.GnbRepository
import com.gnb.domain.model.Rate
import javax.inject.Inject

class GetRatesUseCase @Inject constructor(private val repository: GnbRepository) {

    suspend operator fun invoke(): List<Rate> {
        val rates: List<Rate> = repository.getRemoteRates()
        return if (rates.isNotEmpty()) {
            repository.insertRates(rates)
            repository.getLocalRates()
        } else {
            repository.getLocalRates()
        }
    }

}