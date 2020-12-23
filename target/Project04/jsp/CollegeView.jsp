<%@page import="in.co.sunrays.proj4.controller.CollegeCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>


<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<html>
<body>
    <form action="CollegeCtl" method="POST">
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CollegeBean" scope="request">
            </jsp:useBean>
<br>
        <center>
        
        	<%
			if (bean.getId() > 0) {
		%>

		<h1>Update College</h1>

		<%
			}

			else

			{
		%>
		<h1>Add College</h1>

		<%
			}
		%>

        
     
        
    <%--  <h1 align="center"> 
      
            <h1>Add College</h1> --%>

            <H2>
                <font color="green">
                	 <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>
            <H2>
                <font color="red"> 
                <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <input type="hidden" name="id" value="<%=bean.getId()%>">
             <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
             <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
                 <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

           <table align="center" style="margin-left: 40%" cellspacing="1" cellpadding="1">
                <tr>
                
                
                
                
                    <th align="left">Name <font color="red">*</font></th>
                    <td>
                    <input type="text" name="name" size="19" placeholder="CollegeName" value="<%=DataUtility.getStringData(bean.getName())%>">
                    <font color="red">
                     <%=ServletUtility.getErrorMessage("name", request)%>
                     </font>
                     </td>
                </tr>
                <tr>
                    <th align="left">Address <font color="red">*</font></th>
                    <td>
                    <input type="text" name="address" size="19" placeholder="Address"  value="<%=DataUtility.getStringData(bean.getAddress())%>">
                    <font color="red"> 
                    <%=ServletUtility.getErrorMessage("address", request)%>
                    </font>
                    </td>
                </tr>
                <tr>
                    <th align="left">State <font color="red">*</font></th>
                    <td>
                    <input type="text" name="state" size="19" placeholder="State" value="<%=DataUtility.getStringData(bean.getState())%>">
                    <font color="red"> 
                    <%=ServletUtility.getErrorMessage("state", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">City <font color="red">*</font></th>
                    <td>
                    <input type="text" name="city" size="19" placeholder="City" value="<%=DataUtility.getStringData(bean.getCity())%>">
       <font color="red">
        <%=ServletUtility.getErrorMessage("city", request)%>
        </font>
        </td>
               
                </tr>
                <tr>
                    <th align="left">PhoneNo <font color="red">*</font></th>
                    <td>
                    <input type="text" name="phoneNo" size="19" maxlength="10" placeholder="PhoneNo" value="<%=DataUtility.getStringData(bean.getPhoneNo())%>">
 			<font color="red"> <%=ServletUtility.getErrorMessage("phoneNo", request)%>
 			</font>
 			</td>
                </tr>


                <tr>
                    <th></th>
                    <td colspan="2">
                    
                   <%
     	if (bean.getId() > 0)
     {
     		
 		%><input type="submit" name="operation" value="<%=CollegeCtl.OP_UPDATE%>">   
 		<input type="submit" name="operation" value="<%=CollegeCtl.OP_CANCEL%>">
 		<%
 	} else {
 %>
     
    	 <input type="submit" name="operation" value="<%=CollegeCtl.OP_SAVE%>">
    	 
    	 <input type="submit" name="operation" value="<%=CollegeCtl.OP_RESET%>"><% 
    	 
     }
 %>&emsp;
 	
 	 </td>
                </tr>
            </table>
    </form>
    </center>
    <%@ include file="Footer.jsp"%>
</body>
</html>
