package com.liurz.util;

import java.util.List;

import org.json.JSONObject;
import org.json.XML;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 使用jackson进行json处理
 * @author Administrator
 *
 */


public class JsonUtils {

    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 将对象转换成json字符串。
     * <p>Title: pojoToJson</p>
     * <p>Description: </p>
     * @param data
     * @return
     */
    
    public static String objectToJson(Object data) {
    	try {
			
    		String string = MAPPER.writeValueAsString(data);
			
			return string;
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
    	return null;
    }
    
    /**
     * 将json结果集转化为对象
     * 
     * @param jsonData json数据
     * @param clazz 对象中的object类型
     * @return
     */
    public static <T> T jsonToPojo(String jsonData, Class<T> beanType) {
        try {
           
        	T t = MAPPER.readValue(jsonData, beanType);
           
        	return t;
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return null;
    }
    
    /**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
      <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
    	
    	try {
    		List<T> list = MAPPER.readValue(jsonData, javaType);
    	
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
    /**
     * 将xml解析成json数据
     * @param xml
     * @return
     */
    public static JSONObject XmlToJSON(String xml){
    	
    	JSONObject jsonObject = XML.toJSONObject(xml);
    	
        return 	jsonObject;
    }
    public static void main(String[] args) {
	    //String str = "\"[{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"ADDRCODE\",\"colEntty\":\"addrcode\",\"colType\":\"string\",\"colLength\":\"64\",\"colComment\":\"标准地址编码\",\"isBase\":1,\"isShow\":0,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"ADDRDESC\",\"colEntty\":\"addrcode\",\"colType\":\"string\",\"colLength\":\"64\",\"colComment\":\"标准地址描述\",\"isBase\":1,\"isShow\":0,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"DYWGCODE\",\"colEntty\":\"dywgcode\",\"colType\":\"string\",\"colLength\":\"15\",\"colComment\":\"单元网格编码\",\"isBase\":1,\"isShow\":0,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"CREATEDATE\",\"colEntty\":\"createdate\",\"colType\":\"date\",\"colLength\":null,\"colComment\":\"创建时间\",\"isBase\":1,\"isShow\":1,\"isRequired\":0,\"orderNum\":null,\"attr\":\"SYSDATE\",\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":0,\"isNull\":1,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"UPDATEDATE\",\"colEntty\":\"updatedate\",\"colType\":\"date\",\"colLength\":null,\"colComment\":\"更新时间\",\"isBase\":1,\"isShow\":1,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":0,\"isNull\":1,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"DW_SRC\",\"colEntty\":\"dw_src\",\"colType\":\"string\",\"colLength\":\"64\",\"colComment\":\"数据来源\",\"isBase\":1,\"isShow\":1,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"DW_NOTE\",\"colEntty\":\"dw_note\",\"colType\":\"string\",\"colLength\":\"64\",\"colComment\":\"备注\",\"isBase\":1,\"isShow\":0,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":0,\"isNull\":1,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"TASK_ID\",\"colEntty\":\"task_id\",\"colType\":\"string\",\"colLength\":\"40\",\"colComment\":\"任务ID\",\"isBase\":1,\"isShow\":0,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":0,\"isNull\":0,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"USER_ID\",\"colEntty\":\"user_id\",\"colType\":\"string\",\"colLength\":\"40\",\"colComment\":\"落块人ID\",\"isBase\":1,\"isShow\":0,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":0,\"isNull\":0,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"STATUS\",\"colEntty\":\"status\",\"colType\":\"int\",\"colLength\":\"1\",\"colComment\":\"数据状态\",\"isBase\":1,\"isShow\":0,\"isRequired\":0,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":0,\"isNull\":0,\"selectCol\":null,\"isMust\":0},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"A\",\"colEntty\":\"a\",\"colType\":\"string\",\"colLength\":\"1\",\"colComment\":\"1\",\"isBase\":0,\"isShow\":1,\"isRequired\":1,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":1},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"B\",\"colEntty\":\"b\",\"colType\":\"int\",\"colLength\":\"2\",\"colComment\":\"2\",\"isBase\":0,\"isShow\":1,\"isRequired\":1,\"orderNum\":null,\"attr\":\"SHOW\",\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":1},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"C\",\"colEntty\":\"c\",\"colType\":\"double\",\"colLength\":\"3,4\",\"colComment\":\"3\",\"isBase\":0,\"isShow\":1,\"isRequired\":1,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":1},{\"id\":null,\"ywId\":1020,\"tableName\":\"U_FZ_TEST\",\"tableEntity\":\"uFzTest\",\"colName\":\"D\",\"colEntty\":\"d\",\"colType\":\"date\",\"colLength\":\"\",\"colComment\":\"5\",\"isBase\":0,\"isShow\":1,\"isRequired\":1,\"orderNum\":null,\"attr\":null,\"foreignTable\":null,\"foreignCol\":null,\"isBlock\":1,\"isNull\":1,\"selectCol\":null,\"isMust\":1}]\"";
		String str1="<id name='liurz' age=12 ></id>";
		System.out.println(JsonUtils.XmlToJSON(str1).get("id"));

	}
}
