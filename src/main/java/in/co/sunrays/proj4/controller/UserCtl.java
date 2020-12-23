package in.co.sunrays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.BaseBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.UserModel;
import in.co.sunrays.proj4.util.DataUtility;
import in.co.sunrays.proj4.util.DataValidator;
import in.co.sunrays.proj4.util.PropertyReader;
import in.co.sunrays.proj4.util.ServletUtility;

/**
 * * User functionality Controller. Performs operation for add, update and get
 * User
 *
 * @author FrontController
 * @version 1.0
 * 
 */
@WebServlet(name = "UserCtl", urlPatterns = { "/ctl/UserCtl" })
public class UserCtl extends BaseCtl {

	private static final long serialVersionUID = 1L;

	private static Logger log = Logger.getLogger(UserCtl.class);

	@Override
	protected void preload(HttpServletRequest request) {

		RoleModel model = new RoleModel();

		try {
			List l = model.list();
			//System.out.println(l);
			request.setAttribute("roleList", l);
		} catch (ApplicationException e) {
			log.error(e);
		}
	}

	@Override
	protected boolean validate(HttpServletRequest request) {

		log.debug("UserCtl Method validate Started");

		boolean pass = true;

		// String login=request.getParameter("login");
		String dob = request.getParameter("dob");
		System.out.println(dob);

		if (DataValidator.isNull(request.getParameter("firstName")))

		{

			System.out.println("inside isnull fn");
			request.setAttribute("firstName", PropertyReader.getValue("error.require", "First Name"));

			pass = false;
		}

		else if (!DataValidator.isString(request.getParameter("firstName"))) {
			request.setAttribute("firstName", PropertyReader.getValue("error.valid", "First Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("lastName"))) {
			System.out.println("inside isnull ln");

			request.setAttribute("lastName", PropertyReader.getValue("error.require", "Last Name"));

			pass = false;
		}

		else if (!DataValidator.isString(request.getParameter("lastName"))) {
			request.setAttribute("lastName", PropertyReader.getValue("error.valid", "Last Name"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("mobileNo")))

		{

			// System.out.println("inside isnull login");
			request.setAttribute("mobileNo", PropertyReader.getValue("error.require", "Mobile No"));
			pass = false;
		}

		else if (!DataValidator.isMobNo(request.getParameter("mobileNo"))) {
			request.setAttribute("mobileNo", PropertyReader.getValue("error.roll", "Mobile No"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("login")))

		{

			System.out.println("inside isnull login");
			request.setAttribute("login", PropertyReader.getValue("error.require", "Login"));

			pass = false;
		}

		else if (!DataValidator.isEmail(request.getParameter("login"))) {
			request.setAttribute("login", PropertyReader.getValue("error.email", ""));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("password")))

		{
			System.out.println("inside isnull pass");
			request.setAttribute("password", PropertyReader.getValue("error.require", "Password"));
			pass = false;

		} else if (!DataValidator.isPassword(request.getParameter("password"))) {
			request.setAttribute("password", PropertyReader.getValue("error.pwd", "Password"));
			pass = false;
		}

	 if (DataValidator.isNull(request.getParameter("confirmPassword")))

		{
			System.out.println("inside isnull cpass");
			request.setAttribute("confirmPassword", PropertyReader.getValue("error.require", "ConfirmPassword"));

			pass = false;
		}

		else if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))
				&& !"".equals(request.getParameter("confirmPassword")))

		{
			// ServletUtility.setErrorMessage("Confirm Password should be
			// matched.", request);
			request.setAttribute("password",PropertyReader.getValue("password & confirm passsword should match", "Date Of Birth"));
			pass = false;
		}

		if (DataValidator.isNull(request.getParameter("gender")))

		{

			System.out.println("inside isnull gender");
			request.setAttribute("gender", PropertyReader.getValue("error.require", " Gender"));

			pass = false;
		}

		if (DataValidator.isNull(dob))

		{
			System.out.println("inside isnull dob");
			request.setAttribute("dob", PropertyReader.getValue("error.require", "Date Of Birth"));

			pass = false;

		}

		else if (!DataValidator.isDate(dob)) {
			System.out.println("inside isnull dobbb");
			request.setAttribute("dob", PropertyReader.getValue("error.date", "Date Of Birth"));

			pass = false;

		}

		else if (!DataValidator.isValidAge(dob)) {
			System.out.println("inside isnull dobbb");
			request.setAttribute("dob", PropertyReader.getValue("Age must be greater then 18 year", "Date Of Birth"));

			pass = false;

		}

		if (DataValidator.isNull(request.getParameter("roleId")))

		{
			System.out.println("inside isnull pass");
			request.setAttribute("roleId", PropertyReader.getValue("error.require", "RoleName"));
			pass = false;

		}

		log.debug("UserCtl Method validate Ended");

		return pass;
	}

	protected BaseBean populateBean(HttpServletRequest request) {

		log.debug("UserCtl Method populatebean Started");

		UserBean bean = new UserBean();

		 bean.setId(DataUtility.getLong(request.getParameter("id")));

		 bean.setFirstName(DataUtility.getString(request.getParameter("firstName")));

		 bean.setLastName(DataUtility.getString(request.getParameter("lastName")));

		 bean.setMobileNo(DataUtility.getString(request.getParameter("mobileNo")));

		 bean.setLogin(DataUtility.getString(request.getParameter("login")));

		 bean.setPassword(DataUtility.getString(request.getParameter("password")));

		 bean.setConfirmPassword(DataUtility.getString(request.getParameter("confirmPassword")));

		 bean.setGender(DataUtility.getString(request.getParameter("gender")));

		 bean.setRoleId(DataUtility.getLong(request.getParameter("roleId")));

		 bean.setDob(DataUtility.getDate(request.getParameter("dob")));

		// populateDTO(bean, request);

		log.debug("UserCtl Method populatebean Ended");

		return bean;
	}

	/**
	 * Contains DIsplay logics
	 */

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserCtl Method doGet Started");

		String op = DataUtility.getString(request.getParameter("operation"));
		// get model

		UserModel model = new UserModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (id > 0 || op != null)

		{

			System.out.println("in id > 0  condition");

			UserBean bean;

			try 
			{
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
		System.out.println("in doget userctl");
		log.debug("UserCtl Method doGet Ended");
	}

	/**
	 * Contains Submit logics
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		log.debug("UserCtl Method doPost Started");

		String op = DataUtility.getString(request.getParameter("operation"));

		// get model

		UserModel model = new UserModel();

		long id = DataUtility.getLong(request.getParameter("id"));

		if (OP_SAVE.equalsIgnoreCase(op) || OP_UPDATE.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);

			try {
				if (id > 0) {
					model.update(bean);
					ServletUtility.setBean(bean, request);
					ServletUtility.setSuccessMessage("Data is successfully updated", request);

				}

				else

				{
					long pk = model.add(bean);
					// bean.setId(pk);
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
				ServletUtility.setErrorMessage("Login id already exists", request);
			}
		}

		else if (OP_DELETE.equalsIgnoreCase(op)) {

			UserBean bean = (UserBean) populateBean(request);
			try {
				model.delete(bean);
				ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
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

			ServletUtility.redirect(ORSView.USER_LIST_CTL, request, response);
			return;
		}
		ServletUtility.forward(getView(), request, response);

		log.debug("UserCtl Method doPostEnded");
	}

	// @Override
	protected String getView()

	{
		return ORSView.USER_VIEW;
	}

}
