package com.jit.cdx.myapp_cdx.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.jit.cdx.myapp_cdx.Bean.LeftBean;
import com.jit.cdx.myapp_cdx.Bean.RightBean;
import com.jit.cdx.myapp_cdx.R;

import java.util.List;
import java.util.Random;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by 14032 on 2019/12/11.
 */

public class RightAdapter extends BaseAdapter implements StickyListHeadersAdapter {
    private List<LeftBean> mLeft;
    private List<RightBean> mRight;
    private Context context;
    private LeftAdapter leftAdapter;
    private onButtonClick onButtonClick;

    public RightAdapter(List<LeftBean> mLeft, List<RightBean> mRight, Context context, LeftAdapter leftAdapter) {
        this.mLeft = mLeft;
        this.mRight = mRight;
        this.context = context;
        this.leftAdapter = leftAdapter;
    }
    //接口回调
    public interface onButtonClick{
        public void myButtonClick(int id);
    }
    public void setOnButtonClick(onButtonClick onButtonClick) {
        this.onButtonClick = onButtonClick;
    }

    //漂浮栏
    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        TextView tv = new TextView(context);
        tv.setTextColor(Color.RED);
        tv.setTextSize(18);
        tv.setText(mRight.get(position).type);
        tv.setBackgroundColor(Color.WHITE);
        return tv;
    }

    @Override
    public long getHeaderId(int position) {
        return mRight.get(position).typeId;
    }

    @Override
    public int getCount() {
        return mRight.size();
    }

    @Override
    public RightBean getItem(int position) {
        return mRight.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        if (convertView==null){
            convertView = View.inflate(context, R.layout.right_item,null);
            viewHolder = new ViewHolder();
            viewHolder.title1 = convertView.findViewById(R.id.tv_right_title1);
            viewHolder.title2 = convertView.findViewById(R.id.tv_right_title2);
            viewHolder.count = convertView.findViewById(R.id.tv_right_count);
            viewHolder.ib_add = convertView.findViewById(R.id.ib_add);
            viewHolder.ib_minus = convertView.findViewById(R.id.ib_minus);
            viewHolder.et_num = convertView.findViewById(R.id.et_num);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        final RightBean rightBean = mRight.get(position);
        viewHolder.title1.setText(rightBean.gname);
        viewHolder.title2.setText("  单价:¥"+rightBean.price);//todo price
        //使用Random获取随机数
        Random random = new Random();
        int i = random.nextInt(100);
        viewHolder.count.setText("月销量"+i+"份");
        viewHolder.et_num.setText(rightBean.gnum+"");
//        String n = viewHolder.et_num.getText().toString();
//        num = Integer.parseInt(n);
//        if (onButtonClick!=null){
//            viewHolder.ib_minus.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////                    onButtonClick.myButtonClick(position);
//                    notifyDataSetChanged();
//                }
//            });
//            viewHolder.ib_add.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    onButtonClick.myButtonClick(position);
//                }
//            });
//
//        }
        viewHolder.ib_add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    if(rightBean.gnum==0){
                        viewHolder.ib_minus.setAnimation(getShowAnimation());
//                    }
                    rightBean.gnum++;
                    viewHolder.et_num.setText(rightBean.gnum+"");
                    viewHolder.et_num.setVisibility(View.VISIBLE);
                    viewHolder.ib_minus.setVisibility(View.VISIBLE);

                    int typeId = mRight.get(position).typeId;
                    //遍历左侧条目
                    for (int i = 0; i < mLeft.size(); i++) {
                        //获取左侧条目的type,与右侧条目的typeId对比是否一致
                        if (typeId == mLeft.get(i).type) {
                            //说明找到了对应条目,跳出循环,设置当前被选中的条目

                            //设置当前被选中的左侧条目
                            leftAdapter.setCurrentSelect(i);
                            //刷新数据适配器
                            leftAdapter.notifyDataSetChanged();
                            break;
                        }
                    }
                    leftAdapter.notifyDataSetChanged();
                    onButtonClick.myButtonClick(position);
                }

            });
        viewHolder.ib_minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rightBean.gnum-=1;
                if (rightBean.gnum<=0){
                    rightBean.gnum = 0;
                }
                if(rightBean.gnum==0){
                    viewHolder.ib_minus.setAnimation(getHiddenAnimation());
                    viewHolder.ib_minus.setVisibility(View.INVISIBLE);
                    viewHolder.et_num.setVisibility(View.INVISIBLE);

                }
                viewHolder.et_num.setText(rightBean.gnum+"");

                int typeId = mRight.get(position).typeId;
                //遍历左侧条目
                for (int i = 0; i < mLeft.size(); i++) {
                    //获取左侧条目的type,与右侧条目的typeId对比是否一致
                    if (typeId == mLeft.get(i).type) {
                        //说明找到了对应条目,跳出循环,设置当前被选中的条目

                        //设置当前被选中的左侧条目
                        leftAdapter.setCurrentSelect(i);
                        //刷新数据适配器
                        leftAdapter.notifyDataSetChanged();
                        break;
                    }
                }
                leftAdapter.notifyDataSetChanged();
                onButtonClick.myButtonClick(position);
            }
        });
        return convertView;
    }

    static class ViewHolder{
        TextView title1;
        TextView title2;
        TextView count;
        ImageButton ib_add,ib_minus;
        EditText et_num;
    }
    //显示减号的动画
    private Animation getShowAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(0,1);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }

    //隐藏减号的动画
    private Animation getHiddenAnimation(){
        AnimationSet set = new AnimationSet(true);
        RotateAnimation rotate = new RotateAnimation(0,720,RotateAnimation.RELATIVE_TO_SELF,0.5f,RotateAnimation.RELATIVE_TO_SELF,0.5f);
        set.addAnimation(rotate);
        TranslateAnimation translate = new TranslateAnimation(
                TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,2f
                ,TranslateAnimation.RELATIVE_TO_SELF,0
                ,TranslateAnimation.RELATIVE_TO_SELF,0);
        set.addAnimation(translate);
        AlphaAnimation alpha = new AlphaAnimation(1,0);
        set.addAnimation(alpha);
        set.setDuration(500);
        return set;
    }
}
