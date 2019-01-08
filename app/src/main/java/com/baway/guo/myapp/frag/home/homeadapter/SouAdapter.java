package com.baway.guo.myapp.frag.home.homeadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.entity.HomeSouEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class SouAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<HomeSouEntity.ResultBean> mList;

    public SouAdapter(Context context, List<HomeSouEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<HomeSouEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_sou_item, viewGroup, false);
        SouViewHolder souViewHolder = new SouViewHolder(view);
        return souViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof SouViewHolder) {
            Glide.with(mContext).load(mList.get(i).getMasterPic()).into(((SouViewHolder) viewHolder).mReimg);
            ((SouViewHolder) viewHolder).mReprice.setText("$ " + mList.get(i).getPrice());
            ((SouViewHolder) viewHolder).mRename.setText(mList.get(i).getCommodityName());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class SouViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mReimg;
        private final TextView mReprice;
        private final TextView mRename;

        public SouViewHolder(@NonNull View itemView) {
            super(itemView);
            mReimg = itemView.findViewById(R.id.souimg);
            mReprice = itemView.findViewById(R.id.souprice);
            mRename = itemView.findViewById(R.id.souname);
        }
    }
}
