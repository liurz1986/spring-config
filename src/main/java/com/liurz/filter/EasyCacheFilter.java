package com.liurz.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 静�?�资源缓�?
 * @author Administrator
 *
 */
public class EasyCacheFilter implements Filter {
	 private FilterConfig filterConfig;
	    
	    public void init(FilterConfig filterConfig) throws ServletException {
	        System.out.println("----过滤器初始化----");
	        this.filterConfig = filterConfig;
	    }

	    //过滤器功能在这里实现
	    
	    public void doFilter(ServletRequest req, ServletResponse resp,
	            FilterChain chain) throws IOException, ServletException {
	        HttpServletRequest request = (HttpServletRequest) req;
	        HttpServletResponse response = (HttpServletResponse) resp;

	        //1.获取用户想访问的资源
	        String uri = request.getRequestURI(); 
	        //2.得到用户想访问的资源的后�?�?
	        String ext = uri.substring(uri.lastIndexOf(".")+1); 
	        //得到资源�?要缓存的时间
	        String time = filterConfig.getInitParameter(ext);
	        if(time!=null){
	            long t = Long.parseLong(time)*3600*1000;
	            //设置浏览器缓�?
	            response.setDateHeader("expires", System.currentTimeMillis() + t);
	        } 
	        chain.doFilter(request, response);
	    }

	    
	    public void destroy() {
	        System.out.println("----过滤器销�?----");
	    }
	

}
