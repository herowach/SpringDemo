package com.demo.spring.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;


@Controller
@RequestMapping(value="/ajax",method={RequestMethod.GET,RequestMethod.POST})
public class AjaxRequestController {
	
	/*@ResponseBody
	@RequestMapping(value="/listTest", method = RequestMethod.POST)	
	public ParamWithArray jsonParamWithListTest(@RequestBody ParamWithArray param) {
		return param;
	}*/
	
	@ResponseBody
	@RequestMapping(value="/objectTest", method = RequestMethod.POST)	
	public Map<String, Object> jsonParamTest(@RequestBody Map<String, Object> param) {
		return param;
	}
	
	@ResponseBody
	@RequestMapping(value="/arrayTest", method = RequestMethod.POST)	
	public String[] arrayJsonTest(@RequestBody String[] params) {
		return params;
	}
	
	@ResponseBody
	@RequestMapping(value="/arrayObjectTest", method = RequestMethod.POST)	
	public List<Object> complexJsonParamTest(@RequestBody Object[] params) {
		@SuppressWarnings("unchecked")
		List<Object> list = CollectionUtils.arrayToList(params);
		@SuppressWarnings("unused")
		List<Object> list2 = Arrays.asList(params);
		return list;
	}
	
	@ResponseBody
	@RequestMapping(value="/getObjectListTest", method = RequestMethod.POST)	
	public List<Map<String, Object>> jsonStringTest(String paramObj, 
			int otherInt, boolean otherBoolean, @DateTimeFormat(pattern="yyyy-MM-dd") Date otherDate) {
		Map<String, Object> map = new HashMap<String,Object>();
		try {
			map = new ObjectMapper().readValue(paramObj, new TypeReference<HashMap<String,Object>>(){});
		} catch (Exception e) {
		}
		
		List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
		Map<String, Object> record = new HashMap<String, Object>();
		record.put("field1", map);
		record.put("field2", otherInt);
		record.put("field3", otherBoolean);
		record.put("field4", otherDate);
		result.add(record);
		return result;
	}
	
	@SuppressWarnings("all")
	@ResponseBody
	@RequestMapping(value="/getArrayListTest", method = RequestMethod.POST)	
	public List<List<String>> arrayJsonStringTest(
			@RequestParam String paramObj, //if the pamar is a JSON string, don't add @RequestBody before the arg defination
			@RequestParam("other")String recordStr) {
		
		Map<String, Object> map = new HashMap<String,Object>();
		List<Map<String,Object>> records = new ArrayList<Map<String,Object>>();
		
		try {
			map = new ObjectMapper().readValue(paramObj, new TypeReference<HashMap<String,Object>>(){});
			records = new ObjectMapper().readValue(recordStr, new TypeReference<ArrayList<HashMap<String,Object>>>(){});
		} catch (Exception e) {
		}
		
		List<String> list = new ArrayList<String>();
		List<List<String>> result = new ArrayList<List<String>>();
		List<String> arr1 = new ArrayList<String>();
		arr1.add("name1");
		arr1.add("value1");
		List<String> arr2 = new ArrayList<String>();
		arr2.add("name2");
		arr2.add("value2");
		
		result.add(arr1);
		result.add(arr2);
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/getJavaObjectArrayTest", method = RequestMethod.POST)	
	public Object[] javaArrayTest() {
		Object[] objs = new Object[6];
		
		List<String> list = new ArrayList<String>();
		List<String> arr1 = new ArrayList<String>();
		arr1.add("name1");
		arr1.add("value1");
		List<String> arr2 = new ArrayList<String>();
		arr2.add("name2");
		arr2.add("value2");
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("field1", "value1");
		map.put("field2", "value2");
		
		objs[0] = "success";
		objs[1] = 123;
		objs[2] = true;
		objs[3] = new String[]{"subItem1", "subitem2"};
		objs[4] = list;
		objs[5] = map;
		return objs;
	}
	
	@ResponseBody
	@RequestMapping(value="/parseJSONFromURL/{jsonObj}/{jsonArray}", method = {RequestMethod.GET, RequestMethod.POST})	
	public Map<String, Object> parseJSONFromURL(
			@RequestBody @PathVariable Map<String, Object> jsonObj,
			@RequestBody @PathVariable Object[] jsonArray) {
		jsonObj.put("jsonArray", jsonArray);
		return jsonObj;
	}
	
	//404 will be returned if don't specified @ResponseBody and don't write anything
	@RequestMapping(value="/nonResponseBody", method = {RequestMethod.GET, RequestMethod.POST})	
	public void withoutResponseBody(String jsonString, HttpServletResponse response)  throws ServletException, IOException {
		response.addHeader("Content-Type", "application/json;charset=UTF-8");
		PrintWriter out = response.getWriter();
		out.write(jsonString);
		out.flush();
		out.close();
	}
	
	@ResponseBody
	@RequestMapping(value="/exceptionTest", method = RequestMethod.GET)
	public void exceptionTest() throws Exception {
		throw new Exception("This is an exception!");
	}
	
	@ResponseBody
	@RequestMapping(value="/fileupload", method = RequestMethod.POST)
	public void fileUpload(@RequestParam MultipartFile photo) throws Exception {
		String originName = photo.getOriginalFilename();
		//String extn = FilenameUtils.getExtension(originName);
		File folder = new File(getClassPath() + "\\upload");
		if(!folder.exists()) {
			folder.mkdir();
		}
		File localFile = new File(getClassPath() + "\\upload", originName);
		if(localFile.exists()) {
			localFile.delete();
		}
		photo.transferTo(localFile);
	}
	
	public static String getClassPath() {
		URL location = AjaxRequestController.class.getProtectionDomain().getCodeSource().getLocation();
	    File file = new File(location.getFile());
	    return file.getParent();
	}
}
