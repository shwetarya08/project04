<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.SubjectCtl"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>



	<form action="<%=ORSView.SUBJECT_CTL%>" method="post">
       
        <%@ include file="Header.jsp"%>
       
        
       
<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.SubjectBean" scope="request"/>
        <%
            List l = (List) request.getAttribute("courseList");
        %>

        <center>
        
        
        
         <%
			if (bean.getId() > 0) {
		%>

		<h1>Update Subject</h1>

		<%
			}

			else

			{
		%>
		<h1>Add Subject</h1>

		<%
			}
		%>
        
        
        
        
           <!--  <h1>Add Subject</h1> -->

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
            
             <tr>
                    <th align="left">Name <font color="red">*</font></th>
                    
                    <td>
               
                    <input type="text" name="name" size="19" placeholder="Subject Name" value="<%=DataUtility.getStringData(bean.getName())%>">
                    
                    	<font color="red"> <%=ServletUtility.getErrorMessage("name", request)%></font></td>
                </tr>
                
                
                 <tr>
                    <th align="left">Description <font color="red">*</font></th>
                    <td>
                    
                   <textarea rows="4" cols="21" name="description"  placeholder="Description"><%=DataUtility.getStringData(bean.getDescription())%></textarea>	<font color="red"> <%=ServletUtility.getErrorMessage("description", request)%></font></td>
                </tr>
            
                <tr>
                    <th align="left">Course Name <font color="red">*</font></th>
                    
                    <td>
						<%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), l)%>
						
						<font color="red">
							 <%=ServletUtility.getErrorMessage("courseId", request)%>
						</font>
					</td>
                    
                    
                    
                    
                    <%-- 	<td>
                    
                    		<input type="text" name="courseName"value="<%=DataUtility.getStringData(bean.getCourseName())%>">
                    			<font color="red"> <%=ServletUtility.getErrorMessage("courseName", request)%></font>
                    	</td> --%>
                </tr>
               
               <tr>
                    <th></th>
                    
                    	<td colspan="2">
                    	
                    	
                    	
                                <%
     	if (bean.getId() > 0)
     {
     		
 		%><input type="submit" name="operation" value="<%=SubjectCtl.OP_UPDATE%>"> 
    	 <input type="submit" name="operation" value="<%=SubjectCtl.OP_CANCEL%>">
    	 
    	 <%
 	} else {
 %>
                    	
                    	<input type="submit" name="operation" value="<%=SubjectCtl.OP_SAVE%>"> 
                   
                   	    <input type="submit" name="operation" value="<%=SubjectCtl.OP_RESET%>"><% 
                   	
                   		  	 
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