package com.demo.spring.mvc;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.demo.model.UserInfo;

/*
 * @Controller
 * @SessionAttributes
 * @RequestMapping
 * @ResponseBody
 * 
 * @PathVariable
 * @RequestParam
 * @CookieValue
 * @RequestHeader
 * @Value
 * WebRequest webRequest
 * HttpServletRequest request
 * HttpServletResponse response
 * HttpSession httpSession
 * 
 */

@Controller
@RequestMapping(value="/",method={RequestMethod.GET})
public class MvcController {
	
	final static Logger logger = LoggerFactory.getLogger(MvcController.class);
	
	@RequestMapping(value = "{page}", method = {RequestMethod.GET, RequestMethod.POST})
	public String showJsp(@PathVariable String page) throws Exception{
		logger.debug(".................showJsp: "+page);
		return page;
	}
	
	//showJsp will not be invoded, since the mapping has been created at compile phase
	@RequestMapping(value = "login", method = {RequestMethod.GET})
	public String login(HttpSession httpSession) throws Exception{
		logger.debug(".................login");
		if(httpSession != null && httpSession.getAttribute("USER") != null) {
			logger.debug("session is null");
			return "index";
		}
		return "login";
	}
	
	@RequestMapping(value = "handler/login", method = {RequestMethod.GET, RequestMethod.POST})
	public String loginHandler(
			@RequestParam String username, 
			@RequestParam("password") String password, 
			HttpServletRequest request, 
			HttpServletResponse response,
			HttpSession httpSession) throws Exception{
		logger.debug(".................loginHandler");
		
		httpSession = request.getSession(true);
		httpSession.setAttribute("USER", new UserInfo(username, password));
		response.addHeader("customHeader", "customHeaderValue");
		return "index";
	}
	
	@RequestMapping(value = "getList/com.spring.{packageName}/{className}", 
			method = {RequestMethod.GET,RequestMethod.POST}, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
	public Map<String, Object> getList(
    		@PathVariable String packageName,
    		@PathVariable(value="className") String cName,//have to specify a value if the variable name is not same as the path name
    		@CookieValue(value="myCookie", required=false, defaultValue="no cookie yet") String myCookie, 
    		@RequestHeader(value="User-Agent",required=true) String header,
    		@RequestParam("username") String username,//equals to @RequestParam(value="username",required=true)
    		@Value("#{systemProperties['app.logs.root']}") String logpath,
    		WebRequest webRequest, 
    		HttpServletRequest request, 
    		HttpServletResponse response,
    		HttpSession httpSession) throws Exception{
		
		Cookie[] cookies = request.getCookies();
		for(Cookie cookie : cookies) {
			if("myCookie".equals(cookie.getName())) {
				logger.debug(cookie.toString());
			}
		}
		Cookie cookie = new Cookie("myCookie", httpSession.getAttribute("USER").toString());
		cookie.setPath("/");//root folder
		cookie.setMaxAge(60 * 60 * 24);//1 day, 0 means it's a temp cookie
		response.addCookie(cookie);
		
		Iterator<String> paramIter = webRequest.getParameterNames();
        String key, value;
        while(paramIter.hasNext()){
        	key = paramIter.next();	
        	value = webRequest.getParameter(key);
            if (value != null) {
            	logger.debug(key + ": "+value);
            }
        }

		Map<String, Object> result = new LinkedHashMap<String, Object>();
		result.put("name", username);
		result.put("packageName", packageName);
		result.put("className", cName);
		result.put("myCookie", myCookie);
		result.put("logpath", logpath);
		result.put("header", header);
		result.put("isSecure", webRequest.isSecure());
		result.put("contextPath", webRequest.getContextPath());
		result.put("status", "success");
		return result;
	}
	
	@RequestMapping(value="logout", method = {RequestMethod.GET})
	public ModelAndView logout(
			HttpServletResponse responses, 
			HttpSession httpSession) throws Exception{
		logger.debug(".................logout");
		if(httpSession != null) {
			httpSession.invalidate();
			logger.debug(".................destory session");
		}
		
		return new ModelAndView(new RedirectView("./logoutSuccess.html"));
		//return "redirect:./logoutSuccess.html";
	}
	
}
