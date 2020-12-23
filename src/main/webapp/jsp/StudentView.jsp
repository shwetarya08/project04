<%@page import="in.co.sunrays.proj4.controller.StudentCtl"%>
<%@page import="in.co.sunrays.proj4.controller.UserCtl"%>
<%@page import="java.util.List"%>
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

<html>
<body>
   <form action="<%=ORSView.STUDENT_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
        
       
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.StudentBean"
            scope="request"></jsp:useBean>

        <%
            List l = (List) request.getAttribute("collegeList");
        %>
<br>
        <center>
        
       <!--   if (id > 0) 
	                {
	                    model.update(bean);
	                } --> 
        
        
            <center>
          
              <%
			if (bean.getId() > 0) {
		%>

		<h1 align="center">Update Student</h1>

		<%
			}

			else

			{
		%>
		<h1 align="center">Add Student</h1>

		<%
			}
		%>
        


            <H2>
                <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
                </font>
            </H2>

            <H2>
                <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
                </font>
            </H2>



            <input type="hidden" name="id" value="<%=bean.getId()%>">
            <input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
            <input type="hidden" name="modifiedBy" value="<%=bean.getModifiedBy()%>">
            <input type="hidden" name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
            <input type="hidden" name="modifiedDatetime" value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

			


            <table align="center" style="margin-left: 40%">
            
                  
                </tr>
            
                <tr>
                    <th align="left">First Name <font color="red">*</font></th>
                  <td>
                    	<input type="text" name="firstName" size="19" placeholder="First Name" value="<%=DataUtility.getStringData(bean.getFirstName())%>">
                    	
                    	<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
                    </td>
                </tr>
                <tr>
                    <th align="left">Last Name <font color="red">*</font></th>
                    <td>
                    	<input type="text" name="lastName" size="19" placeholder="Last Name"value="<%=DataUtility.getStringData(bean.getLastName())%>">
                    	
                    	<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font>
                    </td>
                </tr>
               
                <tr>
                    <th align="left">Mobile Number <font color="red">*</font></th>
	                    <td>
	                    
	                  <%--   <input type="text" name="mobileNo" pattern="[1-9]{1}[0-9]{9}" <%=DataUtility.getStringData(bean.getMobileNo())%>"> 
	                    
	                    <input type="text" name="mobileNo" maxlength="10"<%=DataUtility.getStringData(bean.getMobileNo())%>"/>
		                  
		                  --%>    <input type="text" name="mobileNo" size="19" maxlength="10" placeholder="Mobile No" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
	                    	 
	                    	
	                    	<font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
                    	</td>
                </tr>
                <tr>
                    <th align="left">Email Id <font color="red">*</font></th>
                    <td>
                    	<input type="text" name="email" size="19" placeholder="Email Id" value="<%=DataUtility.getStringData(bean.getEmail())%>">
                        
                        <font color="red"> <%=ServletUtility.getErrorMessage("email", request)%></font>
                        
                     </td>
                </tr>
               
              
		 		<tr>
                    <th align="left">CollegeName <font color="red">*</font></th>
                    
                    
                   <td><%=HTMLUtility.getList("collegeId",String.valueOf(bean.getCollegeId()), l)%>
                  
				<a>	<font color="red"> <%=ServletUtility.getErrorMessage("collegeId", request)%>
							 
						</font></a>
					</td> 
               
               
                <tr>
                    <th align="left">Date Of Birth <font color="red">*</font></th>
                    <td>
                    
	                    <input type="text" name="dob" size="19" placeholder="yyyy/mm/dd" id="datepicker"  readonly="readonly" value="<%=DataUtility.getDateString(bean.getDob())%>">
	                    
	                    <font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>
                                        </td>
                </tr>
               
               
               
                <tr>
                    <th></th>
	                    <td colspan="2">
	                    
 <%
     	if (bean.getId() > 0)
     {
     		
 		%><input type="submit" name="operation" value="<%=StudentCtl.OP_UPDATE%>">
    	 <input type="submit" name="operation" value="<%=StudentCtl.OP_CANCEL%>">
    	 
    	 <%
 	} else {
 %>    
	                    
	                    
	                    
	                    <input type="submit" name="operation" value="<%=StudentCtl.OP_SAVE%>"> 
	                    
	                    <input type="submit" name="operation" value="<%=StudentCtl.OP_RESET%>"></td>
                
                
                  <% 
    	 
     }
 %>&emsp;
               
                
                
                
                </tr>
            </table>
    </form>
    </center>
    
     <%@ include file="Footer.jsp"%>
</body>
</html>