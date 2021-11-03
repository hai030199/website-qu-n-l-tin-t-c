<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-user" />
<c:url var="UserURL" value="/admin-user" />
<html>
<head>
<title>Chỉnh sửa Tài khoản</title>
</head>
<body>
	<div class="main-content">
		<div class="main-content-inner">
			<div class="breadcrumbs" id="breadcrumbs">
				<script type="text/javascript">
					try {
						ace.settings.check('breadcrumbs', 'fixed')
					} catch (e) {
					}
				</script>
				<ul class="breadcrumb">
					<li><i class="ace-icon fa fa-home home-icon"></i> <a href="#">Trang
							chủ</a></li>
					<li class="active">Chỉnh sửa tài khoản</li>
				</ul>
				<!-- /.breadcrumb -->
			</div>
			<div class="page-content">
				<div class="row">
					<div class="col-xs-12">
						<c:if test="${not empty messageResponse}">
							<div class="alert alert-${alert}">${messageResponse}</div>
						</c:if>
						<form id="formSubmit">
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"> Vai trò
									</label>
								<div class="col-sm-9">
								
							<select class="form-control" id="roleCode"
										name="roleCode">
										<c:if test="${empty model.roleCode}">
											<option value="">Ch?n lo?i vai trò</option>
											<c:forEach var="item" items="${roles}">
												<option value="${item.code}">${item.name}</option>
											</c:forEach>
										</c:if>
										<c:if test="${not empty model.roleCode}">
											<option value="">Ch?n lo?i vai trò</option>
											<c:forEach var="item" items="${roles}">
												<option value="${item.code}"
													<c:if test="${item.code == model.roleCode}">selected="selected"</c:if>>
													${item.name}</option>
											</c:forEach>
										</c:if>
									</select>
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Tên tài khoản
								</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="userName" name="userName"
										value="${model.userName}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right"> Mật khẩu
									</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="password"
										name="password" value="${model.password}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Họ và tên
									</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="fullName"
										name="fullName" value="${model.fullName}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Trạng thái
									</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="status"
										name="status" value="${model.status}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<div class="col-sm-12">
									<c:if test="${not empty model.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold"
											value="Cập nhật tài khoản" id="btnAddOrUpdateUser" />
									</c:if>
									<c:if test="${empty model.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold"
											value="Thêm tài khoản" id="btnAddOrUpdateUser" />
									</c:if>
								</div>
							</div>
							<input type="hidden" value="${model.id}" id="id" name="id" />
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script>

		$('#btnAddOrUpdateUser').click(function(e) {
			e.preventDefault();
			var data = {};
			var fromData = $('#formSubmit').serializeArray();
			$.each(fromData, function(i, v) {
				data["" + v.name + ""] = v.value;

			});
			var id = $('#id').val();
			if (id == "") {
				AddUser(data);
			} else {
				UpdateUser(data);

			}

		});

		function AddUser(data) {
			$.ajax({
				url : '${APIurl}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					 window.location.href = "${UserURL}?type=edit&id="+result.id+"&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${UserURL}?type=list&message=error_system";
				}
			});
		}
		function UpdateUser(data) {
			$.ajax({
				url : '${APIurl}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					  window.location.href = "${UserURL}?type=edit&id="+result.id+"&message=update_success";
				},
				error : function(error) {
					window.location.href = "${UserURL}?type=list&message=error_system";
				}
			});
		}
	</script>
</body>
</html>
