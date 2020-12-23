package in.co.sunrays.proj4.controller;


import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * * Subject functionality Controller. Performs operation for add, update and get
 * Subject
 *
 * @author FrontController
 * @version 1.0
 *
 */

@WebServlet(name="SubjectCtl",urlPatterns={"/ctl/SubjectCtl"})
public class SubjectCtl extends BaseCtl {
	
	 private static final long serialVersionUID = 1L;

	    private static Logger log = Logger.getLogger(SubjectCtl.class);

	    @Override
	    protected void preload(HttpServletRequest request) {
	        CourseModel model = new CourseModel();
	        try
	        {
	            List l = model.list();
	            request.setAttribute("courseList", l);
	        } 
	        
	        catch (ApplicationException e)
	        {
	            log.error(e);
	        }

	    }
	    

	    protected boolean validate(HttpServletRequest request) {

	    	System.out.println("inside validate");
	    	
            log.debug("SubjectCtl Method validate Started");

            boolean pass = true;


            if(DataValidator.isNull(request.getParameter("name")))
            
            {
            	request.setAttribute("name", PropertyReader.getValue("error.require","Name"));
            	
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
            	
            		   pass = false;
            	}
            
        	
            if(DataValidator.isNull(request.getParameter("courseId")))
            
            {
            	request.setAttribute("courseId", PropertyReader.getValue("error.require","Course Name"));
            	
            		   pass = false;
            		   	   
            	}
            
            
            
          /*  else if (DataValidator.isNotNull(request.getParameter("courseId"))) {
    			if ("----Select----".equals(request.getParameter("courseId"))) {
    				request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course name"));
    				pass = false;
    			}
            
            }*/
            
            
            
            
            System.out.println("validate end"+pass);
            log.debug("SubjectCtl Method validate Ended");
			return pass;
            
	    }  
	    
	    
            
            
            protected BaseBean populateBean(HttpServletRequest request) {

	            log.debug("SubjectCtl Method populatebean Started");

	            SubjectBean bean = new SubjectBean();

	            bean.setId(DataUtility.getLong(request.getParameter("id")));

	            bean.setName(DataUtility.getString(request.getParameter("name")));

	            bean.setDescription(DataUtility.getString(request.getParameter("description")));

	           bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

	           System.out.println("courname is---------->"+request.getParameter("courseId"));

	           //bean.setCourseName(DataUtility.getString(request.getParameter("courseId")));
                  
	          
	            log.debug("SubjectCtl Method populatebean Ended");
             
	            return bean;

            }
            
            /**
	         * Contains DIsplay logics
	         */
            
            protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
	            
	        	log.debug("SubjectCtl Method doGet Started");
	            System.out.println("subject doGet ");
	        	
	            String op = DataUtility.getString(request.getParameter("operation"));
	        	
	            SubjectModel model = new SubjectModel();
	            
	        	long id = DataUtility.getLong(request.getParameter("id"));

	        	// get model
	            
	        	
	        	
	            
	        	 System.out.println("subject doget");
	        	
	            if (id > 0 || op != null) {
	               
	        		System.out.println("in id > 0  condition"+id);
	                
	        		SubjectBean bean;
	                
	        		try {
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
		            log.debug("SubjectCtl Method doGet Ended");
		        }
	        
            
            
            /**
	         * Contains Submit logics
	         */
            
            
 protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
    
	 
	 System.out.println("in subject doPost");
 	log.debug("SubjectCtl Method doPost Started");
     
 	String op = DataUtility.getString(request.getParameter("operation"));
 	System.out.println("in subject ctl");
 	
 	SubjectModel model = new SubjectModel();
    
 	
 	long id = DataUtility.getLong(request.getParameter("id"));
     // get model
     
 	 
 	
     
 	if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {
        
 		SubjectBean bean = (SubjectBean) populateBean(request);

         try {
             if (id > 0) 
	             {
	                 model.update(bean);
	                 ServletUtility.setBean(bean, request);
			            
	                 ServletUtility.setSuccessMessage("Data is successfully updated",request);

	             } 
             
             else
             
	             {
	                 long pk = model.add(bean);
	                // bean.setId(pk);
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
                 ServletUtility.setErrorMessage("Subject already exists",request);
             }
 		} 
 	
 	
 	
 	 else if (OP_RESET.equalsIgnoreCase(op)) {

         ServletUtility
                 .redirect(ORSView.SUBJECT_CTL, request, response);
         return;

     }
 	
 	else if (OP_DELETE.equalsIgnoreCase(op))
     	{

             SubjectBean bean = (SubjectBean) populateBean(request);
             try {
                 model.delete(bean);
                 ServletUtility.setBean(bean, request);
                 
                 ServletUtility.setSuccessMessage("Data is successfully deleted",request);

                 ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request,response);
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
	
		                ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
		                return;
     			}
			          
 	
 					ServletUtility.forward(getView(), request, response);
		
			            log.debug("SubjectCtl Method doPostEnded");
			      }

	@Override
	protected String getView() {
		
		return ORSView.SUBJECT_VIEW;
	}

}
