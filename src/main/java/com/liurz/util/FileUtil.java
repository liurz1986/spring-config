package com.liurz.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
/**
 * 文件上传和文件下载
 * @author Administrator
 *
 */
public class FileUtil {
     static	Logger log=LoggerFactory.getLogger(FileUtil.class);
	/**
	 * 文件上传
	 * @param request
	 * @param uploadFileToPath 文件上传的地址
	 * @return
	 */
     public static boolean uploadFile(HttpServletRequest request,String uploadFileToPath){
		 log.info("start file upload");
    	 try{
    	 ServletFileUpload  fileupload=new ServletFileUpload(new DiskFileItemFactory());
    	 List<FileItem> fileitems=fileupload.parseRequest(request);
    	 if(!fileitems.isEmpty()){
    		 for(FileItem fileitem:fileitems){
    			 if(null!=fileitem&&!"".equals(fileitem)){
    				 String filepath=uploadFileToPath+File.separator+fileitem.getName();
    				 FileUtils.copyInputStreamToFile(fileitem.getInputStream(), new File(filepath));
    			 }
    		 } 
    	 }
    	 log.info("file upload success");
    	 return true;
    	 }catch(Exception e){
    		 log.info("file upload faile");
    		 return false;
    	 }
     }
     
     /**
      * 文件下载
      * @param downFilePath
      * @param response
      * @return
      */
     public static boolean downFile(String downFilePath,HttpServletResponse response){
    	 try{
    		 log.info("start file down");
    		 File file=new File(downFilePath);
    		 InputStream input=new FileInputStream(file);
    		 BufferedInputStream buffer=new BufferedInputStream(input);
    		 byte[] by=new byte[buffer.available()];//临时存放文件
    		 buffer.read(by);
    		 
    		 buffer.close();
    		 input.close();
    		 
    		 //重置response
    		 response.reset();
    		 //设置application/x-download，response编码方式
    		 response.setContentType("application/x-download");
    		 //写明要下载文件的大小
    		 response.setContentLength((int)file.length());
    		 //设置文件名
    		 String fileName=URLEncoder.encode(file.getName(),"utf-8");
    		 response.addHeader("Content-Dispostion","attachment;filename="+fileName);
    		 
    		 OutputStream out=response.getOutputStream();
    		 out.write(by);
    		 out.close();
    		 log.info("file down success");
    		 return true; 
    	 }catch(Exception e){
    		 log.info("file down faile");
    		 return false;
    		 
    	 }
     }
}
