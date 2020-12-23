package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;

/**
 * JDBC Implementation of CourseModel
 *
 * @author FrontController
 * @version 1.0
 *
 */

public class CourseModel {

	Logger log = Logger.getLogger(CourseModel.class);

	/**
	 * Find next PK of course
	 *
	 * @throws DatabaseException
	 *             return pk + 1
	 */

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			System.out.println("Connection successfully establish");

			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_COURSE");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);

			}
			rs.close();

		} catch (Exception e) {
			log.error(e);
			throw new DatabaseException("Exception in Course getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);

		}
		log.debug("Model nextPK end");
		return pk + 1;
	}

	/**
	 * Add a Course
	 *
	 * @param bean
	 * @throws ApplicationException
	 *             DuplicateRecordException
	 *
	 */

	public long add(CourseBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("Model add Started");
		Connection conn = null;
		int pk = 0;
		
		//System.out.println("college add first pk "+pk);
		
		CourseBean existbean = findByCourseName(bean.getName());
		
		//System.out.println("college add first after bean "+existbean);
		
		if (existbean != null) {
			
			throw new DuplicateRecordException("Course Name already exists");

		}
		System.out.println("college add first beforte try");

		try {
			System.out.println("college add first in the try");
			conn = JDBCDataSource.getConnection();
			pk = nextPK();

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getName());
			pstmt.setString(3, bean.getDescription());
			pstmt.setString(4, bean.getDuration());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add College");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}

	/**
	 * Delete a Course
	 *
	 * @param bean
	 * @throws ApplicationException
	 */

	public void delete(CourseBean bean) throws ApplicationException {
		log.debug("Model delete Started");
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_COURSE WHERE ID=?");

			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();

		} 
		catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete course");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model delete Started");
	}

	/**
	 * Update a Course
	 *
	 * @param bean
	 * @throws ApplicationException DuplicateRecordException
	 */

	public void update(CourseBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;
		
		CourseBean existbean = findByCourseName(bean.getName());
	
		if (existbean != null) {
			
			throw new DuplicateRecordException("Course Name already exists");

		}

		
		
		
		
		
		/*
		 * CourseBean beanExist = findByName(bean.getName());
		 * 
		 * 
		 * if (beanExist != null && beanExist.getId() != bean.getId()) {
		 * 
		 * throw new DuplicateRecordException("Course is already exist"); }
		 */
		try {

			conn = JDBCDataSource.getConnection();

			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE ST_COURSE SET NAME=?, DESCRIPTION=?,DURATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");

			pstmt.setString(1, bean.getName());
			pstmt.setString(2, bean.getDescription());
			pstmt.setString(3, bean.getDuration());
			pstmt.setString(4, bean.getCreatedBy());
			pstmt.setString(5, bean.getModifiedBy());
			pstmt.setTimestamp(6, bean.getCreatedDatetime());
			pstmt.setTimestamp(7, bean.getModifiedDatetime());
			pstmt.setLong(8, bean.getId());

			pstmt.executeUpdate();

			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating Course ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

	/**
	 * Search Course
	 *
	 * @param bean
	 *            : Search Parameters
	 * @throws ApplicationException
	 */

	public List search(CourseBean bean) throws ApplicationException

	{
		return search(bean, 0, 0);
	}

	/**
	 * Search Course with pagination
	 *
	 * @return list : List of Courses
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 *
	 * @throws ApplicationException
	 */

	public List search(CourseBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}

			if (bean.getName() != null && bean.getName().length() > 0) {
				sql.append(" AND NAME like '" + bean.getName() + "%'");
			}

			if (bean.getDescription() != null && bean.getDescription().length() > 0) {
				sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");

			}
			if (bean.getDuration() != null && bean.getDuration().length() > 0) {
				sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
			}

		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);

		}

		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search college");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}

	/**
	 * Get List of Course
	 *
	 * @return list : List of Course
	 * @throws ApplicationException
	 */

	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of Course with pagination
	 *
	 * @return list : List of courses
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws ApplicationException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");

		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from ST_COURSE");

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				CourseBean bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));

				list.add(bean);
			}

			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);

			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

	/**
	 * Find Course by PK
	 *
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */

	public CourseBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE ID=?");

		CourseBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		}

		catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	/**
	 * Find Course by cname
	 *
	 * @param cname
	 *            : get parameter
	 * @return bean
	 * @throws ApplicationException
	 */

	public CourseBean findByCourseName(String cname) throws ApplicationException {
		log.debug("Model findByCourseName Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURSE WHERE NAME=?");
		 System.out.println("me pagal hu");
		CourseBean bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, cname);
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new CourseBean();
				bean.setId(rs.getLong(1));
				bean.setName(rs.getString(2));
				bean.setDescription(rs.getString(3));
				bean.setDuration(rs.getString(4));
				bean.setCreatedBy(rs.getString(5));
				bean.setModifiedBy(rs.getString(6));
				bean.setCreatedDatetime(rs.getTimestamp(7));
				bean.setModifiedDatetime(rs.getTimestamp(8));
			}
			rs.close();
		}

		catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by Pk");
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByCourseName End");
		return bean;
	}

}