package com.jtanveer.mindvolley.sample.feed;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by jtanveer on 20/4/18.
 */

public class ItemViewHolder extends RecyclerView.ViewHolder {

    private ViewDataBinding binding;

    public ItemViewHolder(View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public ViewDataBinding getBinding() {
        return binding;
    }
}
