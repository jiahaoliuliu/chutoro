package com.jiahaoliuliu.chutoro.ui.itemslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiahaoliuliu.chutoro.databinding.LayoutItemBinding;
import com.jiahaoliuliu.chutoro.entity.ITransaction;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ItemHolder>{

    private List<? extends ITransaction> itemsList;
    private Picasso picasso;

    public ItemsListAdapter() {
        this.itemsList = new ArrayList<>();
        this.picasso = Picasso.get();
    }

    public void setItemsList(List<? extends ITransaction> itemsList) {
        this.itemsList = itemsList;
        notifyDataSetChanged();
    }

    @Override
    public ItemHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());

        LayoutItemBinding layoutItemBinding =
                LayoutItemBinding.inflate(layoutInflater, parent, false);
        return new ItemHolder(layoutItemBinding);
    }

    @Override
    public void onBindViewHolder(ItemHolder holder, int position) {
        ITransaction ITransaction = itemsList.get(position);
        holder.bind(ITransaction);
    }

    @Override
    public int getItemCount() {
        return itemsList.size();
    }

    class ItemHolder extends RecyclerView.ViewHolder {

        private LayoutItemBinding layoutItemBinding;
        private ImageView image;

        public ItemHolder(LayoutItemBinding layoutItemBinding) {
            super(layoutItemBinding.getRoot());
            this.layoutItemBinding = layoutItemBinding;
            this.image = layoutItemBinding.image;
        }

        public void bind(ITransaction ITransaction) {
            layoutItemBinding.setItem(ITransaction);
            layoutItemBinding.executePendingBindings();
            picasso.load(ITransaction.getImageUrl()).into(image);
        }
    }
}
