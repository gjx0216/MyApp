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
import com.baway.guo.myapp.frag.home.entity.PinzhiGuiEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class PinzhiGuiAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<PinzhiGuiEntity.ResultBean> mList;

    public PinzhiGuiAdapter(Context context, List<PinzhiGuiEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<PinzhiGuiEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_pinzhi_item, viewGroup, false);
        PinViewHolder pinViewHolder = new PinViewHolder(view);
        return pinViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PinViewHolder) {
            Glide.with(mContext).load(mList.get(i).getMasterPic()).into(((PinViewHolder) viewHolder).mReimg);
            ((PinViewHolder) viewHolder).mRename.setText(mList.get(i).getCommodityName());
            ((PinViewHolder) viewHolder).mReprice.setText("$ " + mList.get(i).getPrice());

        }

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PinViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mReimg;
        private final TextView mReprice;
        private final TextView mRename;

        public PinViewHolder(@NonNull View itemView) {
            super(itemView);
            mReimg = itemView.findViewById(R.id.pinzhiGuiimg);
            mReprice = itemView.findViewById(R.id.pinzhiGuiprice);
            mRename = itemView.findViewById(R.id.pinzhiGuiname);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClick.onItemClik(v, getAdapterPosition());
                }
            });
        }
    }

    private OnItemClick mOnItemClick;

    public interface OnItemClick {
        void onItemClik(View view, int i);
    }

    public void setOnItemClick(OnItemClick onItemClick) {
        this.mOnItemClick = onItemClick;
    }
}
