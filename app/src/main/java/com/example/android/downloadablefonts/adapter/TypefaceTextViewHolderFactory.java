package com.example.android.downloadablefonts.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.android.downloadablefonts.data.TypefaceText;
import com.example.android.downloadablefonts.databinding.ItemTypefaceTextBinding;

import net.cattaka.android.adaptertoolbox.adapter.ScrambleAdapter;
import net.cattaka.android.adaptertoolbox.adapter.listener.ForwardingListener;

/**
 * Created by cattaka on 17/06/23.
 */

public class TypefaceTextViewHolderFactory extends ScrambleAdapter.AbsViewHolderFactory<TypefaceTextViewHolderFactory.ViewHolder> {
    @Override
    public boolean isAssignable(Object object) {
        return object instanceof TypefaceText;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ScrambleAdapter<?> adapter, @NonNull ViewGroup parent, @NonNull ForwardingListener<ScrambleAdapter<?>, RecyclerView.ViewHolder> forwardingListener) {
        ViewHolder holder = new ViewHolder(ItemTypefaceTextBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false));
        holder.itemView.setOnClickListener(forwardingListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ScrambleAdapter<?> adapter, @NonNull ViewHolder holder, int position, Object object) {
        holder.mBinding.setItem((TypefaceText) object);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ItemTypefaceTextBinding mBinding;

        public ViewHolder(ItemTypefaceTextBinding binding) {
            super(binding.getRoot());
            mBinding = binding;
        }
    }
}
