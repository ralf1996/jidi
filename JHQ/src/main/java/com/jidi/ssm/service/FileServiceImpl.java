package com.jidi.ssm.service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class FileServiceImpl implements FileService
{
    private final String uploadPath = "Files/"; //上传文件的目录
    private final String tempPath = "Files/temp/"; //临时文件目录
    private String serverPath = "";

    private int sizeMax = 3;//图片最大上限

    //執行文件上傳
    public String executeUpLoad(HttpServletRequest request,HttpServletResponse response,String name)
    {
        String info="";

        String serverPath = this.checkUsersPath(name, request);

        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setRepository(new File(serverPath+tempPath+name+"/"));//临时文件目录

        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(sizeMax*1024*1024*1024);//文件最大上限

        String filePath = null;

        try
        {
            List<FileItem> items = upload.parseRequest(request);//获取所有文件列表

            for (FileItem item : items)
            {
                //获得文件名，这个文件名包括路径
                if(!item.isFormField())
                {
                    //fileName.endsWith(fileType[0])
                    //文件名
                    String fileName = item.getName().toLowerCase();
                    String sourseFileName=new String(fileName.substring(fileName.lastIndexOf("\\")+1).getBytes("GBK"),"utf-8");
                    //String sourseFileName=fileName.substring(fileName.lastIndexOf("\\")+1);
                    System.out.println(sourseFileName);
                    //得到一個隨機字符串作為文件名
                    //String uuid = UUID.randomUUID().toString();
                    filePath = serverPath+uploadPath+name+"/"+sourseFileName;
                    System.out.println(filePath);
                    ArrayList<String> al=this.getAllFilesName(request, response, name);

                    for(int i=0;i<al.size();i++)
                    {
                        if((al.get(i)).equals(sourseFileName))
                        {
                            //文件已存在
                            info="exist";
                        }
                    }

                    item.write(new File(filePath));
                }
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return info;
    }

    //執行文件下載
    public void executeDownLoad(HttpServletRequest request,HttpServletResponse response,String name) throws UnsupportedEncodingException
    {
        //获取文件下载路径
        String serverPath = this.checkUsersPath(name, request);

        String filename="";

        request.setCharacterEncoding("UTF-8");

        try
        {
            filename = new String((request.getParameter("filename")).getBytes("GBK"),"utf-8");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        System.out.println(filename);
        File file = new File(serverPath + uploadPath + name + "/" + filename);

        if(file.exists())
        {
            //设置相应类型application/octet-stream
            response.setContentType("application/x-msdownload");
            //设置头信息
            response.setHeader( "Content-Disposition", "attachment;filename=" + new String( filename.getBytes("gb2312"), "ISO8859-1" ) );
            //response.setHeader("Content-Disposition", "attachment;filename=\"" + filename + "\"");
            InputStream inputStream=null;

            try
            {
                inputStream = new FileInputStream(file);
                ServletOutputStream ouputStream = response.getOutputStream();
                byte b[] = new byte[1024];
                int n ;
                while((n = inputStream.read(b)) != -1)
                {
                    ouputStream.write(b,0,n);
                }
                //关闭流、释放资源
                ouputStream.close();
                inputStream.close();
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            //文件不存在下载失败！
            System.out.println("失敗");
        }
    }

    //獲取所有文件名
    public ArrayList<String> getAllFilesName(HttpServletRequest request,HttpServletResponse response,String name)
    {
        String serverPath=this.checkUsersPath(name, request);

        File upload=new File(serverPath+"Files/"+name);

        if(!upload.isDirectory())
        {
            upload.mkdir();
        }

        File[] allFiles=upload.listFiles();

        ArrayList<String> al=new ArrayList<String>();

        for(int i=0;i<allFiles.length;i++)
        {
            al.add(allFiles[i].getName());
        }

        return al;
    }

    //執行文件刪除
    public void fileDelete(HttpServletRequest request,HttpServletResponse response,String name)
    {
        //获取文件下载路径
        String serverPath = this.checkUsersPath(name, request);

        String filename="";

        try
        {
            filename = new String((request.getParameter("filename")).getBytes("iso-8859-1"),"utf-8");
        }
        catch (UnsupportedEncodingException e1)
        {
            e1.printStackTrace();
        }

        File deleteFile = new File(serverPath + uploadPath + name + "/" + filename);

        deleteFile.delete();
    }

    //檢查用戶的文件路徑是否有問題
    public String checkUsersPath(String name,HttpServletRequest request)
    {
        //serverPath = getServletContext().getRealPath("/").replace("\\", "/");
        serverPath = request.getSession().getServletContext().getRealPath("/").replace("\\", "/");
        //判斷服務器下網盤的總文件夾存不存在
        if(!new File(serverPath+uploadPath).isDirectory())
        {
            new File(serverPath+uploadPath).mkdirs();
        }
        //判斷服務器下網盤的總文件夾下對應用戶存放文件的文件夾存不存在
        if(!new File(serverPath+uploadPath+name+"/").isDirectory())
        {
            new File(serverPath+uploadPath+name+"/").mkdirs();
        }
        //判斷服務器下網盤的總文件夾下存放臨時文件的文件夾存不存在
        if(!new File(serverPath+tempPath).isDirectory())
        {
            new File(serverPath+tempPath).mkdirs();
        }
        //判斷服務器下網盤的總文件夾下存放臨時文件的文件夾下存放對應用戶的臨時文件的文件夾存不存在
        if(!new File(serverPath+tempPath+name+"/").isDirectory())
        {
            new File(serverPath+tempPath+name+"/").mkdirs();
        }

        return serverPath;
    }
}
