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
import com.baway.guo.myapp.frag.home.entity.DetilEntity;
import com.bumptech.glide.Glide;
import com.facebook.drawee.view.SimpleDraweeView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

/**
 * 郭佳兴
 **/
public class ShopDetilAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private DetilEntity.ResultBean mList;

    public ShopDetilAdapter(Context context, DetilEntity.ResultBean list) {
        mContext = context;
        mList = list;
    }

    public void setList(DetilEntity.ResultBean list) {
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_detil01, viewGroup, false);
            MyViewHolder1 myViewHolder1 = new MyViewHolder1(view);
            return myViewHolder1;
        } else if (i == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_detil02, viewGroup, false);
            MyViewHolder2 myViewHolder2 = new MyViewHolder2(view);
            return myViewHolder2;
        } else if (i == 2) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_detil03, viewGroup, false);
            MyViewHolder3 myViewHolder3 = new MyViewHolder3(view);
            return myViewHolder3;
        } else if (i == 3) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_detil04, viewGroup, false);
            MyViewHolder4 myViewHolder4 = new MyViewHolder4(view);
            return myViewHolder4;
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        switch (itemViewType) {
            case 0:
                if (viewHolder instanceof MyViewHolder1) {
                    String picture = mList.getPicture();
                    String[] split = picture.split(",");
                    ArrayList<String> list = new ArrayList<>();
                    for (int j = 0; j < split.length; j++) {
                        list.add(split[j]);
                    }

                    ((MyViewHolder1) viewHolder).banner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load(path).into(imageView);
                        }
                    });
                    ((MyViewHolder1) viewHolder).banner.setIndicatorGravity(BannerConfig.RIGHT);
                    ((MyViewHolder1) viewHolder).banner.setDelayTime(2000);//设置轮播时间
                    ((MyViewHolder1) viewHolder).banner.setImages(list);
                    ((MyViewHolder1) viewHolder).banner.isAutoPlay(true);
                    ((MyViewHolder1) viewHolder).banner.start();
                }
                break;
            case 1:
                if (viewHolder instanceof MyViewHolder2) {
                    ((MyViewHolder2) viewHolder).price.setText(mList.getPrice() + "");
                    ((MyViewHolder2) viewHolder).num.setText(mList.getCommentNum() + "");
                    ((MyViewHolder2) viewHolder).content.setText(mList.getDescribe());
                    String picture = mList.getPicture();
                    String[] split = picture.split(",");
                    ((MyViewHolder2) viewHolder).image.setImageURI(split[0]);
                }
                //点击吐司 接口回调
                break;
            case 2:
                if (viewHolder instanceof MyViewHolder3) {
                    ((MyViewHolder3) viewHolder).content3.setText(mList.getDescribe());
                    String picture = mList.getPicture();
                    String[] split = picture.split(",");
                    ((MyViewHolder3) viewHolder).image3.setImageURI(split[1]);
                    ((MyViewHolder3) viewHolder).name3.setText(mList.getCommodityName());
                    ((MyViewHolder3) viewHolder).guige3.setText(mList.getWeight() + "");
                    ((MyViewHolder3) viewHolder).guo3.setText(mList.getCategoryName());
                    ((MyViewHolder3) viewHolder).waitao3.setText("有外套");
                    ((MyViewHolder3) viewHolder).people3.setText("所有人群");
                    ((MyViewHolder3) viewHolder).data3.setText("以实物为准");

                }
                break;
            case 3:
                if (viewHolder instanceof MyViewHolder4) {
                    ((MyViewHolder4) viewHolder).content4.setText(mList.getDescribe());
                    String picture = mList.getPicture();
                    String[] split = picture.split(",");
                    ((MyViewHolder4) viewHolder).image4.setImageURI(split[2]);
                    ((MyViewHolder4) viewHolder).content5.setText(mList.getCommodityName());
                    ((MyViewHolder4) viewHolder).image5.setImageURI(split[3]);
                    ((MyViewHolder4) viewHolder).name4.setText(mList.getCategoryName());
                    ((MyViewHolder4) viewHolder).data5.setText(mList.getSaleNum() + "");
                }
                break;
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }

    @Override
    public int getItemViewType(int position) {
        int type = 0;
        if (position % 4 == 1) {
            type = 1;
        } else if (position % 4 == 2) {
            type = 2;
        } else if (position % 4 == 3) {
            type = 3;
        }
        return type;
    }

    public class MyViewHolder1 extends RecyclerView.ViewHolder {

        private final Banner banner;

        public MyViewHolder1(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class MyViewHolder2 extends RecyclerView.ViewHolder {

        private final TextView price;
        private final TextView num;
        private final TextView content;
        private final SimpleDraweeView image;

        public MyViewHolder2(@NonNull View itemView) {
            super(itemView);
            price = itemView.findViewById(R.id.price);
            num = itemView.findViewById(R.id.price);
            content = itemView.findViewById(R.id.price);
            image = itemView.findViewById(R.id.image);

        }
    }

    public class MyViewHolder3 extends RecyclerView.ViewHolder {

        private final TextView content3;
        private final SimpleDraweeView image3;
        private final TextView name3;
        private final TextView guige3;
        private final TextView guo3;
        private final TextView waitao3;
        private final TextView people3;
        private final TextView data3;

        public MyViewHolder3(@NonNull View itemView) {
            super(itemView);
            content3 = itemView.findViewById(R.id.content3);
            image3 = itemView.findViewById(R.id.image3);
            name3 = itemView.findViewById(R.id.name3);
            guige3 = itemView.findViewById(R.id.guige3);
            guo3 = itemView.findViewById(R.id.guo3);
            waitao3 = itemView.findViewById(R.id.waitao3);
            people3 = itemView.findViewById(R.id.people3);
            data3 = itemView.findViewById(R.id.data3);


        }
    }

    public class MyViewHolder4 extends RecyclerView.ViewHolder {
        private final SimpleDraweeView image4;
        private final SimpleDraweeView image5;
        private final TextView content4;
        private final TextView data5;
        private final TextView content5;
        private final TextView name4;

        public MyViewHolder4(@NonNull View itemView) {
            super(itemView);
            content4 = itemView.findViewById(R.id.content4);
            image4 = itemView.findViewById(R.id.image4);
            name4 = itemView.findViewById(R.id.name4);
            data5 = itemView.findViewById(R.id.data4);
            content5 = itemView.findViewById(R.id.content5);
            image5 = itemView.findViewById(R.id.image5);

        }


    }
}
