package com.gnb.domain

import com.gnb.data.repository.GnbRepository
import com.gnb.domain.model.Transaction
import javax.inject.Inject

class GetLocalTransactionsUseCase @Inject constructor(private val repository: GnbRepository) {

    suspend operator fun invoke(): List<Transaction> {
        val transactions: List<Transaction> = repository.getLocalTransactions()
        return if (transactions.isNotEmpty()) {
            transactions
        } else {
            emptyList()
        }
    }
}