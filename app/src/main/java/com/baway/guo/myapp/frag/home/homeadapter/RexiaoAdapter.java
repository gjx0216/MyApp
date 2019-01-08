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

public class RexiaoAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private HomeEntity.ResultBean.RxxpBean list;

    public RexiaoAdapter(Context context, HomeEntity.ResultBean.RxxpBean list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_item1_i, viewGroup, false);
        RexiaoViewHolder rexiaoViewHolder = new RexiaoViewHolder(view);
        return rexiaoViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof RexiaoViewHolder) {
           ((RexiaoViewHolder) viewHolder).name.setText(list.getCommodityList().get(i).getCommodityName());
            ((RexiaoViewHolder) viewHolder).price.setText("$ " + list.getCommodityList().get(i).getPrice());
            Glide.with(mContext).load(list.getCommodityList().get(i).getMasterPic()).into(((RexiaoViewHolder) viewHolder).image);
        }
    }

    @Override
    public int getItemCount() {
        return list.getCommodityList().size();
    }

    public class RexiaoViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;
        private final TextView price;

        public RexiaoViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
