package com.wp.myboot.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.mapper.Mp4Mapper;
import com.wp.myboot.service.Mp4Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class Mp4ServiceImpl implements Mp4Service {

    private final Logger log = LoggerFactory.getLogger(com.wp.myboot.service.impl.Mp4ServiceImpl.class);

    @Autowired
    private Mp4Mapper mp4Mapper;

    @Override
    public SpringResult getMp4_category() {
        SpringResult springResult = new SpringResult();
        List<Map<String, String>> res=mp4Mapper.getMp4_category();
        if(res!=null && res.size()>0){
            springResult.setResult(res);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }

    @Override
    public SpringResult findMp4_data(String type, String name, Integer pageStart, Integer pageSize) {
        SpringResult springResult = new SpringResult();
        JSONObject object=new JSONObject();
        List<Map<String, String>> res=mp4Mapper.findMp4_data(type,name,pageStart,pageSize);
        object.put("list", res);
        /*总条数*/
        int totalCount=mp4Mapper.getTotleCount(type,name);
        object.put("totalCount", totalCount);
        if(object!=null){
            springResult.setResult(object);
            springResult.setResultCode("200");
        }else {
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }

    @Override
    public SpringResult findMp4Novel() {
        SpringResult springResult = new SpringResult();
        List<Map<String, String>> res=mp4Mapper.findMp4Novel();
        if(res!=null && res.size()>0){
            springResult.setResult(res);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }

        return springResult;
    }

    /**
     * 获取app的版本和version
     * @return
     */
    @Override
    public SpringResult getAppVersionAndUrl() {
        SpringResult springResult = new SpringResult();
        Map<String, String> map= mp4Mapper.getAppVersionAndUrl();
        if(map!=null && map.size()>0){
            springResult.setResult(map);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }

        return springResult;
    }

    /**
     * 获取图片轮播连接
     * @return
     */
    @Override
    public SpringResult getImgLink() {
        SpringResult springResult = new SpringResult();
        List<Map<String, String>> res=mp4Mapper.getImgLink();
        if(res!=null && res.size()>0){
            springResult.setResult(res);
            springResult.setResultCode("200");
        }else{
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }

    @Override
    public SpringResult saveFileNameMul(String randStr, String fileType, String fileContentType, String fileName) {
        SpringResult springResult = new SpringResult();
        try {
            Integer res=mp4Mapper.saveFileNameMul(randStr,fileType,fileContentType,fileName);
            springResult.setResult(res);
            springResult.setResultCode("200");
            springResult.setMessage("文件上传成功");
        }catch (Exception e){
            springResult.setResult(null);
            springResult.setResultCode("300");
            springResult.setMessage("文件上传失败");
            log.error("保存文件错误"+e.toString());
        }
        return springResult;
    }

    @Override
    public SpringResult saveFileNameMulNew(String fileContentType, String video, String link, String pic, String s1) {
        SpringResult springResult = new SpringResult();
        try {
            Integer res=mp4Mapper.saveFileNameMulNew(fileContentType,video,pic,link);
            springResult.setResult(res);
            springResult.setResultCode("200");
            springResult.setMessage("文件上传成功");
        }catch (Exception e){
            springResult.setResult(null);
            springResult.setResultCode("300");
            springResult.setMessage("文件上传失败");
            log.error("保存文件错误"+e.toString());
        }
        return springResult;
    }

    @Override
    public SpringResult saveFileNameMulNewImg(String fileName, String imgLink) {
        SpringResult springResult = new SpringResult();
        try {
            Integer res=mp4Mapper.saveFileNameMulNewImg(fileName,imgLink);
            springResult.setResult(res);
            springResult.setResultCode("200");
            springResult.setMessage("文件上传成功");
        }catch (Exception e){
            springResult.setResult(null);
            springResult.setResultCode("300");
            springResult.setMessage("文件上传失败");
            log.error("保存文件错误"+e.toString());
        }
        return springResult;
    }

    /**
     * 根据名字模糊搜索影片
     * @param name
     * @param pageStart
     * @param pageSize
     * @return
     */
    @Override
    public SpringResult findMp4DataByName(String name, Integer pageStart, Integer pageSize) {
        SpringResult springResult = new SpringResult();
        JSONObject object=new JSONObject();
        List<Map<String, String>> res=mp4Mapper.findMp4DataByName(name,pageStart,pageSize);
        object.put("list", res);
        /*总条数*/
        int totalCount=mp4Mapper.getTotleByNameCount(name);
        object.put("totalCount", totalCount);
        if(object!=null){
            springResult.setResult(object);
            springResult.setResultCode("200");
        }else {
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }

    @Override
    public List<Map<String, String>> getCateType() {
        List<Map<String, String>> res=mp4Mapper.getCateType();
        return res;
    }

    @Override
    public Integer saveFeedback(String phoneNumber,String reason,String createTime) {
        Integer res=mp4Mapper.saveFeedback(phoneNumber,reason,createTime);
        if(res>0){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public Integer saveOrder(String phoneNumber,String out_trade_no, String total_fee, String currDate) {
        Integer res=mp4Mapper.saveOrder(phoneNumber,out_trade_no,total_fee,currDate);
        if(res>0){
            return 1;
        }else {
            return -1;
        }
    }

    @Override
    public Map<String, String> findMp4OrderInfo(String out_trade_no, Integer status) {
        Map<String, String> order = mp4Mapper.findMp4OrderInfo(out_trade_no, status);
        return order;
    }

    /**
     * 更新订单
     * @param trade_order
     */
    @Override
    public Integer updateMap4Order(String trade_order) {
        return mp4Mapper.updateMap4Order(trade_order);
    }

    /**
     * 更新用户时间
     * @param phoneNumber
     */
    @Override
    public Integer updateUserTime(String phoneNumber,int time) {
        return mp4Mapper.updateUserTime(phoneNumber,time);
    }

    /**
     * 检测是否存在该用户
     * @param phoneNumber
     * @return
     */
    @Override
    public Map<String, String> checkoutUser(String phoneNumber) {
        Map<String, String> map=mp4Mapper.checkoutUser(phoneNumber);
        return map;
    }

    @Override
    public Map<String, String> getUserExpiredTime(String phoneNumber) {
        Map<String, String> map = mp4Mapper.getUserExpiredTime(phoneNumber);
        return map;
    }

    /**
     * 上传图片
     * @param fileContentType
     * @param fileName
     * @return
     */
    @Override
    public Integer saveuploadImg(String fileContentType, String fileName) {
        Integer res=mp4Mapper.saveuploadImg(fileContentType,fileName);
        if(res>0){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * 获取图片列表
     * @param pageStart
     * @param pageSize
     * @return
     */
    @Override
    public SpringResult getPicture(Integer pageStart, Integer pageSize) {
        SpringResult springResult = new SpringResult();
        JSONObject object=new JSONObject();
        List<Map<String, String>> list = mp4Mapper.getPicture(pageStart,pageSize);
        object.put("list", list);
        /*总条数*/
        Integer totalCount=mp4Mapper.getPictureCount();
        object.put("totalCount", totalCount);
        if(object!=null){
            springResult.setResult(object);
            springResult.setResultCode("200");
        }else {
            springResult.setResult(null);
            springResult.setResultCode("300");
        }
        return springResult;
    }
}
