package com.wp.myboot.service;

import com.wp.myboot.controller.result.SpringResult;

import java.util.List;
import java.util.Map;

public interface Mp4Service {

    SpringResult getMp4_category();

    SpringResult findMp4_data(String type, String name, Integer pageStart, Integer pageSize);

    SpringResult findMp4Novel();

    /**
     * 获取app的版本和version
     * @return
     */
    SpringResult getAppVersionAndUrl();

    /**
     * 获取图片轮播连接
     * @return
     */
    SpringResult getImgLink();

    SpringResult saveFileNameMul(String randStr, String fileType, String fileContentType, String fileName);


    SpringResult saveFileNameMulNew(String fileContentType, String video, String s, String pic, String s1);

    SpringResult saveFileNameMulNewImg(String fileName, String imgLink);

    /**
     * 根据名字模糊搜索影片
     * @param name
     * @param pageStart
     * @param pageSize
     * @return
     */
    SpringResult findMp4DataByName(String name, Integer pageStart, Integer pageSize);

    /**
     * 类别加载
     * @return
     */
    List<Map<String,String>> getCateType();

    /**
     * 获取反馈的用户信息
     * @param phoneNumber
     * @return
     */
    Integer saveFeedback(String phoneNumber, String reason, String createTime);

    /**
     * 保存订单流水
     * @param out_trade_no
     * @param total_fee
     * @param currDate
     */
    Integer saveOrder(String phoneNumber, String out_trade_no, String total_fee, String currDate);

    /**
     * 发现订单
     * @param out_trade_no
     * @return
     */
    Map<String, String> findMp4OrderInfo(String out_trade_no, Integer status);

    /**
     * 更新订单时间
     * @param trade_order
     */
    Integer updateMap4Order(String trade_order);

    /**
     * 更新用户时间
     * @param phoneNumber
     */
    Integer updateUserTime(String phoneNumber, int time);

    /**
     * 检测是否存在该用户
     * @param phoneNumber
     * @return
     */
    Map<String,String> checkoutUser(String phoneNumber);

    /**
     * 检测用户到期时间
     *
     * @param phoneNumber
     * @return
     */
    Map<String, String> getUserExpiredTime(String phoneNumber);

    /**
     * 上传图片
     * @param fileContentType
     * @param fileName
     * @return
     */
    Integer saveuploadImg(String fileContentType, String fileName);

    /**
     * 获取图片
     * @return
     */
    SpringResult getPicture(Integer pageStart, Integer pageSize);
}
