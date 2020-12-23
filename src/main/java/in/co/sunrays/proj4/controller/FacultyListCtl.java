package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Faculty List functionality Controller. Performs operation for list, search and
 * delete operations of Faculty
 *
 * @author FrontController
 * @version 1.0
 *
 */

@WebServlet(name="FacultyListCtl",urlPatterns={"/ctl/FacultyListCtl"})
public class FacultyListCtl extends BaseCtl {

	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(FacultyListCtl.class);
	
	
	
	

	 @Override
	protected void preload(HttpServletRequest request) {
	
	 
		 CourseModel coursemodel = new CourseModel();
			
			try
			{
				List listcourse = coursemodel.list();
				request.setAttribute("courseList", listcourse);
			} 
			
			catch (ApplicationException e)
			{
				log.error(e);
			}
	 
	 
			CollegeModel collegemodel = new CollegeModel();
			
			try {
				List listcollege = collegemodel.list();
				request.setAttribute("collegeList", listcollege);
			} 
			catch (ApplicationException e)
			{
				log.error(e);
			}
			
			
	 
	 
	 }



	protected BaseBean populateBean(HttpServletRequest request){
			
			FacultyBean bean=new FacultyBean();
			
			
			bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));
			//bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));
			bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));
			bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
			bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));
			bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));
			//bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));
			//bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));
			bean.setQualifiaction(DataUtility.getString(request.getParameter("Qualification")));
			bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));
			bean.setDob(DataUtility.getDate(request.getParameter("dob")));
			bean.setMobNo(DataUtility.getString(request.getParameter("MobNo")));
			
		//	bean.setId(DataUtility.getLong(request.getParameter("id")));
	
			return bean;
	
	
	
	 }
	 
	 /**
     * Contains Display logics
     */
	 
	 protected void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException,IOException 
	 
	 {
		  
			log.debug("FacultyListCtl doGet Start");
		        
			  List list = new ArrayList();
			
		        
			  int pageNo = 1;
		        
		       
		       
		        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));
		        
		        FacultyBean bean = (FacultyBean) populateBean(request);
		     
			       
		        String op = DataUtility.getString(request.getParameter("operation"));
		        
		        FacultyModel model = new FacultyModel();
		      
			       
		        
		        try {
		            list = model.search(bean, pageNo, pageSize);
		           // ServletUtility.setList(list, request);
		           System.out.println("in doGet");
		           
		           if (list == null || list.size() == 0) 
			            {
		            	
			                ServletUtility.setErrorMessage("No record found ", request);
			            }
		           
		            ServletUtility.setList(list, request);
		           
				       
		            ServletUtility.setPageNo(pageNo, request);
		            
		            ServletUtility.setPageSize(pageSize, request);
		           
				       
		            ServletUtility.forward(getView(), request, response);
		        }
		       
		        catch (ApplicationException e) 
			        {
			            log.error(e);
			            ServletUtility.handleException(e, request, response);
			            return;
			        }
		        log.debug("SubjectListCtl doGet End");
		    }
	 
	  /**
	     * Contains Display logics
	     */
	    
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	      
		 log.debug("FacultyListCtl doPost Start");
	        
	        List list = null;
	        
	        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        
	        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));
	        
	        pageNo = (pageNo == 0) ? 1 : pageNo;
	       
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;
	      
	        FacultyBean bean = (FacultyBean) populateBean(request);
	        
	        String op = DataUtility.getString(request.getParameter("operation"));

	        String[] ids = request.getParameterValues("ids");
	        
	        FacultyModel model = new FacultyModel();

	        try {

	           /* if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
	                    || "Previous".equalsIgnoreCase(op)) 
	            
	            {*/

	                if (OP_SEARCH.equalsIgnoreCase(op)) 
		                {
		                    pageNo = 1;
		                } 
	                else if (OP_NEXT.equalsIgnoreCase(op))
		                {
		                    pageNo++;
		                } 
	                
	                else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) 
		                {
		                    pageNo--;
		                }
	                
	                
	                else if (OP_NEW.equalsIgnoreCase(op)) 
	    	            
		            {
		                ServletUtility.redirect(ORSView.FACULTY_CTL, request, response);
		                
		                return;
		            } 
	                
	                
	                
	                else if (OP_DELETE.equalsIgnoreCase(op))
	    	            
		            {
		                pageNo = 1;
		                if (ids != null && ids.length > 0) 
		                
		                {
		                    FacultyBean deletebean = new FacultyBean();
		                    for (String id : ids) 
		                    
		                    {
		                        deletebean.setId(DataUtility.getInt(id));
		                        model.delete(deletebean);
		                    }
		                    ServletUtility.setSuccessMessage("Data is successfully deleted",request);

		                }
		                
		                
		                else
		                
		                {
		                    ServletUtility.setErrorMessage("Select at least one record", request);
		                }
		            }
		            
	                
	                else if (OP_RESET.equalsIgnoreCase(op))
					{
						ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
						return;
					}
	                

	            //}
	            
	            list = model.search(bean, pageNo, pageSize);
	            
	           // ServletUtility.setList(list, request);
	            
	            if (list == null || list.size() == 0 &&!OP_DELETE.equalsIgnoreCase(op)) 
		            {
		                ServletUtility.setErrorMessage("No record found ", request);
		            }
	                 
	            	ServletUtility.setBean(bean, request);
		            	
	                   ServletUtility.setList(list, request);

			           ServletUtility.setPageNo(pageNo, request);
			           
			           ServletUtility.setPageSize(pageSize, request);
			            
			           ServletUtility.forward(getView(), request, response);

	        } 
	        
	        catch (ApplicationException e) 
		        {
		            log.error(e);
		            ServletUtility.handleException(e, request, response);
		            return;
		        }
		        log.debug("FacultyListCtl doPost End");
	    }


	 
	
	@Override
	protected String getView() {
		
		return ORSView.FACULTY_LIST_VIEW;
	}

}
