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
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.utils.HttpUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/api-admin-role" })
public class RoleAPI extends HttpServlet {
	@Inject
	private IRoleService roleService;

	private static final long serialVersionUID = -915988021506484384L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RoleModel roleModel = HttpUtil.of(req.getReader()).toModel(RoleModel.class);
		roleModel.setCreatedby(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());
		roleModel = roleService.save(roleModel);
		mapper.writeValue(resp.getOutputStream(), roleModel);
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();

		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RoleModel roleModel = HttpUtil.of(req.getReader()).toModel(RoleModel.class);
		roleService.delete(roleModel.getIds());
		mapper.writeValue(resp.getOutputStream(), "{ }");
		
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ObjectMapper mapper = new ObjectMapper();
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("application/json");
		RoleModel updateModel = HttpUtil.of(req.getReader()).toModel(RoleModel.class);
		updateModel.setModifiedby(((UserModel)SessionUtil.getInstance().getValue(req, "USERMODEL")).getUserName());	
		updateModel=roleService.update(updateModel);
		mapper.writeValue(resp.getOutputStream(), updateModel);	
		
		
		
		
	}
}
