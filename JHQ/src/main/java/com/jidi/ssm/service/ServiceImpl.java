package com.jidi.ssm.service;

import com.jidi.ssm.mapper.UserMapper;
import com.jidi.ssm.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceImpl implements  UsersService
{
    @Autowired
    private UserMapper userMapper;

    public String[] checkUser(String name, String inputPassword)
    {
        //成功與否,錯誤原因
        String[] info_login={"",""};

        String truePassword=userMapper.selectPasswordByName(name);

        if(inputPassword.equals(truePassword))
        {
            //密碼正確
            info_login[0]="success";
            info_login[1]="null";
        }
        else
        {
            //密碼不正確
            info_login[0]="fail";
            info_login[1]="passwd_Err";
        }

        return info_login;
    }

    public void userRegister(String name, String pass, String email)
    {
        User users=new User();

        users.setName(name);
        users.setPassword(pass);
        users.setEmail(email);

        userMapper.insertUser(users);
    }

    public void userUpData(int id, String name, String pass, String email)
    {
        User users=new User();

        users.setName(name);
        users.setPassword(pass);
        users.setEmail(email);
        users.setID(id);

        userMapper.updateUser(users);
    }

    public User getUser(String name)
    {
        User users=userMapper.selectUserByName(name);

        return users;
    }
}
