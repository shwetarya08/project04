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
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;
/**
 * User List functionality Controller. Performs operation for list, search and
 * delete operations of User
 *
 * @author FrontController
 * @version 1.0
 *
 */


@WebServlet(name="UserListCtl",urlPatterns={"/ctl/UserListCtl"})
public class UserListCtl extends BaseCtl {
	
	
	
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static Logger log = Logger.getLogger(UserListCtl.class);

	 
	    @Override
	protected void preload(HttpServletRequest request) {
		
	    	RoleModel model = new RoleModel();
	        
	    	try {
		            List l = model.list();
		            System.out.println(l);
		            request.setAttribute("roleList", l);
		        } 
	        catch (ApplicationException e) 
		    	{
		        	log.error(e);
		        }
	    }    
	    
	    	
	    	
	    	

		@Override
	    protected BaseBean populateBean(HttpServletRequest request) {
	        
	    	UserBean bean = new UserBean();

	        bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

	        bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

	        bean.setLogin(DataUtility.getString(request.getParameter("login")));
	        
	        bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));
	        
	        bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));
		       
	        
	        bean.setPassword(DataUtility.getString(request.getParameter("password")));
	       
	        bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));
	        
	        bean.setGender(DataUtility.getString(request.getParameter("gender")));
	        
	       
	        bean.setDob(DataUtility.getDate(request.getParameter("dob")));
	        
	        
	       
	        return bean;
	    }

	    /**
	     * Contains Display logics
	     */
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    	log.debug("UserListCtl doGet Start");
	    	   
	    	
	    	List list = new ArrayList();
	        
	    //	System.out.println("happy new yearrrrrrrrrrrr"+list);
	        
	    	int pageNo = 1;
	        
	    	int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

	    	UserBean bean = (UserBean) populateBean(request);
	        
	    	//String op = DataUtility.getString(request.getParameter("operation"));
	        // get the selected checkbox ids array for delete list
	        
	    	//String[] ids = request.getParameterValues("ids");
	        
	    	UserModel model = new UserModel();
	        try 
	        
		        {
	        	 
		            list = model.search(bean, pageNo, pageSize);
		          // ServletUtility.setList(list, request);
		            
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
	        log.debug("UserListCtl doGet End");
	    }

	   
	    /**
	     * Contains Display logics
	     */
	    
	    @Override
	    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       
	    	log.debug("UserListCtl doPost Start");
	        
	    	List list = null;
	         
	    	int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
	        
	    	int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

	        pageNo = (pageNo == 0) ? 1 : pageNo;
	        
	        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader .getValue("page.size")) : pageSize;
	        
	        UserBean bean = (UserBean) populateBean(request);
	        
	       String op = DataUtility.getString(request.getParameter("operation"));
	       
	        // get the selected checkbox ids array for delete list
	          
	        String[] ids = request.getParameterValues("ids");
	        
	        UserModel model = new UserModel();
	        try {

	            /*if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
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
	                ServletUtility.redirect(ORSView.USER_CTL, request, response);
	                
	                return;
 	            } 
	            
	            
	            else if (OP_DELETE.equalsIgnoreCase(op))
	            
	            {
	               pageNo = 1;
	                if (ids != null && ids.length > 0) 
	                
	                {
	                    UserBean deletebean = new UserBean();
	                    for (String id : ids) 
	                    
	                    {
	                        deletebean.setId(DataUtility.getInt(id));
	                        model.delete(deletebean);
	                        ServletUtility.setSuccessMessage("Data is successfully deleted",request);


	                    }
	                    
	            }
	                
	                
	                else
	                
	                {
	                    ServletUtility.setErrorMessage("Select at least one record", request);
	                }
	            }
	            else if (OP_RESET.equalsIgnoreCase(op))
				{
					ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
					return;
				}

	         
	            list = model.search(bean, pageNo, pageSize);
	            System.out.println("hpppppyyyyyyyy newwwww yearrrrrrrrrr"+list);
	            //ServletUtility.setList(list, request);
	            
	            if (list == null || list.size() == 0 &&!OP_DELETE.equalsIgnoreCase(op)) 
	            
	            {
	                ServletUtility.setErrorMessage("No record found ", request);
	            }
	            
	            
	            System.out.println("hpppppyyyyyyyy newwwww yearrrrrrrrrr 22222222"+list);
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
	        log.debug("UserListCtl dopost End");
	    }

	@Override
	protected String getView() {
		
		return ORSView.USER_LIST_VIEW;
	}

}
