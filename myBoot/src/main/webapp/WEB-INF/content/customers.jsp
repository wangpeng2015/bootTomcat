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
		<!-- <h3>客户管理列表</h3>
		<div class="input-group" style="left:8%;width:450px;" >
	      <input type="text" class="form-control" placeholder="根据电话或者姓名查询用户信息" id="searchText">
	      <span class="input-group-btn">
	        <button class="btn btn-default" type="button" onclick="searchCustomer()">查询</button>
	      </span>
	    </div>/input-group
		<div style="width:60%">
		<table class="table table-striped">
			<thead>
				<tr>
					<td>姓名</td>
					<td>电话</td>
					<td>金额</td>
					<td>所在门店</td>
					<td>创建时间</td>
					<td>修改</td>
					<td>删除</td>
				</tr>
			</thead>
			<tbody id="customerTable_tbody">
				
			</tbody>
		</table>
		</div> -->
		
		 <div class="panel panel-default">
	      <!-- Default panel contents -->
	      <div class="panel-heading">客户管理</div>
	      <div class="panel-body">
	        <button type="button" class="btn btn-primary"  style="float:left;" onclick="addCustomer()">添加顾客</button>
			<button type="button" class="btn btn-primary" style="float:left;margin-left:10px;" onclick="addCustomerMoney()">充值</button>
	      </div>
	
	      <!-- Table -->
	      <table class="table">
	        <thead>
	          <tr>
	            <th>#</th>
	            <th>姓名</th>
	            <th>电话</th>
	            <th>金额</th>
	          </tr>
	        </thead>
	        <tbody id="customerTable_tbody">
	          <!-- <tr>
	            <th scope="row">1</th>
	            <td>Mark</td>
	            <td>Otto</td>
	            <td>@mdo</td>
	          </tr>
	          <tr>
	            <th scope="row">2</th>
	            <td>Jacob</td>
	            <td>Thornton</td>
	            <td>@fat</td>
	          </tr>
	          <tr>
	            <th scope="row">3</th>
	            <td>Larry</td>
	            <td>the Bird</td>
	            <td>@twitter</td>
	          </tr> -->
	        </tbody>
	      </table>
	      </div>
	</div>
	
	<!--修改  -->
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">客户信息修改</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group" style="display:none">
	            <label for="recipient-name" class="control-label">id</label>
	            <input type="text" class="form-control" id="customer_id">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">姓名:</label>
	            <input type="text" class="form-control" id="customer_userName">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">电话:</label>
	            <input type="text" class="form-control" id="customer_phoneNumber">
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
	
	<!--充值  -->
	<div class="modal fade" id="suctomerCZ" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">客户金额充值</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">金额:</label>
	            <input type="text" class="form-control" id="customer_money">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">电话:</label>
	            <input type="text" class="form-control" id="customer_moneyphoneNumberr">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="addCustomerMoney">充值</button>
	      </div>
	    </div>
	  </div>
	</div>
</body>
<script type="text/javascript">
(function(){
	
	loadCustomerList();
	
	/**
	 *点击修改之后传递id进行信息修改
	 */
	$("#modify").on("click",function(){
		$.ajax({
			type:"post",
			url:"customerController/doUpdateCustomerById",
			data:{
				customer_id:$("#customer_id").val(),
				customer_name:$("#customer_userName").val(),
				customer_phoneNumber:$("#customer_phoneNumber").val()
			},
			async:false,
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200){
					 $('#exampleModal').modal('hide');
					 loadCustomerList();
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
	
	
	$("#addCustomerMoney").on("click",function(){
		$.ajax({
			type:"post",
			url:"customerController/doUpdateCustomerByPhone2", 
			async:false,
			data:{
				money:$("#customer_money").val(),
				customer_phoneNumber:$("#customer_moneyphoneNumberr").val()
			},
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200){
					 $('#suctomerCZ').modal('hide');
					 loadCustomerList();
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
/**
 * 加载顾客列表 
 */
function loadCustomerList(){
	$.ajax({
		type:"post",
		url:"customerController/dofindCustomerByStoreCode",
		async:false,
		success:function(data){
			 var resJson=JSON.parse(data)
			 var resCode=resJson.resultCode;
			 var resBean=resJson.resultBean;
			 var resMessage=resJson.message;
			 if(resCode==200){
				 loadCustomersList(resBean);
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
 * 成功之后加载顾客信息 
 */
function loadCustomersList(resBean){
	$("#customerTable_tbody").empty();
	var html="";
	for(var i=0;i<resBean.length;i++){
		//var res=i%2;
		html+="<tr><th scope=row'>"+i+"</th><td>"+resBean[i].customer_name+"</td><td>"+resBean[i].customer_phoneNumber+"</td><td>"+resBean[i].customer_money+"</td><td style='display:none'>"+resBean[i].customer_storeCode+"</td><td style='display:none'>"+resBean[i].createTime+"</td><td style='display:none'><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='modifyCustomer("+resBean[i].customer_phoneNumber+")'>修改</button></td><td style='color:red;display:none' onclick='deleteCustomer("+resBean[i].customer_phoneNumber+")'>删除</td></tr>";
	}
	$("#customerTable_tbody").append(html);
}

/**
 * 根据电话删除顾客信息 
 *
 */
function deleteCustomer(phoneNumer){
	console.log("phoneNumer="+phoneNumer);
	var r=confirm("是否确认删除此客户？");
	if(r==true){
		$.ajax({
			type:"post",
			url:"customerController/doDelCustomerByPhone",
			async:false,
			data:{
				phoneNumber:phoneNumer
			},
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200 && resBean==1){
					 loadCustomerList();
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


function modifyCustomer(phoneNumber){
	  /**
   	   *根据电话查询出这个用户
   	   */
	   $.ajax({
			type:"post",
			url:"customerController/dofindCustomerList",
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
					 $("#customer_id").val(resBean[0].customer_id);
					 $("#customer_userName").val(resBean[0].customer_name);
					 $("#customer_phoneNumber").val(resBean[0].customer_phoneNumber);
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
 * 充值
 */
function addCustomerMoney(){
	$('#suctomerCZ').modal('show');
}

/**
 * 查询
 */
 
function searchCustomer(){
	var searchText=$("#searchText").val();
	if(searchText=="" || typeof(searchText)=="undefined"){
		alert("查询条件不能为空")
		return;
	}
	
	$.ajax({
		type:"post",
		url:"customerController/dofindCustomerList",
		async:false,
		data:{
			phoneNumber:searchText,
			customerName:searchText
		},
		success:function(data){
			 var resJson=JSON.parse(data)
			 var resCode=resJson.resultCode;
			 var resBean=resJson.resultBean;
			 var resMessage=resJson.message;
			 if(resCode==200){
				 loadCustomersList(resBean);
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
 * 添加顾客 
 */
/* function addCustomer(){
	
} */
</script>
</html>