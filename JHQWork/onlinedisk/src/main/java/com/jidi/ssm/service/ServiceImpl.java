package com.jidi.ssm.service;

import com.jidi.ssm.mapper.UserMapper;
import com.jidi.ssm.pojo.User;
import com.jidi.ssm.pojo.UserFileVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceImpl implements  UsersService
{
    @Autowired
    private UserMapper userMapper;

    public String[] checkUser(String name, String inputPassword)
    {
        //成功與否,錯誤原因,是否管理員,用戶ID
        String[] info_login={"","","",""};

        User loginUser=userMapper.selectUserLoginInfoByName(name);

        String truePassword=loginUser.getPassword();
        boolean isAdministrator=loginUser.getisAdministrator();
        int id=loginUser.getID();

        if(inputPassword.equals(truePassword))
        {
            //密碼正確
            info_login[0]="success";
            info_login[1]="null";
            info_login[3]=id+"";

            if(isAdministrator)
            {
                info_login[2]="true";
            }
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

    public ArrayList<UserFileVo> selectUsersByFileName(String fileName)
    {
        return userMapper.selectUsersByFileName(fileName);
    }
}
