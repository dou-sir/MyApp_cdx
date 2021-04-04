package com.jit.cdx.myapp_cdx.util;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


/**
 * Created by 14032 on 2019/12/2.
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //自定义构造方法 会创建一个my_db作为名字的数据库
    public DataBaseHelper(Context context){
        super(context,"myApp_db",null,1);
    }
    //一般会在onCreate 进行数据库初始化 程序第一次创建时，调用DataBaseHelper构造方法

    @Override
    public void onCreate(SQLiteDatabase db) {
        //1、用户表
        db.execSQL("create table if not exists tb_user(uid integer primary key autoincrement,"+
                "uname String,upwd String , usex integer,ubirth String, uphone String, uaddress String, ustate int )"); //, uimg String
        //2、商家表
        db.execSQL("create table if not exists tb_shop(sid integer primary key autoincrement,"+
                "sname String,sphone String, saddress String, simg int )");
        //3.订单表  oid为自增长
        db.execSQL("create table if not exists tb_order(oid integer primary key autoincrement,"+
                "uid integer,date String,sname String,ophone String,oaddress String,oitems String,cost int)");
        //4、商品表
        db.execSQL("create table if not exists tb_goods(gid integer primary key autoincrement,"+
                "gname String,sid int,price int,gimg int)");

        //用户
        db.execSQL("insert into tb_user values(0,'cxk','123','2019/06/06',1,'6655','鸡你太美',1)");
        //商家
        db.execSQL("insert into tb_shop values(0,'光头烧烤','7788','格致路99号',0)");
        db.execSQL("insert into tb_shop values(1,'新石器烤肉','17788','万达广场1号',1)");
        db.execSQL("insert into tb_shop values(2,'张亮麻辣烫','27788','万达广场2号',2)");
        db.execSQL("insert into tb_shop values(3,'肯德基','37788','万达广场3号',3)");
        db.execSQL("insert into tb_shop values(4,'酸汤肥牛','47788','万达广场4号',4)");
        db.execSQL("insert into tb_shop values(5,'爱马仕炒饭','57788','万达广场5号',5)");
        db.execSQL("insert into tb_shop values(6,'一点点','67788','万达广场6号',6)");
        //商品
        db.execSQL("insert into tb_goods values(0,'烧鸡',0,36,1)");
        db.execSQL("insert into tb_goods values(1,'鸡肉串',0,2,1)");
        db.execSQL("insert into tb_goods values(2,'羊肉串',0,4,1)");
        db.execSQL("insert into tb_goods values(3,'牛肉串',0,4,1)");
        db.execSQL("insert into tb_goods values(4,'土豆',0,1,1)");
        db.execSQL("insert into tb_goods values(5,'韭菜',0,1,1)");
        db.execSQL("insert into tb_goods values(6,'生蚝',0,10,1)");
        db.execSQL("insert into tb_goods values(7,'小黄鱼',0,8,1)");
        db.execSQL("insert into tb_goods values(8,'花菜',0,2,1)");
        db.execSQL("insert into tb_goods values(9,'金针菇',0,1,1)");
        db.execSQL("insert into tb_goods values(10,'香菇',0,1,1)");
        db.execSQL("insert into tb_goods values(11,'香肠',0,5,1)");
        db.execSQL("insert into tb_goods values(12,'炒面',0,10,1)");
        db.execSQL("insert into tb_goods values(13,'五花肉',0,3,1)");
        db.execSQL("insert into tb_goods values(14,'骨肉相连',0,3,1)");
        db.execSQL("insert into tb_goods values(15,'里脊肉',0,4,1)");
        db.execSQL("insert into tb_goods values(16,'掌中宝',0,5,1)");
        db.execSQL("insert into tb_goods values(17,'鱿鱼',0,10,1)");
        db.execSQL("insert into tb_goods values(18,'年糕',0,4,1)");
        db.execSQL("insert into tb_goods values(19,'面筋',0,3,1)");
        db.execSQL("insert into tb_goods values(20,'牛肚',0,6,1)");
        db.execSQL("insert into tb_goods values(21,'泡菜五花肉饭',1,20,1)");
        db.execSQL("insert into tb_goods values(22,'招牌肥牛饭',1,28,1)");
        db.execSQL("insert into tb_goods values(23,'番茄牛肉汤',1,16,1)");
        db.execSQL("insert into tb_goods values(24,'黑椒鸡肉饭',1,24,1)");
        db.execSQL("insert into tb_goods values(25,'香菇牛肉饭',1,28,1)");
        db.execSQL("insert into tb_goods values(26,'娃娃菜',2,2,1)");
        db.execSQL("insert into tb_goods values(27,'金针菇',2,2,1)");
        db.execSQL("insert into tb_goods values(28,'方便面',2,1,1)");
        db.execSQL("insert into tb_goods values(29,'撒尿牛丸',2,5,1)");
        db.execSQL("insert into tb_goods values(30,'培根',2,3,1)");
        db.execSQL("insert into tb_goods values(31,'吮指原味鸡',3,11,1)");
        db.execSQL("insert into tb_goods values(32,'上校鸡块',3,12,1)");
        db.execSQL("insert into tb_goods values(33,'老北京鸡肉卷',3,14,1)");
        db.execSQL("insert into tb_goods values(34,'红豆派',3,8,1)");
        db.execSQL("insert into tb_goods values(35,'可乐',3,6,1)");
        db.execSQL("insert into tb_goods values(36,'酸汤肥牛',4,12,1)");
        db.execSQL("insert into tb_goods values(37,'大盘鸡',4,22,1)");
        db.execSQL("insert into tb_goods values(38,'酱香排骨',4,14,1)");
        db.execSQL("insert into tb_goods values(39,'水煮肉',4,15,1)");
        db.execSQL("insert into tb_goods values(40,'水煮鱼',4,16,1)");
        db.execSQL("insert into tb_goods values(41,'老干妈炒饭',5,10,1)");
        db.execSQL("insert into tb_goods values(42,'鸡丁炒饭',5,12,1)");
        db.execSQL("insert into tb_goods values(43,'肉丝炒饭',5,13,1)");
        db.execSQL("insert into tb_goods values(44,'蛋炒饭',5,9,1)");
        db.execSQL("insert into tb_goods values(45,'牛肉炒饭',5,15,1)");
        db.execSQL("insert into tb_goods values(46,'黑糖奶绿',6,10,1)");
        db.execSQL("insert into tb_goods values(47,'波霸奶茶',6,12,1)");
        db.execSQL("insert into tb_goods values(48,'四季奶青',6,13,1)");
        db.execSQL("insert into tb_goods values(49,'红糖玛奇朵',6,14,1)");
        db.execSQL("insert into tb_goods values(50,'乌龙茶',6,15,1)");

        //水果类
        db.execSQL("insert into tb_goods values(51,'苹果',7,15,1)");
        db.execSQL("insert into tb_goods values(52,'橘子',7,15,1)");
        db.execSQL("insert into tb_goods values(53,'车厘子',7,30,1)");
        db.execSQL("insert into tb_goods values(54,'榴莲',7,50,1)");
        db.execSQL("insert into tb_goods values(55,'香蕉',7,15,1)");
        //蔬菜类
        db.execSQL("insert into tb_goods values(56,'青椒',8,2,1)");
        db.execSQL("insert into tb_goods values(57,'生菜',8,1,1)");
        db.execSQL("insert into tb_goods values(58,'生姜',8,1,1)");
        db.execSQL("insert into tb_goods values(59,'西红柿',8,2,1)");
        db.execSQL("insert into tb_goods values(60,'洋葱',8,3,1)");
        //肉类
        db.execSQL("insert into tb_goods values(61,'鸡蛋',9,5,1)");
        db.execSQL("insert into tb_goods values(62,'猪肉',9,18,1)");
        db.execSQL("insert into tb_goods values(63,'鸡肉',9,15,1)");
        db.execSQL("insert into tb_goods values(64,'牛肉',9,45,1)");
        db.execSQL("insert into tb_goods values(65,'羊肉',9,50,1)");
        //饮品类
        db.execSQL("insert into tb_goods values(66,'卫岗牛奶',10,5,1)");
        db.execSQL("insert into tb_goods values(67,'伊利金典',10,9,1)");
        db.execSQL("insert into tb_goods values(68,'养乐多',10,12,1)");
        db.execSQL("insert into tb_goods values(69,'光明牛奶',10,6,1)");
        db.execSQL("insert into tb_goods values(70,'蒙牛牛奶',10,7,1)");
        //酒水类
        db.execSQL("insert into tb_goods values(71,'雪花啤酒',11,5,1)");
        db.execSQL("insert into tb_goods values(72,'科罗娜',11,16,1)");
        db.execSQL("insert into tb_goods values(73,'比利时啤酒',11,12,1)");
        db.execSQL("insert into tb_goods values(74,'青岛啤酒',11,10,1)");
        db.execSQL("insert into tb_goods values(75,'二锅头',11,5,1)");
        //零食类
        db.execSQL("insert into tb_goods values(76,'乐事薯片',12,5,1)");
        db.execSQL("insert into tb_goods values(77,'黄桃干',12,8,1)");
        db.execSQL("insert into tb_goods values(78,'蔓越莓曲奇',12,12,1)");
        db.execSQL("insert into tb_goods values(79,'达利园面包',12,8,1)");
        db.execSQL("insert into tb_goods values(80,'鸡蛋沙琪玛',12,14,1)");
        //面包类
        db.execSQL("insert into tb_goods values(81,'天然酵母面包',13,6,1)");
        db.execSQL("insert into tb_goods values(82,'金冠麻仁',13,10,1)");
        db.execSQL("insert into tb_goods values(83,'法棍',13,8,1)");
        db.execSQL("insert into tb_goods values(84,'巧克力面包',13,10,1)");
        db.execSQL("insert into tb_goods values(85,'肉松面包',13,6,1)");
        //粮油类
        db.execSQL("insert into tb_goods values(86,'橄榄油',14,60,1)");
        db.execSQL("insert into tb_goods values(87,'花生油',14,50,1)");
        db.execSQL("insert into tb_goods values(88,'大豆油',14,40,1)");
        db.execSQL("insert into tb_goods values(89,'菜籽油',14,30,1)");
        db.execSQL("insert into tb_goods values(90,'挂面',14,10,1)");

        //订单
        db.execSQL("insert into tb_order values(0,0,'2019/06/06','光头烧烤','6655','cxkHOME','0,1',36)");
        db.execSQL("insert into tb_order values(1,0,'2019/06/07','光头烧烤','6655','cxkHOME','0,2',72)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
