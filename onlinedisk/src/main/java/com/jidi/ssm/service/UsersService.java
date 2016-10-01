package com.jidi.ssm.service;

import com.jidi.ssm.pojo.User;
import org.springframework.stereotype.Service;

@Service
public interface UsersService
{
    public User selectUserByName(String name);
}
