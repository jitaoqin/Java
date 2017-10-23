<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path;
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%=basePath%>
	</br>
	<font size="25px" color="blue"><c:out value="${testName}" /></font>
	</br>
	<!-- ****************************test1*************************************** -->
	<a href="<%=basePath%>/test1.htmls?testName='test1'">test1</a>
	<c:forEach items="${map1}" var="item">  
			${item.key}
			${item.value}
		</c:forEach>
	</br>
	<!-- ****************************test2*************************************** -->
	<a href="<%=basePath%>/test2.htmls?testName='test2'">test2</a>
	</br>
	<!-- ****************************test3*************************************** -->
	<form action="<%=basePath%>/test3.htmls? method="post">
		test3： 用户ID<input type="text" name="uId" value="${userInfo.uId}" /></br>
		用户名<input type="text" name="userName" value="${userInfo.userName}" /></br>
		电 话<input type="text" name="tel" value="${userInfo.tel}" /></br> <input
			type="submit" value="提交" />
	</form>
	<!-- ****************************test4*************************************** -->
	<a href="<%=basePath%>/test4.htmls">test4</a>
	</br>
	<!-- ****************************test5*************************************** -->
	<a href="<%=basePath%>/test5/select;name=lilei,hanmeimei,xiaoqin;num=3">test5</a>
	</br>
	<!-- ****************************test5*************************************** -->
	<a href="<%=basePath%>/test6">test6</a>
	</br>
	<!-- ****************************test3*************************************** -->
	<form action="<%=basePath%>/test13" method="post">
		用户ID<input type="text" name="uId" /> ${userInfo.uId}</br> 用户名<input
			type="text" name="userName" /> ${userInfo.userName}</br> 电 话<input
			type="text" name="tel" /> ${userInfo.tel}</br> ${userInfo.registerTime}
		<input type="submit" value="test13" />
	</form>
	<!-- ****************************test3*************************************** -->
	<form action="<%=basePath%>/test14" method="post">
		用户ID<input type="text" name="uId" /> ${userInfo.uId}</br> 用户名<input
			type="text" name="userName" /> ${user.userName}</br> 电 话<input
			type="text" name="tel" /> ${userInfo.tel}</br> 电 话<input type="text"
			name="registerTime" /> ${userInfo.registerTime}</br> <input
			type="submit" value="test14" />
	</form>
	<!-- ****************************test15*************************************** -->
	<form action="<%=basePath%>/test15" method="post">
		用户ID<input type="text" name="uId" /> ${userInfo.uId}</br> 用 户 名<input
			type="text" name="userName" /> ${userInfo.userName}</br> 电 话<input
			type="text" name="tel" /> ${userInfo.tel}</br> 注册日期<input type="text"
			name="registerTime" /> ${userInfo.registerTime}</br> <input
			type="submit" value="test15" />
	</form>
	<!-- ****************************test16*************************************** -->
	<a href="<%=basePath%>/test16">test16 : 下载excel</a>
	</br>
	<!-- ****************************test17*************************************** -->
	<a href="<%=basePath%>/test17">test17 : 下载pdf</a>
	</br>
	<!-- ****************************test18*************************************** -->
	<a href="<%=basePath%>/test18">test18 : 输出xml</a>
	</br>
	<!-- ****************************test19*************************************** -->
	<a href="<%=basePath%>/test19">test19 : 输出json</a>
	</br>
	<!-- ****************************test20*************************************** -->
	<a href="<%=basePath%>/test20.html?content=xml">test20 : ContentNegotiationViewResolver</a>
	</br>
</body>
</html>