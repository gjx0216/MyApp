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

public class MoliAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private HomeEntity.ResultBean.MlssBean list;

    public MoliAdapter(Context context, HomeEntity.ResultBean.MlssBean list) {
        mContext = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_item2_i, viewGroup, false);
        MoliViewHolder moliViewHolder = new MoliViewHolder(view);
        return moliViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MoliViewHolder) {
            ((MoliViewHolder) viewHolder).name.setText(list.getCommodityList().get(i).getCommodityName());
            ((MoliViewHolder) viewHolder).price.setText("$ " + list.getCommodityList().get(i).getPrice());
            Glide.with(mContext).load(list.getCommodityList().get(i).getMasterPic()).into(((MoliViewHolder) viewHolder).image);
        }
    }

    @Override
    public int getItemCount() {
        return list.getCommodityList().size();
    }

    public class MoliViewHolder extends RecyclerView.ViewHolder {
        private final ImageView image;
        private final TextView name;
        private final TextView price;

        public MoliViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            price = itemView.findViewById(R.id.price);
        }
    }
}
