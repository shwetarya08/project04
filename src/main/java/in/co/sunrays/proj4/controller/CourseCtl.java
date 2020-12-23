package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * * Course functionality Controller. Performs operation for add, update and get
 * Course
 *
 * @author FrontController 
 * @version 1.0
 * 
 */

@WebServlet(name="CourseCtl",urlPatterns={"/ctl/CourseCtl"})

public class CourseCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(CourseCtl.class);
	  

	@Override
	protected boolean validate(HttpServletRequest request)
	{
		log.debug("CourseCtl Method validate Started");
		boolean pass=true;
		

		if (DataValidator.isNull(request.getParameter("name"))) {
			
			request.setAttribute("name", PropertyReader.getValue("error.require", "Name"));
			
			pass = false;
		
		}
		
		  
        else if(!DataValidator.isName(request.getParameter("name")))
        {
        	request.setAttribute("name", PropertyReader.getValue("error.name","Course Name"));
        	pass=false;
        }
        	
		
		
		if(DataValidator.isNull(request.getParameter("description")))
		{
			request.setAttribute("description", PropertyReader.getValue("error.require","Description"));
			
			pass=false;
		}
	
		 if (DataValidator.isNull(request.getParameter("duration")))
		 {
	            request.setAttribute("duration",PropertyReader.getValue("error.require", "Duration"));
	            
	            pass = false;
	        }

	        log.debug("CourseCtl Method validate Ended");

	        return pass;
	    }
	

	   
	@Override
	    protected BaseBean populateBean(HttpServletRequest request) 
	{

	        log.debug("CourseCtl Method populatebean Started");

	        CourseBean bean = new CourseBean();

	        bean.setId(DataUtility.getLong(request.getParameter("id")));
	        bean.setName(DataUtility.getString(request.getParameter("name")));
	        bean.setDescription(DataUtility.getString(request.getParameter("description")));
	        bean.setDuration(DataUtility.getString(request.getParameter("duration")));

	      //  populateDTO(bean, request);

	        log.debug("CourseCtl Method populatebean Ended");

	        return bean;
	    }

	  /**
     * Contains DIsplay logics
     */
	    
	    protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	    {
	        log.debug("CourseCtl Method doGet Started");

	        System.out.println("In do get");

	        String op = DataUtility.getString(request.getParameter("operation"));

	       
	        CourseModel model = new CourseModel();

	        long id = DataUtility.getLong(request.getParameter("id"));
	       
	        if (id > 0 || op != null)
	        {
	            CourseBean bean;
	            try 
	            {
	                bean = model.findByPK(id);
	                ServletUtility.setBean(bean, request);
	            }
	            catch (ApplicationException e) 
	            {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }
	        }
	        ServletUtility.forward(getView(), request, response);
	        log.debug("CourseCtl Method doGetEnded");
	    }


	    
	    /**
         * Contains Submit logics
         */ 
  
	    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
	    {
	        
	    	log.debug("CourseCtl Method doGet Started");

	        System.out.println("In course doGet");

	        String op = DataUtility.getString(request.getParameter("operation"));

	    
	        CourseModel model = new CourseModel();

	        long id = DataUtility.getLong(request.getParameter("id"));

	        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op))
	        {

	        	System.out.println("college ctl first");
	            CourseBean bean =  (CourseBean) populateBean(request);

	            try {
	                if (id > 0)
	                {
	                    model.update(bean);
	                    ServletUtility.setBean(bean, request);
		                ServletUtility.setSuccessMessage("Data is successfully updated",request);

	                } 
	                else 
	                {
	                	System.out.println("college  up the add first");
	                    long pk = model.add(bean);
	                    System.out.println("aa gaia expection");
	                    //bean.setId(pk);
	                    ServletUtility.setBean(bean, request);
		                ServletUtility.setSuccessMessage("Data is successfully saved",request);

	                }

	                
	            }
	            
	            
	            catch (ApplicationException e) 
	            {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            } 
	            
	            
	            catch (DuplicateRecordException e) 
	            {
	                ServletUtility.setBean(bean, request);
	                ServletUtility.setErrorMessage("Course already exists", request);
	            }

	        }
	       
	        else if (OP_DELETE.equalsIgnoreCase(op)) 
	        {

	            CourseBean bean =  (CourseBean) populateBean(request);
	            try
	            {
	                model.delete(bean);
	                ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request,response);
	                return;
	                
	            } 
	            
	            catch (ApplicationException e) 
	            {
	                log.error(e);
	                ServletUtility.handleException(e, request, response);
	                return;
	            }

	        } 
	        
	        
	        else if (OP_CANCEL.equalsIgnoreCase(op))
	        {

	            ServletUtility.redirect(ORSView.COURSE_LIST_CTL, request, response);
	            return;

	        }

	        ServletUtility.forward(getView(), request, response);

	        log.debug("CourseCtl Method doPOst Ended");
	    }

	 
	
	@Override
	protected String getView() {
		
		return ORSView.COURSE_VIEW;
	}

}
