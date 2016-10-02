package com.jidi.ssm.service;

import com.jidi.ssm.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UsersService
{
    public String[] checkUser(String name,String inputPassword);

    public void userRegister(String name,String pass,String email);

    public void userUpData(int id,String name,String pass,String email);

    public User getUser(String name);
}
