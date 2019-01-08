package com.baway.guo.myapp.frag.home.homeadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.entity.PopYiEntity;

import java.util.List;

public class PopYiAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<PopYiEntity.ResultBean> mList;

    public PopYiAdapter(Context context, List<PopYiEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<PopYiEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_pop_item1, viewGroup, false);
        PopYiViewHolder popYiViewHolder = new PopYiViewHolder(view);
        return popYiViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof PopYiViewHolder) {
            ((PopYiViewHolder) viewHolder).mName.setText(mList.get(i).getName());
        }
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class PopYiViewHolder extends RecyclerView.ViewHolder {

        private final TextView mName;

        public PopYiViewHolder(@NonNull View itemView) {
            super(itemView);
            mName = itemView.findViewById(R.id.popname);
            mName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    popListener.poplistener(mList.get(getAdapterPosition()).getId());
                }
            });
        }
    }
    private PopListener popListener;

    public void setPopListener(PopListener popListener) {
        this.popListener = popListener;
    }

    public interface PopListener {
        void poplistener(String popId);
    }
}
