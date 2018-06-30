package com.liurz.controller;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.liurz.util.FileUtil;
import com.liurz.util.ResultDateUtil;


@RestController
@RequestMapping("/FileController")
public class FileController{
	  @RequestMapping(value="/fileupload",method=RequestMethod.POST)
	  public String fileuplod(HttpServletRequest request){
		  String uploadFileToPath=request.getRealPath("")+"upload";
		  boolean flag=FileUtil.uploadFile(request, uploadFileToPath);
		  if(flag){
			return ResultDateUtil.successResult("success");
		  }
		    return ResultDateUtil.errorResult("error");
	  }
	  @RequestMapping(value="/filedown",method=RequestMethod.POST)
	  public String filedown(HttpServletResponse response,HttpServletRequest request){
		 String downFilePath=request.getParameter("filepath");
		 boolean flag= FileUtil.downFile(downFilePath, response);
		 if(flag){
			 return ResultDateUtil.successResult("success");
		  }
		     return ResultDateUtil.errorResult("error");
	  }
}
 