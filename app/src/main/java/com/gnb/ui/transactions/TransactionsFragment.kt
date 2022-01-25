package com.gnb.ui.transactions

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.gnb.R
import com.gnb.databinding.TransactionsFragmentBinding
import com.gnb.domain.UtilsTransactions
import com.gnb.domain.UtilsTransactions.Companion.amountToString
import com.gnb.ui.transactions.adapter.TransactionsAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TransactionsFragment : Fragment() {

    private val viewModel: TransactionsViewModel by viewModels()
    private var _binding: TransactionsFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = TransactionsFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        observeTransactions()
        val args = arguments?.let { TransactionsFragmentArgs.fromBundle(it) }
        args?.sku?.let { requestProductsFiltered(it) }

    }


    fun requestProductsFiltered(sku: String) {
        viewModel.requestTransactionsFiltered(sku)
    }

    fun observeTransactions() {
        viewModel.transactions.observe(viewLifecycleOwner, {

            binding.transactionsDescriptionTextView.text =
                getString(R.string.total_label) + amountToString(UtilsTransactions.sumAllValues(it)) + getString(
                    R.string.euro_currency
                )

            binding.transactionsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            binding.transactionsRecyclerView.adapter = TransactionsAdapter(it)
        })
    }

}