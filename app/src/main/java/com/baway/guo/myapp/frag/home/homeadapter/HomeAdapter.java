package com.baway.guo.myapp.frag.home.homeadapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.frag.home.entity.BannerEntity;
import com.baway.guo.myapp.frag.home.entity.HomeEntity;
import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

public class HomeAdapter extends RecyclerView.Adapter {
    private Context mContext;
    private HomeEntity.ResultBean mlist;
    private List<BannerEntity.ResultBean> data;

    public HomeAdapter(Context context, HomeEntity.ResultBean mlist, List<BannerEntity.ResultBean> data) {
        mContext = context;
        this.mlist = mlist;
        this.data = data;
    }

    public void setData(List<BannerEntity.ResultBean> data) {
        this.data = data;
    }

    public void setMlist(HomeEntity.ResultBean mlist) {
        this.mlist = mlist;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == 0) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_banner, viewGroup, false);
            BannerViewHolder bannerViewHolder = new BannerViewHolder(view);
            return bannerViewHolder;
        } else if (i == 1) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_item1, viewGroup, false);
            RexiaoViewHolder rexiaoViewHolder = new RexiaoViewHolder(view);
            return rexiaoViewHolder;
        } else if (i == 2) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_item2, viewGroup, false);
            MoliViewHolder moliViewHolder = new MoliViewHolder(view);
            return moliViewHolder;
        } else if (i == 3) {
            View view = LayoutInflater.from(mContext).inflate(R.layout.frag_home_item3, viewGroup, false);
            PinzhiViewHolder pinzhiViewHolder = new PinzhiViewHolder(view);
            return pinzhiViewHolder;
        } else {
            return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        switch (itemViewType) {
            case 0:
                if (viewHolder instanceof BannerViewHolder) {
                    ArrayList<String> list = new ArrayList<>();
                    for (int j = 0; j < data.size(); j++) {
                        list.add(data.get(j).getImageUrl());
                    }
                    ((BannerViewHolder) viewHolder).mBanner.setImageLoader(new ImageLoader() {
                        @Override
                        public void displayImage(Context context, Object path, ImageView imageView) {
                            Glide.with(context).load((String) path).into(imageView);
                        }
                    });
                    ((BannerViewHolder) viewHolder).mBanner.setIndicatorGravity(BannerConfig.PADDING_SIZE);
                    ((BannerViewHolder) viewHolder).mBanner.setBannerAnimation(Transformer.Accordion);
                    ((BannerViewHolder) viewHolder).mBanner.setDelayTime(1000);
                    ((BannerViewHolder) viewHolder).mBanner.setImages(list);
                    ((BannerViewHolder) viewHolder).mBanner.isAutoPlay(true);
                    ((BannerViewHolder) viewHolder).mBanner.start();
                }
                break;
            case 1:
                if (viewHolder instanceof RexiaoViewHolder) {
                    ((RexiaoViewHolder) viewHolder).mRexiaoText.setText(mlist.getRxxp().get(0).getName());
                    //布局管理器
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 3);
                    ((RexiaoViewHolder) viewHolder).mRexiaorecycle.setLayoutManager(gridLayoutManager);
                    RexiaoAdapter rexiaoAdapter = new RexiaoAdapter(mContext, mlist.getRxxp().get(0));
                    ((RexiaoViewHolder) viewHolder).mRexiaorecycle.setAdapter(rexiaoAdapter);
                }
                break;
            case 2:
                if (viewHolder instanceof MoliViewHolder) {
                    ((MoliViewHolder) viewHolder).mMoliText.setText(mlist.getMlss().get(0).getName());
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext);
                    ((MoliViewHolder) viewHolder).mMolirecycle.setLayoutManager(linearLayoutManager);
                    MoliAdapter moliAdapter = new MoliAdapter(mContext, mlist.getMlss().get(0));
                    ((MoliViewHolder) viewHolder).mMolirecycle.setAdapter(moliAdapter);
                }
                break;
            case 3:
                if (viewHolder instanceof PinzhiViewHolder) {
                    ((PinzhiViewHolder) viewHolder).mPinzhiText.setText(mlist.getMlss().get(0).getName());
                    GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 2);
                    ((PinzhiViewHolder) viewHolder).mPinzhirecycle.setLayoutManager(gridLayoutManager);
                    PinzhiAdapter pinzhiAdapter = new PinzhiAdapter(mContext, mlist.getPzsh().get(0));
                    ((PinzhiViewHolder) viewHolder).mPinzhirecycle.setAdapter(pinzhiAdapter);
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

    public class BannerViewHolder extends RecyclerView.ViewHolder {

        private final Banner mBanner;

        public BannerViewHolder(@NonNull View itemView) {
            super(itemView);
            mBanner = itemView.findViewById(R.id.homebanner);
        }
    }

    public class RexiaoViewHolder extends RecyclerView.ViewHolder {

        private final TextView mRexiaoText;
        private final ImageView mRexiaoGui;
        private final RecyclerView mRexiaorecycle;

        public RexiaoViewHolder(@NonNull View itemView) {
            super(itemView);
            mRexiaoText = itemView.findViewById(R.id.RxiaoName);
            mRexiaorecycle = itemView.findViewById(R.id.RexiaoRecyclerView);
            mRexiaoGui = itemView.findViewById(R.id.Rexiaogui);
            mRexiaoGui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnRexiaoClick.RexiaoClick();
                }
            });
        }
    }

    public class MoliViewHolder extends RecyclerView.ViewHolder {
        private final TextView mMoliText;
        private final ImageView mMoligui;
        private final RecyclerView mMolirecycle;

        public MoliViewHolder(@NonNull View itemView) {
            super(itemView);
            mMoliText = itemView.findViewById(R.id.MoliText);
            mMolirecycle = itemView.findViewById(R.id.MoliRecyclerView);
            mMoligui = itemView.findViewById(R.id.MoliSan);
            mMoligui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnMoliClick.MOliClick();
                }
            });
        }
    }

    public class PinzhiViewHolder extends RecyclerView.ViewHolder {
        private final TextView mPinzhiText;
        private final ImageView mPinzhiaoGui;
        private final RecyclerView mPinzhirecycle;

        public PinzhiViewHolder(@NonNull View itemView) {
            super(itemView);
            mPinzhiText = itemView.findViewById(R.id.PinzhiText);
            mPinzhirecycle = itemView.findViewById(R.id.PinzhiRecyclerView);
            mPinzhiaoGui = itemView.findViewById(R.id.PinzhiSan);
            mPinzhiaoGui.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnPinzhiClick.PinzhiClick();
                }
            });
        }
    }

    //归属标签接口回调
    private onRexiaoClick mOnRexiaoClick;
    private onMoliClick mOnMoliClick;
    private onPinzhiClick mOnPinzhiClick;

    public interface onRexiaoClick {
        void RexiaoClick();
    }

    public interface onMoliClick {
        void MOliClick();
    }

    public interface onPinzhiClick {
        void PinzhiClick();
    }

    public void RexiaoClick(onRexiaoClick onCliklistener) {
        this.mOnRexiaoClick = onCliklistener;

    }

    public void MoliClick(onMoliClick onMoliClick) {

        this.mOnMoliClick = onMoliClick;

    }

    public void PinnzhiClick(onPinzhiClick onPinzhiClick) {
        this.mOnPinzhiClick = onPinzhiClick;
    }
}
