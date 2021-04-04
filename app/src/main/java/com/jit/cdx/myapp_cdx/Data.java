package com.jit.cdx.myapp_cdx;

import com.jit.cdx.myapp_cdx.Bean.LeftBean;
import com.jit.cdx.myapp_cdx.Bean.RightBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 14032 on 2019/12/11.
 */

public class Data {

    private  String[] leftData = new String[]{"热销","特价","推荐"};//,"用心营养套餐(不含主食)","三杯鸡双拼尊享套餐","带鱼双拼尊享套餐","红烧肉双拼尊享套餐"};
    private  String[] rightData0 = new String[]{"洋芋粉炒腊肉","土鸡炖香菇","新疆大盘辣子土鸡","清炖土鸡块","农家蒸碗 ","香辣野猪肉","香辣薯条大虾","麻辣猪血"};
    private  String[] rightData1 = new String[]{"商芝扣肉","羊肉萝卜","干烧鱼 ","干煸野猪肉 ","排骨火锅","土鸡火锅","牛肉火锅","狗肉火锅 "};
    private  String[] rightData2 = new String[]{"虎皮辣子炒咸肉","重庆飘香水煮鱼","红烧土鸡块","干煸辣子土鸡","清炖全鸡 "};

    private String[] gname;
    private int[] price;
    private int[] gid;

    public Data() {
    }

    public Data(String[] gname,int[] price,int[] gid) {
        this.gname = gname;
        this.price = price;
        this.gid = gid;
        this.rightData0 = gname;
        this.rightData1 = gname;
        this.rightData2 = gname;
    }

    public String[] getGname() {
        return gname;
    }

    public void setGname(String[] gname) {
        this.gname = gname;
    }

    public int[] getPrice() {
        return price;
    }

    public void setPrice(int[] price) {
        this.price = price;
    }

    public int[] getGid() {
        return gid;
    }

    public void setGid(int[] gid) {
        this.gid = gid;
    }

    public String[] getRightData0() {
        return rightData0;
    }

    public void setRightData0(String[] rightData0) {
        this.rightData0 = rightData0;
    }

    public String[] getRightData1() {
        return rightData1;
    }

    public void setRightData1(String[] rightData1) {
        this.rightData1 = rightData1;
    }

    public String[] getRightData2() {
        return rightData2;
    }

    public void setRightData2(String[] rightData2) {
        this.rightData2 = rightData2;
    }

    public void setLeftData(String[] leftData) {
        this.leftData = leftData;
    }
    public  List<LeftBean> getLeftData(){
        List<LeftBean> list = new ArrayList<LeftBean>();
        for (int i = 0; i < leftData.length; i++) {
            LeftBean bean = new LeftBean();
            bean.title = leftData[i];
            bean.type = i;
            list.add(bean);
        }

        return list;

    }

    public  List<RightBean> getRightData(List<LeftBean> list){
        List<RightBean> rightList = new ArrayList<RightBean>();
        for (int i = 0; i < list.size(); i++) {
            LeftBean leftBean = list.get(i);
            int mType = leftBean.type;
            switch (mType) {
                case 0:
                    rightList = getRightList(rightData0, price, gid, leftBean, mType, rightList);
                    break;
                case 1:
                    rightList = getRightList(rightData1, price, gid, leftBean, mType, rightList);
                    break;
                case 2:
                    rightList = getRightList(rightData2, price, gid, leftBean, mType, rightList);
                    break;
            }
        }
        return rightList;

    }


    private  List<RightBean> getRightList(String[] rData, int[] price, int[] gid, LeftBean leftBean, int mType, List<RightBean> rightList){
        for (int j = 0; j < rData.length; j++) {
            RightBean bean = new RightBean();
            bean.gname = rData[j];
            bean.price = price[j];
            bean.gid = gid[j];
            bean.type = leftBean.title;
            bean.typeId = mType;
            rightList.add(bean);
        }
        return rightList;
    }

}
