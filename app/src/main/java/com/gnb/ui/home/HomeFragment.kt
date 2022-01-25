package com.gnb.ui.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.gnb.R
import com.gnb.core.Utils
import com.gnb.databinding.HomeFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()
    private var _binding: HomeFragmentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = HomeFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()

        viewModel.requestTransactions(Utils.isOnline(requireContext()))
        observeProductsTypes()
        loadingMode()


    }

    fun loadingMode() {
        binding.transactionsTypeSpinner.visibility = View.INVISIBLE
        binding.startTextView.text = getString(R.string.loading_products_description)
        binding.goToTransactionsButton.visibility = View.GONE
    }

    private fun observeProductsTypes() {
        viewModel.productsTypes.observe(viewLifecycleOwner, {
            val adapter: ArrayAdapter<String> = ArrayAdapter<String>(
                requireContext(),
                R.layout.support_simple_spinner_dropdown_item, it
            )
            binding.transactionsTypeSpinner.adapter = adapter
            binding.transactionsTypeSpinner.visibility = View.VISIBLE
            binding.startTextView.text = getString(com.gnb.R.string.description_start_text)
            binding.goToTransactionsButton.visibility = View.VISIBLE
            bindClick()
        })
    }

    private fun bindClick() {


        binding.goToTransactionsButton.setOnClickListener {
            val sku: String = binding.transactionsTypeSpinner.selectedItem.toString()

            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToTransactionsFragment(
                    sku
                )
            )
        }
    }


}