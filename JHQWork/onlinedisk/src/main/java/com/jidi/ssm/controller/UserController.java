package com.jidi.ssm.controller;

import com.jidi.ssm.pojo.User;
import com.jidi.ssm.pojo.UserFileVo;
import com.jidi.ssm.service.FileServiceImpl;
import com.jidi.ssm.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
@RequestMapping("/users")
public class UserController
{
    @Autowired
    private UsersService usersService;

    @RequestMapping("login")
    public ModelAndView login()
    {
        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("/login");
        return modelAndView;
    }

    //用戶登錄時檢驗用戶是否合法
    @RequestMapping("/checkUserController")
    public ModelAndView checkUserController(HttpServletRequest request,
                                            @RequestParam(value = "username", required = true)String name,
                                            @RequestParam(value = "passwd", required = true)String inputPassword ) throws Exception
    {
        String[] info_login=usersService.checkUser(name, inputPassword);

        ModelAndView modelAndView = new ModelAndView();

        if(info_login[0].equals("fail"))
        {
            //登錄失敗
            if(info_login[1].equals("userName_Err"))
            {
                //用戶名不存在
                modelAndView.addObject("info", "用戶名不存在！");
                modelAndView.setViewName("login");
                return modelAndView;
            }
            else
            {
                //密碼錯誤
                modelAndView.addObject("info", "密碼錯誤！");
                modelAndView.setViewName("login");
                return modelAndView;
            }
        }
        else
        {
            //登錄成功,跳轉到主界面
            int id=Integer.parseInt(info_login[3]);

            HttpSession hs=request.getSession(true);
            hs.setAttribute("name", name);
            hs.setAttribute("userId", id+"");

            if(info_login[2].equals("true"))
            {
                //說明是管理員登錄
                modelAndView.addObject("isAdministrator", "true");
                modelAndView.setViewName("main");
                return modelAndView;
            }
            else
            {
                //說明是普通用戶登錄
                modelAndView.setViewName("main");
                return modelAndView;
            }
        }
    }

    //為修改個人信息的用戶提供其本人信息(從數據庫中獲取)
    @RequestMapping("/prepareDataController")
    public ModelAndView prepareDataController(@RequestParam(value = "name", required = true) String name) throws Exception
    {
        User users=usersService.getUser(name);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("userbean", users);
        modelAndView.setViewName("userInfoRenew");

        return modelAndView;
    }

    //用戶更新
    @RequestMapping("/userUpDataController")
    public ModelAndView userUpDataController(@RequestParam(value = "id", required = false) int id,
                                             @RequestParam(value = "uname", required = true) String name,
                                             @RequestParam(value = "passwd", required = true) String pass,
                                             @RequestParam(value = "email", required = true) String email) throws Exception
    {
        usersService.userUpData(id, name, pass, email);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.addObject("info", "修改成功，請重新登錄！");
        modelAndView.setViewName("login");

        return modelAndView;
    }

    //用戶報名
    @RequestMapping("/userRegisterController")
    public ModelAndView userRegisterController(HttpServletRequest request,
                                               @RequestParam(value = "uname", required = true) String name,
                                               @RequestParam(value = "passwd", required = true) String pass,
                                               @RequestParam(value = "email", required = true) String email) throws Exception
    {
        usersService.userRegister(name, pass, email);

        new FileServiceImpl().checkUsersPath(name,request);

        HttpSession hs=request.getSession(true);
        hs.setAttribute("name", name);

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName("main");

        return modelAndView;
    }

    //根據文件名得到用戶
    @RequestMapping("/selectUsersByFileName")
    public ModelAndView selectUsersByFileName(@RequestParam(value = "fileName", required = true) String fileName)
    {
        ModelAndView modelAndView = new ModelAndView();

        ArrayList<UserFileVo> arrayList=usersService.selectUsersByFileName(fileName);

        modelAndView.addObject("fileAndUserInfo", arrayList);
        modelAndView.addObject("resultCount", arrayList.size()+"");
        modelAndView.setViewName("selectUsersByFileNameResult");

        return modelAndView;
    }

    //跳轉到用戶註冊界面
    @RequestMapping("/sentRedirectToRegister")
    public ModelAndView sentRedirectToRegister()
    {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");

        return modelAndView;
    }
}
