<%@page import="in.co.sunrays.proj4.util.DataValidator"%>
<%@page import="in.co.sunrays.proj4.controller.MarksheetCtl"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
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
	<form action="<%=ORSView.MARKSHEET_CTL%>" method="post">
		<%@ include file="Header.jsp"%>

<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean" scope="request"></jsp:useBean>		

		<%
			List studentl = (List) request.getAttribute("studentList");
		%>



		<%
			if (bean.getId() > 0) {
		%>

		<h1 align="center">Update Marksheet</h1>

		<%
			}

			else

			{
		%>
		<h1 align="center">Add Marksheet</h1>

		<%
			}
		%>

		</head>
		<body>
			<div id="container">



				<div id="subcontent">

					<h2 align="center">
						<font color="red"><%=ServletUtility.getErrorMessage(request)%></font>

						<font color="green"> <%=ServletUtility.getSuccessMessage(request)%></font>
					</H2>

					<input type="hidden" name="id" value="<%=bean.getId()%>"> 
					<input type="hidden" name="createdBy" value="<%=bean.getCreatedBy()%>">
					<input type="hidden" name="modifiedBy"value="<%=bean.getModifiedBy()%>"> 
					<input type="hidden"name="createdDatetime" value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
					<input type="hidden" name="modifiedDatetime"value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">

					<center>

						<table align="center" style="margin-left: 40%" cellspacing="1" cellpadding="1">
<%-- 							<tr>
								<td colspan="2" height="30">
									<h1 align="center">
										<%
											if (bean.getId() > 0) {
										%>
										Update Marksheet
										<%
											}

											else {
										%>
										Add Marksheet
										<%
											}
										%>
									</h1>
								</td>

							</tr>
							<tr height="10"></tr> --%>

							<tr>
								<th align="left">RollNo <font color="red">*</font></th>
								<td><input type="text" size="19" name="rollNo" value="<%=DataUtility.getStringData(bean.getRollNo())%>"
									<%=(bean.getId() > 0) ? "readonly" : ""%>
									placeholder="Enter RollNo">&nbsp; <font color="red">
										<%=ServletUtility.getErrorMessage("rollNo", request)%></font></td>

							</tr>

							<tr>
								<th align="left">Student Name <font color="red">*</font></font>
								</th>
								

								<td><%=HTMLUtility.getList("studentId", String.valueOf(bean.getStudentId()), studentl)%>
									<font color="red"> <%=ServletUtility.getErrorMessage("studentId", request)%>
								</font></td>

							</tr>


								<% String phy;
							
											
									
											if(bean.getPhysics()>0)
											
											{
												System.out.println(" lests set me in");
											
											 phy=DataUtility.getStringData(bean.getPhysics());
											
											}
											else
											{
												System.out.println(" lests set me in else");
											phy=DataUtility.getStringData(bean.getPhysicsS());
												
											}		
											System.out.println("phy"+phy);
									 
									
									
							 %>


							<tr>
								<th align="left">Physics <font color="red">*</font></th>
								<td>
									 <input type="text" size="19" name="physics"  maxlength="3"
									value="<%=phy%>" placeholder="Enter Physics"> <font color="red">
											
										<%=ServletUtility.getErrorMessage("physics", request)%>
								</font>
								</td>
							</tr>

								<% String chem;
											
									
											if(bean.getChemistry()>0)
											
											{
											
											 chem=DataUtility.getStringData(bean.getChemistry());
											
											}
											else
											{
												chem=DataUtility.getStringData(bean.getChemistryY());
												
											}		
									 
									
									
							 %>



							<tr>
								<th align="left">Chemistry <font color="red">*</font></th>
								<td>
									
							 <input type="text" size="19" name="chemistry"  maxlength="3"value="<%=chem %>"  placeholder="Enter Chemistry"> <font color="red">
										<%=ServletUtility.getErrorMessage("chemistry", request)%>
								</font>
								</td>
							</tr>
									
									
									<% String s1;
											
									
											if(bean.getMaths()>0)
											
											{
											
											 s1=DataUtility.getStringData(bean.getMaths());
											
											}
											else
											{
											s1=DataUtility.getStringData(bean.getMathsS());
												
											}		
									 
									
									
							 %>
							<tr>
								<th align="left">Maths <font color="red">*</font></th>
								<td>
									 <input type="text" size="19"  name="maths" maxlength="3"
									value="<%= s1%>"
										placeholder="Enter Maths"> <font color="red"> <%=ServletUtility.getErrorMessage("maths", request)%>
								</font>
								</td>

							</tr>

							<tr>
								<th></th>

								<td colspan="2">
									<%
										if (bean.getId() > 0) {
									%> <input type="submit" name="operation"
									value="<%=MarksheetCtl.OP_UPDATE%>">
									 <input
									type="submit" name="operation"
									value="<%=MarksheetCtl.OP_CANCEL%>"> <%
 	} else {
 %> <input
									type="submit" name="operation"
									value="<%=MarksheetCtl.OP_SAVE%>">
									<input type="submit"
									name="operation" value="<%=MarksheetCtl.OP_RESET%>"> 
									<%
 	}
 %>

								</td>
							</tr>

						</table>
					</center>
				</div>
	</form>





	<%@ include file="Footer.jsp"%>
</body>
</html>