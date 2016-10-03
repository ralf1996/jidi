package com.jidi.ssm.mapper;

import com.jidi.ssm.pojo.UserFile;
import com.jidi.ssm.pojo.UserVO;

import java.util.ArrayList;

public interface FileMapper
{
    public void insertFile(UserFile file);

    public void deleteFileFromDB(UserFile userFile);

    public ArrayList<UserVO> selectFileByUserName(String userName);
}
