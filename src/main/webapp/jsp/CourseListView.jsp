<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.controller.CourseListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.controller.CourseCtl"%>
<%@page import="in.co.sunrays.proj4.bean.CourseBean"%>


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

	<%-- <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CourseBean"
			scope="request"></jsp:useBean>
 --%>

<script type="text/javascript">
function checkAll(bx)
{
	 
	var form =bx.form;
	var isChecked =bx.checked;
	for (var i=0; i<form.length; i++){
		
		if(form[i].type=='checkbox')
		{
			
			form[i].checked= isChecked;
			
			
		}
		
	}
	
}
	
function checkAl(bx)
{
	
	document.getElementById("UNCHECK").checked=false;
	
}
	

</script>


 <form action="<%=ORSView.COURSE_LIST_CTL%>" method="post">
<%@include file="Header.jsp"%>

    <center>
        <h1>Course List</h1>

	 			 <tr>
	 			 	<h2>
                    	<td colspan="8">
                    		<font color="red">
                    			<%=ServletUtility.getErrorMessage(request)%>
                    		</font>
                    	</td>
                    </h2>
                </tr>
      
            
            <table width="80%">
            
               <tr>
                 <td align="center">
            
                  <label><b>Name :</b></label>
                 
                  <input type="text" name="name" placeholder ="Name" value="<%=ServletUtility.getParameter("name", request)%>">&nbsp;
                  
                  <input type="submit" name="operation" value="<%=CourseListCtl.OP_SEARCH %>">
                  
                  <input type="submit" name="operation" value="<%=CourseListCtl.OP_RESET %>">
                    
                  </td>
                </tr>
            </table>
            <br>
            
            <%
 
 List list=ServletUtility.getList(request);
 if(list.size()==0){}else{
 
 
 %>
            
            <table border="1" width="80%">
                <tr>
                    <th align="left"><input type="checkbox" id="UNCHECK" onclick="checkAll(this)">Select All</th>
                    <th>S.No</th>
                 	<th>Course Name</th>
                    <th>Description</th>
                    <th>Duration</th>
                    <th>Edit</th>
                </tr>
              

                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                   // List list = ServletUtility.getList(request);
                    
                    Iterator<CourseBean> it = list.iterator();
                   
                    while (it.hasNext()) 
                    {
                        CourseBean bean = it.next();
                %>
                <tr>
                <td><input type="checkbox" name="ids" onclick="checkAl(this)" value="<%=bean.getId()%>"></td>
                
                    <td align="center"><%=index++%></td>
                  	<td align="center"><%=bean.getName() %>
                    <td align="center"><%=bean.getDescription()%></td>
                    <td align="center"><%=bean.getDuration()%> Year</td>
                    <td align="center">
                    <a href="CourseCtl?id=<%=bean.getId()%>">Edit</a>
                    </td>
                <%
                    }
                %>
            </table>
           
            <table width="80%">
                <tr>
                    <td>
                    <input type="submit" name="operation" value="<%=CourseListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>
                    </td>
                    
                    <td>
                    <input type="submit" name="operation" value="<%=CourseListCtl.OP_NEW%>">
                  	</td>
                    
                    <td><input type="submit" name="operation" value="<%=CourseListCtl.OP_DELETE%>">
                  	</td>
                  	
                  	<td align="right">
                    <input type="submit" name="operation" value="<%=CourseListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>>
                    </td>
                </tr>
            </table>
            
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
      
      	<%  } %>
       
        </form>
    
  
     </center>
   <br><br><br>  <br><br>
  
    <%@include file="Footer.jsp"%>

 
 
 
 
 
<!--  
 <html>
<head>
<style>
.foot{

height:60px;
 position:relative; 
left:0;
width:100%;
bottom:0;
color:black;
text-align: center;
/*  background-color: blue;  */
/* background-color:rgb( 251 179 175); */

}


</style>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div class="foot">

<hr>
<CENTER>

    <H3>&copy; <i> SUNRAYS Technologies</i></H3>
</CENTER>
</div>


</body>
</html>
	
	 -->
 
 
</body>
</html>