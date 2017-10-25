<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<base href="<%=basePath%>" />
	<script>
		function mycheck(){
			if(document.all("user.password").value != document.all("again").value){
				alert("两次输入的密码不正确，请更正");
				return fasle;
			}else{
				return true;
			}
		}
	</script>
</head>
<body>
	<%=basePath%>
	用户注册信息
	<form action="<c:url value='/register.html'/>"  method="post" onsubmit="return mycheck()">
		<c:if test="${!empty errorMSg}">
			<div style="color:red">${errorMsg}</div>
		</c:if>
		用户名<input type="text" name="userName"/>
		密码<input type= "password" name="password"/>
		密码确认<input type="password" name="again">
		<input type="submit" value="保存"/>
		<input type="reset" value="重置"/>
	</form>
	
	

</body>
</html>