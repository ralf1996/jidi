package com.jidi.ssm.mapper;

import com.jidi.ssm.pojo.User;

public interface UserMapper
{
    public String selectPasswordByName(String name);

    public int selectCount();

    public User selectUserByName(String name);

    public void insertUser(User users);

    public void updateUser(User users);
}
