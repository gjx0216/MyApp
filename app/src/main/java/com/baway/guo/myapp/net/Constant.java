package com.baway.guo.myapp.net;

public interface Constant {
    String PATH = "http://172.17.8.100/";
    //登录
    String LOGIN_PATH = "small/user/v1/login";
    //注册
    String REG_PATH = "small/user/v1/register";
    //banner图
    String ZHU_BANNER = "small/commodity/v1/bannerShow";
    //首页列表
    String ZHU_SHOPHTTP = "small/commodity/v1/commodityList";
    //热销商品归属
    String ReXiaoGui = "http://172.17.8.100/small/commodity/v1/findCommodityListByLabel?labelId=1002&page=1&count=8";
     //搜索商品
    String HomeSou = "http://172.17.8.100/small/commodity/v1/findCommodityByKeyword?keyword=板鞋&page=1&count=8";
    //魔力商品归属
    String MoLiGui = "http://172.17.8.100/small/commodity/v1/findCommodityListByLabel?labelId=1003&page=1&count=8";
    //品质商品归属
    String PinZhi = "http://172.17.8.100/small/commodity/v1/findCommodityListByLabel?labelId=1004&page=1&count=8";
    //商品详情页
    String ZHU_SHOPXIANG = "small/commodity/v1/findCommodityListByLabel";
    //圈子列表
    String CIRCLEPATH = "small/circle/v1/findCircleList";
    //首页三个点跳转
    String ZHU_SANSHOP = "small/commodity/v1/findCommodityDetailsById?commodityId=80";
    //我的足迹
    String ZHUJI = "small/commodity/verify/v1/browseList?page=1&count=5";
    String QUERY_NAME = "small/commodity/v1/findCommodityByKeyword";
    //我的圈子
    String MYCIRCLE = "small/circle/verify/v1/findMyCircleById";
    //收货地址列表
    String MYADDRESS = "small/user/verify/v1/receiveAddressList";
    //新增收货地址
    String MYADDRESS1 = "small/user/verify/v1/addReceiveAddress";
    //点赞
    String DIANZAN = "small/circle/verify/v1/addCircleGreat";
    //取消点赞
    String QUXIAO = "small/circle/verify/v1/cancelCircleGreat";
    //修改昵称
    String UPDATENICEHNG = "small/user/verify/v1/modifyUserNick";
    //修改密码
    String MIMA = "small/user/verify/v1/modifyUserPwd";
    //修改地址http://172.17.8.100/small/user/verify/v1/changeReceiveAddress
    String UPDATEDIZHI = "small/user/verify/v1/changeReceiveAddress";
    //删除我的圈子http://172.17.8.100/small/circle/verify/v1/deleteCircle
    String SHANCHUWOCIRCLE = "small/circle/verify/v1/deleteCircle";
    //同步购物车
    String TOBUSHOP = "small/order/verify/v1/syncShoppingCart";
    //查询购物车
    String QUERYSHOP = "small/order/verify/v1/findShoppingCart";
    //设置默认地址
    String MORENDIZHI = "http://172.17.8.100/small/user/verify/v1/setDefaultReceiveAddress";
    //查询一级类目
    String YIJI = "small/commodity/v1/findFirstCategory";
    //查询二级类目
    String ERJI = "small/commodity/v1/findSecondCategory";
    //订单
    String DINGDAN = "small/order/verify/v1/createOrder";
    //全部
    String QUANBU = "small/order/verify/v1/findOrderListByStatus";
    //上传头像
    String ONIMAGE = "small/user/verify/v1/modifyHeadPic";

}
