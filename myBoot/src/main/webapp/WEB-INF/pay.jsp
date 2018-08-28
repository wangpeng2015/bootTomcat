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
	<div class="container content">
		<div class="row">
			<div class="col-lg-12">
                <a class="navbar-brand">
                    小小播会员充值
                </a>
            </div>
			<div class="divider"></divstatic
                <div class="col-sm-3"></div>
			<div class="col-sm-6 custom-form-style">
				<!--h1 class="centered" style="color: black;">Firecode.io is in Private Beta. <a href="/pages/landing">Request an invite</a> to get started!</h1-->
				<p>&nbsp;</p>
				<!-- Sign in panel -->
				  <form action="/wechatPayController/wechat_pay" method="post">
					<div class="form-group">
						<label class="custom-label" >用户名</label>
						<input
							autofocus="autofocus" class="form-control" id="userPhoneNumber" name="userPhoneNumber"
							placeholder="电话"/>
					</div>
					<div class="form-group">
						<label class="custom-label">金额</label>
						<%--<select class="form-control"  name="money" id="money">
							<option value="1800">18元/月</option>
							<option value="18800">188/永久</option>
						</select>--%>
						<select class="form-control" name="money" id="money">
							<option value="1">18元/月</option>
							<option value="2">188/永久</option>
						</select>
					</div>
					<div class="form-group">
						<input class="btn btn-success btn-lg btn-block"
							   type="submit" id="BtnSubmit" value="充值" onclick="return check(this.from)"/>
					</div>
				  </form>
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
    function check(){
        var phone=$("#userPhoneNumber").val();
        if(phone.trim().length!=11){
            alert("请输入正确手机号");
            return false;
        }
        var ddd=$("#money").val();
        if(ddd==""){
            alert("请选择充值金额");
            return false;
        }
    }

    (function(){
        $("#userPhoneNumber").bind("input propertychange",function(event){
            var txt=$(this).val();
            if(txt.length==11){
                $.ajax({
                    type:"post",
                    url:"/userController/checkoutUser",
                    data:{
                        "phoneNumber":$("#userPhoneNumber").val(),
                    },
                    success:function(data){
                        var code=data.code;
                        var message=data.message;
                        if(code=="200"){
                            alert("您已经注册小小播，请放心充值");
                        }else if(code=="300"){
                            alert("抱歉，您还不是系统会员，请先注册");
                        }else{
                            alert("系统错误");
                        }
                    },
                    error:function(){
                        alert("后台错误");
                    },
                    complete:function(){
                        console.log("complete");
                    }
                });
            }
        });
    })()
</script>
</html>
