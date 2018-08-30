package com.wp.myboot.mapper;

import com.boot.commons.entity.CommonMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mapper
public interface Mp4Mapper extends CommonMapper<Log,Integer> {


    List<Map<String,String>> getMp4_category();

    List<Map<String,String>> findMp4_data(@Param("type")String type,@Param("name")String name,@Param("pageStart")Integer pageStart,@Param("pageSize")Integer pageSize);

    List<Map<String,String>> findMp4Novel();

    Map<String,String> getAppVersionAndUrl();

    Integer getTotleCount(@Param("type")String type,@Param("name")String name);

    List<Map<String,String>> getImgLink();

    Integer saveFileNameMul(@Param("randStr")String randStr, @Param("fileType")String fileType,@Param("fileContentType")String fileContentType,@Param("fileName")String fileName);

    Integer saveFileNameMulNew(@Param("fileContentType")String fileContentType, @Param("video")String video, @Param("pic")String pic, @Param("link")String link);

    Integer saveFileNameMulNewImg(@Param("fileName")String fileName, @Param("imgLink")String imgLink);

    List<Map<String,String>> findMp4DataByName(@Param("name")String name, @Param("pageStart")Integer pageStart, @Param("pageSize")Integer pageSize);

    Integer getTotleByNameCount(@Param("name")String name);

    List<Map<String,String>> getCateType();

    Integer saveFeedback(@Param("phoneNumber")String phoneNumber, @Param("reason")String reason, @Param("createTime")String createTime);

    Integer saveOrder(@Param("phoneNumber")String phoneNumber,@Param("out_trade_no")String out_trade_no, @Param("total_fee")String total_fee, @Param("currDate")String currDate);

    Map<String,String> findMp4OrderInfo(@Param("tradeOrder")String tradeOrder);

    Integer updateMap4Order(@Param("trade_order")String trade_order);

    Integer updateUserTime(@Param("phoneNumber")String phoneNumber,@Param("time")int time);

    Map<String,String> checkoutUser(@Param("phoneNumber")String phoneNumber);

    Map<String, String> getUserExpiredTime(@Param("phoneNumber") String phoneNumber);
}
