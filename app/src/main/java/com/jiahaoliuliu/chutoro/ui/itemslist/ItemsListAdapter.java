package com.jiahaoliuliu.chutoro.ui.itemslist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jiahaoliuliu.chutoro.databinding.LayoutItemBinding;
import com.jiahaoliuliu.chutoro.entity.Item;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ItemsListAdapter extends RecyclerView.Adapter<ItemsListAdapter.ItemHolder>{

    private List<? extends Item> itemsList;
    private Picasso picasso;

    public ItemsListAdapter() {
        this.itemsList = new ArrayList<>();
        this.picasso = Picasso.get();
    }

    public void setItemsList(List<? extends Item> itemsList) {
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
        Item item = itemsList.get(position);
        holder.bind(item);
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

        public void bind(Item item) {
            layoutItemBinding.setItem(item);
            layoutItemBinding.executePendingBindings();
            picasso.load(item.getImageUrl()).into(image);
        }
    }
}
