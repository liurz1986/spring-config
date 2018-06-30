package com.liurz.controller;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONObject;
@Controller
@RequestMapping(value="/UserController")
public class restClient {
	@Autowired
	RestTemplate restTemplate ;//rest�ͻ��ˣ����Է���rest�ӿ�
	//http://localhost:8080/LRZ_Web/UserController/findById?id=1
	@RequestMapping(value="/findById")
	@ResponseBody
	public String findById(@RequestParam("id") Integer id,HttpServletRequest request) {
		String urls="http://localhost:8081/rest/UserController/all?id={id}&&name={name}";
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("name","liurz");
		map.put("id",12);
		String result=restTemplate.postForObject(urls,null,String.class,map);
		return result;
	}
	@ResponseBody
	@RequestMapping(value="/getRest",method=RequestMethod.GET)
    public String getRest(@RequestParam("id") Integer id){
		String url="http://localhost:8081/rest/UserController/findById?id="+id;
		return restTemplate.getForObject(url, String.class);
    }
	@ResponseBody
	@RequestMapping(value="/postRestMap",method=RequestMethod.POST)
    public String postRestMap(String name,Integer id){
		String url="http://localhost:8081/rest/UserController/postRest?id={id}&&data={data}";
		
		Map<String, Object> map=new HashMap<String,Object>();
		map.put("id",id);
		JSONObject json=new JSONObject();
		json.put("name", name);
		map.put("data",json.toString());
		
		String result=restTemplate.postForObject(url,null,String.class,map);
		return  result;
    }
	//http://localhost:8080/LRZ_Web/UserController/postRestJSON  
	@ResponseBody
	@RequestMapping(value="/postRestJSON",method=RequestMethod.POST)
    public String postRestJSON(String name,Integer id){
		String url="http://localhost:8081/rest/UserController/postRestJSON?result=success";
		List<JSONObject> user=new ArrayList<JSONObject>();
		JSONObject json=new JSONObject();
		json.put("userName", "liurz");
		json.put("age", 21);
		user.add(json);
		String result=restTemplate.postForObject(url, user.toString(), String.class);
		System.out.println(result);
		return  result;
    }
	@ResponseBody
	@RequestMapping(value="/putRest",method=RequestMethod.PUT)
    public String putRest(String name,Integer id){
		
		JSONObject json1=new JSONObject();
		json1.put("userName", "liurz1");
		json1.put("role", "nan1");
		json1.put("age", 111);
		JSONObject json2=new JSONObject();
		json2.put("userName", "liurz2");
		json2.put("role", "nan2");
		json2.put("age", 112);
		JSONObject json3=new JSONObject();
		json3.put("userName", "liurz3");
		json3.put("role", "nan3");
		json3.put("age", 113);
		List<JSONObject> obj=new ArrayList<JSONObject>();
		obj.add(json1);
		obj.add(json2);
		obj.add(json3);
		String url="http://localhost:8081/rest/UserController/upadateRest";
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(obj.toString(), requestHeaders);
		ResponseEntity<String> result=
				restTemplate.exchange(url,HttpMethod.PUT,requestEntity,String.class);
		String res=result.getBody();
		return res;
    }
	@ResponseBody
	@RequestMapping(value="/deleteRest",method=RequestMethod.DELETE)
    public String deleteRest(String name,Integer id){
		String url="http://localhost:8081/rest/UserController/deleteRest?userName="+name+"&&userId="+id;
		try{
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
		ResponseEntity<String> result=
				restTemplate.exchange(url,HttpMethod.DELETE,requestEntity,String.class);
		String res=result.getBody();
		 return res;
		}catch(Exception e){
		   return "deleteRest error";
		}
    }
}
