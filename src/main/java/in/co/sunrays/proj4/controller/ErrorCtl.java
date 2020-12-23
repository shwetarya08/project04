package in.co.sunrays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.util.ServletUtility;



/**
 * Error functionality Controller. Performs operation for error
 *
 *
 * @author FrontController
 * @version 1.0
 * 
 */


@WebServlet(name="ErrorCtl", urlPatterns={"/ErrorCtl"})
public class ErrorCtl extends BaseCtl{
	
	
	private static final Long servialVersionUID=1L;
	private static Logger log=Logger.getLogger(ErrorCtl.class);
	
	 /**
     * Concept of Display logic
     *
     */
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		log.debug( "error ctl do get started");
		ServletUtility.forward(getView(), request, response);
		log.debug(" error ctl do get end");
	}
	 /**
     * Concept of submit logic
     *
     */
 protected void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
	 log.debug("error ctl do post started");
  ServletUtility.forward(getView(), request, response);
 
   log.debug("error ctl do post end");
 }

	
	@Override
	protected String getView() {
	
		return ORSView.ERROR_VIEW;
		
	}

}
