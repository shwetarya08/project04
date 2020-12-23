package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.ServletUtility;



/**
 * Base controller class of project. It contain (1) Generic operations (2)
 * Generic constants (3) Generic work flow
 *
 * @author FrontController
 * @version 1.0
 * 
 */
public abstract class BaseCtl extends HttpServlet {
	
	
	 /**
     * operation save key constant
     */
	 	public static final String OP_SAVE = "Save";
	 	 /**
	     * operation Cancel key constant
	     */
	    public static final String OP_CANCEL = "Cancel";
	    /**
	     * operation Delete key constant
	     */
	    public static final String OP_DELETE = "Delete";
	    /**
	     * operation List key constant
	     */
	    public static final String OP_LIST = "List";
	    /**
	     * operation Search key constant
	     */
	    public static final String OP_SEARCH = "Search";
	    /**
	     * operation View key constant
	     */
	    public static final String OP_VIEW = "View";
	    /**
	     * operation Next key constant
	     */
	    public static final String OP_NEXT = "Next";
	    /**
	     * operation Previous key constant
	     */
	    public static final String OP_PREVIOUS = "Previous";
	    /**
	     * operation New key constant
	     */
	    public static final String OP_NEW = "New";
	    /**
	     * operation Reset key constant
	     */
	    public static final String OP_RESET="Reset";
	    /**
	     * operation Go key constant
	     */
	    public static final String OP_GO = "Go";
	    /**
	     * operation Back key constant
	     */
	    public static final String OP_BACK = "Back";
	    /**
	     * operation Logout key constant
	     */
	    public static final String OP_LOG_OUT = "Logout";
	    /**
	     * operation Update key constant
	     */
	    public static final String OP_UPDATE = "Update";
	    /**
	     * operation SignUP key constant
	     */
	    public static final String OP_SIGN_UP = "SignUp";
	    /**
	     * operation ForgetPassword key constant
	     */
	    public static final String OP_FORGET_PASSWORD = "ForgetPassword";
	    
	    
	    
	    /**
	     * Success message key constant
	     */
	    public static final String MSG_SUCCESS = "success";
	    
	    /**
	     * Error message key constant
	     */
	    
	    public static final String MSG_ERROR = "error";
	    
	    /**
	     * Validates input data entered by User
	     *
	     * @param request
	     * @return
	     */
	    
	    protected boolean validate(HttpServletRequest request) {
	      System.out.println("validate/basectl");
	    	return true;
	    }
	    
	    
	    
	    /**
	     * Loads list and other data required to display at HTML form
	     *
	     * @param request
	     */
	    protected void preload(HttpServletRequest request) {
	    	System.out.println("preload/basectl");
	    }
	    
	    

	    /**
	     * Populates bean object from request parameters
	     *
	     * @param request
	     * @return
	     */
	    
	    protected BaseBean populateBean(HttpServletRequest request) {
	    	 System.out.println("populate/basectl");
	    	return null;
	  
	    
	    }
	    
	    /**
	     * To provide generic work flow
	     *
	     * @param request
	     */
	  
	    @Override
	    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    	System.out.println("service/preload/basectl11");
	    	preload(request);
	    	System.out.println("service/preload/basectl");
	    	//System.out.println("in service");

	        String op = DataUtility.getString(request.getParameter("operation"));

	        if (DataValidator.isNotNull(op) && !OP_CANCEL.equalsIgnoreCase(op)
	                && !OP_VIEW.equalsIgnoreCase(op)
	                && !OP_DELETE.equalsIgnoreCase(op) && !OP_RESET.equalsIgnoreCase(op)) {
	        	
	        	//System.out.println("in service 2");
	        	
	        	 if (!validate(request)) 
	        	 {   
                     System.out.println("validate/basectl");
	                 
                     BaseBean bean = (BaseBean) populateBean(request);
	                 System.out.println("/service/popolate/basectl");
                     ServletUtility.setBean(bean, request);
	                 ServletUtility.forward(getView(), request, response);
	                 return;
	               
	                 
	             }
	        	
	        }
	        System.out.println("superrrrr");
	        super.service(request, response);
	    	System.out.println("super.service/basectl");
	  }
	    
	    
	    
	    /**
	     * Returns the VIEW page of this Controller
	     *
	     * @return
	     */
	    	
	    protected abstract String getView();

  }



