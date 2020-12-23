<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.FacultyListCtl"%>
<%@page import="in.co.sunrays.proj4.bean.FacultyBean"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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



	<%@include file="Header.jsp"%>

	<form action="<%=ORSView.FACULTY_LIST_CTL%>" method="post">



		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.FacultyBean"
			scope="request"></jsp:useBean>


		<%
			List coursel = (List) request.getAttribute("courseList");

			/* List subjectl = (List) request.getAttribute("subjectList"); */

			List collegel = (List) request.getAttribute("collegeList");
		%>

<%if(coursel.size() !=0 && collegel.size() !=0){ %>



		<center>
			<h1>Faculty List</h1>

			<tr>
				<h2>
					<td colspan="8"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></td>
				</h2>
			</tr>
			
			
			<tr>
				<h2>
					<td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
				</h2>
			</tr>




			<table width="90%">
				<tr>
					<td align="center"><label><b>Course Name :</b></label> <%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), coursel)%>




						<label> <b>First Name :</b></label> 
						<input type="text"
						name="firstName" placeholder="First Name"
						value="<%=ServletUtility.getParameter("firstName", request)%>">

						
						<label><b>College Name :</b></label> <%=HTMLUtility.getList("collegeId", String.valueOf(bean.getCollegeId()), collegel)%>


						<label><b>Email_Id :</b></label> 
						<input type="text" name="emailId"
						placeholder="EmailId"
						value="<%=ServletUtility.getParameter("emailId", request)%>">

						<input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_SEARCH%>"> <input
						type="submit" name="operation"
						value="<%=FacultyListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>
			<br>
<%
 
 List list=ServletUtility.getList(request);
 if(list.size()==0){}else{
 
 
 %>


			<table border="1" width="90%">
				<tr>
					<th align="left">
						 <input type='checkbox' id="UNCHECK" onclick="checkAll(this)">Select All
					</th>

					<th align="left">S.No.</th>

					<th>First Name.</th>
					<th>Last Name.</th>
					<th>Course Name.</th>
					<th>Subject Name.</th>
					<th>College Name.</th>
					<th>Qualification.</th>
					<th>Email Id.</th>
					<th>Date Of Birth.</th>
					<th>Mobile No.</th>
					<th>Edit</th>
				</tr>

				<%
					int pageNo = ServletUtility.getPageNo(request);
					int pageSize = ServletUtility.getPageSize(request);
					int index = ((pageNo - 1) * pageSize) + 1;

					 //List list = ServletUtility.getList(request);

					Iterator<FacultyBean> it = list.iterator();

					while (it.hasNext()) {

						bean = it.next();
				%>
				<tr>
					<td><input type="checkbox" name="ids" onclick="checkAl(this)"
						value="<%=bean.getId()%>"> <%-- <input type="checkbox" name="ids" id="checkbox-1" value="<%=bean.getId()%>" >
						 --%></td>

					<td align="center"><%=index++%></td>
					<td align="center"><%=bean.getFirstName()%></td>
					<td align="center"><%=bean.getLastName()%></td>
					<td align="center"><%=bean.getCourseName()%></td>
					<td align="center"><%=bean.getSubjectName()%></td>
					<td align="center"><%=bean.getCollegeName()%></td>
					<td align="center"><%=bean.getQualification()%></td>
					<td align="center"><%=bean.getEmailId()%></td>
					<td align="center"><%=bean.getDob()%></td>
					<td align="center"><%=bean.getMobNo()%></td>

					<td align="center"><a href="FacultyCtl?id=<%=bean.getId()%>">Edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="90%">
				<tr>
					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>></td>

					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEW%>"></td>

					<td><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						value="<%=FacultyListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>"> 
			<input type="hidden" name="pageSize" value="<%=pageSize%>">
			
			<% } } %>
	</form>
	</center>
	<br><br><br><br>
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