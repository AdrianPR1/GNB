package com.gnb.ui.transactions.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.gnb.R;
import com.gnb.domain.UtilsTransactions;
import com.gnb.domain.model.Transaction;

import java.util.List;

public class TransactionsAdapter extends RecyclerView.Adapter<TransactionsAdapter.TransactionsViewHolder> {

    private List<Transaction> transactionsList;

    public TransactionsAdapter(List<Transaction> transactions) {
        this.transactionsList = transactions;
    }

    @Override
    public TransactionsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_transaction, parent, false);
        return new TransactionsViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(TransactionsViewHolder holder, int position) {
        Transaction transaction = transactionsList.get(position);
        holder.transactionSkuTextView.setText(transaction.getSku());
        holder.valueTextView.setText(UtilsTransactions.Companion.amountToString(transaction.getAmount()));
        holder.currencyTextView.setText(transaction.getCurrency());
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    public static class TransactionsViewHolder extends RecyclerView.ViewHolder {
        TextView transactionSkuTextView, valueTextView, currencyTextView;


        private TransactionsViewHolder(View itemView) {
            super(itemView);
            transactionSkuTextView = itemView.findViewById(R.id.transactionSkuTextView);
            valueTextView = itemView.findViewById(R.id.valueTextView);
            currencyTextView = itemView.findViewById(R.id.currencyTextView);

        }


    }
}

