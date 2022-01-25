package com.gnb.ui.transactions

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnb.domain.GetRatesUseCase
import com.gnb.domain.GetTransactionsFilterUseCase
import com.gnb.domain.UtilsTransactions
import com.gnb.domain.model.Rate
import com.gnb.domain.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TransactionsViewModel @Inject constructor(
    private val getRatesUseCase: GetRatesUseCase,
    private val getTransactionsFilterUseCase: GetTransactionsFilterUseCase
) : ViewModel() {

    val transactions = MutableLiveData<List<Transaction>>()
    lateinit var rates: List<Rate>
    lateinit var transactionList: List<Transaction>
    lateinit var transactionListEur: List<Transaction>

    fun requestTransactionsFiltered(sku: String) {
        viewModelScope.launch {

            transactionList = getTransactionsFilterUseCase.invoke(sku)
            if (!transactionList.isNullOrEmpty()) {
                requestRates()

            }
        }

    }

    fun requestRates() {
        viewModelScope.launch {

            val rates: List<Rate> = getRatesUseCase()
            if (!rates.isNullOrEmpty()) {
                transactionListEur = UtilsTransactions.transactionsToEur(transactionList, rates)
                transactions.postValue(transactionListEur)
            }
        }
    }

}