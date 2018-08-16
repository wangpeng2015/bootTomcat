<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!-- Top menu -->
<nav class="navbar navbar-inverse navbar-no-bg" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#top-navbar-1">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand">
				 狼播后台管理系统
			</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse" id="top-navbar-1">
			<ul class="nav navbar-nav navbar-right">
				<!-- <li><a class="scroll-link" href="">首页</a></li> -->
				<!-- <li><a class="scroll-link" href="employeeController/showEmployeeView">人员管理</a></li>-->
				<li><a class="scroll-link" href="javascript:void(0);" id="WriteOff">注销</a></li> 
			</ul>
		</div>
	</div>
</nav>

<script>
	//提交登录 
	$("#WriteOff").on("click",function(){
		console.log("BtnSubmit---click");
		$.ajax({
			type:"post",
			url:"employeeController/doWriteOff",
			success:function(data){
				window.location.href="http://101.132.168.202:8090/goo/";
			}
		});
	});
</script>