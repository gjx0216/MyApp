package com.baway.guo.myapp.frag.mine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.mine.bean.MyCircleEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * 郭佳兴
 **/
public class MyCricleAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<MyCircleEntity.ResultBean> mBeans;
    private boolean flag = false;

    public MyCricleAdapter(Context context, List<MyCircleEntity.ResultBean> beans) {
        mContext = context;
        mBeans = beans;
    }

    public void setBeans(List<MyCircleEntity.ResultBean> beans) {
        mBeans = beans;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_mycricle, viewGroup, false);
        MyCricleViewHolder myViewHolder = new MyCricleViewHolder(view, shanchuListener);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof MyCricleViewHolder) {
            ((MyCricleViewHolder) viewHolder).name.setText(mBeans.get(i).getContent());
            ((MyCricleViewHolder) viewHolder).image.setImageURI(mBeans.get(i).getImage());
            long createTime = mBeans.get(i).getCreateTime();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(createTime);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ((MyCricleViewHolder) viewHolder).data.setText(df.format(gc.getTime()));
            ((MyCricleViewHolder) viewHolder).num.setText(mBeans.get(i).getGreatNum() + "");
            ((MyCricleViewHolder) viewHolder).rb.setChecked(false);
            //点赞
            ((MyCricleViewHolder) viewHolder).ima.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!flag) {
                        //点赞
                        ((MyCricleViewHolder) viewHolder).ima.setImageResource(R.mipmap.dianzan_true);
                        int greatNum = mBeans.get(i).getGreatNum();
                        greatNum = greatNum + 1;
                        mBeans.get(i).setGreatNum(greatNum);
                        //点赞
                        flag = true;
                        notifyDataSetChanged();
                        dianjiListener.dianji(i);
                    } else {
                        //取消
                        flag = false;
                        if (((MyCricleViewHolder) viewHolder).ima.isClickable()) {
                            ((MyCricleViewHolder) viewHolder).ima.setImageResource(R.mipmap.dianzan_false);
                            int greatNum = mBeans.get(i).getGreatNum();
                            if (greatNum > 0) {
                                greatNum--;
                            }
                            mBeans.get(i).setGreatNum(greatNum);
                            //点赞
                            flag = false;
                            notifyDataSetChanged();
                            quxiaojiListener.quxiao(i);
                        }

                    }
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return mBeans.size();
    }

    public class MyCricleViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final SimpleDraweeView image;
        private final TextView data;
        private final ImageView ima;
        private final TextView num;
        private final RadioButton rb;

        public MyCricleViewHolder(@NonNull View itemView, final ShanchuListener shanchuListener) {
            super(itemView);
            rb = itemView.findViewById(R.id.shanchu);
            name = itemView.findViewById(R.id.name);
            image = itemView.findViewById(R.id.image);
            data = itemView.findViewById(R.id.data);
            ima = itemView.findViewById(R.id.ima);
            num = itemView.findViewById(R.id.num);

        }
    }

    private DianjiListener dianjiListener;

    public void setDianjiListener(DianjiListener dianjiListener) {
        this.dianjiListener = dianjiListener;
    }

    public interface DianjiListener {
        void dianji(int po);
    }

    private QuxiaoListener quxiaojiListener;

    public void setQuxiaoListener(QuxiaoListener quxiaojiListener) {
        this.quxiaojiListener = quxiaojiListener;
    }

    public interface QuxiaoListener {
        void quxiao(int po);
    }

    private ShanchuListener shanchuListener;

    public void setShanchuListener(ShanchuListener shanchuListener) {
        this.shanchuListener = shanchuListener;
    }

    public interface ShanchuListener {
        void shanchu(int po);
    }
}
