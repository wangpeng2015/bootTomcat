<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort() + path + "/";
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
		<h3>订单管理列表</h3>
		<button type="button" class="btn btn-primary" style="float:right;margin-bottom:10px;" onclick="addOrder()">添加订单</button>
		<div class="table-responsive">
		<table class="table table-striped">
			<thead>
				<tr>
					<td>单据名称</td>
					<td>单据价格</td>
					<td>所在门店</td>
					<td>发起人</td>
					<td>处理人</td>
					<td>客户电话</td>
					<td>是否审核</td>
					<td>单据是否正常</td>
					<td>单据处理时间</td>
					<td>单据创建时间</td>
					
				</tr>
			</thead>
			<tbody id="orderTable_tbody">
			</tbody>
		</table>
		</div>
	</div>
	
	<div class="modal fade" id="addOrder" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">添加订单</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">单据名称:</label>
	            <input type="text" class="form-control" id="order_addorderName">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">单据信息:</label>
	            <input type="text" class="form-control" id="order_ordermessage">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">单据价格:</label>
	            <input type="text" class="form-control" id="order_price">
	          </div>
	          <!-- <div class="form-group">
	            <label for="recipient-name" class="control-label">申请人:</label>
	            <input type="text" class="form-control" id="order_application">
	          </div> -->
	          <div class="form-group" style="display:none">
	            <label for="recipient-name" class="control-label">申请人的id:</label>
	            <input type="text" class="form-control" id="order_application_id" value="${sessionScope._user_id}">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">是否是会员:</label>
	            <select class="form-control" id="order_isVip">
				  <option value="1">是</option>
				  <option value="0">否</option>
				</select>
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">客户姓名:</label>
	            <input type="text" class="form-control" id="order_customerName">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">客户电话:</label>
	            <input type="text" class="form-control" id="order_customerphoneNumber">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">门店:</label>
	            <select class="form-control" id="order_selectStore">
				  <option value="001">空中花园</option>
				  <option value="002">蓝山理发</option>
				  <option value="003">雅婷阁</option>
				  <option value="008">华轩造型沙龙</option>
				</select>
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="saveOrder">保存</button>
	      </div>
	    </div>
	  </div>
	</div>
	
	<!-- <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
	  <div class="modal-dialog" role="document">
	    <div class="modal-content">
	      <div class="modal-header">
	        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
	        <h4 class="modal-title" id="exampleModalLabel">订单信息修改</h4>
	      </div>
	      <div class="modal-body">
	        <form>
	          <div class="form-group" style="display:none">
	            <label for="recipient-name" class="control-label">id</label>
	            <input type="text" class="form-control" id="customer_id">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">单据名称:</label>
	            <input type="text" class="form-control" id="order_Name">
	          </div>
	          <div class="form-group">
	            <label for="recipient-name" class="control-label">单据价格:</label>
	            <input type="text" class="form-control" id="order_price">
	          </div>
	          <div class="form-group">
	            <label for="message-text" class="control-label">客户电话:</label>
	            <input type="text" class="form-control" id="order_phoneNumber">
	          </div>
	        </form>
	      </div>
	      <div class="modal-footer">
	        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
	        <button type="button" class="btn btn-primary" id="orderModify">修改</button>
	      </div>
	    </div>
	  </div>
	</div> -->
</body>
<script type="text/javascript">
(function(){
	
	loadOrderList();
	
	$("#saveOrder").on("click",function(){
		if($("#order_price").val()==""){
			alert("请输入单据价格");
		}
		$.ajax({
			type:"post",
			url:"orderController/doRequestSaveOrder",
			async:false,
			data:{
				danju_name:$("#order_addorderName").val(),
				danju_message:$("#order_ordermessage").val(),
				danju_price:$("#order_price").val(),
				danju_applicant:$("#order_application").val(),
				danju_applicant_id:$("#order_application_id").val(),
				danju_isVip:$("#order_isVip").val(),
				customer_name:$("#order_customerName").val(),
				customer_phone:$("#order_customerphoneNumber").val(),
				danju_storesCode:$("#order_selectStore").val()
			},
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200){
					 $('#addOrder').modal('hide');
					 loadOrderList();
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

function loadOrderList(){
	$.ajax({
		type:"post",
		url:"orderController/doGetOrderList",
		async:false,
		success:function(data){
			 var resJson=JSON.parse(data)
			 var resCode=resJson.resultCode;
			 var resBean=resJson.resultBean;
			 var resMessage=resJson.message;
			 if(resCode==200){
				 loadOrdersList(resBean);
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

function loadOrdersList(resBean){
	$("#orderTable_tbody").empty();
	var html="";
	for(var i=0;i<resBean.length;i++){
		if(resBean[i].danju_invalid=="1"){
			str="作废";
		}else{
			str="正常";  
		}
		html+="<tr><td>"+resBean[i].danju_name+"</td><td>"+resBean[i].danju_price+"</td><td>"+resBean[i].danju_storesCode+"</td><td>"+resBean[i].danju_applicant+"</td><td>"+resBean[i].danju_deal+"</td><td>"+resBean[i].customer_phone+"</td><td>"+resBean[i].danju_isConfirm+"</td>"+
		"<td style='color:red'>"+str+"</td><td>"+resBean[i].danju_dealTime+"</td><td>"+resBean[i].danju_createTime+"</td><td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='modifyOrder("+resBean[i].phoneNumber+")'>修改</button></td><td style='color:red' ><button type='button' class='btn btn-primary' onclick='invalidOrder("+resBean[i].danju_id+")'>作废</button></td><td style='color:green'><button type='button' class='btn btn-primary' onclick='shenHe("+resBean[i].danju_id+")'>审核</button></td></tr>";
	}
	$("#orderTable_tbody").append(html);
}

/**
 * 作废订单 
 */
function invalidOrder(danju_id){
	console.log("danju_id="+danju_id);
	var r=confirm("是否确认作废此订单？");
	if(r==true){
		$.ajax({
			type:"post",
			url:"orderController/doInvalidOrder",
			async:false,
			data:{
				danju_id:danju_id
			},
			success:function(data){
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200){
					 loadOrderList();
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
 * 添加订单 
 */
function addOrder(){
	$("#addOrder").modal('show');
}

/**
 * 审核
 */
function shenHe(danju_id){
	console.log("审核="+danju_id);
	//取出登录人的id信息 
	var userINFO=JSON.parse(sessionStorage.getItem("userInfo"));
	var r=confirm("是否确认审核此订单？"); 
	if(r==true){
		$.ajax({
			type:"post",
			url:"orderController/doDealOrder",
			async:false,
			data:{
				danju_id:danju_id
			},
			success:function(data){
				 debugger
				 var resJson=JSON.parse(data)
				 var resCode=resJson.resultCode;
				 var resBean=resJson.resultBean;
				 var resMessage=resJson.message;
				 if(resCode==200 && resBean==1){
					 loadOrderList();
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
</script>
</html>