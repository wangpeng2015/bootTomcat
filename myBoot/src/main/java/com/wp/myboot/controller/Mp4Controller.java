package com.wp.myboot.controller;

import com.boot.commons.utils.DateUtil;
import com.boot.commons.utils.FtpUtils;
import com.boot.commons.utils.HttpClient;
import com.boot.commons.utils.RandomUtil;
import com.wp.myboot.controller.result.SpringResult;
import com.wp.myboot.service.Mp4Service;
import org.apache.commons.fileupload.disk.DiskFileItem;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.InputStream;
import java.util.*;

@Controller
@RequestMapping(value="/mp4Controller")
public class Mp4Controller {

	private final Logger log = LoggerFactory.getLogger(Mp4Controller.class);
	
	@Resource
	private Mp4Service mp4Service;
	
	@RequestMapping(value="/getMp4category")
	@ResponseBody
	public Object getMp4_category(){
		SpringResult springResult = new SpringResult();
		springResult = mp4Service.getMp4_category();
		return springResult;
	}

	@RequestMapping(value="/findMp4Data")
	@ResponseBody
	public Object findMp4_data(String type, String name,Integer pageStart, Integer pageSize){
		SpringResult springResult = new SpringResult();
		springResult = mp4Service.findMp4_data(type,name,pageStart,pageSize);
		return springResult;
	}


	/**
	 * 根据名字模糊搜索movie 未使用
	 * @param name
	 * @param pageStart
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/findMp4DataByName")
	@ResponseBody
		public Object findMp4DataByName(String name, Integer pageStart, Integer pageSize){
		SpringResult springResult = new SpringResult();
		springResult = mp4Service.findMp4DataByName(name,pageStart,pageSize);
		return springResult;
	}
	
	@RequestMapping(value="/findMp4Novel")
	@ResponseBody
	public Object findMp4_novel(){
		SpringResult springResult = new SpringResult();
		springResult = mp4Service.findMp4Novel();
		return springResult;
	}

	/**
	 * 获取app的版本和version
	 * @return
	 */
	@RequestMapping(value = "/getAppVersionAndUrl", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object getAppVersionAndUrl(){
		SpringResult springResult = new SpringResult();
		springResult=mp4Service.getAppVersionAndUrl();
		return  springResult;

	}

	/**
	 * 获取图片轮播连接
	 * @return
	 */
	@RequestMapping(value = "/getImgLink", method={RequestMethod.POST, RequestMethod.GET})
	@ResponseBody
	public Object getImgLink(){
		SpringResult springResult = new SpringResult();
		springResult=mp4Service.getImgLink();
		return  springResult;

	}

	/**
	 * 上传linux地址(多文件上传)
	 * @param multipartFile
	 * @param fileContentType
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadMultipartFile", method={RequestMethod.POST})
	@ResponseBody
	public Object uploadMultipartFile(@RequestParam(value = "file") List<MultipartFile> multipartFile,
																		@RequestParam(value="filename1")String filename1,
																		@RequestParam(value="fileContentType")String fileContentType,
																		HttpServletRequest request, HttpServletResponse response) {
		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		SpringResult springResult = new SpringResult();
		try {
			String randStr=RandomUtil.generateString(10);
			FtpUtils ff=FtpUtils.getSftpUtil("640661.ichengyun.net", 22, "testuser", "*IKMNHY^2018a");
			Map<String,String> names=new HashMap<String, String>();
			for (MultipartFile file:multipartFile){
				String fileName=file.getOriginalFilename();
				String houzhui= com.boot.commons.utils.StringUtils.substringAfter(fileName,".");
				if(!StringUtils.isBlank(filename1)){
					fileName=filename1+"."+houzhui;
				}
				if("jpg".equals(houzhui) || "jpeg".equals(houzhui)  || "png".equals(houzhui)) {
					names.put("pic",fileName);
				}else{
					names.put("video",fileName);
				}
				int size=(int)file.getSize();
				log.info(fileName + "-->" + size);
				InputStream input=file.getInputStream();

				if(file.isEmpty()){
					springResult.setResult(null);
					springResult.setResultCode("300");
					return springResult;
				}else{
					ff.uploadByStream("/web/java/apache-tomcat-gooSe-8088/webapps/videos", fileName, input);
					/*保存文件信息*/
					//springResult=mp4Service.saveFileNameMul(randStr,fileType,fileContentType,randStr+"-"+fileName);
				}
			}
			/*保存文件信息到mp4_data表*/
			mp4Service.saveFileNameMulNew(fileContentType,names.get("video"), "/videos/",names.get("pic"),"/videos/");
			springResult.setMessage("上传成功");
			springResult.setResultCode("200");
		}catch (Exception e){
			log.error("多文件上传错误"+e);
			springResult.setMessage("上传失败");
			springResult.setResultCode("200");
		}
		return springResult;
	}

	/**
	 * 上传linux地址 图片轮播
	 * @param multipartFile
	 * @param imgLink
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/uploadPicture", method={RequestMethod.POST})
	@ResponseBody
	public Object uploadPicture(@RequestParam(value = "file", required = false) MultipartFile multipartFile,
                              @RequestParam(value="imgLink")String imgLink,
                              HttpServletRequest request,HttpServletResponse response) {

		response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
		SpringResult springResult = new SpringResult();
		String randStr=RandomUtil.generateString(3);
		try {
//			CommonsMultipartFile cFile = (CommonsMultipartFile) multipartFile;
//			DiskFileItem fileItem = (DiskFileItem) cFile.getFileItem();
//			String fileName=fileItem.getName();
//			InputStream input = fileItem.getInputStream();
			String fileName=multipartFile.getOriginalFilename();
			InputStream input=multipartFile.getInputStream();

			FtpUtils ff=FtpUtils.getSftpUtil("640661.ichengyun.net", 22, "testuser", "*IKMNHY^2018a");
			ff.uploadByStream("/web/java/apache-tomcat-gooSe-8088/webapps/videos", fileName, input);

			/*把文件名字存起来*/
			springResult=mp4Service.saveFileNameMulNewImg(fileName,imgLink);

			springResult.setMessage("上传成功");
			springResult.setResultCode("200");
		}catch (Exception e) {
			log.error("多文件上传错误"+e);
			springResult.setMessage("上传失败");
			springResult.setResultCode("200");
		}
		return springResult;
	}

}
