<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>index.jsp</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css"
	href="./bootstrap-3.3.5-dist/css/bootstrap.css">

<script type="text/javascript" src="./jquery/jquery-3.1.0.min.js"></script>
<script type="text/javascript"
	src="./bootstrap-3.3.5-dist/js/bootstrap.js"></script>
</head>
<body>
	<jsp:include page="nav.jsp"></jsp:include>
	<div class="container content">
		<h3>员工管理列表</h3>
		<button type="button" class="btn btn-primary" style="float:right;margin-bottom:10px" onclick="addEmp()">添加员工</button>
		<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<td>姓名</td>
					<td>电话</td>
					<td>所在门店</td>
					<td>创建时间</td>
					<td>修改</td>
					<td>删除</td>
				</tr>
			</thead>
			<tbody id="empTable_tbody">
			</tbody>
		</table>
		</div>
	</div>
	
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">员工信息修改</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group" style="display:none">
	            <label for="recipient-name" class="control-label">id</label>
	            <input type="text" class="form-control" id="emp_id">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">姓名:</label>
	            <input type="text" class="form-control" id="emp_userName">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">电话:</label>
	            <!-- <textarea class="form-control" id="emp_phoneNumber"></textarea> -->
	            <input type="text" class="form-control" id="emp_phoneNumber">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="modify">修改</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<div class="modal fade" id="addModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">添加员工</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">姓名:</label>
	            <input type="text" class="form-control" id="emp_adduserName">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">编码:</label>
	            <input type="text" class="form-control" id="emp_adduserCode">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">密码:</label>
	            <input type="text" class="form-control" id="emp_addpassword">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">确认密码:</label>
	            <input type="text" class="form-control" id="emp_addconfirmpassword">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">角色:</label>
	            <!-- <input type="text" class="form-control" id="emp_addRoles"> -->
	            <select class="form-control" id="emp_addRoles">
	            	<option value="1">店员</option>
				    <option value="2">店长</option>
				</select>
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">门店:</label>
	            <select class="form-control" id="selectStore">
				  <option value="001">空中花园</option>
				  <option value="002">蓝山理发</option>
				  <option value="003">雅婷阁</option>
				  <option value="008">华轩造型沙龙</option>
				</select>
	          </div>
	          <!-- <div class="form-group">
	            <label for="recipient-name" class="control-label">身份证号:</label>
	            <input type="text" class="form-control" id="emp_addIDCard">
	          </div> -->
	          <div class="form-group">
	            <label for="message-text" class="control-label">电话:</label>
	            <!-- <textarea class="form-control" id="emp_phoneNumber"></textarea> -->
	            <input type="text" class="form-control" id="emp_addphoneNumber">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="saveEmp">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	
</body>
<script type="text/javascript">
(function(){
	
	loadEmpList();
	
	/**
	 *点击修改之后传递id进行信息修改
	 */
	$("#modify").on("click",function(){
		console.log("modify");
		$.ajax({
			type:"post",
			url:"employeeController/doUpdateUserById",
			data:{
				id:$("#emp_adduserName").val(),
				userName:$("#emp_userName").val(),
				phoneNumber:$("#emp_phoneNumber").val()
			},
			async:false,
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200){
					 $('#exampleModal').modal('hide');
					 loadEmpList();
				 }else{
					 //alert(resMessage);
				 }
			},
			error:function(){
				console.log("error");
			},
			complete:function(){
				console.log("complete");
			}
		});
	});
	
	
	$("#saveEmp").on("click",function(){
		console.log("保存");
		if($("#emp_addpassword").val()!=$("#emp_addconfirmpassword").val()){
			alert("密码不一致");
			return;
		}
		alert($("#selectStore").find("option:selected").text());
		$.ajax({
			type:"post",
			url:"employeeController/doSave",
			data:{
				userName:$("#emp_adduserName").val(),
				userCode:$("#emp_adduserCode").val(),
				passWord:$("#emp_addpassword").val(),
				roles:$("#emp_addRoles").val(),
				storeName:$("#selectStore").find("option:selected").text(),
				storeCode:$("#selectStore").val(),
				//idCard:$("#emp_addIDCard").val(),
				phoneNumber:$("#emp_addphoneNumber").val()
			},
			async:false,
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200){
					 $('#addModal').modal('hide')
					 loadEmpList();
				 }else{
					 alert(resMessage);
				 }
			},
			error:function(){
				console.log("error");
			},
			complete:function(){
				console.log("complete");
			}
		});
	});
	
})()

function loadEmpList(){
	$.ajax({
		type:"post",
		url:"employeeController/doFindEmpsList",
		async:false,
		success:function(data){
			 var resJson=JSON.parse(data)
			 var resCode=resJson.resultCode;
			 var resBean=resJson.resultBean;
			 var resMessage=resJson.message;
			 if(resCode==200){
				 loadEmpsList(resBean);
			 }else{
				 alert(resMessage);
			 }
		},
		error:function(){
			console.log("error");
		},
		complete:function(){
			console.log("complete");
		}
	});
}

/**
 *  成功之后添加人员列表
 */
function loadEmpsList(resBean){
	$("#empTable_tbody").empty();
	var html="";
	for(var i=0;i<resBean.length;i++){
		html+="<tr><td>"+resBean[i].userName+"</td><td>"+resBean[i].phoneNumber+"</td><td>"+resBean[i].storeName+"</td><td>"+resBean[i].createTime+"</td><td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='modifyEmp("+resBean[i].phoneNumber+")'>修改</button></td><td><button type='button' class='btn btn-primary' data-toggle='modal' onclick='deleteEmp("+resBean[i].id+")'>删除</button></td></tr>";
	}
	$("#empTable_tbody").append(html);
}

/**
 * 根据id删除店内人员 
 */
function deleteEmp(id){
	console.log("delete="+id);
	var r=confirm("是否确认删除此员工？");
	if(r==true){
		$.ajax({
			type:"post",
			url:"employeeController/doDelete",
			async:false,
			data:{
				id:id
			},
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200 && resBean==1){
					 loadEmpList();
				 }else{
					 alert(resMessage);
				 }
			},
			error:function(){
				console.log("error");
			},
			complete:function(){
				console.log("complete");
			}
		});
	}
}

/**
 * 修改店内人员信息
 */
function modifyEmp(phoneNumber){
   console.log("modifyEmp="+phoneNumber);
	 
   /**
   	*根据电话查询出这个用户
   	*/
   $.ajax({
		type:"post",
		url:"employeeController/doSearchUserByphoneOrName",
		async:false,
		data:{
			phoneNumber:phoneNumber
		},
		success:function(data){
			 var resJson=JSON.parse(data)
			 var resCode=resJson.resultCode;
			 var resBean=resJson.resultBean;
			 var resMessage=resJson.message;
			 if(resCode==200){
				 $("#emp_id").val(resBean[0].id);
				 $("#emp_userName").val(resBean[0].userName);
				 $("#emp_phoneNumber").val(resBean[0].phoneNumber);
			 }else{
				 alert(resMessage);
			 }
		},
		error:function(){
			console.log("error");
		},
		complete:function(){
			console.log("complete");
		}
	});
}

/**
 * 添加店员 
 */
function addEmp(){
	 $('#addModal').modal('show');
}
</script>
</html>