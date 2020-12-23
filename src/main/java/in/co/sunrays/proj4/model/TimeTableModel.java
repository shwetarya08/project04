package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;


/**
 * JDBC Implementation of TimeTable Model
 *
 * @author SunilOS
 * @version 1.0
 *
 */

public class TimeTableModel {

	private static Logger log = Logger.getLogger(TimeTableModel.class);

	  /**
     * Find next PK of TimeTable
     *
     * @throws DatabaseException
     */
	public Integer nextPK() throws DatabaseException {
		log.debug("Timetable Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_TIMETABLE");

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable Model nextPK End");
		return pk + 1;
	}

	  /**
     * Add a TimeTable
     *
     * @param bean
     * @throws Exception
     *
     */
	public long add(TimeTableBean bean) throws Exception {

		log.debug("timetable Model add Started");

		Connection conn = null;
		int pk = 0;

		CourseModel coModel = new CourseModel();
		CourseBean courseBean = coModel.findByPK(bean.getCourseId());
		bean.setCourseName(courseBean.getName());

		SubjectModel sModel = new SubjectModel();
		SubjectBean subjectBean = sModel.findByPK(bean.getSubjectId());
		bean.setSubjectName(subjectBean.getName());

		/*TimeTableModel tmodel=new TimeTableModel();
		TimeTableBean tbean=new TimeTableBean();
		bean.setSemester(tbean.getSemester());
		*/

		  TimeTableBean beann=findBySubCourseName(bean.getSubjectName(),bean.getCourseName());
		 
		  if( beann !=null) {
		  
		  throw new DuplicateRecordException(" TimeTable already exist");
		  
		  }
		 

		try {

			conn = JDBCDataSource.getConnection();

			pk = nextPK();

			System.out.println(pk + "in ModelJDBC");

			conn.setAutoCommit(false);

			PreparedStatement pstmt = conn.prepareStatement("INSERT INTO ST_TIMETABLE VALUES(?,?,?,?,?,?,?,?,?,?,?,?)");

			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setString(3, bean.getCourseName());
			pstmt.setLong(4, bean.getSubjectId());
			pstmt.setString(5, bean.getSubjectName());
			pstmt.setString(6, bean.getSemester());
			pstmt.setDate(7, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(8, bean.getTime());
			pstmt.setString(9, bean.getCreatedBy());
			pstmt.setString(10, bean.getModifiedBy());
			pstmt.setTimestamp(11, bean.getCreatedDatetime());
			pstmt.setTimestamp(12, bean.getModifiedDatetime());

			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		}

		catch (Exception e) {
			e.printStackTrace();

			log.error("Database Exception.." + e);

			try {
				conn.rollback();

			} catch (Exception ex) {

				ex.printStackTrace();

				throw new ApplicationException("Exception: add rollback exception" + ex.getMessage());

			}
			throw new ApplicationException("Exception : Exception in add TimeTable");
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("TimeTable Model add End");
		System.out.println("yippi..3");
		return pk;
	}

	 /**
     * Delete a TimeTable
     *
     * @param bean
     * @throws ApplicationException
     */
	public void delete(TimeTableBean bean) throws ApplicationException {

		log.debug("TimeTable Model delete Started");
		Connection conn = null;
		try {

			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);// begin transaction

			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");

			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit();// end transaction
			pstmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);

			try {
				conn.rollback();

			}

			catch (Exception ex) {

				throw new ApplicationException("Execption: Delete rollback exception" + ex.getMessage());

			}
			throw new ApplicationException("Exception: Exception in delete TimeTable");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Timetable Model delete Started");

	}

	/*
	 * public TimeTableBean findByLogin(String login)throws
	 * ApplicationException{
	 * 
	 * //log.debug("Model findByLogin Started");
	 * 
	 * StringBuffer sql=new
	 * StringBuffer("SELECT * FROM ST_TIMETABLE WHERE LOGIN=?");
	 * 
	 * TimeTableBean bean=null; Connection conn=null;
	 * System.out.println("sql"+sql);
	 * 
	 * try { conn=JDBCDataSource.getConnection(); PreparedStatement
	 * pstmt=conn.prepareStatement(sql.toString()); pstmt.setString(1, login);
	 * 
	 * ResultSet rs=pstmt.executeQuery();
	 * 
	 * while(rs.next()) { bean=new TimeTableBean();
	 * bean.setCourseId(rs.getLong(1)); bean.setCourseName(rs.getString(3));
	 * bean.setSubjectId(rs.getLong(4)); bean.setSubjectName(rs.getString(5));
	 * bean.setSemester(rs.getString(6)); bean.setExamDate(rs.getDate(7));
	 * bean.setTime(rs.getString(8));
	 * 
	 * 
	 * 
	 * } rs.close();
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * //log.error("Database Exception.." e); throw new
	 * ApplicationException("Exception in getting User by pk");
	 * 
	 * }finally{ JDBCDataSource.closeConnection(conn);
	 * 
	 * } log.debug("Model findByPK End");
	 * 
	 * return bean; }
	 */
	
	 /**
     * Find TimeTable by PK
     *
     * @param pk
     *            : get parameter
     * @return bean
     * @throws ApplicationException
     */

	public TimeTableBean findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE ID=?");
		TimeTableBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by pk");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByPK End");
		return bean;
	}

	
	public TimeTableBean findBySubCourseName(String sname ,String cname) throws ApplicationException
	{
		log.debug("Model findBySubName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE SUBJECT_NAME=? AND COURSE_NAME=? ");
		TimeTableBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, sname);
			pstmt.setString(2,cname);
			//pstmt.setString(3,sem);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by sname");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findBySubName End");
		return bean;
	}


	
	/*public TimeTableBean findByCourseName(long cname) throws ApplicationException {
		log.debug("Model findByCourseName Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE COURSE_ID=?");
		TimeTableBean bean = null;

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1,cname);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by cname");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByCourseName End");
		return bean;
	}

*/
	
	
	
	 /**
     * Update a TimeTable
     *
     * @param bean
     * @throws ApplicationException DuplicateRecordException
     */


	public void update(TimeTableBean bean) throws ApplicationException, DuplicateRecordException {

		log.debug("TimeTable Model Updated Started");

		Connection conn = null;

		
		  TimeTableBean beanExist=findByPK(bean.getId());
		 
		 /*if(beanExist!=null && !(beanExist.getId()==bean.getId()))
		  { throw new DuplicateRecordException("LoginId is already exist");
		  
		  }*/
		
		  
		  
			CourseModel coModel = new CourseModel();
			CourseBean courseBean = coModel.findByPK(bean.getCourseId());
			bean.setCourseName(courseBean.getName());

			SubjectModel sModel = new SubjectModel();
			SubjectBean subjectBean = sModel.findByPK(bean.getSubjectId());
			bean.setSubjectName(subjectBean.getName());


			 
			  TimeTableBean beann=findBySubCourseName(bean.getSubjectName(),bean.getCourseName());
				 
			  if( beann !=null) {
			  
			  throw new DuplicateRecordException(" TimeTable already exist");
			  
			  }
			  
			
			
			
		 
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction

			PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_TIMETABLE SET  COURSE_ID=?, COURSE_NAME=?, SUBJECT_ID=?, SUBJECT_NAME=?, SEMESTER=?,EXAM_DATE=?,TIME=?, CREATED_BY=?, MODIFIED_BY=?, CREATED_DATETIME=?, MODIFIED_DATETIME=? WHERE ID=?");
			
		
			pstmt.setLong(1, bean.getCourseId());
			pstmt.setString(2, bean.getCourseName());
			pstmt.setLong(3, bean.getSubjectId());
			pstmt.setString(4, bean.getSubjectName());
			pstmt.setString(5, bean.getSemester());
			pstmt.setDate(6, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(7, bean.getTime());
			pstmt.setString(8, bean.getCreatedBy());
			pstmt.setString(9, bean.getModifiedBy());
			pstmt.setTimestamp(10, bean.getCreatedDatetime());
			pstmt.setTimestamp(11, bean.getModifiedDatetime());
			pstmt.setLong(12, bean.getId());

			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		}

		catch (Exception e) {
			e.printStackTrace();

			log.error("Database Exception..", e);

			try {
				conn.rollback();
			}

			catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}

			throw new ApplicationException("Exception in updating User ");
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("TimeTable Model update End");

	}


	   /**
	     * Search TimeTable
	     *
	     * @param bean: Search Parameters
	     * @throws ApplicationException
	     */

	public List search(TimeTableBean bean) throws ApplicationException {

		return search(bean, 0, 0);

	}

	 /**
     * Search TimeTable with pagination
     *
     * @return list : List of TimeTables
     * @param bean : Search Parameters
     * @param pageNo: Current Page No.
     * @param pageSize: Size of Page
     *
     * @throws ApplicationException
     */
	public List search(TimeTableBean bean, int pageNo, int pageSize) throws ApplicationException {

		log.debug("TimeTable Model search Started");

		StringBuffer sql = new StringBuffer("SELECT * FROM ST_TIMETABLE WHERE 1=1");

		/*
		 * if (bean != null) { if (bean.getId() > 0) { sql.append(" AND id = " +
		 * bean.getId()); }
		 */

		if (bean.getCourseId() > 0) {
			sql.append(" AND COURSE_ID = " + bean.getCourseId());
		}

		if (bean.getSubjectId() > 0) {
			sql.append(" AND SUBJECT_ID = " + bean.getSubjectId());
		}

		/*
		 * if (bean.getCourseName() != null && bean.getCourseName().length() >
		 * 0) { sql.append(" AND COURSE_NAME like '" + bean.getCourseName() +
		 * "%'"); }
		 */
		/*
		 * if (bean.getSubjectName() != null && bean.getSubjectName().length() >
		 * 0) { sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() +
		 * "%'");
		 * 
		 * }
		 */
		if (bean.getSemester() != null && bean.getSemester().length() > 0) {
			sql.append(" AND SEMESTER like '" + bean.getSemester() + "%'");
		}

		if (bean.getExamDate() != null && bean.getExamDate().getDate() > 0) {
			sql.append(" AND EXAM_DATE like '" + bean.getExamDate() + "%'");
		}

		if (bean.getTime() != null && bean.getTime().length() > 0) {
			sql.append(" AND TIME like '" + bean.getTime() + "%'");
		}

		if (pageSize > 0) {

			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);

		}

		System.out.println(sql);
		List list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				bean = new TimeTableBean();

				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

				list.add(bean);
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");

		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Timetable Model search End");
		return list;
	}

	 
	  /**
	     * Get List of TimeTable
	     *
	     * @return list : List of TimeTable
	     * @throws ApplicationException
	     */



	public List list() throws ApplicationException {
		return list(0, 0);
	}

	 /**
     * Get List of TimeTable with pagination
     *
     * @return list : List of TimeTable
     * @param pageNo : Current Page No.
     * @param pageSize : Size of Page
     * @throws ApplicationException
     */


	public List list(int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model list Started");

		ArrayList list = new ArrayList();

		StringBuffer sql = new StringBuffer("select * from st_timetable");

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

				TimeTableBean bean = new TimeTableBean();
				bean.setId(rs.getLong(1));
				bean.setCourseId(rs.getLong(2));
				bean.setCourseName(rs.getString(3));
				bean.setSubjectId(rs.getLong(4));
				bean.setSubjectName(rs.getString(5));
				bean.setSemester(rs.getString(6));
				bean.setExamDate(rs.getDate(7));
				bean.setTime(rs.getString(8));
				bean.setCreatedBy(rs.getString(9));
				bean.setModifiedBy(rs.getString(10));
				bean.setCreatedDatetime(rs.getTimestamp(11));
				bean.setModifiedDatetime(rs.getTimestamp(12));

				list.add(bean);
			}
			rs.close();
		}

		catch (Exception e) {

			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		}

		finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}

}
