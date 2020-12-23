<%@page import="in.co.sunrays.proj4.controller.UserCtl"%>
<%@page import="in.co.sunrays.proj4.controller.FacultyCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.HashMap"%>
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

	<form action="<%=ORSView.FACULTY_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

		
		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.FacultyBean"
			scope="request" />
		<%
			List coursel = (List) request.getAttribute("courseList");

			List subjectl = (List) request.getAttribute("subjectList");

			List collegel = (List) request.getAttribute("collegeList");
		%>

		<center>
		
		
		<%
			if (bean.getId() > 0) {
		%>

		<h1>Update Faculty</h1>

		<%
			}

			else

			{
		%>
		<h1>Add Faculty</h1>

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
			<input type="hidden" name="modifiedBy"value="<%=bean.getModifiedBy()%>"> 
			<input type="hidden"name="createdDatetime"value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table align="center" style="margin-left: 40%" cellspacing="1" cellpadding="1">



				<tr>
					<th align="left">First Name <font color="red">*</font></th>

					<td><input type="text" name="firstName" size="19" placeholder="First Name"value="<%=DataUtility.getStringData(bean.getFirstName())%>">
						<font color="red"> <%=ServletUtility.getErrorMessage("firstName", request)%></font>
					</td>
				</tr>

				<tr>
					<th align="left">Last Name <font color="red">*</font></th>

					<td><input type="text" name="lastName" size="19" placeholder="Last Name"value="<%=DataUtility.getStringData(bean.getLastName())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("lastName", request)%></font></td>
				</tr>


				<tr>
					<th align="left">Qualification <font color="red">*</font></th>
					<td><input type="text" name="qualification" size="19" placeholder="Qualification"value="<%=DataUtility.getStringData(bean.getQualification())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("qualification", request)%></font>

					</td>
				</tr>



				<tr>
					<th align="left">Email-Id <font color="red">*</font></th>
					<td><input type="text" name="emailId" size="19" placeholder="EmailId"	value="<%=DataUtility.getStringData(bean.getEmailId())%>">

						<font color="red"> <%=ServletUtility.getErrorMessage("emailId", request)%></font>

					</td>
				</tr>

				<tr>
					<th align="left">Date Of Birth <font color="red">*</font></th>
					<td>
					<input type="text" name="dob" size="19" placeholder="dd/mm/yyyy"id="datepicker"  readonly="readonly"  value="<%=DataUtility.getDateString(bean.getDob())%>"> 
						<font color="red"> <%=ServletUtility.getErrorMessage("dob", request)%></font>

					</td>
				</tr>


				<tr>
					<th align="left">Mobile No <font color="red">*</font></th>
					<td>
					<input type="text" name="mobNo" size="19" maxlength="10" placeholder="Mobile No"	value="<%=DataUtility.getStringData(bean.getMobNo())%>"> 
					<font color="red"> <%=ServletUtility.getErrorMessage("mobNo", request)%></font>

					</td>
				</tr>

				<tr>
					<th align="left">Course Name <font color="red">*</font></th>

					<td><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), coursel)%>
						<font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%>
					</font>
				</tr>
				<tr>
					<th align="left">Subject Name <font color="red">*</font></th>

					<td><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectl)%>
						<font color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%>
					</font></td>
				
				<tr>
					<th align="left">College Name <font color="red">*</font></th>
					
					<td><%=HTMLUtility.getList("collegeId", String.valueOf(bean.getCollegeId()), collegel)%>
						<font color="red"> <%=ServletUtility.getErrorMessage("collegeId", request)%>
					</font></td>
				</tr>




				<tr>
					<th></th>

					<td colspan="2">
					  <%
     			if (bean.getId() > 0)
		    	 {
		     		%>
						
						
						<input type="submit" name="operation" value="<%=FacultyCtl.OP_UPDATE%>">   
 						<input type="submit" name="operation" value="<%=FacultyCtl.OP_CANCEL%>">
 		<%
 	} else {
 %>
						<input type="submit" name="operation" value="<%=FacultyCtl.OP_SAVE%>"> 
						
						<input type="submit" name="operation" value="<%=FacultyCtl.OP_RESET%>"></td>
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