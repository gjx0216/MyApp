package com.baway.guo.myapp.frag.shop.addsubview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.baway.guo.myapp.R;
import com.baway.guo.myapp.util.ToastUtil;

/**
 * 郭佳兴
 **/
public class AddSubView extends LinearLayout {

    private View mView;
    private TextView mJia;
    private TextView mNum;
    private TextView mJian;

    public AddSubView(Context context) {
        this(context, null);
    }

    public AddSubView(Context context, AttributeSet attrs) {
        this(context, attrs, -1);
    }

    public AddSubView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        initLintener();
    }

    private void initLintener() {
        mJia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jia();
            }
        });
        mJian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                jian();
            }
        });
    }

    private void jia() {
        String i = mNum.getText().toString();
        int num = Integer.parseInt(i);
        num++;
        setCurNum(num);
    }

    private void setCurNum(int num) {
        mNum.setText("" + num);
        if (mOnNumChanagerListener != null) {
            mOnNumChanagerListener.getOnNumChange(num);
        }
    }

    private void jian() {
        String i = mNum.getText().toString();
        int num = Integer.parseInt(i);
        if (num > 1) {
            num--;
            setCurNum(num);
        } else {
            ToastUtil.ToastUtil("商品数量最低为1");
        }
    }


    private OnNumChanagerListener mOnNumChanagerListener;

    public void setOnNumChanagerListener(OnNumChanagerListener onNumChanagerListener) {
        mOnNumChanagerListener = onNumChanagerListener;
    }

    public interface OnNumChanagerListener {
        void getOnNumChange(int curNum);
    }


    private void initView(Context context) {

        mView = View.inflate(context, R.layout.frag_shop_subview_item, this);
        mJia = mView.findViewById(R.id.jia);
        mNum = mView.findViewById(R.id.num);
        mJian = mView.findViewById(R.id.jian);

    }
}
