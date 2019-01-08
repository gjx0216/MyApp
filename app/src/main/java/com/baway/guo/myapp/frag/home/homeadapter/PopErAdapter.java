package com.baway.guo.myapp.frag.home.homeadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.entity.PopErEntity;

import java.util.List;

public class PopErAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<PopErEntity.ResultBean> mList;

    public PopErAdapter(Context context, List<PopErEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<PopErEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_pop_item1, viewGroup, false);
        PopErViewHolder popErViewHolder = new PopErViewHolder(view);
        return popErViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PopErViewHolder) {
            ((PopErViewHolder) viewHolder).mName.setText(mList.get(i).getName());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PopErViewHolder extends RecyclerView.ViewHolder {

        private final TextView mName;

        public PopErViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.popname);
        }
    }
}
