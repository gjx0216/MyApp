package com.baway.guo.myapp.frag.circle.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.circle.bean.CricleEntity;
import com.facebook.drawee.view.SimpleDraweeView;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.List;

public class CricleAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private List<CricleEntity.ResultBean> mList;
    private boolean flag = false;

    public CricleAdapter(Context context, List<CricleEntity.ResultBean> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<CricleEntity.ResultBean> list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.frag_cricle_item, viewGroup, false);
        CricleViewHolder cricleViewHolder = new CricleViewHolder(view);
        return cricleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, final int i) {
        if (viewHolder instanceof CricleViewHolder) {
            ((CricleViewHolder) viewHolder).image.setImageURI(mList.get(i).getHeadPic());
            ((CricleViewHolder) viewHolder).name.setText(mList.get(i).getNickName());
            long createTime = mList.get(i).getCreateTime();
            GregorianCalendar gc = new GregorianCalendar();
            gc.setTimeInMillis(createTime);
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            ((CricleViewHolder) viewHolder).data.setText(df.format(gc.getTime()));
            ((CricleViewHolder) viewHolder).cottent.setText(mList.get(i).getContent());
            ((CricleViewHolder) viewHolder).num.setText(mList.get(i).getGreatNum() + "");
            if (mList.get(i).getImage() != null) {
                ((CricleViewHolder) viewHolder).simImage.setImageURI(mList.get(i).getImage());
            } else {
                ((CricleViewHolder) viewHolder).simImage.setImageURI("http://www.baidu.com/link?url=lW0RAy_0obMqdoE1_kL-UWLpAMoIYYJ7j9yjXu35rO9Jec50zyYng7kF-XPl_-KgM0zFtVoEgVCzb2KXLRrJSlRYc7aVA-X_Zwx9_EODlC-5_Az2RrDwegFm2WIORFEBkthtYKzbzNP_nGlGqxZ2S32yCqTO7HuyplZ1YdAE3cCcU81vBmRRqWXbKP73dl8DIACgTKECKRizMP4LwzhQhZr902i0UAuZ8OFiWT_IPFsI6IZaXVV88ylZG9uoaOkwKwmlESzHOvbgmmSTT21nr96y5xHtT7ho6h8YnLHC9WFFnErYfsjH4i-rLF92tTuLO37tMpvykKf8z6pGnobyCiqQNmMKPmNKhLdhfDaSSaCHZbflfHSidtgMXMzzKtig4j6qDedKKuQys7ZbzkGe1odWPaRsdnBXLLFGanI-3749dXDvVBQXMwS18LDsmD4J_gqzgUe7BreWRrBpDvQpsSQpSY48nKyLPx-slBxV-IEnHWbWc-217eAOqDo3GBTMUMYrfbW_2GO460RJxxSOYzYerWY6ajpFA1vn6iPRFAlXoFos80WtTDJQbUIv4HhX89G8vs9zqPhXUNCB3l1L4LrQ3Y8BW16tGzrwe6jfmyiDRf7TSolhy_B1tfIz3iovqH18RuXdrC4YDum7liJF8-aoeA6LbBbnVTwTqJGInSa&timg=https%3A%2F%2Fss0.bdstatic.com%2F94oJfD_bAAcT8t7mm9GUKT-xh_%2Ftimg%3Fimage%26quality%3D100%26size%3Db4000_4000%26sec%3D1544438408%26di%3D239f259e7207f4aaff170271cb3aea87%26src%3Dhttp%3A%2F%2Fimgsrc.baidu.com%2Fimgad%2Fpic%2Fitem%2F34fae6cd7b899e51fab3e9c048a7d933c8950d21.jpg&click_t=1544438388675&s_info=1349_635&wd=&eqid=810ab01b00077c31000000025c0e4287");
            }
            //点赞
            ((CricleViewHolder) viewHolder).ima.setOnClickListener(new View.OnClickListener() {


                @Override
                public void onClick(View v) {
                    if (!flag) {
                        //点赞
                        ((CricleViewHolder) viewHolder).ima.setImageResource(R.mipmap.dianzan_true);
                        int greatNum = mList.get(i).getGreatNum();
                        greatNum = greatNum + 1;
                        mList.get(i).setGreatNum(greatNum);
                        //点赞
                        flag = true;
                        notifyDataSetChanged();
                        dianjiListener.dianji(i);
                    } else {
                        //取消
                        flag = false;
                        if (((CricleViewHolder) viewHolder).ima.isClickable()) {
                            ((CricleViewHolder) viewHolder).ima.setImageResource(R.mipmap.dianzan_false);
                            int greatNum = mList.get(i).getGreatNum();
                            if (greatNum > 0) {
                                greatNum--;
                            }
                            mList.get(i).setGreatNum(greatNum);
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
        return mList.size();
    }


    public class CricleViewHolder extends RecyclerView.ViewHolder {

        private final SimpleDraweeView image;
        private final TextView name;
        private final TextView data;
        private final TextView cottent;
        private final SimpleDraweeView simImage;
        private final ImageView ima;
        private final TextView num;

        public CricleViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            data = itemView.findViewById(R.id.data);
            cottent = itemView.findViewById(R.id.cottent);
            simImage = itemView.findViewById(R.id.simImage);
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
}
