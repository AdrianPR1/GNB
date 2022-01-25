package com.gnb.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gnb.domain.GetDifferentProductsUseCase
import com.gnb.domain.GetLocalTransactionsUseCase
import com.gnb.domain.GetTransactionsUseCase
import com.gnb.domain.model.Transaction
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getTransactionsUseCase: GetTransactionsUseCase,
    private val getLocalTransactionsUseCase: GetLocalTransactionsUseCase,
    private val getDifferentProductsUseCase: GetDifferentProductsUseCase
) : ViewModel() {

    val productsTypes = MutableLiveData<List<String>>()


    fun requestTransactions(isOnline: Boolean) {
        viewModelScope.launch {
            val transactions: List<Transaction> = if (isOnline) {
                getTransactionsUseCase()
            } else {
                getLocalTransactionsUseCase()
            }
            if (transactions.isNotEmpty()) {
                requestProductsTypes()
            }
        }
    }


    fun requestProductsTypes() {
        viewModelScope.launch {

            val result = getDifferentProductsUseCase()

            if (result.isNotEmpty()) {
                productsTypes.postValue(result)
            }
        }
    }

}