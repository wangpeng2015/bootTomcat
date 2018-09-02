package com.wp.myboot.mapper;


import com.wp.myboot.entity.TestUser;

import java.util.List;

public interface TestBootMapper extends CommonMapper<Log,Integer> {

    List<TestUser> selectALL();
}
