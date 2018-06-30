package com.liurz.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSONArray;
import com.liurz.entity.User;
import com.liurz.service.UserService;
import com.liurz.util.PropertiesUtil;
import com.liurz.util.ResultDateUtil;

@RestController
@RequestMapping(value = "/UserController", produces = { "application/json;charset=UTF-8" })
public class UserController {
	Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	RestTemplate restTemplate;

	@Autowired
	private UserService userServiceImpl;

	/**
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
			HttpSession session) {
		try {
			String sessionId = String.valueOf(session.getAttribute("sessionId"));
			if (StringUtils.isEmpty(sessionId)) {
				return ResultDateUtil.errorResult("user is exist");
			} else {
				User user = userServiceImpl.getUser(name, password);
				if (!StringUtils.isEmpty(user)) {
					session.setAttribute("user", user);
					return ResultDateUtil.successResult("login success");
				}
			}
			return ResultDateUtil.errorResult("login failure");
		} catch (Exception e) {
			log.info("checkUser failure" + e);
			return ResultDateUtil.errorResult("login failure");
		}
	}

	// http://localhost:8080/LRZ_Web/UserController/logout
	@RequestMapping(value = "/logout", produces = { "application/json" }, method = RequestMethod.POST)
	public void logout(HttpSession session) {

		session.invalidate();// 销毁，调用session监听中销毁方法
	}

	// http://localhost:8080/LRZ_Web/UserController/saveUser?userName=lwx393577&&data=[{'name':'liurz','pwd':'liurz'}]
	@RequestMapping(value = "/saveUser", produces = { "application/json" }, method = RequestMethod.POST)
	public String saveUser(@RequestParam(value = "data") String users) {

		try {
			/*String url = PropertiesUtil.getValue("UserController.saveUser");
			JSONArray array = JSONArray.parseArray(users);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<String>(array.toString(), requestHeaders);
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			String res = result.getBody();
			return res;*/
			System.out.println(users);
			return "success";
		} catch (Exception e) {
			log.info("saveUser failure" + e);
			return ResultDateUtil.errorResult("saveUser failure");
		}
	}

	// http://localhost:8080/LRZ_Web/UserController/checkUser?userName=lwx393577&&data=[{'name':'liurz','pwd':'liurz'}]
	@RequestMapping(value = "/checkUser", produces = { "application/json" }, method = RequestMethod.POST)
	public String checkUser(@RequestParam(value = "data") String users) {

		try {
			String url = PropertiesUtil.getValue("UserController.checkUser");
			JSONArray array = JSONArray.parseArray(users);
			String reslut = restTemplate.postForObject(url, array, String.class);
			return reslut;
		} catch (Exception e) {
			log.info("checkUser failure" + e);
			return ResultDateUtil.errorResult("checkUser failure");
		}
	}

	// http://localhost:8080/LRZ_Web/UserController/deleteUser?userName=lwx393577&data=[{'id':3}]
	@RequestMapping(value = "/deleteUser", produces = { "application/json" }, method = RequestMethod.DELETE)
	public String deleteUser(@RequestParam(value = "data") String ids) {

		try {
			StringBuffer url = new StringBuffer();
			url.append(PropertiesUtil.getValue("UserController.deleteUser"));
			JSONArray array = JSONArray.parseArray(ids);
			// ����ɾ������post���󣬺ô���Щ�������Ļ���delete����
			String reslut = restTemplate.postForObject(url.toString(), array, String.class);

			return reslut;
		} catch (Exception e) {
			log.info("delete user failure" + e);
			return ResultDateUtil.errorResult("delete user failure");
		}
	}

	// http://localhost:8080/LRZ_Web/UserController/saveOrUpdateUser?userName=lwx393577&&data=[{'id':3,'name':'liurzs','pwd':'liurzs'}]
	@RequestMapping(value = "/saveOrUpdateUser", produces = { "application/json" }, method = RequestMethod.POST)
	public String saveOrUpdateUser(@RequestParam(value = "data") String users) {

		try {
			String url = PropertiesUtil.getValue("UserController.saveOrUpdateUser");
			JSONArray array = JSONArray.parseArray(users);
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<String>(array.toString(), requestHeaders);
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, String.class);
			String res = result.getBody();
			return res;
		} catch (Exception e) {
			log.info("saveOrUpdateUser failure" + e);
			return ResultDateUtil.errorResult("saveOrUpdateUser failure");
		}
	}
}
