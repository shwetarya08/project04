package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;



/**
 * College functionality Controller. Performs operation for add, update, delete
 * and get College
 *
 * @author FrontController
 * @version 1.0
 * 
 */

@WebServlet(name="CollegeCtl",urlPatterns={"/ctl/CollegeCtl"})
public class CollegeCtl extends BaseCtl 
{

    private static final long serialVersionUID = 1L;

    private static Logger log = Logger.getLogger(CollegeCtl.class);

    @Override
    protected boolean validate(HttpServletRequest request) 
    {

    	System.out.println("validate of collegectl");
        log.debug("CollegeCtl Method validate Started");

        boolean pass = true;

        System.out.println("validate of collegectl=========");
        
        if (DataValidator.isNull(request.getParameter("name"))) 
        {
            request.setAttribute("name",PropertyReader.getValue("error.require", "Name"));
            pass = false;
        }
        
        
        else if(!DataValidator.isString(request.getParameter("name")))
        {
        	request.setAttribute("name", PropertyReader.getValue("error.valid","College"));
        	pass=false;
        }
        	

        if (DataValidator.isNull(request.getParameter("address"))) 
        {
            request.setAttribute("address",PropertyReader.getValue("error.require", "Address"));
            pass = false;
        }

        if (DataValidator.isNull(request.getParameter("state"))) 
        {
            request.setAttribute("state",PropertyReader.getValue("error.require", "State"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("city"))) 
        {
            request.setAttribute("city",PropertyReader.getValue("error.require", "City"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("phoneNo"))) 
        {
            request.setAttribute("phoneNo",PropertyReader.getValue("error.require", "Phone No"));
            pass = false;
        }

        log.debug("CollegeCtl Method validate Ended");

        return pass;
    }

    
    
    @Override
    protected BaseBean populateBean(HttpServletRequest request) 
    {

        log.debug("CollegeCtl Method populatebean Started");

       System.out.println("populate/clgCtl");
        
        CollegeBean bean = new CollegeBean();

        System.out.println("populate/clgCtl........+++++");
        
        bean.setId(DataUtility.getLong(request.getParameter("id")));

        bean.setName(DataUtility.getString(request.getParameter("name")));

        bean.setAddress(DataUtility.getString(request.getParameter("address")));

        bean.setState(DataUtility.getString(request.getParameter("state")));

        bean.setCity(DataUtility.getString(request.getParameter("city")));

        bean.setPhoneNo(DataUtility.getString(request.getParameter("phoneNo")));

       // populateDTO(bean, request);

        log.debug("CollegeCtl Method populatebean Ended");

        return bean;
    }

  

    /**
     * Contains display logic
     */
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {

    	
    	System.out.println("doget of collegectl.1");
    	  
        String op = DataUtility.getString(request.getParameter("operation"));

        System.out.println("doget of collegectl");
        
        CollegeModel model = new CollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        System.out.println("collegeView++++++++++++");
        
        
        if (id > 0) 
        {
            CollegeBean bean;
            try {
                bean = model.findByPK(id);
                ServletUtility.setBean(bean, request);
                
                System.out.println("collegeView================");
                
            } 
            catch (ApplicationException e) 
            {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
        }

        ServletUtility.forward(getView(), request, response);
    }

    /**
     * Contains Submit logics
     */
    
    protected void doPost(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException
    {

        log.debug("CollegeCtl Method populatebean Started");

        System.out.println("dopost of collegectl");
        
        String op = DataUtility.getString(request.getParameter("operation"));

      //  System.out.println("dopost........second");
        
        CollegeModel model = new CollegeModel();

        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op))
        {
        	
        	System.out.println("dopost of colgctl...2");
        	CollegeBean bean = (CollegeBean) populateBean(request);

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

                  //  System.out.println("dopost........add");
                }
               
            } 
            catch (ApplicationException e)
            {
                e.printStackTrace();
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }
            catch (DuplicateRecordException e)
            {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("College Name already exists",request);
            }

        } 
     /*
        else if (OP_DELETE.equalsIgnoreCase(op)) 
        {

            CollegeBean bean = (CollegeBean) populateBean(request);
            try 
            {
                model.delete(bean);
                ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request,response);
                return;

            } catch (ApplicationException e)
            {
                log.error(e);
                ServletUtility.handleException(e, request, response);
                return;
            }

        }*/
        
        else if (OP_RESET.equalsIgnoreCase(op))
    	{
    		ServletUtility.redirect(ORSView.COLLEGE_CTL, request, response);
    		return;
    	}
        else if (OP_CANCEL.equalsIgnoreCase(op)) 
        {

            ServletUtility.redirect(ORSView.COLLEGE_LIST_CTL, request, response);
            return;

        }

        ServletUtility.forward(getView(), request, response);

        log.debug("CollegeCtl Method doGet Ended");
    }
   

    @Override
    protected String getView()
    {
    	System.out.println("getview of collegectl");
        return ORSView.COLLEGE_VIEW;
    }

}