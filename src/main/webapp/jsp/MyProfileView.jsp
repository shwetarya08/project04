<%@page import="in.co.sunrays.proj4.controller.MyProfileCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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

<form action="<%=ORSView.MY_PROFILE_CTL%>" method="post">

        <%@ include file="Header.jsp"%>
       
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>My Profile</h1>
            
            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>
            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table>
                <tr>
                    <th align="left">LoginId<font color="red"> *</font></th>
                    <td><input type="text" name="login" size="19"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"readonly="readonly"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>

                <tr>
                    <th align="left">First Name <font color="red">*</font></th>
                    <td><input type="text" name="firstName" size="19"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Last Name <font color="red">*</font></th>
                    <td><input type="text" name="lastName" size="19"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Gender<font color="red">*</font></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                            map.put("","------------Select------------");
                        	map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
                        %> <%=htmlList%>
                    </td>
                </tr>
               
                <tr>
                    <th align="left">Mobile No <font color="red">*</font></th>
                    <td><input type="text" name="mobileNo" size="19"  value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
                    <font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font></td>
                </tr>

                <tr>
                    <th align="left">Date Of Birth <font color="red">*</font></th>
                   
                    <td><input type="text" name="dob" size="19" id="datepicker" value="<%=DataUtility.getDateString(bean.getDob())%>">
                   
                 <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                
            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
                
                <tr>
                    <th></th>
                    <td colspan="2">
                    <input type="submit" name="operation" value="<%=MyProfileCtl.OP_CHANGE_MY_PASSWORD %>"><input type="submit" name="operation" value="<%=MyProfileCtl.OP_SAVE %>"> </td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>







</body>
</html>