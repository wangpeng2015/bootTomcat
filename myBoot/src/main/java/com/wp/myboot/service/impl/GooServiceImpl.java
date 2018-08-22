package com.wp.myboot.service.impl;

import com.wp.myboot.controller.GooController;
import com.wp.myboot.mapper.GooMapper;
import com.wp.myboot.service.GooService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;


@Service("gooService")
public class GooServiceImpl implements GooService {

	private final Logger logger = LoggerFactory.getLogger(GooController.class);
	
	@Resource
	private GooMapper gooMapper;
	
	@Override
	public Integer addSignUserMsg(String name, String passwd, String confirmPass, String tuijianCode) {
		//查看是否注册
		try {
			Map<String, String> uu=gooMapper.findUserByName(name.trim());
			//已经存在用户
			if(uu!=null && uu.size()>0){
				return -1;
			}else{
				//插入信息
				int res=gooMapper.saveSignUserMsgDao(name,passwd,confirmPass,tuijianCode);
				if(res>=0){
					return 1;
				}else{
					return -1;
				}
			}
		} catch (Exception e) {
			logger.info("注册错误-----"+e.toString());
		}
		return 1;
	}
}
