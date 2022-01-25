package com.gnb.domain

import android.util.Log
import com.gnb.domain.model.Rate
import com.gnb.domain.model.Transaction
import java.math.RoundingMode

class UtilsTransactions {

    companion object {

        private const val CURRENCY_EUR = "EUR"

        fun transactionsToEur(
            transactions: List<Transaction>,
            rates: List<Rate>
        ): List<Transaction> {
            val transactionsList = ArrayList<Transaction>()
            transactions.forEach {
                if (it.currency == CURRENCY_EUR) {
                    transactionsList.add(it)
                } else {
                    transactionsList.add(
                        Transaction(
                            it.sku,
                            convertToEUR(it, rates),
                            CURRENCY_EUR
                        )
                    )
                }
            }
            return transactionsList

        }

        private fun convertToEUR(transaction: Transaction, rates: List<Rate>): Double {
            var transactionAux =
                Transaction(transaction.sku, transaction.amount, transaction.currency)
            var done = false
            while (!done) {
                rates.forEach {
                    if (transactionAux.currency == it.from) {
                        transactionAux.currency = it.to
                        transactionAux.amount = it.rate * transaction.amount
                        if (transactionAux.currency == CURRENCY_EUR) {
                            done = true
                            return transactionAux.amount.toBigDecimal()
                                .setScale(2, RoundingMode.HALF_EVEN).toDouble()
                        }
                    }
                }
            }
            return -1.0
        }

        fun sumAllValues(transactions: List<Transaction>): Double {
            var sum = 0.0
            transactions.forEach {
                sum += it.amount
            }
            return sum
        }

        fun amountToString(value: Double): String {

            return value.toBigDecimal().setScale(2, RoundingMode.HALF_EVEN).toString().replace(".",",")
        }
    }


}