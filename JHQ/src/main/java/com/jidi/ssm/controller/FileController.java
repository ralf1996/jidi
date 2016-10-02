package com.jidi.ssm.controller;

import com.jidi.ssm.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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

        String info=fileService.executeUpLoad(request, response, name);

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

        ArrayList<String> al=fileService.getAllFilesName(request, response,name);

        modelAndView.addObject("name", name);
        modelAndView.addObject("allFilesName", al);
        modelAndView.addObject("fileCount", al.size()+"");
        modelAndView.setViewName("mydisk");

        return modelAndView;
    }

    //文件刪除
    @RequestMapping("/deleteFile")
    public ModelAndView deleteFile(HttpServletRequest request,HttpServletResponse response)
    {
        ModelAndView modelAndView = new ModelAndView();

        //調用文件刪除的Java模型
        String name=request.getParameter("name");

        fileService.fileDelete(request, response, name);

        //刪除后再次準備文件名并跳轉回我的網盤界面,相當與幫助用戶刷新
        ArrayList<String> al=fileService.getAllFilesName(request, response,name);

        modelAndView.addObject("name", name);
        modelAndView.addObject("allFilesName", al);
        modelAndView.addObject("fileCount", al.size()+"");
        modelAndView.setViewName("mydisk");

        return modelAndView;
    }
}