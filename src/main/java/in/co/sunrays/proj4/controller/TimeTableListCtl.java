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
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.TimeTableModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;


/**
 * TimeTable List functionality Controller. Performs operation for list, search and
 * delete operations of TimeTable
 *
 * @author FrontController
 * @version 1.0
 * 
 */

@WebServlet(name = "TimeTableListCtl", urlPatterns = { "/ctl/TimeTableListCtl" })
public class TimeTableListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(TimeTableListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModel coursemodel = new CourseModel();

		try {
			List listcourse = coursemodel.list();
			request.setAttribute("courseList", listcourse);
		} 
		
		catch (ApplicationException e) 
		{
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

		/*
		TimeTableModel tmodel=new TimeTableModel();
		
		try{
			
			List tlist=tmodel.list();
			request.setAttribute("examlist",tlist);
			
		}
		
		catch(ApplicationException e){
			
			log.error(e);
		}
		
*/		
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		TimeTableBean bean = new TimeTableBean();

		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		bean.setSubjectId(DataUtility.getLong(request.getParameter("subjectId")));

		bean.setSemester(DataUtility.getString(request.getParameter("semester")));

		bean.setExamDate(DataUtility.getDate(request.getParameter("examDate")));
       
		System.out.println("-------"+request.getParameter("examDate"));
		
		bean.setTime(DataUtility.getString(request.getParameter("Time")));
		
		bean.setId(DataUtility.getLong(request.getParameter("id")));
		return bean;

	}

	 /**
     * Contains Display logics
     */
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("TimeTableListCtl doGet Start");

		List list = new ArrayList();

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		TimeTableBean bean = (TimeTableBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		TimeTableModel model = new TimeTableModel();

		try {
			
			list = model.search(bean, pageNo, pageSize);

			

			if (list == null || list.size() == 0) 
			{
				ServletUtility.setErrorMessage("No record found ", request);
			}

			ServletUtility.setList(list, request);

			ServletUtility.setPageNo(pageNo, request);

			ServletUtility.setPageSize(pageSize, request);

			ServletUtility.forward(getView(), request, response);
		}

		catch (ApplicationException e) {
			log.error(e);
			ServletUtility.handleException(e, request, response);
			e.printStackTrace();
			return;
		}
		log.debug("TimeTableListCtl doGet End");
	}

	  /**
     * Contains Display logics
     */
    
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("TimeTableListCtl doPost Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		TimeTableBean bean = (TimeTableBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));
		
		String[] ids = request.getParameterValues("ids");

		TimeTableModel model = new TimeTableModel();

		try {

			
			/*  if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
			  || "Previous".equalsIgnoreCase(op))
			  
			  {
			 */

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
				ServletUtility.redirect(ORSView.TIMETABLE_CTL, request, response);

				return;
			}

			else if (OP_DELETE.equalsIgnoreCase(op))

			{
				pageNo = 1;
				if (ids != null && ids.length > 0)

				{
					TimeTableBean deletebean = new TimeTableBean();
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
					ServletUtility.redirect(ORSView.TIMETABLE_LIST_CTL, request, response);
					return;
				}
			
			
				list = model.search(bean, pageNo, pageSize);

				ServletUtility.setList(list, request);

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

		catch (ApplicationException e) {
			log.error(e);
			e.printStackTrace();
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("TimeTableListCtl doPost End");
	}

	@Override
	protected String getView() {

		return ORSView.TIMETABLE_LIST_VIEW;
	}

}
