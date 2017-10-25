<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<<<<<<< HEAD
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
=======
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>
>>>>>>> ebd98d7e9f399538e889effbc9c504091dd51ac3
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
<<<<<<< HEAD
	<base href="<%=basePath%>" />
=======
>>>>>>> ebd98d7e9f399538e889effbc9c504091dd51ac3
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
<<<<<<< HEAD
	<%=basePath%>
	用户注册信息
	<form action="<c:url value='/register.html'/>"  method="post" onsubmit="return mycheck()">
=======
	用户注册信息
	<form action="<c:url value="/register.html"/>" method="post" onsubmit="return mycheck()">
>>>>>>> ebd98d7e9f399538e889effbc9c504091dd51ac3
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