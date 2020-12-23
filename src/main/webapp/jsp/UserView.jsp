<%@page import="in.co.sunrays.proj4.controller.UserCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<html>

<head>

<script>




</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>

<body>
    <form action="<%=ORSView.USER_CTL%>" method="post">
        <%@ include file="Header.jsp"%>
           
        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
            scope="request"></jsp:useBean>

        <%
            List l = (List) request.getAttribute("roleList");
        	System.out.println("rolelist:"+l);
        %>

        <center>
          
              <%
			if (bean.getId() > 0) {
		%>

		<h1 align="center">Update User</h1>

		<%
			}

			else

			{
		%>
		<h1 align="center">Add User</h1>

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


            <table align="center" style="margin-left: 40%" >
                <tr>
                    <th align="left">First Name <font color="red">*</font></th>
                    <td><input type="text" name="firstName" size="19" placeholder="First Name" value="<%=DataUtility.getStringData(bean.getFirstName())%>"><font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font></td>
                </tr>
               
                <tr>
                    <th align="left">Last Name <font color="red">*</font></th>
                    <td><input type="text" name="lastName" size="19" placeholder="Last Name"
                        value="<%=DataUtility.getStringData(bean.getLastName())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
                </tr>
                
                 <th align="left">Mobile Number <font color="red">*</font></th>
	                    <td>  <input type="text" name="mobileNo" size="19" maxlength="10" placeholder="Mobile No" value="<%=DataUtility.getStringData(bean.getMobileNo())%>">
	                    	 
	                    	
	                    	<font color="red"> <%=ServletUtility.getErrorMessage("mobileNo", request)%></font>
                    	</td>
                
                <tr>
                    <th align="left">LoginId <font color="red">*</font></th>
                    <td><input type="text" name="login" size="19" placeholder="Login Id"
                        value="<%=DataUtility.getStringData(bean.getLogin())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("login", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Password <font color="red">*</font></th>
                    <td><input type="password" name="password" size="19" placeholder="Password" value="<%=DataUtility.getStringData(bean.getPassword())%>">
                    <font color="red"> <%=ServletUtility.getErrorMessage("password", request)%></font></td>
                </tr>
                <tr>
                    <th align="left">Confirm Password<font color="red">*</font></th>
                    <td><input type="password" name="confirmPassword" size="19" placeholder="Confirm Password"
                        value="<%=DataUtility.getStringData(bean.getPassword())%>"><font
                        color="red"> <%=ServletUtility.getErrorMessage("confirmPassword",
                    request)%></font></td>
                </tr>
            
                <tr>
                    <th align="left">Gender<font color="red">*</font></th>
                    <td>
                         <%
                            HashMap map = new HashMap();
                         	map.put("", "-------------select------------");
                         	map.put("Male", "Male");
                            map.put("Female", "Female");

                            String htmlList = HTMLUtility.getList("gender", bean.getGender(), map);
                        %> <%=htmlList%><font color="red"><%=ServletUtility.getErrorMessage("gender",
                    request)%></font>
                    
                    </td>
                    </tr>
                    
                    <tr>
	                   	 <th align="left">  
	                     	<b>Role name <font color="red">*</font></b>
	                    </th>
                  
	                   <td><%=HTMLUtility.getList("roleId",String.valueOf(bean.getRoleId()), l)%>
	                  
					  		<a><font color="red"> <%=ServletUtility.getErrorMessage("roleId", request)%>
								 
						</font></a>
						</td> 
                </tr>
               
               
               
                <tr>
                    <th align="left">Date Of Birth <font color="red">*</font></th>
                    <td><input type="text"  name="dob" placeholder="yyyy/mm/dd" size="19" id="datepicker" readonly="readonly" value="<%=DataUtility.getDateString(bean.getDob())%>" >
                   <font
                        color="red"><%=ServletUtility.getErrorMessage("dob", request)%></font></td>
                </tr>
                <tr>
                    <th></th>
                    <td colspan="2">
                    
 	  <%
     	if (bean.getId() > 0)
     {
     		
 		%>
 		
 		<input type="submit" name="operation" value="<%=UserCtl.OP_UPDATE%>">
    	<input type="submit" name="operation" value="<%=UserCtl.OP_CANCEL%>">
    	 
    	 <%
 		} 
     	else
     	
     	{
 		%>
                    
                    <input type="submit" name="operation" value="<%=UserCtl.OP_SAVE%>"> 
                    <input type="submit" name="operation" value="<%=UserCtl.OP_RESET%>"></td>
               
               
               <% 
    	 
     }
 %>&emsp;
               
               
               
               
               
                </tr>
            </table>
    </form>
    </center>
    <br><br>
    <%@ include file="Footer.jsp"%>
</body>
</html>