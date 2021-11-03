<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@include file="/common/taglib.jsp"%>
<c:url var="APIurl" value="/api-admin-role"/>
<c:url var ="RoleURL" value="/admin-role"/>
<html>
<head>
<title>Chỉnh sửa Vai trò</title>
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
					<li class="active">Chỉnh sửa vai trò</li>
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
								<label class="col-sm-3 control-label no-padding-right">Vai
									trò</label>
								
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Tên vai trò</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="name" name="name"
										value="${model.name}" />
								</div>
							</div>
							<br /> <br />
							<div class="form-group">
								<label class="col-sm-3 control-label no-padding-right">Mã vai trò</label>
								<div class="col-sm-9">
									<input type="text" class="form-control" id="code"
										name="code" value="${model.code}" />
								</div>
							</div>
											<div class="form-group">
								<div class="col-sm-12">
									<c:if test="${not empty model.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold"
											value="Cập nhật vai trò" id="btnAddOrUpdateRole" />
									</c:if>
									<c:if test="${empty model.id}">
										<input type="button"
											class="btn btn-white btn-warning btn-bold"
											value="Thêm vai trò" id="btnAddOrUpdateRole" />
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
		
		$('#btnAddOrUpdateRole').click(function(e) {
			e.preventDefault();
			var data = {};
			var fromData = $('#formSubmit').serializeArray();
			$.each(fromData, function(i, v) {
				data["" + v.name + ""] = v.value;

			});
			var id = $('#id').val();
			if (id == "") {
				AddRole(data);
			} else {
				UpdateRole(data);

			}

		});

		function AddRole(data) {
			$.ajax({
				url : '${APIurl}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					 window.location.href = "${RoleURL}?type=edit&id="+result.id+"&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${RoleURL}?type=list&message=error_system";
				}
			});
		}
		function UpdateRole(data) {
			$.ajax({
				url : '${APIurl}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) {
					  window.location.href = "${RoleURL}?type=edit&id="+result.id+"&message=update_success";
				},
				error : function(error) {
					window.location.href = "${RoleURL}?type=list&message=error_system";
				}
			});
		}
	</script>
</body>
</html>
