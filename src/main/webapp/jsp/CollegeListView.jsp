<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.CollegeListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.CollegeBean"%>
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

<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.CollegeBean"
			scope="request"></jsp:useBean>


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



	<form action="<%=ORSView.COLLEGE_LIST_CTL%>" method="POST">

		<%@include file="Header.jsp"%>


<%
			List collegel = (List) request.getAttribute("collegeList");
%>


		<center>
			<h1>College List</h1>


			<tr>
				<h2>
					<td colspan="8"><font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
					</font>
				</h2>
				</td>
			</tr>
			
			<tr>
				<h2>
					<td colspan="8"><font color="red"> <%=ServletUtility.getErrorMessage(request)%>
					</font>
				</h2>
				</td>
			</tr>
			
			
			
			
			
			<table width="80%">
				<tr>
					<td align="center"><label><b>College Name :</b></label> 
					<input type="text" name="name"value="<%=ServletUtility.getParameter("name", request)%>"	placeholder="Name"> &emsp; 
						
						
					 
					 <%-- 
					  <%=HTMLUtility.getList("name", String.valueOf(bean.getName()), collegel)%>&emsp;&emsp;
					 
					  --%>
					 
					 
					 
					 <label><b>City :</b></label>
						
						 <input type="text" name="city"	value="<%=ServletUtility.getParameter("city", request)%>"placeholder="City">
						 <input type="submit" name="operation"value="<%=CollegeListCtl.OP_SEARCH%>"> 
						
						<input type="submit" name="operation"value="<%=CollegeListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			
			
			 <%
 
			 List list=ServletUtility.getList(request);
			 if(list.size()==0){}else{
			 
 
 			%>
			
			<table border="1" width="80%">
				<tr>
					<th align="left"><input type="checkbox"  id="UNCHECK" onclick="checkAll(this)">Select</th>
					<th>S.No</th>
					<th>Name.</th>
					<th>Address.</th>
					<th>State.</th>
					<th>City.</th>
					<th>PhoneNo.</th>
					<th>Edit</th>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					/*  List list = ServletUtility.getList(request); */ 
					Iterator<CollegeBean> it = list.iterator();

					while (it.hasNext()) {

						 bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="ids" onclick="checkAl(this)"
						value="<%=bean.getId()%>">
					</td>
					
					<td align="center"><%=index++%></td>
					<td align="center"><%=bean.getName()%></td>
					<td align="center"><%=bean.getAddress()%></td>
					<td align="center"><%=bean.getState()%></td>
					<td align="center"><%=bean.getCity()%></td>
					<td align="center"><%=bean.getPhoneNo()%></td>

					<td align="center"><a href="CollegeCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>

			<table width="80%">
				<tr>
					<td><input type="submit" name="operation"value="<%=CollegeListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>

					<td><input type="submit" name="operation"value="<%=CollegeListCtl.OP_NEW%>"></td>


					<td><input type="submit" name="operation"value="<%=CollegeListCtl.OP_DELETE%>"></td>


					<td align="right"><input type="submit" name="operation"value="<%=CollegeListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>">
			 <input	type="hidden" name="pageSize" value="<%=pageSize%>">
			 
			  <% }  %>
	</form>
	</center>
<br><br><br><br><br>
	<%@ include file="Footer.jsp"%>
	
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