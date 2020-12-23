<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.util.HTMLUtility"%>
<%@page import="in.co.sunrays.proj4.controller.MarksheetListCtl"%>
<%@page import="in.co.sunrays.proj4.controller.BaseCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.MarksheetBean"%>
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

<%@include file="Header.jsp"%>
    
      <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean" scope="request"></jsp:useBean>

		        <%
           			 List marksheetl = (List) request.getAttribute("marksheetList");
       			%>

 
 <form action="<%=ORSView.MARKSHEET_LIST_CTL%>" method="POST">

    
		     
    
 
    <center>
        <h1>Marksheet List</h1>

       			 <tr><h2>
                    <td colspan="8"><font color="green"><%=ServletUtility.getSuccessMessage(request)%></font></td>
                </h2></tr>
           
            <tr><h2>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </h2></tr>
           
           
           
           
            <table width="80%">
                <tr>
                    <td align="center">
                    	
                    	
                    	
                    	<label>
	 						
	 						<b>RollNo :</b>
	 					
	 					</label> 
	 					
	 					<%=HTMLUtility.getList("rollNo", String.valueOf(bean.getId()), marksheetl)%>&emsp;&emsp;
					
	 					
	 					
                    	<label> 
                    		
                    		<b>Name :</b>
                    	
                    	</label> 
                    	
                    		<input type="text" name="name" placeholder="Name" value="<%=ServletUtility.getParameter("name", request)%>">&emsp;
	 					
	 					
						
							<%-- <input type="text" name="rollNo" value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
                         --%>
                        <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_SEARCH %>">
                		<input type="submit" name="operation" value="<%=MarksheetListCtl.OP_RESET %>"></td>
   
               
                </tr>
            </table>
            <br>
 
<%--  <%if(marksheetl.size() !=0){ %> --%>
 
            
            
 <%
 
 List list=ServletUtility.getList(request);
 if(list.size()==0){}else{
 
 
 %>
            
            <table border="1" width="80%">
                <tr>
                    <th align="left"><input type="checkbox" id="UNCHECK" onclick="checkAll(this)">Select All</th>
                  	 
                  	 <th>S.No.</th>
                    <th>RollNo</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
                    <th>Total</th>
                    <th>Result</th>
                    <th>Percentage%</th>
                    <th>Edit</th>
                </tr>
               
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                   // List list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext())
                    {

                       bean = it.next();
                %>
                <tr>
                    <td >
                    <input type="checkbox" name="ids" onclick="checkAl(this)" value="<%=bean.getId()%>"></td>
                  <%--   <td><%=bean.getId()%></td> --%>
                   <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getRollNo()%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getPhysics()%></td>
                    <td align="center"><%=bean.getChemistry()%></td>
                    <td align="center"><%=bean.getMaths()%></td>
                   <td align="center">
							<%
								int marksTotal = (DataUtility.getIntegerData(bean.getPhysics())
												+ DataUtility.getIntegerData(bean.getChemistry())
												+ DataUtility.getIntegerData(bean.getMaths()));
							%><%=marksTotal%>

						</td>
						<td align="center">
						
						
							<%
									String physics = ((100 >= DataUtility.getIntegerData(bean.getPhysics()))
												&& (DataUtility.getIntegerData(bean.getPhysics()) >= 35)) ? "Pass" : "Fail";
								%>
						
						
						
								<%
									String chemistry = ((100 >= DataUtility.getIntegerData(bean.getChemistry()))
												&& (DataUtility.getIntegerData(bean.getChemistry()) >= 35)) ? "Pass" : "Fail";
								%>
						
						
					
						 		<%
									String maths = ((100 >= DataUtility.getIntegerData(bean.getMaths()))
												&& (DataUtility.getIntegerData(bean.getMaths()) >= 35)) ? "Pass" : "Fail";
								%>
						
						
							<%
									String result = ((physics.equals("Pass")) && (chemistry.equals("Pass")) && (maths.equals("Pass")))
												? "Pass" : "Fail";
								%><%=result%>
						
						</td>
						
						
						
						<td align="center"> 
						
						
						  <%
             

				  double  percent= (double)marksTotal/300;
				  float percent1=(float)percent*100F;
				  DecimalFormat df3 = new DecimalFormat("#.##");
				  
             %>
						
						
						 <%=df3.format(percent1)%>%</td>
						
                    <td align="center"><a href="MarksheetCtl?id=<%=bean.getId()%>">Edit</a></td>
                </tr>

                <%
                    }
                %>
            </table>
            <table width="80%">
                <tr>
                    <td>
                    <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_PREVIOUS%>"<%=(pageNo==1)?"disabled":"" %>>
                    </td>
                    
                    <td>
                    <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_NEW%>">
                    </td>
                    
                    <td>
                    <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_DELETE%>">
                  	</td>
                    
                    <td align="right">
                    <input type="submit" name="operation" value="<%=MarksheetListCtl.OP_NEXT%>"<%=(list.size()<pageSize)?"disabled":"" %>>
                    </td>
               
                </tr>
           
            </table>
             <input type="hidden" name="pageNo" value="<%=pageNo%>">
             <input type="hidden" name="pageSize" value="<%=pageSize%>">
       <% } %>
        </form>
    </center>
    
    
    <br><br><br><br><br>
    <%@include file="Footer.jsp"%>
    
    <br>
    
<!--  <html>
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
