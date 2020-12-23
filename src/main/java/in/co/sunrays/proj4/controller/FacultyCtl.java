package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * * Faculty functionality Controller. Performs operation for add, update and get
 * Faculty
 *
 * @author FrontController
 * @version 1.0
 *
 */


@WebServlet(name = "FacultyCtl", urlPatterns = { "/ctl/FacultyCtl" })
public class FacultyCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(FacultyCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModel coursemodel = new CourseModel();

		try {
			List listcourse = coursemodel.list();
			request.setAttribute("courseList", listcourse);
		}
		catch (ApplicationException e) {
			log.error(e);
		}

		SubjectModel subjectmodel = new SubjectModel();

		try {
			List listsubject = subjectmodel.list();
			request.setAttribute("subjectList", listsubject);
		}

		catch (ApplicationException e) {
			log.error(e);
		}

		CollegeModel model = new CollegeModel();
		try {
			List l = model.list();
			request.setAttribute("collegeList", l);
		}

		catch (ApplicationException e) {
			log.error(e);
		}

	}

	protected boolean validate(HttpServletRequest request) {

		log.debug("facultyCtl Method validate Started");

		boolean pass = true;

	

		if (DataValidator.isNull(request.getParameter("collegeId")))

		{
			request.setAttribute("collegeId", PropertyReader.getValue("error.require", "College Name"));

			pass = false;
		}

		/*if (DataValidator.isNull(request.getParameter("courseName")))

		{
			request.setAttribute("courseName", PropertyReader.getValue("error.require", "Course Name"));

			pass = false;
		}*/

		if (DataValidator.isNull(request.getParameter("firstName")))

		{
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));

			pass = false;
		}
		
		
		  
        else if(!DataValidator.isString(request.getParameter("firstName")))
        {
        	request.setAttribute("firstName", PropertyReader.getValue("error.valid","First Name"));
        	pass=false;
        }
        	

		if (DataValidator.isNull(request.getParameter("lastName")))

		{
			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("subjectId")))

		{
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));

			pass = false;
		}
		if (DataValidator.isNull(request.getParameter("courseId")))

		{
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));

			pass = false;
		}
		/*if (DataValidator.isNull(request.getParameter("subjectName")))

		{
			request.setAttribute("subjectName", PropertyReader.getValue("error.require", "Subject Name"));

			pass = false;
		}*/

		/*if (DataValidator.isNull(request.getParameter("collegeName")))

		{
			request.setAttribute("collegeName", PropertyReader.getValue("error.require", "College Name"));

			pass = false;
		}
*/
		if (DataValidator.isNull(request.getParameter("qualification")))

		{
			request.setAttribute("qualification", PropertyReader.getValue("error.require", "Qualification"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("emailId")))

		{
			request.setAttribute("emailId", PropertyReader.getValue("error.require", "Email Id"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("dob")))

		{
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Dob"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobNo")))

		{
			request.setAttribute("mobNo", PropertyReader.getValue("error.require", "Mobile"));

			pass = false;
		}
		
		   else if(!DataValidator.isMobNo(request.getParameter("mobNo")))
           {
           	request.setAttribute("mobNo", PropertyReader.getValue("error.roll","Mobile No"));
           	pass=false;
           }
           
		
		
		
		
		
		
		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserCtl Method populatebean Started");

		FacultyBean bean = new FacultyBean();

		bean.setCollegeId(DataUtility.getLong(request.getParameter("collegeId")));

		
		bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		bean.setLastName(DataUtility.getString(request.getParameter("lastName")));
		
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));


		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

	/*	bean.setSubjectName(DataUtility.getString(request.getParameter("subjectName")));

		bean.setCollegeName(DataUtility.getString(request.getParameter("collegeName")));*/

		bean.setQualifiaction(DataUtility.getString(request.getParameter("qualification")));

		bean.setEmailId(DataUtility.getString(request.getParameter("emailId")));

		bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		bean.setMobNo(DataUtility.getString(request.getParameter("mobNo")));

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		log.debug("FacultyCtl Method populatebean Ended");

		return bean;
	}
	
	
	  /**
     * Contains DIsplay logics
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("FacultyCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model

		FacultyModel model = new FacultyModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null) {

			System.out.println("in id > 0  condition"+id);

			FacultyBean bean;

			try {
				bean = model.findByPK(id);
				ServletUtility.setBean(bean, request);
			}

			catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}

		ServletUtility.forward(getView(), request, response);
		log.debug("FacultyCtl Method doGet Ended");
	}
	
	  /**
     * Contains Submit logics
     */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("FacultyCtl Method doPost Started");
       
		System.out.println("in do post of faculty ctl");
		
		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		
		System.out.println("doPost faculty");
		
		FacultyModel model = new FacultyModel();

		long id = DataUtility.getLong(request.getParameter("id"));
		System.out.println("doPost faculty"+id);
		
		
		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			FacultyBean bean = (FacultyBean) populateBean(request);

			try {
				System.out.println("in update");
				if (id > 0) {
					System.out.println("in condition");
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully updated", request);

				}
				

				else

				{
					long pk = model.add(bean);
					//bean.setId(pk);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully saved", request);

				}
				
			}

			catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

			catch (DuplicateRecordException e) {
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("Email id already exists", request);
			}
			
			
			
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			FacultyBean bean = (FacultyBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
				return;
			}

			catch (ApplicationException e)

			{
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}

		}

		else if (OP_CANCEL.equalsIgnoreCase(op)) {

			ServletUtility.redirect(ORSView.FACULTY_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("FacultyCtl Method doPostEnded");
	}

	@Override
	protected String getView() {

		return ORSView.FACULTY_VIEW;
	}

}
