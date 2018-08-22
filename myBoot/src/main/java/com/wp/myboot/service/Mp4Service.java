package com.wp.myboot.service;

import com.wp.myboot.controller.result.SpringResult;

import java.util.List;
import java.util.Map;

public interface Mp4Service {

    SpringResult getMp4_category();

    SpringResult findMp4_data(String type,String name, Integer pageStart, Integer pageSize);

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

    SpringResult saveFileNameMul(String randStr,String fileType, String fileContentType, String fileName);


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
}
