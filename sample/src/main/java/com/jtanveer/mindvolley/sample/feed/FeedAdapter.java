package com.jtanveer.mindvolley.sample.feed;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jtanveer.mindvolley.sample.BR;
import com.jtanveer.mindvolley.sample.R;
import com.jtanveer.mindvolley.sample.model.FeedResponse;

import java.util.List;

/**
 * Created by jtanveer on 20/4/18.
 */

public class FeedAdapter extends RecyclerView.Adapter<ItemViewHolder> {

    private List<FeedResponse> data;

    public FeedAdapter(List<FeedResponse> data) {
        this.data = data;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_feed, parent, false);
        return new ItemViewHolder(item);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        FeedResponse item = data.get(position);
        holder.getBinding().setVariable(BR.feedResponse, item);
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
