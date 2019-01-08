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
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.bumptech.glide.Glide;

public class PinzhiAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private HomeEntity.ResultBean.PzshBean list;

    public PinzhiAdapter(Context context, HomeEntity.ResultBean.PzshBean list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_item3_i, viewGroup, false);
        PinzhiViewHolder pinzhiViewHolder = new PinzhiViewHolder(view);
        return pinzhiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PinzhiViewHolder) {
            ((PinzhiViewHolder) viewHolder).name.setText(list.getCommodityList().get(i).getCommodityName());
            ((PinzhiViewHolder) viewHolder).price.setText("$ " + list.getCommodityList().get(i).getPrice());
            Glide.with(mContext).load(list.getCommodityList().get(i).getMasterPic()).into(((PinzhiViewHolder) viewHolder).image);
        }
    }

    @Override
    public int getItemCount() {
        return list.getCommodityList().size();
    }

    public class PinzhiViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;
        private final TextView price;

        public PinzhiViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }

}
