<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@page import="in.co.sunrays.proj4.controller.ORSView"%>
    <%@ include file="Header.jsp"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



<a href=""><b>Home</b></a>&nbsp;&nbsp;&nbsp;
<a href="<%=ORSView.LOGIN_CTL%>"><b>Login</b></a>&nbsp;&nbsp;&nbsp;
    <form action="">
        <h1 align="Center">
            <font size="10px" color="red">Welcome to ORS </font>
        </h1>
        <br> <br> <a href="<%=ORSView.MARKSHEET_LIST_VIEW%>"><b>Click Here
                </b></a>


 <%@ include file="Footer.jsp"%>





</form>

</body>
</html>