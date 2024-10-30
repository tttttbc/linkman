package com.example.device_weixiu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.device_weixiu.domain.User;
import com.example.device_weixiu.service.UserService;
import com.example.device_weixiu.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author 10642
* @description 针对表【user】的数据库操作Service实现
* @createDate 2024-10-24 19:29:47
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




