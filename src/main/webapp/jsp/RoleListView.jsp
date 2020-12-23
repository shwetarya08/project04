<%@page import="in.co.sunrays.proj4.controller.RoleListCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.bean.RoleBean"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

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


	 <form action="<%=ORSView.ROLE_LIST_CTL%>" method="post">
    <%@include file="Header.jsp"%>

    <center>
        <h1>Role List</h1>
		 		<tr><h2>
                   <td colspan="8">
                   <font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></h2> </td>
                </tr>
                
                <tr>
                   <h2> <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
             </h2>   </tr>
       
            <table width="80%">
               <tr>
                 <td align="center"><label><b>Name :</b></label> <input type="text" name="name" placeholder="Name" value="<%=ServletUtility.getParameter("name", request)%>"> &nbsp; 
                        <input type="submit" name="operation" value="<%=RoleListCtl.OP_SEARCH %>">
                        <input type="Reset" name="operation" value="<%=RoleListCtl.OP_RESET %>">
                    </td>
                </tr>
            </table>
            <br><br>
               <%
 
 List list=ServletUtility.getList(request);
 if(list.size()==0){}else{
 
 
 %>
 
            <table border="1" width="80%">
                <tr>
                <th align="left"><input type="checkbox" id="UNCHECK" onclick="checkAll(this)">Select</th>
                    <th>S.No.</th>
                    <!-- <th>Id</th> -->
                    <th>Name</th>
                    <th>Description</th>
                    <th>Edit</th>
                </tr>
               

                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                  //  List list = ServletUtility.getList(request);
                    Iterator<RoleBean> it = list.iterator();
                    while (it.hasNext()) 
                    {
                        RoleBean bean = it.next();
                %>
                <tr>
             <td><input type="checkbox" name="ids" onclick="checkAl(this)" value="<%=bean.getId()%>"></td>
                    <td align="center"><%=index++%></td>
                	<td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getDescription()%></td>
                    <%if(bean.getId()<4){
                    %>
                    <td align="center"><a href="#">...</a></td>
                	<% }else{%>
                
					<td align="center"><a href="RoleCtl?id=<%=bean.getId()%>">Edit</a></td>                		
                	<% }%>
                </tr>
                <%
                    }
                %>
            </table>
            <table width="80%">
                <tr>
                    <td><input type="submit" name="operation" value="<%=RoleListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>
					<td align="right"><input type="submit" name="operation" value="<%=RoleListCtl.OP_NEW%>"></td>
	                <td align="right"><input type="submit" name="operation" value="<%=RoleListCtl.OP_DELETE%>"></td>
					<td align="right"><input type="submit" name="operation" value="<%=RoleListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
	                
         
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> 
            <input type="hidden" name="pageSize" value="<%=pageSize%>">
        	<%  } %>
 
        </form>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>