package com.jidi.ssm.controller;

import com.jidi.ssm.pojo.User;
import com.jidi.ssm.service.ServiceImpl;
import com.jidi.ssm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/users")
public class UserController
{
//    @Autowired
//    private ServiceImpl usersService;

    @RequestMapping("login")
    @ResponseBody
    public String login()
    {
//        User user=usersService.selectUserByName("ralf");
//
//        return user.getEmail();

        return "asdqwe123";
    }
}
