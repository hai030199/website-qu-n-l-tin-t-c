package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class SessionUtil {
	private static SessionUtil sessionUtil = null;

	public static SessionUtil getInstance() {
		if (sessionUtil == null) {
			 sessionUtil = new SessionUtil();
		}
		return sessionUtil;
	}

	public void putValue(HttpServletRequest request, String key, Object Value) {
		request.getSession().setAttribute(key, Value);//lưu thông tin đang nhập của user
		
	}

	public Object getValue(HttpServletRequest request, String key) {
		return request.getSession().getAttribute(key);//lấy ra thông tin đang nhập của user
	}

	public void removeValue(HttpServletRequest request, String key) {
		request.getSession().removeAttribute(key);//remove thông tin đang nhập của user
	}

}
