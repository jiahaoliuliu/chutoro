package com.jiahaoliuliu.chutoro.ui.transactionslist;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.jiahaoliuliu.chutoro.databinding.LayoutTransactionBinding;
import com.jiahaoliuliu.chutoro.storagelayer.ui.ITransactionShown;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionHolder>{

    private List<? extends ITransactionShown> transactionsList;

    public TransactionsListAdapter() {
        this.transactionsList = new ArrayList<>();
    }

    public void setTransactionsList(List<? extends ITransactionShown> transactionsList) {
        this.transactionsList = transactionsList;
        notifyDataSetChanged();
    }

    @Override
    public TransactionHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        LayoutTransactionBinding layoutTransactionBinding =
                LayoutTransactionBinding.inflate(layoutInflater, parent, false);
        return new TransactionHolder(layoutTransactionBinding);
    }

    @Override
    public void onBindViewHolder(TransactionHolder holder, int position) {
        ITransactionShown transactionShown = transactionsList.get(position);
        holder.bind(transactionShown);
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    class TransactionHolder extends RecyclerView.ViewHolder {
        private static final String DATE_FORMAT = "dd/MM/yyyy HH:mm:ss";

        private LayoutTransactionBinding layoutTransactionBinding;
        private SimpleDateFormat simpleDateFormatter;

        public TransactionHolder(LayoutTransactionBinding layoutTransactionBinding) {
            super(layoutTransactionBinding.getRoot());
            this.layoutTransactionBinding = layoutTransactionBinding;
            simpleDateFormatter = new SimpleDateFormat(DATE_FORMAT);
        }

        public void bind(ITransactionShown transactionShown) {
            layoutTransactionBinding.setTransaction(transactionShown);
            layoutTransactionBinding.executePendingBindings();
            layoutTransactionBinding.date.setText(parseDate(transactionShown.getDate()));
            layoutTransactionBinding.quantity.setText(parseQuantity(transactionShown.getQuantity()));
        }

        private String parseDate(long date) {
            return simpleDateFormatter.format(new Date(date));
        }

        private String parseQuantity(int quantity) {
            double quantityDouble = quantity/100d;
            return String.format("%.2f", quantityDouble);
        }
    }
}
