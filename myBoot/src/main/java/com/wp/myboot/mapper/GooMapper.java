package com.wp.myboot.mapper;

import com.boot.commons.entity.CommonMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface GooMapper extends CommonMapper<Log,Integer> {

	Map<String, String> findUserByName(@Param("name")String name);

	int saveSignUserMsgDao(@Param("name") String name, @Param("passwd") String passwd, @Param("confirmPass") String confirmPass, @Param("tuijianCode") String tuijianCode);


}


