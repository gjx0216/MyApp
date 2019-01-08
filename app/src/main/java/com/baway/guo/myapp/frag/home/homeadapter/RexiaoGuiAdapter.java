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
import com.baway.guo.myapp.frag.home.entity.RexiaoguiEntity;
import com.bumptech.glide.Glide;

import java.util.List;

public class RexiaoGuiAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<RexiaoguiEntity.ResultBean> mList;

    public RexiaoGuiAdapter(Context context, List<RexiaoguiEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<RexiaoguiEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_rexiaogui_item, viewGroup, false);
        ReViewHolder reViewHolder = new ReViewHolder(view);
        return reViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ReViewHolder) {
            Glide.with(mContext).load(mList.get(i).getMasterPic()).into(((ReViewHolder) viewHolder).mReimg);
            ((ReViewHolder) viewHolder).mReprice.setText("$ " + mList.get(i).getPrice());
            ((ReViewHolder) viewHolder).mRename.setText(mList.get(i).getCommodityName());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class ReViewHolder extends RecyclerView.ViewHolder {

        private final ImageView mReimg;
        private final TextView mReprice;
        private final TextView mRename;

        public ReViewHolder(@NonNull View itemView) {
            super(itemView);
            mReimg = itemView.findViewById(R.id.rexiaoguiimg);
            mReprice = itemView.findViewById(R.id.rexiaoguiprice);
            mRename = itemView.findViewById(R.id.rexiaoguiname);
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
