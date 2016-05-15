package com.demo.spring.mvc;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.demo.model.Employee;
import com.demo.model.UserInfo;

//@Controller
//@RequestMapping(value="/mvc",method={RequestMethod.OPTIONS,RequestMethod.GET,RequestMethod.POST})
public class WebMVCTestService {
	
	@ResponseBody
	@RequestMapping(value = "first/{sencond}/{third}", 
			method = {RequestMethod.GET,RequestMethod.POST}, 
			produces = MediaType.APPLICATION_JSON_VALUE)
	Map<String, Object> dispatchTest(
    		@PathVariable String sencond,
    		@PathVariable String third,
    		@CookieValue(value="myCookie", defaultValue="no cookie yet") String myCookie, 
    		@RequestHeader(value="Host",required=false) String header,
    		@RequestParam("username") String username,//@RequestParam(value="username",required=true)
    		@RequestParam(value="password",required=false) String password,
    		@Value("#{systemProperties['java.vm.version']}") String jvmVersion,
    		WebRequest webRequest, 
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		HttpSession httpSession) throws Exception{
		
		response.addHeader("customHeader", "customHeaderValue");
		Cookie cookie = new Cookie("myCookie", new UserInfo(username, password).toString());
		cookie.setPath("/");//root folder
		cookie.setMaxAge(60 * 60 * 24);//1 day
		response.addCookie(cookie);
		
		Iterator<String> paramIter = webRequest.getParameterNames();
        String key, value;
        while(paramIter.hasNext()){
        	key = paramIter.next();	
        	value = webRequest.getParameter(key);
            if (value != null) {
                System.out.println(key + ": "+value);
            }
        }
        
        if (httpSession == null) {
        	System.out.println("httpSession does not exist");
        	httpSession = request.getSession(true);
        }
        
        UserInfo currentuser = (UserInfo)httpSession.getAttribute("currentUser");
        if (currentuser == null) {
        	System.out.println("current user does not exist");
        	httpSession.setAttribute("currentUser", new UserInfo(username, password));
        }

		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("name", username);
		result.put("pwd", password);
		result.put("sencond", sencond);
		result.put("third", third);
		result.put("myCookie", myCookie);
		result.put("header", header);
		result.put("isSecure", webRequest.isSecure());
		result.put("contextPath", webRequest.getContextPath());
		result.put("status", "success");
		return result;
	}
	
	
	//a common query
	@RequestMapping(value = "query/{qid}", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    <T> List<T> getMyBatisQueryViaParameterPost(
    		@PathVariable String qid,  
    		@RequestParam(value="pageSize",required=false) String pageSize,
    		@RequestParam(value="pageNumber",required=false) String pageNumber,
    		WebRequest webRequest)
    		throws Exception {

    	//return getMyBatisQueryViaParameterGet(qid, loggedInUser, pageSize, pageNumber, webRequest);
    	return null;
    }
	
	@RequestMapping(value = "/getEmpsAction}")
	public ModelAndView getEmployees(){
		List<Employee> list = new ArrayList<>();
		
		ModelAndView mv = new ModelAndView("showEmployeeList");
		mv.addObject("empList", list);
		return mv;
	}
}
