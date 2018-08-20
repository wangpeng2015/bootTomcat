<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
			<form id="upload" method="post" action="/mp4Controller/uploadMultipartFile" enctype="multipart/form-data">
		        <div>
		          <div>
		          	 <select name="fileContentType" id="fileContentType">
						 <c:forEach var="movieType" items="${movieList}"  varStatus="status">
							 <option value="${movieType.type_id}">${movieType.name}</option>
						 </c:forEach>
		          	 </select><br>
					  视频文件名称:<input type="text" name="filename1" id="filename1"><br>
					  <input type="file" name="file" id="file"><br>
					  图片文件
					  <input type="file" name="file" id="file"><br>
		            <input type="submit" value="上传"  onclick="return check(this.from)">
		          </div>   
		        </div>
      		</form>
		</div>
	<!-- Row End -->
	</div>
	<!--/ .container -->
	</div>
	<!--/ #headerwrap -->

	</div>
</body>
<script>
		function check(){
			/*var sss=$("#fileType").val();
			if(sss=="-1"){
				alert("请选择文件类型");
				return false;
			}*/
			var ddd=$("#fileContentType").val();
			if(ddd=="-1"){
				alert("请选择文件内容类型");
				return false;
			}
		}
</script>
</html>
