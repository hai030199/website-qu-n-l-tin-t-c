package com.laptrinhjavaweb.controller.admin;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.model.RoleModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = { "/admin-role" })
public class RoleController extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IRoleService roleService;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		RoleModel model = FormUtil.toModel(RoleModel.class, req);
		UserModel userModel=(UserModel) (SessionUtil.getInstance().getValue(req, "USERMODEL"));
		
		String view = "";
		if(userModel.getRole().getCode().equals(SystemConstant.ADMIN_T)) {
		if (model.getType().equals(SystemConstant.LIST))

		{
			model.setListResult(roleService.findAll());
			view = "/views/admin/role/list.jsp";
			
	
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = roleService.findOne(model.getId());
			}
			req.setAttribute("roles", roleService.findAll());
			view = "/views/admin/role/edit.jsp";
			

		}
		}
	else if (userModel.getRole().getCode().equals(SystemConstant.ADMIN)) {
		view = "/views/admin/home.jsp";
			}
		else {
			view = "/views/admin/home.jsp";
		}
		MessageUtil.showMessage(req);
		req.setAttribute(SystemConstant.MODEL, model);
		RequestDispatcher rd = req.getRequestDispatcher(view);
		rd.forward(req, resp);
	}
	

}
