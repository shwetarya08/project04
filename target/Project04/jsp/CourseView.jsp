<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.controller.CourseCtl"%>
<%@page import="in.co.sunrays.proj4.controller.CourseListCtl"%>
<%@page import="in.co.sunrays.proj4.bean.CourseBean"%>
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
<form action="<%=ORSView.COURSE_CTL%>" method="post" >
        <%@ include file="Header.jsp"%>

        <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CourseBean" scope="request">
        </jsp:useBean>

        <center>
        
        <%
			if (bean.getId() > 0) {
		%>

		<h1>Update Course</h1>

		<%
			}

			else

			{
		%>
		<h1>Add Course</h1>

		<%
			}
		%>
        
      
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
            

	 <div>
            <table align="center" style="margin-left: 38%">
           
            
             <tr>
                    <th align="left">Name <font color="red">*</font></th>
                    <td>
                    <input type="text" name="name" size="19" value="<%=DataUtility.getStringData(bean.getName())%>"placeholder="CourseName" size="19">
                       
                         <font color="red">
						 	<%=ServletUtility.getErrorMessage("name", request)%>
						 </font>
                        </td>
                </tr>
           
            
            
                <tr>
                    <th align="left">Description <font color="red">*</font></th>
                    <td>
                   <textarea rows="4" cols="21"  name="description" placeholder="Description"><%=DataUtility.getStringData(bean.getDescription())%></textarea> 
                       
                        <font color="red"> <%=ServletUtility.getErrorMessage("description", request)%>
                       
                        </font>
                       
                        </td>
                </tr>
                <tr>
                    <th align="left">Duration <font color="red">*</font></th>
                    
                    <td>
						<%
							HashMap map = new HashMap();
							map.put("", "------------select------------");

							map.put("1", "1 Year");
							map.put("2", "2 Year");
							map.put("3", "3 Year");
							map.put("4", "4 Year");
							/* map.put("5", "5 Year"); */

							String htmlList = HTMLUtility.getList("duration", bean.getDuration(), map);
						%> <%=htmlList%> <font color="red"> <%=ServletUtility.getErrorMessage("duration", request)%></font>
					</td>

                             <%-- <td>
                    <input type="text" name="duration" value="<%=DataUtility.getStringData(bean.getDuration())%>">
                    	
                    	<font color="red">
                         <%=ServletUtility.getErrorMessage("duration", request)%>
                         </font>
                        
                         </td> --%>
                </tr>
                <tr>
               
                    <th></th>
                  
                    <td colspan="2">
                   
                                <%
     	if (bean.getId() > 0)
     {
     		
 		%><input type="submit" name="operation" value="<%=CourseCtl.OP_UPDATE%>"> 
    	 <input type="submit" name="operation" value="<%=CourseCtl.OP_CANCEL%>">
    	 
    	 <%
 	} else {
 %>
 
  <input type="submit" name="operation" value="<%=CourseCtl.OP_SAVE%>">
    	 
    	 <input type="submit" name="operation" value="<%=CourseCtl.OP_RESET%>"><% 
    	 
     }
 %>&emsp;
      </td>
                </tr>
             
            </table>
    </div>
    </form>
    </center>
   
    <%@ include file="Footer.jsp"%>

</body>
</html>