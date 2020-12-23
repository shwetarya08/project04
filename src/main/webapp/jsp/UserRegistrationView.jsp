<%@page import="in.co.sunrays.proj4.controller.UserRegistrationCtl"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>
<body>
    <form action="<%=ORSView.USER_REGISTRATION_CTL%>" method="post">

        <%@ include file="Header.jsp"%>
       
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>

        <center>
            <h1>User Registration</h1>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
             <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2> 

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">
            

            <table align="center" style="margin-left: 40%" style="margin-right: 40%" >

                <tr>
                    <th align="left">First Name<font color="red">*</font></th>
                    <td><input type="text" name="firstName"  placeholder="First Name"
                        value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Last Name<font color="red">*</font></th>
                    <td><input type="text" name="lastName" placeholder="Last Name"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">LoginId<font color="red">*</font></th>
                    <td><input type="text" name="login"
                        placeholder="Must be Email ID"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Password<font color="red">*</font></th>
                    <td><input type="password" name="password" placeholder="Password"  value="<%=DataUtility.getStringData(bean.getPassword())%>">
                    <font
                        color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Confirm Password<font color="red">*</font></th>
                    <td><input type="password" name="confirmPassword" placeholder="ConfirmedPassword" value="<%=DataUtility.getStringData(bean.getConfirmPassword())%>">
                    <font color="red"> <%=ServletUtility.getErrorMessage("confirmPassword", request)%></font>
                    <%--  <font color="red"> <%=ServletUtility.getErrorMessage(request)%> --%>
                </font>
                    
                    </td>
                </tr>
                <tr>
                    <th align="left">Gender<font color="red">*</font></th>
                    <td>
                        <%
                            HashMap map = new HashMap();
                           	map.put("","------------Select-------------");
                        	map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(),
                                    map);
                        %> <%=htmlList%>
  <font
                        color="red"> <%=ServletUtility.getErrorMessage("gender", request)%></font>
                    </td>
                </tr>

                <tr>
                    <th align="left">Date Of Birth<font color="red">*</font></th>
                   
                    <td><input type="text" name="dob" placeholder="Date of Birth" readonly="readonly" id="datepicker" value="<%=DataUtility.getDateString(bean.getDob())%>">
                       
                    <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                
                 <tr>
                    <th align="left">Mobile No<font color="red">*</font></th>
                    <td><input type="text" name="mob" placeholder="Mobile No"  maxlength="10"  value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
                    <font
                        color="red"> <%=ServletUtility.getErrorMessage("mob", request)%></font></td>
                </tr>
                
                <tr>
                    <th></th>
                    <td colspan="2">
                         <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_SIGN_UP %>">
                        <input type="submit" name="operation" value="<%=UserRegistrationCtl.OP_RESET %>">
                    </td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>