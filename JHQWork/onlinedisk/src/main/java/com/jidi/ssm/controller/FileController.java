package com.jidi.ssm.controller;

import com.jidi.ssm.pojo.UserVO;
import com.jidi.ssm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

@Controller
@RequestMapping("/files")
public class FileController
{
    @Autowired
    private FileService fileService;

    //文件上傳
    @RequestMapping("/upLoad")
    public ModelAndView upLoad(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView();

        String name=(String)(request.getSession(true).getAttribute("name"));
        int userId=Integer.parseInt((String)(request.getSession(true).getAttribute("userId")));

        String info=fileService.executeUpLoad(request, response, name,userId);

        if(info.equals("exist"))
        {
            //文件已存在
            modelAndView.addObject("info", "文件已存在，請更改文件名再上傳或刪除網盤中的同名文件！");
            modelAndView.setViewName("error");
            return modelAndView;
        }
        else
        {
            //上傳后再次準備文件名并跳轉回我的網盤界面,相當與幫助用戶刷新
            ArrayList<String> al=fileService.getAllFilesName(request, response,name);

            modelAndView.addObject("name", name);
            modelAndView.addObject("allFilesName", al);
            modelAndView.addObject("fileCount", al.size()+"");
            modelAndView.setViewName("mydisk");
            return modelAndView;
        }
    }

    //文件下載
    @RequestMapping("/downLoad")
    public void downLoad(HttpServletRequest request,HttpServletResponse response)
    {
        //調用文件下載的Java模型
        String name=request.getParameter("name");

        try
        {
            fileService.executeDownLoad(request, response, name);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    //獲取所有文件名
    @RequestMapping("/fileNamePrepare")
    public ModelAndView fileNamePrepare(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView();

        //調用文件名獲取的Java模型
        String name=request.getParameter("name");
        String command=request.getParameter("command");

        ArrayList<String> al=fileService.getAllFilesName(request, response,name);

        modelAndView.addObject("name", name);
        modelAndView.addObject("allFilesName", al);
        modelAndView.addObject("fileCount", al.size()+"");

        if(command.equals("administrator"))
        {
            modelAndView.setViewName("userFileView");

            return modelAndView;
        }
        else
        {
            modelAndView.setViewName("mydisk");

            return modelAndView;
        }
    }

    //為管理用戶的界面提供所有用戶文件夾的信息
    @RequestMapping("/prepareAllUserDirectory")
    public ModelAndView prepareAllUserDirectory(HttpServletRequest request)
    {
        ModelAndView modelAndView = new ModelAndView();

        ArrayList<String> arrayList=fileService.getAllUserDirectory(request);

        modelAndView.addObject("directoryCount", arrayList.size());
        modelAndView.addObject("allUserDirectory", arrayList);
        modelAndView.setViewName("administrator");

        return modelAndView;
    }

    //文件刪除
    @RequestMapping("/deleteFile")
    public ModelAndView deleteFile(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView();

        String name=request.getParameter("name");
        int userId=Integer.parseInt((String)(request.getSession(true).getAttribute("userId")));

        fileService.fileDelete(request, response, name,userId);

        //刪除后再次準備文件名并跳轉回我的網盤界面,相當與幫助用戶刷新
        ArrayList<String> al=fileService.getAllFilesName(request, response,name);

        modelAndView.addObject("name", name);
        modelAndView.addObject("allFilesName", al);
        modelAndView.addObject("fileCount", al.size()+"");
        modelAndView.setViewName("mydisk");

        return modelAndView;
    }

    //根據用戶名得到該用戶上傳的所有文件名和文件的信息
    @RequestMapping("/selectFilesByUserName")
    public ModelAndView selectUsersByFileName(@RequestParam(value = "userName", required = true) String userName)
    {
        ModelAndView modelAndView = new ModelAndView();

        ArrayList<UserVO> arrayList=fileService.findFileByUserName(userName);

        modelAndView.addObject("allFilesUser", arrayList);
        modelAndView.addObject("resultCount", arrayList.size()+"");
        modelAndView.setViewName("selectFilesByUserNameResult");

        return modelAndView;
    }
}