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
import in.co.sunrays.proj4.bean.SubjectBean;

import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.TimeTableModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * Subject List functionality Controller. Performs operation for list, search
 * and delete operations of Subject
 *
 * @author FrontController
 * @version 1.0
 * 
 */

@WebServlet(name = "SubjectListCtl", urlPatterns = { "/ctl/SubjectListCtl" })
public class SubjectListCtl extends BaseCtl {

	private static Logger log = Logger.getLogger(SubjectListCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		CourseModel coursemodel = new CourseModel();

		try {
			List courselist = coursemodel.list();
			request.setAttribute("courseList", courselist);
		} catch (ApplicationException e) {
			log.error(e);
		}

	}

	protected BaseBean populateBean(HttpServletRequest request) {

		SubjectBean bean = new SubjectBean();

		bean.setId(DataUtility.getLong(request.getParameter("id")));

		bean.setName(DataUtility.getString(request.getParameter("name")));

		bean.setDescription(DataUtility.getString(request.getParameter("description")));

		bean.setCourseId(DataUtility.getLong(request.getParameter("courseId")));

		bean.setCourseName(DataUtility.getString(request.getParameter("courseName")));

		System.out.println(">>>course id<<<+" + bean.getCourseId());
		return bean;

	}

	/**
	 * Contains Display logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("SubjectListCtl doGet Start");

		List list = new ArrayList();

		int pageNo = 1;

		int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

		SubjectBean bean = (SubjectBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		// String[] ids = request.getParameterValues("ids");

		SubjectModel model = new SubjectModel();

		try {
			System.out.println(">>>>>coursebean" + bean);

			list = model.search(bean, pageNo, pageSize);

			if (list == null || list.size() == 0) {
				ServletUtility.setErrorMessage("No record found ", request);
			}

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
		log.debug("SubjectListCtl doGet End");
	}

	/**
	 * Contains Display logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("SubjectListCtl doPost Start");

		List list = null;

		int pageNo = DataUtility.getInt(request.getParameter("pageNo"));

		int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

		pageNo = (pageNo == 0) ? 1 : pageNo;

		pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

		SubjectBean bean = (SubjectBean) populateBean(request);

		String op = DataUtility.getString(request.getParameter("operation"));

		String[] ids = request.getParameterValues("ids");

		SubjectModel model = new SubjectModel();

		try {

			/*
			 * if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op)
			 * || "Previous".equalsIgnoreCase(op))
			 * 
			 * {
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
           

			if (OP_NEW.equalsIgnoreCase(op))

			{
				ServletUtility.redirect(ORSView.SUBJECT_CTL, request, response);

				return;
			}
			if (OP_DELETE.equalsIgnoreCase(op))

			{
				pageNo = 1;
				if (ids != null && ids.length > 0)

				{
					SubjectBean deletebean = new SubjectBean();
					for (String id : ids)

					{

						deletebean.setId(DataUtility.getInt(id));
						model.delete(deletebean);
					}
					ServletUtility.setSuccessMessage("Data is successfully deleted", request);

				} else

				{
					ServletUtility.setErrorMessage("Select at least one record", request);
				}
			}

			else if (OP_RESET.equalsIgnoreCase(op)) {
				ServletUtility.redirect(ORSView.SUBJECT_LIST_CTL, request, response);
				return;
			}

			System.out.println("><<<>>dfdcourse dopostfd" + bean.getCourseId());

			list = model.search(bean, pageNo, pageSize);

			// request.setAttribute("courseList", model.search(bean));

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

		catch (ApplicationException e)

		{
			log.error(e);
			ServletUtility.handleException(e, request, response);
			return;
		}
		log.debug("SubjectListCtl doPost End");
	}

	@Override
	protected String getView() {

		return ORSView.SUBJECT_LIST_VIEW;
	}

}
