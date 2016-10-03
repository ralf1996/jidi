package com.jidi.ssm.mapper;

import com.jidi.ssm.pojo.User;
import com.jidi.ssm.pojo.UserFileVo;

import java.util.ArrayList;

public interface UserMapper
{
    public User selectUserLoginInfoByName(String nmne);

    public User selectUserByName(String name);

    public void insertUser(User users);

    public void updateUser(User users);

    public ArrayList<UserFileVo> selectUsersByFileName(String fileName);
}
