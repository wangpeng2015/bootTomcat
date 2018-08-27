<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>index.jsp</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0, user-scalable=0">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link rel="stylesheet" type="text/css"	href="/static/css/bootstrap.css">
<link rel="stylesheet" type="text/css"	href="/static/css/bootstrap-datetimepicker.min.css">
<script type="text/javascript" src="/static/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/static/js/bootstrap.js"></script>
<script type="text/javascript" src="/static/js/bootstrap-datetimepicker.min.js" charset="UTF-8"></script>
<script type="text/javascript" src="/static/js/bootstrap-datetimepicker.zh-CN.js" charset="UTF-8"></script>
<script type="text/javascript" src="/static/js/pop.js" charset="UTF-8"></script>
</head>

<body>
	<jsp:include page="content/nav.jsp"></jsp:include>
	
	<div class="container content">
		<div class="row">
			<div class="col-lg-12"></div>
			<div class="divider"></divstatic

				<div class="col-sm-3"></div>
			<div class="col-sm-6 custom-form-style">
				<!--h1 class="centered" style="color: black;">Firecode.io is in Private Beta. <a href="/pages/landing">Request an invite</a> to get started!</h1-->
				<p>&nbsp;</p>
				<!-- Sign in panel -->
				<%-- <form accept-charset="UTF-8" action="<%=basePath%>employeeController/doLogin"
					class="new_user" id="new_user" method="post"> --%>
					<div class="form-group">
						<label class="custom-label" >用户名</label> <input
							autofocus="autofocus" class="form-control" id="userName" name="userName"
							placeholder="用户名或者电话"/>
					</div>
					<div class="form-group">
						<label class="custom-label" for="user_password">时间</label>
						   <input	class="form-control" data-parsley-minlength="6"
							data-parsley-required="" data-parsley-trigger="blur"
							id="time" name="time"	type="text" />
					</div>
			</div>
			<div id="search" style="display:none">
				<jsp:include page="content/customerList.jsp"></jsp:include>
			</div>
		</div>
	<!-- Row End -->
	</div>
	<!--/ .container -->
	</div>
	<!--/ #headerwrap -->

	</div>
</body>
<script type="text/javascript">
	(function($,GLOBAL){
		//提交登录
		$("#BtnSubmit").on("click",function(){
			console.log("BtnSubmit---click");
			$.ajax({
				type:"post",
				url:"employeeController/doLogin",
				data:{
					"phoneNumber":$("#userName").val(),
					"passWord":$("#passWord").val(),
					"randomString":$("#captcha").val()
				},
				success:function(data){
					 var resJson=JSON.parse(data);
					 var resCode=resJson.resultCode;
					 var resBean=resJson.resultBean;
					 var resMessage=resJson.message;
					 if(resCode==200){
						 $(".custom-form-style").css("display","none");
						 $("#search").css("display","block");
						 loadCustomer();	
					 }else{
						 alert(resMessage);
					 }
				},
				error:function(){
					alert("后台错误");
				},
				complete:function(){
					console.log("complete");
				}
			});
		});
		
	})(jQuery,window);
</script>
</html>
