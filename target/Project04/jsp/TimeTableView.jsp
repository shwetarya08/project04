<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.SubjectCtl"%>
<%@page import="java.util.List"%>
<%@page import="in.co.sunrays.proj4.controller.TimeTableCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!-- <!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
 -->


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>




<!-- <script>
  $(function() {
		$("#datepicker").datepicker({
			
			changeMonth : true,
			//changeYear : true,
			yearRange : "2019:2020",
			
			dateFormat: "dd/mm/yy",
		});

</script> -->
</head>
<body>



	<form action="<%=ORSView.TIMETABLE_CTL%>" method="post">

		<%@ include file="Header.jsp"%>

	
		<jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.TimeTableBean"
			scope="request"></jsp:useBean>
		<%
			List coursel = (List) request.getAttribute("courseList");

			List subjectl = (List) request.getAttribute("subjectList");
		%>

		<center>
		
		
		<%
			if (bean.getId() > 0) {
		%>

		<h1>Update TimeTable</h1>

		<%
			}

			else

			{
		%>
		<h1>Add TimeTable</h1>

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
			<input type="hidden" name="createdDatetime"value="<%=DataUtility.getTimestamp(bean.getCreatedDatetime())%>">
			<input type="hidden" name="modifiedDatetime"value="<%=DataUtility.getTimestamp(bean.getModifiedDatetime())%>">


			<table align="center" style="margin-left: 40%">
				<tr>
					<th align="left">Course Name <font color="red">*</font></th>

					<td><%=HTMLUtility.getList("courseId", String.valueOf(bean.getCourseId()), coursel)%>
						<font color="red"> <%=ServletUtility.getErrorMessage("courseId", request)%>
					</font></td>
					
				</tr>

				<tr>
					<th align="left">Subject Name <font color="red">*</font></th>

					<td><%=HTMLUtility.getList("subjectId", String.valueOf(bean.getSubjectId()), subjectl)%>
						<font color="red"> <%=ServletUtility.getErrorMessage("subjectId", request)%>
					</font> 
				</tr>


				<tr>
					<th align="left">Semester <font color="red">*</font></th>

					<td>
						<%
							HashMap map = new HashMap();
							map.put("", "------------select-------------");
							map.put("1", "1");
							map.put("2", "2");
							map.put("3", "3");
							map.put("4", "4");
							map.put("5", "5");
							map.put("6", "6");
							map.put("7", "7");
							map.put("8", "8");

							String htmlList = HTMLUtility.getList("semester", bean.getSemester(), map);
						%><%=htmlList%><font color="red"> <%=ServletUtility.getErrorMessage("semester", request)%></font>

					</td>
				
				</tr>

				<tr>
					<th align="left">Exam Date <font color="red">*</font></th>

					
					<td>
						<input type="text" size="19" name="examDate"  placeholder="dd/mm/yyyy" id="datepicker1" readonly="readonly" value="<%=DataUtility.getDateString(bean.getExamDate())%>"
						 <%=(bean.getId() > 0) ? "readonly" : ""%>>

						<font color="red"> <%=ServletUtility.getErrorMessage("examDate", request)%></font>
						
					</td>


					
				</tr>

				<tr>
					<th align="left">Time <font color="red">*</font></th>


					<td size="18">
						<%
							HashMap mapTime = new HashMap();
							mapTime.put("", "------------select-------------");
							mapTime.put("9am to 12pm", "9am to 12pm");
							mapTime.put("12pm to 3pm", "12pm to 3pm");
							mapTime.put("3pm to 6pm", "3pm to 6pm");

							String htmlListT = HTMLUtility.getList("Time", bean.getTime(), mapTime);
						%> <%=htmlListT%><font color="red"> <%=ServletUtility.getErrorMessage("Time", request)%></font>

					</td>



					
				</tr>

				<tr>
					<th></th>
					<td colspan="2">
						  
						    <%
     			if (bean.getId() > 0)
		    	 {
		     		%>
						
						
						<input type="submit" name="operation" value="<%=TimeTableCtl.OP_UPDATE%>">   
 						
 						<input type="submit" name="operation" value="<%=TimeTableCtl.OP_CANCEL%>">
 		<%
 	} else {
 %>
						<input type="submit" name="operation" value="<%=TimeTableCtl.OP_SAVE%>">
						
						<input type="submit" name="operation" value="<%=TimeTableCtl.OP_RESET%>"></td>
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