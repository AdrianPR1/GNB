package com.gnb.domain

import com.gnb.data.repository.GnbRepository
import com.gnb.domain.model.Transaction
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(private val repository: GnbRepository) {

    suspend operator fun invoke(): List<Transaction> {
        val transactions: List<Transaction> = repository.getRemoteTransactions()
        return if (transactions.isNotEmpty()) {
            repository.insertTransactions(transactions)
            repository.getLocalTransactions()

        } else {
            repository.getLocalTransactions()
        }
    }

}