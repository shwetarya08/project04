<%@page import="in.co.sunrays.proj4.model.RoleModel"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="java.util.Iterator"%>
<%@page import="in.co.sunrays.proj4.controller.UserListCtl"%>
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
		function checkAll(bx) {

			var form = bx.form;
			var isChecked = bx.checked;
			for (var i = 0; i < form.length; i++) {

				if (form[i].type == 'checkbox') {

					form[i].checked = isChecked;

				}

			}

		}

		function checkAl(bx) {

			document.getElementById("UNCHECK").checked = false;

		}
	</script>



	<form action="<%=ORSView.USER_LIST_CTL%>" method="post">
		<%@include file="Header.jsp"%>
		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.UserBean"
			scope="request"></jsp:useBean>

		<%
			List l = (List) request.getAttribute("roleList");
			System.out.println("rolelist:" + l);
		%>


		<%
			if (l.size() != 0) {
		%>
		<center>
			<h1>User List</h1>

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
			
			


			<table width="80%">
				<tr>
					<td align="center"><b><label>First Name :</b></label> 
					<input type="text" name="firstName" placeholder="First Name" value="<%=ServletUtility.getParameter("firstName",request)%>">
						&emsp; 
						<label><b>Login Id:</b></label> 
						<input type="text" name="login" placeholder="LoginId" value="<%=ServletUtility.getParameter("login", request)%>">
						&emsp; 
						<label><b>Role</b></label> <%=HTMLUtility.getList("roleId", String.valueOf(bean.getRoleId()), l)%>
						
						<input type="submit" name="operation" value="<%=UserListCtl.OP_SEARCH%>"> 
						<input type="submit" name="operation" value="<%=UserListCtl.OP_RESET%>"></td>
				</tr>
			</table>
			<br>

			<%
				List list = ServletUtility.getList(request);
					if (list.size() == 0) {
					} 
					
					else {
			%>


			<table border="1" width="80%">
				<tr>
					<th align="left"><input type="checkbox" id="UNCHECK"
						onclick="checkAll(this)">Select</th>
					<th>S.No</th>
					<th>FirstName</th>
					<th>LastName</th>
					<th>LoginId</th>
					<th>Mobile No</th>
					<th>Role</th>
					<th>Gender</th>
					<th>DOB</th>
					<th>Edit</th>
				</tr>



				<%
					int pageNo = ServletUtility.getPageNo(request);

					int pageSize = ServletUtility.getPageSize(request);

					int index = ((pageNo - 1) * pageSize) + 1;

							/*   List list = ServletUtility.getList(request); */

							Iterator<UserBean> it = list.iterator();

							RoleModel RM = new RoleModel();
							RoleBean RB = new RoleBean();

							while (it.hasNext()) {
								bean = it.next();
								RB = RM.findByPK(bean.getRoleId());
				%>
				<tr>
					<td><input type="checkbox" name="ids" onclick="checkAl(this)"
						value="<%=bean.getId()%>" <%=(bean.getRoleId() == 1) ? "disabled" : ""%>></td>

					<td align="center"><%=index++%></td>

					<td align="center"><%=bean.getFirstName()%></td>

					<td align="center"><%=bean.getLastName()%></td>

					<td align="center"><%=bean.getLogin()%></td>

					<td align="center"><%=bean.getMobileNo()%></td>

					<td align="center"><%=RB.getName()%></td>

					<td align="center"><%=bean.getGender()%></td>

					<td align="center"><%=bean.getDob()%></td>
					
					<td align="center">
						<%
							if (bean.getRoleId() != 1) {
						%> <a href="UserCtl?id=<%=bean.getId()%>">Edit</a>
						
				 <%
				 	} else {
				 %>
						---
				</tr>
				<%
					}
					}
				%>
				</td>
			</table>
			<table width="80%">
				<tr>

					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_PREVIOUS%>"
						<%=(pageNo == 1) ? "disabled" : ""%>></td>

					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEW%>"></td>

					<td><input type="submit" name="operation"
						value="<%=UserListCtl.OP_DELETE%>"></td>

					<td align="right"><input type="submit" name="operation"
						value="<%=UserListCtl.OP_NEXT%>"
						<%=(list.size() < pageSize) ? "disabled" : ""%>></td>

				</tr>
			</table>

			<input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
				type="hidden" name="pageSize" value="<%=pageSize%>">



			<%
				}
				 } 
			%>
		
	</form>

	</center>
	<br>
	<br>
	<br>
	<br>
	<br>
	<br>
	<%@include file="Footer.jsp"%>

 
</body>
</html>