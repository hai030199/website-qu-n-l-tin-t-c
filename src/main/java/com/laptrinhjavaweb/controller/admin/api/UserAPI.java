package com.laptrinhjavaweb.controller.admin.api;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.laptrinhjavaweb.model.NewModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.INewService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;
@WebServlet(urlPatterns = { "/api-admin-user" })
public class UserAPI extends HttpServlet{
	/**
	 * 
	 */
	@Inject
	private IUserService userService;
	
	
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel updateUser = HttpUtil.of(req.getReader()).toModel(UserModel.class);
		updateUser.setModifiedby(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		updateUser=userService.update(updateUser);
		mapper.writeValue(resp.getOutputStream(), updateUser);	
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
		userModel.setCreatedby(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		userModel = userService.save(userModel);
		mapper.writeValue(resp.getOutputStream(), userModel);
	}
	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		UserModel userModel = HttpUtil.of(req.getReader()).toModel(UserModel.class);
		userService.delete(userModel.getIds());
		mapper.writeValue(resp.getOutputStream(), "{ }");
	}

}
