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
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.service.IRoleService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.FormUtil;
import com.laptrinhjavaweb.utils.MessageUtil;
import com.laptrinhjavaweb.utils.SessionUtil;

@WebServlet(urlPatterns = {"/admin-user"})
public class UserController extends HttpServlet{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private IUserService userService;
	@Inject
	private IRoleService roleService;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		UserModel model = FormUtil.toModel(UserModel.class, req);
		UserModel userModelss=(UserModel) (SessionUtil.getInstance().getValue(req, "USERMODEL"));
	
		String view = "";
		if(userModelss.getRole().getCode().equals(SystemConstant.ADMIN_T)) {
		if (model.getType().equals(SystemConstant.LIST))

		{
			model.setListResult(userService.findAll());
			view = "/views/admin/user/list.jsp";
			
	
		} else if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = userService.findOne(model.getId());
			}
			req.setAttribute("roles", roleService.findAll());
			view = "/views/admin/user/edit.jsp";
			

		}
		}
	else if (userModelss.getRole().getCode().equals(SystemConstant.ADMIN)) {
		model.setListResult(userService.findByRoleId(2));
		view = "/views/admin/user/list.jsp";
		if (model.getType().equals(SystemConstant.EDIT)) {
			if (model.getId() != null) {
				model = userService.findOne(model.getId());
			}
			//req.setAttribute("roles", roleService.findOneByCode("USER"));
			req.setAttribute("roles", roleService.findAll());
			view = "/views/admin/user/edit.jsp";
		}
		
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
