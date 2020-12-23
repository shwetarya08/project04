
<%@page import="in.co.sunrays.proj4.controller.ForgetPasswordCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>



<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

 <form action="<%=ORSView.FORGET_PASSWORD_CTL%>" method="post">

        <%@ include file="Header.jsp"%>

       <%--  <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"scope="request"></jsp:useBean>
 --%>
 
 <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean" scope="request"/>
 
        <center>
            <h1>Forgot your password?</h1>
           
           
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
           
           
            <input type="hidden" name="id" value="<%=bean.getId()%>">

            <table>
                
                <lable>Submit your email address and we'll send you password.</lable><br><br>
                
                <label>Email Id :</label>&emsp;
                
                <input type="text" name="login" placeholder="Enter ID Here" value="<%=ServletUtility.getParameter("login", request)%>">&emsp;
                
                <input type="submit" name="operation" value="<%=ForgetPasswordCtl.OP_GO%>"><br>
                
                <font color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font>
            </table>
            
           
    </form>
    </center>
    <%@ include file="Footer.jsp"%>







</body>
</html>