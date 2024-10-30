package com.example.device_weixiu.mapper;

import com.example.device_weixiu.domain.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 10642
* @description 针对表【user】的数据库操作Mapper
* @createDate 2024-10-24 19:29:47
* @Entity com.example.device_weixiu.domain.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




