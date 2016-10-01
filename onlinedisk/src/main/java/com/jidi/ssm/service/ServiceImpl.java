package com.jidi.ssm.service;

import com.jidi.ssm.mapper.UserMapper;
import com.jidi.ssm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements  UsersService
{
//    @Autowired
//    private UserMapper userMapper;

    public User selectUserByName(String name)
    {
//        User user=userMapper.selectUserByName(name);
//
//        return user;

        User user=new User();

        user.setName("ralf");
        user.setEmail("123");
        user.setPassword("6666");

        return user;
    }
}
