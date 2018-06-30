	package com.liurz.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.liurz.util.PropertiesUtil;
import com.liurz.util.ResultDateUtil;
@RestController
@RequestMapping(value = "/RoleControllerController", produces = {"application/json;charset=UTF-8"})
public class RoleController {
	Logger log = LoggerFactory.getLogger(RoleController.class);
	@Autowired
	RestTemplate restTemplate;

	@RequestMapping(value = "/getById", produces ={"application/json"}, method = RequestMethod.GET)
	public String getById(@RequestParam(value = "id", required = true) Integer id) {
		try {
			String url = PropertiesUtil.getValue("RoleController.getById");
			url = url + "?id=" + id;
			String reslut = restTemplate.getForObject(url, String.class);
			JSONObject json = JSONObject.parseObject(reslut);
			return ResultDateUtil.successResult(json);
		} catch (Exception e) {
			log.info("getById failure" + e);
			return ResultDateUtil.errorResult("getById failure");
		}

	}

	@RequestMapping(value = "/findAll", produces ={"application/json"}, method = RequestMethod.GET)
	public String findAll() {
		try {
			String url = PropertiesUtil.getValue("RoleController.findAll");
			String reslut = restTemplate.getForObject(url, String.class);
			JSONArray array = JSONArray.parseArray(reslut);
			return ResultDateUtil.successResult(array);
		} catch (Exception e) {
			log.info("findAll failure" + e);
			return ResultDateUtil.errorResult("findAll failure");
		}

	}

	// ��json����ֱ�ӽ������󼯺Ͻ��и���
	@RequestMapping(value = "/saveOrUpdate", produces = {"application/json"}, method = RequestMethod.POST)
	public String saveOrUpdate(@RequestParam(value = "RoleControllers") String RoleControllers) {
		try {
			String url = PropertiesUtil.getValue("RoleController.saveOrUpdate");
			JSONArray array = JSONArray.parseArray(RoleControllers);
			String reslut = restTemplate.postForObject(url, array, String.class);
			return reslut;
		} catch (Exception e) {
			log.info("saveOrUpdate failure" + e);
			return ResultDateUtil.errorResult("saveOrUpdate failure");
		}
	}

	@RequestMapping(value = "/delelteRoleController/{id}", produces ={"application/json"}, method = RequestMethod.DELETE)
	public String deleteRoleController(@PathVariable(value = "id") Integer id) {

		try {
			String url = PropertiesUtil.getValue("RoleController.deleteRoleController").replace("{id}", String.valueOf(id));
			HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> requestEntity = new HttpEntity<String>(null, requestHeaders);
			ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.DELETE, requestEntity, String.class);
			String res = result.getBody();
			return res;
		} catch (Exception e) {
			log.info("delete RoleController failure" + e);
			return ResultDateUtil.errorResult("delete role failure");
		}
	}

}
