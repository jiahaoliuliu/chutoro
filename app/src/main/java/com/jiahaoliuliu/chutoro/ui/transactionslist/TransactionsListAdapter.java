package com.jiahaoliuliu.chutoro.ui.transactionslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiahaoliuliu.chutoro.databinding.LayoutTransactionBinding;
import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TransactionsListAdapter extends RecyclerView.Adapter<TransactionsListAdapter.TransactionHolder>{

    private List<? extends ITransaction> transactionsList;
    private Picasso picasso;

    public TransactionsListAdapter() {
        this.transactionsList = new ArrayList<>();
        this.picasso = Picasso.get();
    }

    public void setTransactionsList(List<? extends ITransaction> transactionsList) {
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
        ITransaction ITransaction = transactionsList.get(position);
        holder.bind(ITransaction);
    }

    @Override
    public int getItemCount() {
        return transactionsList.size();
    }

    class TransactionHolder extends RecyclerView.ViewHolder {

        private LayoutTransactionBinding layoutTransactionBinding;
        private ImageView image;

        public TransactionHolder(LayoutTransactionBinding layoutTransactionBinding) {
            super(layoutTransactionBinding.getRoot());
            this.layoutTransactionBinding = layoutTransactionBinding;
            this.image = layoutTransactionBinding.image;
        }

        public void bind(ITransaction ITransaction) {
            layoutTransactionBinding.setTransaction(ITransaction);
            layoutTransactionBinding.executePendingBindings();
            // TODO: Get the image url according to the source
//            picasso.load(ITransaction.getImageUrl()).into(image);
        }
    }
}
