package com.gnb.domain

import com.gnb.data.repository.GnbRepository
import com.gnb.domain.model.Transaction
import javax.inject.Inject

class GetTransactionsFilterUseCase @Inject constructor(private val repository: GnbRepository) {

    suspend operator fun invoke(sku: String): List<Transaction> {
        return repository.getLocalTransactionsFilter(sku)

    }

}