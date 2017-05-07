<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="controller.LoginController"%>

<%
	request.setCharacterEncoding("UTF-8");
	String email = request.getParameter("email");
	String result = "null";
	
	LoginController loginController = new LoginController();
	
	result = loginController.requestUserNickname(email);
	
	out.print(result);
	
%>