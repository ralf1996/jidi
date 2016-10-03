package com.jidi.ssm.service;

import com.jidi.ssm.pojo.UserFileVo;
import com.jidi.ssm.pojo.UserVO;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;


@Service
public interface FileService
{
    public String executeUpLoad(HttpServletRequest request, HttpServletResponse response, String name,int id);

    public void executeDownLoad(HttpServletRequest request,HttpServletResponse response,String name) throws UnsupportedEncodingException;

    public ArrayList<String> getAllUserDirectory(HttpServletRequest request);

    public ArrayList<String> getAllFilesName(HttpServletRequest request, HttpServletResponse response, String name);

    public void fileDelete(HttpServletRequest request,HttpServletResponse response,String name,int userId);

    public String checkUsersPath(String name,HttpServletRequest request);

    public ArrayList<UserVO> findFileByUserName(String userName);
}
