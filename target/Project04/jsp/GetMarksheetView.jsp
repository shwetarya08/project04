<%@page import="in.co.sunrays.proj4.controller.MarksheetCtl"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.util.HashMap"%>
<%@page import="in.co.sunrays.proj4.controller.GetMarksheetCtl"%>
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

<html>

<body>
    <%@ include file="Header.jsp"%>

    <jsp:useBean id="bean" class="in.co.sunrays.proj4.bean.MarksheetBean" scope="request">
  </jsp:useBean>

    <center>
        <h1>Get Marksheet</h1>

        <font color="red"> <%=ServletUtility.getErrorMessage(request)%>
        </font>

        <H2>
            <font color="green"> <%=ServletUtility.getSuccessMessage(request)%>
            </font>
        </H2>

        <form action="<%=ORSView.GET_MARKSHEET_CTL%>" method="post">

            <input type="hidden" name="id" value="<%=bean.getId()%>">
            
            <table>
                <label><b>RollNo :</b></label>&emsp;
                
                <input type="text" name="rollNo" placeholder="Ex-50BE01" value="<%=ServletUtility.getParameter("rollNo", request)%>">&emsp;
                
                <input type="submit" name="operation" value="<%=GetMarksheetCtl.OP_GO%>">
                
                <br>
                
               <h3> <font color="red"><%=ServletUtility.getErrorMessage("rollNo", request)%>
                
                </font></h3>

                <%
                    if (bean.getName()!=null && bean.getName().trim().length() > 0) {
       
                %>
</table>
<br>
<table width="70%" align="center" border="2px" cellspacing="1" cellpadding="5">
               
               <tr><th colspan="6" align="center"><b>Marksheet</b></th></tr>
               
                <tr>
                    <th colspan="2" align="left"><b>Rollno :
                    <%=DataUtility.getStringData(bean.getRollNo())%>
                </b></th>
                	
              
                  <th colspan="5" align="left">  Name :
                    <%=DataUtility.getStringData(bean.getName())%>
                    </th>
               
                </tr>
               
               
               <tr><td><b>Subject</b></td><td>Max Marks</td><td >Min Marks</td><td>Obtained Marks</td><td>Remark</td><td>Grade</td>
                
                <tr>
                    <td>Physics :</td>
                  <td align="center"><%=100%></td><td align="center"><%=35%></td> <td align="center"><%=DataUtility.getStringData(bean.getPhysics())%></td>
               <td>
               
                   <%
									String physicsS = ((100 >= DataUtility.getIntegerData(bean.getPhysics()))
												&& (DataUtility.getIntegerData(bean.getPhysics()) >= 35)) ? "Pass" : "Fail";
								%><%=physicsS %>
               
               </td> <td>
               
               
               <%
									if (100 >= DataUtility.getIntegerData(bean.getPhysics())
												&& DataUtility.getIntegerData(bean.getPhysics()) >= 80) {
								%>A<%
									} else
								%>
								<%
									if (79>= DataUtility.getIntegerData(bean.getPhysics())
												&& DataUtility.getIntegerData(bean.getPhysics()) >= 60) {
								%>B<%
									} else
								%>
								<%
									if (59 >= DataUtility.getIntegerData(bean.getPhysics())
												&& DataUtility.getIntegerData(bean.getPhysics()) >= 40) {
								%>C<%
									} else
								%>
               
               <%
									if (39 >= DataUtility.getIntegerData(bean.getPhysics())
												&& DataUtility.getIntegerData(bean.getPhysics()) >= 0) {
								%>D<%
									}
								%>
               
               
               
               
               </td> 
                </tr>
               
                <tr>
                    <td>Chemistry :</td>
                    <td align="center"><%=100%></td>  <td align="center"><%=35%></td><td align="center"><%=DataUtility.getStringData(bean.getChemistry())%></td>
               
               <td>
               
								<%
									String chemistryY = ((100 >= DataUtility.getIntegerData(bean.getChemistry()))
												&& (DataUtility.getIntegerData(bean.getChemistry()) >= 35)) ? "Pass" : "Fail";
								%>
						
               <%=chemistryY %>
               </td> <td>
               
               
            <%
									if (100 >= DataUtility.getIntegerData(bean.getChemistry())
												&& DataUtility.getIntegerData(bean.getChemistry()) >= 80) {
								%>A<%
									} else
								%>
								<%
									if (79 >= DataUtility.getIntegerData(bean.getChemistry())
												&& DataUtility.getIntegerData(bean.getChemistry()) >= 60) {
								%>B<%
									} else
								%>
								<%
									if (59 >= DataUtility.getIntegerData(bean.getChemistry())
												&& DataUtility.getIntegerData(bean.getChemistry()) >= 40) {
								%>C<%
									} else
								%>
								<%
									if (39 >= DataUtility.getIntegerData(bean.getChemistry())
												&& DataUtility.getIntegerData(bean.getChemistry()) >= 0) {
								%>D<%
									}
								%>
								
								
               
               </td>  </tr>
                
                <tr>
                    <td>Maths :</td>
                    <td align="center"><%=100%></td> <td align="center"><%=35%></td> <td align="center"><%=DataUtility.getStringData(bean.getMaths())%></td>
<td>


<%
									String mathsS = ((100 >= DataUtility.getIntegerData(bean.getMaths()))
												&& (DataUtility.getIntegerData(bean.getMaths()) >= 35)) ? "Pass" : "Fail";
								%><%=mathsS %>
						


</td> <td>


<%
									if (100 >= DataUtility.getIntegerData(bean.getMaths())
												&& DataUtility.getIntegerData(bean.getMaths()) >= 80) {
								%>A<%
									} else
								%>
								<%
									if (79 >= DataUtility.getIntegerData(bean.getMaths())
												&& DataUtility.getIntegerData(bean.getMaths()) >= 60) {
								%>B<%
									} else
								%>
								<%
									if (59 >= DataUtility.getIntegerData(bean.getMaths())
												&& DataUtility.getIntegerData(bean.getMaths()) >= 40) {
								%>C<%
									} else
								%>
								<%
									if (39 >= DataUtility.getIntegerData(bean.getMaths())
												&& DataUtility.getIntegerData(bean.getMaths()) >= 0) {
								%>D<%
									}
								%>



</td> 
                </tr>
                <tr><td></td><td align="center"><%=300 %></td><td></td><td align="center"><%
									int marksTotal = (DataUtility.getIntegerData(bean.getPhysics())
												+ DataUtility.getIntegerData(bean.getChemistry())
												+ DataUtility.getIntegerData(bean.getMaths()));
								%><%=marksTotal%></td><td></td><td></td> </tr>
               
                <tr>
                
                <td colspan="2"><b>Grand Total:</b></td><td> <%=marksTotal%><b> Out of </b><%=300 %></td>
							<td></td>
							<td align="center">
								</td>
                  
                  
                  
                  
                  
                  </td><td colspan="2"></td>
                </tr>
           <tr><th colspan="2" align="left"><b>Result:</b></th> <td colspan="4">
           
           
           <%
									String physics = ((100 >= DataUtility.getIntegerData(bean.getPhysics()))
												&& (DataUtility.getIntegerData(bean.getPhysics()) >= 33)) ? "Pass" : "Fail";
								%>
						
						
						
								<%
									String chemistry = ((100 >= DataUtility.getIntegerData(bean.getChemistry()))
												&& (DataUtility.getIntegerData(bean.getChemistry()) >= 33)) ? "Pass" : "Fail";
								%>
						
						
					
						<%
									String maths = ((100 >= DataUtility.getIntegerData(bean.getMaths()))
												&& (DataUtility.getIntegerData(bean.getMaths()) >= 33)) ? "Pass" : "Fail";
								%>
						
						
						
						
						
							<%
									String result = ((physics.equals("Pass")) && (chemistry.equals("Pass")) && (maths.equals("Pass")))
												? "Pass" : "Fail";
							
								%><%=result%>
						
           
           
           </td></tr>
             <tr><th colspan="2" align="left"><b>Percent/Division:</b></th> <td colspan="5">
             
             <%
             

				  double  percent= (double)marksTotal/300;
				  float percent1=(float)percent*100F;
				  DecimalFormat df3 = new DecimalFormat("#.##");
				  
             %>
             
             
             
             <%=df3.format(percent1)%>%</td></tr>
            </table>
            <%
                }
            %>
           
        </form>
       <br><br> <br><br> <br><br>
        <%@ include file="Footer.jsp"%>
    </center>
    
</body>

</html>