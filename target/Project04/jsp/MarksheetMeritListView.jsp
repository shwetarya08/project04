
<%@page import="java.text.DecimalFormat"%>
<%@page import="in.co.sunrays.proj4.util.DataUtility"%>
<%@page import="in.co.sunrays.proj4.controller.MarksheetMeritListCtl"%>
<%@page import="in.co.sunrays.proj4.util.ServletUtility"%>
<%@page import="in.co.sunrays.proj4.bean.MarksheetBean"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<body>
    <%@include file="Header.jsp"%>
  
  
    <center>
        <h1>Marksheet Merit List</h1>
         <tr>
                    <td colspan="8"><font color="red"><%=ServletUtility.getErrorMessage(request)%></font></td>
                </tr>

        <form action="<%=ORSView.MARKSHEET_MERIT_LIST_CTL%>" method="POST">
            <br>
            <table border="1" width="80%">
                <tr>

                    <th>S.No.</th>
                    <th>Roll No</th>
                    <th>Name</th>
                    <th>Physics</th>
                    <th>Chemistry</th>
                    <th>Maths</th>
                    <th>Total</th>
                    <th>percentage</th>

                </tr>
               
                <%
                    int pageNo = ServletUtility.getPageNo(request);
                    int pageSize = ServletUtility.getPageSize(request);
                    int index = ((pageNo - 1) * pageSize) + 1;

                    List list = ServletUtility.getList(request);
                    Iterator<MarksheetBean> it = list.iterator();

                    while (it.hasNext()) {

                        MarksheetBean bean = it.next();
                %>
                <tr>

                    <td align="center"><%=index++%></td>
                    <td align="center"><%=bean.getRollNo()%></td>
                    <td align="center"><%=bean.getName()%></td>
                    <td align="center"><%=bean.getPhysics()%></td>
                    <td align="center"><%=bean.getChemistry()%></td>
                    <td align="center"><%=bean.getMaths()%></td>
					 <td align="center">
                 	<%
									int marksTotal =  (DataUtility.getIntegerData(bean.getPhysics())
												+ DataUtility.getIntegerData(bean.getChemistry())
												+ DataUtility.getIntegerData(bean.getMaths())); 
							
												%><%=marksTotal%>
                    
              </td>
  <td align="center"><%-- <%=marksTotal/3%>%</td> --%>

 				<% double  percent= (double)marksTotal/300;
				  float percent1=(float)percent*100F;
				  DecimalFormat df3 = new DecimalFormat("#.##");
				  
             %>
						
					
						 <%=df3.format(percent1)%>%</td>
					




                </tr>

                <%
                    }
                %>
            </table>
            <table width="80%">
                <tr>
                    <td align="center"><input type="submit" name="operation"
                        value="<%=MarksheetMeritListCtl.OP_BACK%>"></td>
                </tr>
            </table>
            <input type="hidden" name="pageNo" value="<%=pageNo%>"> <input
                type="hidden" name="pageSize" value="<%=pageSize%>">
        </form>
    </center>
    <%@include file="Footer.jsp"%>
</body>
</html>