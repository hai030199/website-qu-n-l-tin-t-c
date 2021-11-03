package com.laptrinhjavaweb.utils;

import javax.servlet.http.HttpServletRequest;

public class MessageUtil {
	public static void showMessage(HttpServletRequest request) {
		String messageResponse ="";
		String alert="";
		String message=request.getParameter("message");
		if(request.getParameter("message") !=null)
		{

			if(message.equals("insert_success"))
			{
				messageResponse = "insert success";
				alert = "success";
			} else if(message.equals("update_success"))
			{
				messageResponse = "update success";
				alert = "success";
			}else if(message.equals("delete_success"))
			{
				messageResponse = "delete success";
				alert = "success";
			}else if(message.equals("error_system"))
			{
				messageResponse = "error system";
				alert = "danger";
			}
			request.setAttribute("messageResponse", messageResponse);
			request.setAttribute("alert", alert);
		}else 
		{
			messageResponse = "error permission";
			alert = "danger";
		}
		}

}
