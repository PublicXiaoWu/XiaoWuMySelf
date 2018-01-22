package com.xiaowu.myself.main.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.xiaowu.myself.R;

/**
 * Explanation：
 *
 * @author LSX
 *         Created on 2018/1/22.
 */
public class ShoppingOnlineAdapter extends RecyclerView.Adapter {

    private int headSize = 3;
    private int DataSize = 10;


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case 0:
                return new HeadOneHolder(View.inflate(parent.getContext(), R.layout.head_one_item, null));
            case 1:
                return new HeadTwoHolder(View.inflate(parent.getContext(), R.layout.head_two_item, null));
            case 2:
                return new HeadThreeHolder(View.inflate(parent.getContext(), R.layout.head_three_item, null));
            default:
                return new DefaultHolder(View.inflate(parent.getContext(), R.layout.shopping_online_default_item, null));
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (getItemViewType(position)) {
            case 0:
                bindHeadOneHolder(holder,position);
                break;
            case 1:
                break;
            case 2:
                break;
            default:
                bindDefaultHolder(holder, position);
                break;
        }

    }

    private void bindDefaultHolder(RecyclerView.ViewHolder viewHolder, int position) {
        DefaultHolder holder = (DefaultHolder) viewHolder;
        holder.tv_commodity_name.setText("双头眉笔防水防汗不脱色自然眉笔" + (position-headSize));
    }

    private void bindHeadOneHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemViewType(int position) {
        switch (position) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            default:
                return 100;
        }
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return DataSize + headSize;
    }

    private class HeadOneHolder extends RecyclerView.ViewHolder {
        public HeadOneHolder(View itemView) {
            super(itemView);
        }
    }

    private class HeadTwoHolder extends RecyclerView.ViewHolder {
        public HeadTwoHolder(View view) {
            super(view);
        }
    }

    private class HeadThreeHolder extends RecyclerView.ViewHolder {
        public HeadThreeHolder(View inflate) {
            super(inflate);
        }
    }

    private class DefaultHolder extends RecyclerView.ViewHolder {
        TextView tv_commodity_name;

        public DefaultHolder(View inflate) {
            super(inflate);
            findID(inflate);
        }

        private void findID(View view) {
            tv_commodity_name = view.findViewById(R.id.tv_commodity_name);
        }
    }
}
