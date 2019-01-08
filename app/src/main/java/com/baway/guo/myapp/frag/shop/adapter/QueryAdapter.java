package com.baway.guo.myapp.frag.shop.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.shop.addsubview.AddSubView;
import com.baway.guo.myapp.frag.shop.entity.QuerygoucarEntity;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * 郭佳兴
 **/
public class QueryAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<QuerygoucarEntity.ResultBean> mList;

    public QueryAdapter(Context context, List<QuerygoucarEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<QuerygoucarEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_shop_item, viewGroup, false);
        ShopCarViewHolder shopCarViewHolder = new ShopCarViewHolder(view);
        return shopCarViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof ShopCarViewHolder) {
            ((ShopCarViewHolder) viewHolder).mXuanbox.setChecked(mList.get(i).isCheck());
            ((ShopCarViewHolder) viewHolder).mTextTitle.setText(mList.get(i).getCommodityName());
            ((ShopCarViewHolder) viewHolder).mTextPrice.setText("$ " + mList.get(i).getPrice());
            Glide.with(mContext).load(mList.get(i).getPic()).into(((ShopCarViewHolder) viewHolder).mImageView);
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class ShopCarViewHolder extends RecyclerView.ViewHolder {
        private final ImageView mImageView;
        private final TextView mTextTitle;
        private final TextView mTextPrice;
        private final TextView mDel;
        private final CheckBox mXuanbox;
        private final AddSubView mAddSubView;

        public ShopCarViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.shopImage);
            mTextTitle = itemView.findViewById(R.id.shopName);
            mTextPrice = itemView.findViewById(R.id.shopPrice);
            mDel = itemView.findViewById(R.id.del);
            mXuanbox = itemView.findViewById(R.id.shopCk);
            mAddSubView = itemView.findViewById(R.id.addView);
            mAddSubView.setOnNumChanagerListener(new AddSubView.OnNumChanagerListener() {
                @Override
                public void getOnNumChange(int curNum) {
                    mList.get(getAdapterPosition()).setCount(curNum);
                    if (mOnNumChanagerListener != null) {
                        mOnNumChanagerListener.getOnNumChange(curNum);
                    }
                }
            });
            mXuanbox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mHttpBox.getBox(getAdapterPosition());
                }
            });
            mDel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mList.remove(getAdapterPosition());
                }
            });
        }
    }


    private AddSubView.OnNumChanagerListener mOnNumChanagerListener;

    public void setOnNumChanagerListener(AddSubView.OnNumChanagerListener onNumChanagerListener) {
        mOnNumChanagerListener = onNumChanagerListener;
    }

    private HttpBox mHttpBox;

    public void setHttpBox(HttpBox httpBox) {
        mHttpBox = httpBox;
    }

    public interface HttpBox {
        void getBox(int i);
    }
}
