package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.TimeTableModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * * TimeTable functionality Controller. Performs operation for add, update and get
 * TimeTable
 *
 * @author FrontController
 * @version 1.0
 *
 */

@WebServlet(name = "TimeTableCtl", urlPatterns = { "/ctl/TimeTableCtl" })
public class TimeTableCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(TimeTableCtl.class);

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

	}

	protected boolean validate(HttpServletRequest request) {

		log.debug("TimetableCtl Method validate Started");

		boolean pass = true;
	
		
		
		
		

		if (DataValidator.isNull(request.getParameter("courseId")))

		{
			request.setAttribute("courseId", PropertyReader.getValue("error.require", "Course Name"));

			pass = false;
		}

		
		
		
		if (DataValidator.isNull(request.getParameter("subjectId")))

		{
			request.setAttribute("subjectId", PropertyReader.getValue("error.require", "Subject Name"));

			pass = false;
		}
		
		if (DataValidator.isNull(request.getParameter("semester")))

		{
			request.setAttribute("semester", PropertyReader.getValue("error.require", "Semester"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("examDate")))

		{
			request.setAttribute("examDate", PropertyReader.getValue("error.require", "Exam Date"));

			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("Time")))

		{
			request.setAttribute("Time", PropertyReader.getValue("error.require", "Time"));

			pass = false;
		}

		log.debug("TimeTableCtl Method validate Ended");

		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("TimeTableCtl Method populatebean Started");

		TimeTableBean bean = new TimeTableBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));
		
		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		bean.setSemester(DataUtility.getString(request.getParameter("semester")));

		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));

		bean.setTime(DataUtility.getString(request.getParameter("Time")));

		
		// populateDTO(bean, request);

		log.debug("TimeTableCtl Method populatebean Ended");

		return bean;
	}
	
	
	  /**
     * Contains DIsplay logics
     */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("TimeTableCtl Method doGet Started");
		System.out.println("in id > 0  condition====+++++" );

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model

		//System.out.println("in id > 0  condition===="+id );

		TimeTableModel model = new TimeTableModel();
		
		

		long id = DataUtility.getLong(request.getParameter("id"));

		System.out.println(id);
		System.out.println("pk" + op);
	
		if (id > 0 || op != null) {

			TimeTableBean bean;

			try {
				System.out.println("pk+ try");

				
				
				bean = model.findByPK(id);
				
				ServletUtility.setBean(bean, request);
				
				System.out.println("pk+ try====="+bean);
				System.out.println("find by pk");
			}

			catch (ApplicationException e) {
				log.error(e);
				ServletUtility.handleException(e, request, response);
				return;
			}
		}
		System.out.println("out of condition");

		ServletUtility.forward(getView(), request, response);
		System.out.println("get view ends");
		log.debug("TimeTableCtl Method doGet Ended");
	}

	
	  /**
     * Contains Submit logics
     */
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("TimeTableCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model
		System.out.println("in doGet subject ");
		TimeTableModel model = new TimeTableModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);

			try {
				if (id > 0)
				{
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Succesfully Update", request);

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

			catch (DuplicateRecordException e) 
			{
				ServletUtility.setBean(bean, request);
				ServletUtility.setErrorMessage("TimeTable already exists", request);
			} 
			
			catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			TimeTableBean bean = (TimeTableBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
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

			ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("TimeTableCtl Method doPostEnded");
	}

	@Override
	protected String getView() {
		System.out.println(" in get view");
		return ORSView.TIMETABLE_VIEW;

	}

}
