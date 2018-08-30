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
				小小播会员充值,请输入手机号码，检测是否成功
			</a>
		</div>
		<div class="divider"></divstatic
			<div class="col-sm-3"></div>
			<div class="col-sm-6 custom-form-style">
				<!--h1 class="centered" style="color: black;">Firecode.io is in Private Beta. <a href="/pages/landing">Request an invite</a> to get started!</h1-->
				<p>&nbsp;</p>
				<!-- Sign in panel -->
				<div class="form-group">
					<input
							autofocus="autofocus" class="form-control" id="userPhoneNumber" name="userPhoneNumber"
							placeholder="电话" maxlength="11"/>
					<input
							class="form-control" id="expiredTime" name="expiredTime"
							placeholder="到期时间"/>
				</div>
				<div class="form-group">
					<input class="btn btn-success btn-lg btn-block"
						   type="button" id="BtnSubmit" value="检测" onclick="return check(this.from)"/>
				</div>
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
    (function () {
        $("#BtnSubmit").on("click", function () {
            $.ajax({
                type: "post",
                url: "/userController/getUserExpiredTime",
                data: {
                    "phoneNumber": $("#userPhoneNumber").val(),
                },
                success: function (data) {
                    var code = data.code;
                    var result = data.result;
                    if (code == "200") {
                        $("#expiredTime").val("到期时间:" + result);
                    } else if (code == "300") {
                        $("#expiredTime").val("没有查询到该用户信息，请先注册");
                    } else {
                        $("#expiredTime").val("系统异常");
                    }
                },
                error: function () {
                    alert("后台错误");
                },
                complete: function () {
                    console.log("complete");
                }
            });
        })

    })()
</script>
</html>
