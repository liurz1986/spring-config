package com.liurz.filter;
import java.util.List;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import com.alibaba.druid.util.StringUtils;
import com.liurz.controller.LoginController;
import com.liurz.entity.UserLogin;

/**
 * 用户session监听
 * 主要是进行session创建和销毁
 * @author Administrator
 *
 */
public class UserSessionListener implements HttpSessionListener, HttpSessionAttributeListener  {
  

	public void attributeAdded(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {
		// TODO Auto-generated method stub
		
	}
    //创建seesion，调用的方法
	public void sessionCreated(HttpSessionEvent se) {
		// TODO Auto-generated method stub  秒
		se.getSession().setMaxInactiveInterval(60*30);//会话保持时间超过30分钟，超时会调用session销毁方法
	}

	/**
	 * session的销毁：
	 * 1.LoginController中登出中，执行request.getSession().invalidate()。就会调用session销毁方法
	 * 2.设置session超时，超时后会直接调用该session销毁方法
	 * 
	 */
	public void sessionDestroyed(HttpSessionEvent se) {
		String sessionId=se.getSession().getId();
		List<UserLogin> userLogins=LoginController.logonAccounts;
		if(null!=userLogins){
			for(UserLogin userLogin:userLogins){
		        if(!StringUtils.isEmpty(sessionId)){
		        	if(sessionId.equalsIgnoreCase(userLogin.getSessionId())){
		        		userLogins.remove(userLogin);//移除登录用户记录中
		        	}
		        }
			}
		}	
	}	
}
