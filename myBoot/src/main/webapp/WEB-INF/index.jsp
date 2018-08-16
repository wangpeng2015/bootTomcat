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
						<label class="custom-label" for="user_password">密码</label> 
						   <input	class="form-control" data-parsley-minlength="6"
							data-parsley-required="" data-parsley-trigger="blur"
							id="passWord" name="passWord"	type="password" />
					</div>
					<div class="form-group">
						<label class="custom-label" for="">点击图标更换</label> 
						<img id="captchaImage" src="">
						   <input	class="form-control" data-parsley-minlength="6"
							data-parsley-required="" data-parsley-trigger="blur"
							id="captchaImage" name="captchaImage"	type="text" />
					</div>
					<div class="form-group">
						<input class="btn btn-success btn-lg btn-block" 
							type="button" id="BtnSubmit" value="登录" />
					</div>
					<a class="small" href="#">忘记密码?</a>
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
		var id="${_user_id}";
		if(id){
			$(".custom-form-style").css("display","none");
			 $("#search").css("display","block");
			 loadCustomer();
			 return;
		}
		$('#captchaImage').attr("src", "employeeController/captcha?timestamp=" + (new Date()).valueOf());
		// 更换验证码
		$('#captchaImage').click(function(){
		    $('#captchaImage').attr("src", "employeeController/captcha?timestamp=" + (new Date()).valueOf());
		}); 
		
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
	
	$("#search").on("click","#searchBtn",function(){
		console.log("search");
		/* if($("#customerSearch").val()==""){
			return;
		} */
		$.ajax({
			type:"post",
			url:"employeeController/doFindCustomersList",
			data:{
				"userName":$("#customerSearch").val(),
				"currentPage":1,
				"pageSize":20
			},
			success:function(data){
				 var resJson=JSON.parse(data);
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
	});
	
	$("#navPageNavigation").on("click","a",function(e){
		var ev = ev || window.event;
	    var target = ev.target || ev.srcElement;
		if(target.nodeName=="SPAN"){
			return;
	    }
	    $.ajax({
			type:"post",
			url:"employeeController/doFindCustomersList",
			data:{
				"userName":$("#customerSearch").val(),
				"currentPage":target.innerHTML,
				"pageSize":10
			},
			success:function(data){
				 var resJson=JSON.parse(data);
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
	});
	
	/**
	 * 查询用户信息 
	 */
	function loadCustomer(){
		$.ajax({
			type:"post",
			url:"employeeController/doFindCustomersList",
			data:{
				"currentPage":1,
				"pageSize":10
			},
			success:function(data){
				 var resJson=JSON.parse(data);
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
	
	function modifySaveCustomer(userName,its){
		/* console.log($(its).html()); */
		var date=$(its).parents("tr")[0];
		var endDate=$(date).find("input[name=control]").val();
		Ewin.confirm({ message: "确认要修改?"}).on(function (e) {
			if (!e) {
                return;
            }
			$.ajax({
				type:"post",
				url:"employeeController/updateCustomersDateEnd",
				async:false,
				data:{
					"userName":userName,
					"endDate":endDate
				},
				success:function(data){
					 var resJson=JSON.parse(data);
					 var resCode=resJson.resultCode;
					 var resMessage=resJson.message;
					 if(resCode==200){
						 loadCustomer();
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
	};
	
	//日期加天数的方法
	//dataStr日期字符串
	//dayCount 要增加的天数
	//return 增加n天后的日期字符串
	function dateAddDays(dataStr,dayCount) {
		//var timestampA = Date.parse(new Date());
		var timestampA = new Date().getTime();
		timestampB = timestampA + (dayCount * 24 * 60 * 60 * 1000);
		var newDate = new Date(timestampB);
		return newDate.format('yyyy-MM-dd hh:mm:ss');;
	}
	
	Date.prototype.format = function(format) {
	       var date = {
	              "M+": this.getMonth() + 1,
	              "d+": this.getDate(),
	              "h+": this.getHours(),
	              "m+": this.getMinutes(),
	              "s+": this.getSeconds(),
	              "q+": Math.floor((this.getMonth() + 3) / 3),
	              "S+": this.getMilliseconds()
	       };
	       if (/(y+)/i.test(format)) {
	              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length));
	       }
	       for (var k in date) {
	              if (new RegExp("(" + k + ")").test(format)) {
	                     format = format.replace(RegExp.$1, RegExp.$1.length == 1
	                            ? date[k] : ("00" + date[k]).substr(("" + date[k]).length));
	              }
	       }
	       return format;
	}
	
	function modifySaveCustomerForOneMonth(userName,endDate){
		//var date=$(its).parents("tr")[0];
		//var endDate=$(date).find("input[name=control]").val();
		console.log(endDate);
		endDate=dateAddDays(endDate,30);
		console.log(endDate);
		Ewin.confirm({ message: "确认要修改?"}).on(function (e) {
			if (!e) {
                return;
            }
			$.ajax({
				type:"post",
				url:"employeeController/updateCustomersDateEnd",
				async:false,
				data:{
					"userName":userName,
					"endDate":endDate
				},
				success:function(data){
					 var resJson=JSON.parse(data);
					 var resCode=resJson.resultCode;
					 var resMessage=resJson.message;
					 if(resCode==200){
						 loadCustomer();
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
	}
	
	/**
	 *
	 * 删除 
	 */
	function deleteCustomerByphone(userName){
		Ewin.confirm({ message: "确认要删除用户? "+userName}).on(function (e) {
			if (!e) {
               return;
           }
			$.ajax({
				type:"post",
				url:"employeeController/deleteCustomersByphone", 
				async:false,
				data:{
					"userName":userName
				},
				success:function(data){
					 var resJson=JSON.parse(data);
					 var resCode=resJson.resultCode;
					 var resMessage=resJson.message;
					 if(resCode==200){
						 loadCustomer();
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
	}
	
	/**
	 *重置手机
	 *
	 */
	function resertPhone(userName){
		Ewin.confirm({ message: "确认要重置手机? "+userName}).on(function (e) {
			if (!e) {
               return;
           }
			$.ajax({
				type:"post",
				url:"employeeController/resertPhoneCustomersByphone",
				async:false,
				data:{
					"userName":userName
				},
				success:function(data){
					 var resJson=JSON.parse(data);
					 var resCode=resJson.resultCode;
					 var resMessage=resJson.message;
					 if(resCode==200){
						 loadCustomer();
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
	}
	
	/**
	 *重置密码123456
	 *
	 */
	function resertPasswd(userName){
		Ewin.confirm({ message: "确认要重置密码用户? "+userName}).on(function (e) { 
			if (!e) {
               return;
           }
			$.ajax({
				type:"post",
				url:"employeeController/resertPwdCustomersByphone", 
				async:false,
				data:{
					"userName":userName
				},
				success:function(data){
					 var resJson=JSON.parse(data);
					 var resCode=resJson.resultCode;
					 var resMessage=resJson.message;
					 if(resCode==200){
						 loadCustomer();
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
	}
	
	function loadEmpsList(resBean){
		$("#empTable_tbody").empty();
		var html="";
		var dateHTML="";
		for(var i=0;i<resBean.length;i++){
			dateHTML="<div style='width: 200px' class='input-group date form_datetime col-md-5' data-date-format='yyyy-mm-dd hh:ii:ss' data-link-field='dtp_input1'>"
		        	 +"<input class='form-control' name='control' size='50' type='text' value='"+resBean[i].recharge_money_endDate+"' readonly>"
				   	 +"<span class='input-group-addon'><span class='glyphicon glyphicon-th'></span></span>";
			if(typeof(resBean[i].totalCount)!="undefined"){
				$("#count").html(resBean[i].totalCount);
			}else{
				if(typeof(resBean[i].recharge_money)=="undefined"){
					html+="<tr><td>"+resBean[i].username+"</td><td>"+resBean[i].create_date+"</td><td>0</td><td>"+dateHTML+"</td><td>"+resBean[i].recharge_money_update_Date+"</td><td>"+resBean[i].tuijianCode+"</td><td>"+resBean[i].uid+"</td><td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='modifySaveCustomerForOneMonth(\""+resBean[i].username+"\",\""+resBean[i].recharge_money_endDate+"\")'>修改保存一个月</button><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='modifySaveCustomer(\""+resBean[i].username+"\",this)'>修改保存</button><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='deleteCustomerByphone(\""+resBean[i].username+"\")'>删除</button><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='resertPhone(\""+resBean[i].username+"\")'>重置手机</button><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='resertPasswd(\""+resBean[i].username+"\")'>重置密码123456</button></td></tr>";
				}else{
					html+="<tr><td>"+resBean[i].username+"</td><td>"+resBean[i].create_date+"</td><td>"+resBean[i].recharge_money+"</td><td>"+dateHTML+"</td><td>"+resBean[i].recharge_money_update_Date+"</td><td>"+resBean[i].tuijianCode+"</td><td>"+resBean[i].uid+"</td><td><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='modifySaveCustomerForOneMonth(\""+resBean[i].username+"\",\""+resBean[i].recharge_money_endDate+"\")'>修改保存一个月</button><br/><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='modifySaveCustomer(\""+resBean[i].username+"\",this)'>修改保存</button><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='deleteCustomerByphone(\""+resBean[i].username+"\")'>删除</button><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='resertPhone(\""+resBean[i].username+"\")'>重置手机</button><button type='button' class='btn btn-primary' data-toggle='modal' data-target='#exampleModal' onclick='resertPasswd(\""+resBean[i].username+"\")'>重置密码123456</button></td></tr>";
				}
			}
		}
		
		$("#empTable_tbody").append(html);
		$('.form_datetime').datetimepicker({
			language:  'zh-CN',
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			forceParse: 0,
		    showMeridian: 1
		});
	}
</script>
</html>
