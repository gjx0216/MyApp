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
import com.baway.guo.myapp.frag.home.entity.MoliguiEntity;
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class MoliGuiAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MoliguiEntity.ResultBean> mList;

    public MoliGuiAdapter(Context context, List<MoliguiEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<MoliguiEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_moligui_item, viewGroup, false);
        MoViewHolder moViewHolder = new MoViewHolder(view);
        return moViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof MoViewHolder) {
            Glide.with(mContext).load(mList.get(i).getMasterPic()).into(((MoViewHolder) viewHolder).mReimg);
            ((MoViewHolder) viewHolder).mRename.setText(mList.get(i).getCommodityName());
            ((MoViewHolder) viewHolder).mReprice.setText("$ " + mList.get(i).getPrice());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MoViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mReimg;
        private final TextView mReprice;
        private final TextView mRename;

        public MoViewHolder(@NonNull View itemView) {
            super(itemView);
            mReimg = itemView.findViewById(R.id.moliguiimg);
            mReprice = itemView.findViewById(R.id.moliguiprice);
            mRename = itemView.findViewById(R.id.moliguiname);
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
