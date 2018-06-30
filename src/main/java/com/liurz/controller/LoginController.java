package com.liurz.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.liurz.entity.User;
import com.liurz.entity.UserLogin;
import com.liurz.service.UserService;
import com.liurz.util.ResultDateUtil;
/**
 * 登录管理
 * @author Administrator
 * 登录login.jsp界面，两种方式
 * 1.直接登录项目  http://localhost:8080/LRZ_Web/
 * 2.          http://localhost:8080/lrz_web/login
 */
@Controller
public class LoginController {

	public static List<UserLogin> logonAccounts=new ArrayList<UserLogin>();//记录所有登录用户的情况
	@Autowired
	private UserService userServiceImpl;
	
	@RequestMapping(value="/",method=RequestMethod.GET)
	public String login(){
		
		return "login";
	}
	
	@RequestMapping(value="/loign",method=RequestMethod.GET)
	public String logins(){
		
		return "login";
	}
	
	/**
	 * 用户登录
	 * 通过sessionId来判断用户是不是已经登录了，如果登录了其他人不能登录
	 * 
	 * @param name
	 * @param password
	 * @param session
	 * @return
	 */
	// http://localhost:8080/LRZ_Web/UserController/login?name=lwx393577&&password=liurz1986]
	@RequestMapping(value = "/login", produces = { "application/json" }, method = RequestMethod.GET)
	public String login(@RequestParam(value = "name") String name, @RequestParam("password") String password,
			HttpServletRequest request) {
		try {
			
			if(logonAccounts==null){
			    logonAccounts = new ArrayList<UserLogin>();
			}
			String sessionId = request.getSession().getId();
			if(isExist(sessionId,logonAccounts)){//用户已经登录
				return ResultDateUtil.errorResult("user is login");
			}else{//用户不存在
				User user = userServiceImpl.getUser(name, password);
				if (!StringUtils.isEmpty(user)) {
					UserLogin userLogin=new UserLogin();
					userLogin.setUser(user);
					userLogin.setUserUuid(user.getUuid());
					userLogin.setSessionId(sessionId);
					logonAccounts.add(userLogin);
					request.getSession().setAttribute("UserLogin", userLogin);
					return ResultDateUtil.successResult("login success");
				}else{
					return ResultDateUtil.errorResult("user no exist");
				}
			}
			
		} catch (Exception e) {
			return ResultDateUtil.errorResult("login failure");
		}
	}

	/**
	 * 用户登出
	 * 1.用户点击登录按钮
	 * 2.点击浏览器关闭按钮或者用Alt+F4退出，可以用javascript捕捉该页面关闭事件
	 * @param request
	 */
	// http://localhost:8080/LRZ_Web/UserController/logout
	@RequestMapping(value = "/logout", produces = { "application/json" }, method = RequestMethod.POST)
	public void logout(HttpServletRequest request) {
		if(logonAccounts==null){
		    logonAccounts = new ArrayList<UserLogin>();
		}
		request.getSession().invalidate();// session销毁，调用session监听中销毁方法
		
	}
    
	
	private boolean isExist(String sessionId, List<UserLogin> userLogins) {
		if(!StringUtils.isEmpty(sessionId)){
			for(UserLogin user:userLogins){
				if(sessionId.equalsIgnoreCase(user.getSessionId())){
					return true;
				}
			}
		}
		return false;
	}

}
