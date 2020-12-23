package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;


/**
 * JDBC Implementation of FacultyModel
 *
 * @author FrontController
 * @version 1.0
 * 
 */

public class FacultyModel {
	
	private static Logger log = Logger.getLogger(FacultyModel.class);
	
	/**
	 * Find next PK of course
	 *
	 * @throws DatabaseException
	 */
	
	 public Integer nextPK() throws DatabaseException {
	        log.debug("Model nextPK Started");
	        Connection conn = null;
	        int pk = 0;

	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_FACULTY");
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) 
		            {
		                pk = rs.getInt(1);
		            }
		            rs.close();

	        } 
	        catch (Exception e) 
		        {
		            log.error("Database Exception..", e);
		            throw new DatabaseException("Exception : Exception in getting PK");
		        } 
		    finally 
		        {
		            JDBCDataSource.closeConnection(conn);
		        }
			        log.debug("Model nextPK End");
			        return pk + 1;
	    }

	 

	  /**
    * Add a Course
    *
    * @param bean
    * @throws ApplicationException DuplicateRecordException
    *
    */
	 
	 

	 public long add(FacultyBean bean) throws ApplicationException, DuplicateRecordException
	 
	 {
			log.debug("Model add Started");
			
			Connection conn=null;
			int pk=0;
			
			CollegeModel cModel= new CollegeModel();
			CollegeBean collegeBean=cModel.findByPK(bean.getCollegeId());
			bean.setCollegeName(collegeBean.getName());
			
			CourseModel cModel1= new CourseModel();
			CourseBean CourseBean=cModel1.findByPK(bean.getCourseId());
			bean.setCourseName(CourseBean.getName());
			
			SubjectModel SModel= new SubjectModel();
			SubjectBean SubjectBean=SModel.findByPK(bean.getSubjectId());
			bean.setSubjectName(SubjectBean.getName());
			
			
			FacultyBean existbean=findByemailId(bean.getEmailId());
			
			if(existbean !=null){
				throw new DuplicateRecordException("EmailId Id already exists");
				
			}
			try {
				conn=JDBCDataSource.getConnection();
				pk=nextPK();
				
				System.out.println(pk+"in ModelJDBC");
				conn.setAutoCommit(false);
				PreparedStatement pstmt=conn.prepareStatement("INSERT INTO ST_FACULTY VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
				
				pstmt.setLong(1, pk);
				pstmt.setLong(2, bean.getCollegeId());
				pstmt.setString(3, bean.getFirstName());
				pstmt.setString(4, bean.getLastName());
				pstmt.setLong(5, bean.getCourseId());
				pstmt.setString(6, bean.getCourseName());
				pstmt.setLong(7, bean.getSubjectId());
				pstmt.setString(8, bean.getSubjectName());
				pstmt.setString(9, bean.getCollegeName());
				pstmt.setString(10, bean.getQualification());
				pstmt.setString(11, bean.getEmailId());
				pstmt.setDate(12, (new java.sql.Date(bean.getDob().getTime())));
				pstmt.setString(13, bean.getMobNo());
				pstmt.setString(14, bean.getCreatedBy());
				pstmt.setString(15, bean.getModifiedBy());
				pstmt.setTimestamp(16, bean.getCreatedDatetime());
				pstmt.setTimestamp(17, bean.getModifiedDatetime());
				
				pstmt.executeUpdate();
				
				conn.commit();
				pstmt.close();
				
			} catch (Exception e) {
				log.error("Database Exception.."+ e);
				
				try {
					conn.rollback();
					
				} catch (Exception ex) {
					
					ex.printStackTrace();
					
					throw new ApplicationException("Exception: add rollback exception"+ex.getMessage());
					
				}
				
				throw new ApplicationException("Exception : Exception in add Faculty");
			  } 
			
			finally 
				{
		            JDBCDataSource.closeConnection(conn);
		        }
		       
				
				log.debug("Model add End");
		        return pk;
		    }
		
				
	 /**
	     * Delete a Faculty
	     *
	     * @param bean
	     * @throws ApplicationException
	     */	
				
	 
	 
	 public void delete(FacultyBean bean)throws ApplicationException{
			
		 	log.debug("Model delete Started");
			
			Connection conn=null;
			try {
				
				conn=JDBCDataSource.getConnection();
				conn.setAutoCommit(false);//begin transaction
				
				PreparedStatement pstmt=conn.prepareStatement("DELETE FROM ST_FACULTY WHERE ID=?");
				
				pstmt.setLong(1, bean.getId());
				pstmt.executeUpdate();
				conn.commit();//end transaction
				pstmt.close();
				
			} catch (Exception e) {
				
				log.error("Database Exception..",e);
				try {
						conn.rollback();
						
					} 
				catch (Exception ex) 
					{
						throw new ApplicationException("Execption: Delete rollback exception"+ex.getMessage());
						
					}
						throw new ApplicationException("Exception: Exception in delete Faculty");
				
				
				
			}
				finally
				{
					JDBCDataSource.closeConnection(conn);
				}
	
			log.debug("Model delete Started");
			
		}
	 
	 
	 
	 
	 /**
	     * Update a Faculty
	     *
	     * @param bean
	     * @throws ApplicationException DuplicateRecordException
	     */
	 
	 
	 
	 public void update(FacultyBean bean)throws ApplicationException,DuplicateRecordException{
			
			log.debug("Model Updated Started");
			
			Connection conn=null;
			
			/*FacultyBean beanExist=findByPK(bean.getId());
			
			if(beanExist!=null && !(beanExist.getId()==bean.getId()))
				{
					throw new DuplicateRecordException("Id is already exist");
					
				}*/
			
			

			FacultyBean existbean=findByemailId(bean.getEmailId());
			
			if(existbean !=null){
				throw new DuplicateRecordException("EmailId Id already exists");
				
			}
			
			
			
						
			CollegeModel cModel= new CollegeModel();
			CollegeBean collegeBean=cModel.findByPK(bean.getCollegeId());
			bean.setCollegeName(collegeBean.getName());
			
			CourseModel cModel1= new CourseModel();
			CourseBean CourseBean=cModel1.findByPK(bean.getCourseId());
			bean.setCourseName(CourseBean.getName());
			
			SubjectModel SModel= new SubjectModel();
			SubjectBean SubjectBean=SModel.findByPK(bean.getSubjectId());
			bean.setSubjectName(SubjectBean.getName());
			
			
			
			try {
				conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	           
	            PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_FACULTY SET COLLEGE_ID=?,FIRST_NAME=?,LAST_NAME=?,COURSE_ID=?,COURSE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,COLLEGE_NAME=?,QUALIFICATION=?,EMAIL_ID=?,DOB=?,MOB_NO=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	          
	            pstmt.setLong(1, bean.getCollegeId());
	            pstmt.setString(2, bean.getFirstName());
	            pstmt.setString(3, bean.getLastName());
	            pstmt.setLong(4, bean.getCourseId());
	            pstmt.setString(5, bean.getCourseName());
	            pstmt.setLong(6, bean.getSubjectId());
	            pstmt.setString(7, bean.getSubjectName());
	            pstmt.setString(8, bean.getCollegeName());
	           
	            pstmt.setString(9, bean.getQualification());
	            pstmt.setString(10, bean.getEmailId());
	            pstmt.setDate(11,new java.sql.Date(bean.getDob().getTime()));
	            pstmt.setString(12, bean.getMobNo());
	            
	            pstmt.setString(13, bean.getCreatedBy());
	            pstmt.setString(14, bean.getModifiedBy());
	            pstmt.setTimestamp(15, bean.getCreatedDatetime());
	            pstmt.setTimestamp(16, bean.getModifiedDatetime());
	       
	            pstmt.setLong(17, bean.getId());       
	
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } 
			
			catch (Exception e)
				{
	            e.printStackTrace();
	            
	            log.error("Database Exception..", e);
	            
	            
	            try {
	                conn.rollback();
	            	} 
	            catch (Exception ex)
		            {
		               
		            	throw new ApplicationException("Exception : Delete rollback exception "+ ex.getMessage());
		            }
	            	
	            		throw new ApplicationException("Exception in updating faculty ");
	        } 
				finally 
					{
		            JDBCDataSource.closeConnection(conn);
		        	}
		        		log.debug("Model update End");
		  
	}

	 
	 
	 
	 /**
	     * Search Faculty
	     *
	     * @param bean : Search Parameters
	     * @throws ApplicationException
	     */
		
	 
				
	 public List search(FacultyBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }
	 
	 
	 /**
	     * Search Faculty with pagination
	     *
	     * @return list : List of Faculty
	     * @param bean
	     *            : Search Parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     *
	     * @throws ApplicationException
	     */
	 
	 
	 public List search(FacultyBean bean, int pageNo, int pageSize) throws ApplicationException 
	 {
	       
		 log.debug("Model search Started");
	        
		 StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE 1=1");

	        if (bean != null) {
	          
	            

	        	if (bean.getCollegeId() > 0)
	            {
	                sql.append(" AND COLLEGE_ID = " + bean.getCollegeId());
	            }
	            
	        	
	        	
	            if (bean.getFirstName() != null && bean.getFirstName().length() > 0) 
	            {
	                sql.append(" AND FIRST_NAME like '" + bean.getFirstName() + "%'");
	            }
	            
            if (bean.getLastName() != null && bean.getLastName().length() > 0)
	            {
	                sql.append(" AND LAST_NAME like '" + bean.getLastName() + "%'");
	            }
            
    	
            if (bean.getCourseId() > 0) 
            {
                sql.append(" AND COURSE_ID = " + bean.getCourseId());
            }
	           
            
            if (bean.getCourseName() != null && bean.getCourseName().length() > 0)
		            {
		                sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
		            }
	      
	        
	            if (bean.getSubjectId() > 0) 
		            {
		                sql.append(" AND SUBJECT_ID = " + bean.getSubjectId());
		            } 
	            
	            
	            if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0)
	            {
	                sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'");
	            }
	            
	            
	            
	            if (bean.getCollegeName() != null && bean.getCollegeName().length() > 0) 
		            {
		                sql.append(" AND COLLEGE_NAME like '" + bean.getCollegeName() + "%'");
		            }
	            
	          
		            
	            
	            if (bean.getQualification() != null && bean.getQualification().length() > 0) 
		            {
		                sql.append(" AND QUALIFICATION like '" + bean.getQualification() + "%'");
		            }
		            
	            if (bean.getEmailId() != null && bean.getEmailId().length() > 0) 
		            {
		                sql.append(" AND EMAIL_ID like '" + bean.getEmailId() + "%'");
		            }
	            
		           if (bean.getDob() != null && bean.getDob().getDate() > 0) 
			           {
			                sql.append(" AND DOB = " + bean.getDob());
			           }
	            if (bean.getMobNo() != null && bean.getMobNo().length() > 0) 
		            {
		                sql.append(" AND MOBILE_NO = " + bean.getMobNo());
		            }
	            
	           
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
	            while (rs.next()) 
	            {
	                bean = new FacultyBean();
	        
	                bean.setId(rs.getLong(1));
	                
	                bean.setCollegeId(rs.getLong(2));
	                
	                bean.setFirstName(rs.getString(3));
	                bean.setLastName(rs.getString(4));
	                bean.setCourseId(rs.getLong(5));
	                bean.setCourseName(rs.getString(6));
	                bean.setSubjectId(rs.getLong(7));
	                bean.setSubjectName(rs.getString(8));
	                bean.setCollegeName(rs.getString(9));
	                bean.setQualifiaction(rs.getString(10));
	                bean.setEmailId(rs.getString(11));
	               /* bean.setDob(rs.getDate(11));*/
	                bean.setDob(rs.getDate(12));
	                bean.setMobNo(rs.getString(13));
	                bean.setCreatedBy(rs.getString(14));
	                bean.setModifiedBy(rs.getString(15));
	                bean.setCreatedDatetime(rs.getTimestamp(16));
	                bean.setModifiedDatetime(rs.getTimestamp(17));

	                list.add(bean);
	            }
	           
	            rs.close();
	        } 
	        catch (Exception e) 
		        {
		            log.error("Database Exception..", e);
		            throw new ApplicationException("Exception : Exception in search Faculty");
		        }
		        
	        finally 
		        {
		            JDBCDataSource.closeConnection(conn);
		        }

		        log.debug("Model search End");
		        
	 
		        return list;
			    
		        }
	 
	 
	 /**
	     * Get List of Faculty
	     *
	     * @return list : List of Faculty
	     * @throws ApplicationException
	     */
	 
	 
	 
	 public List list() throws ApplicationException {
	        return list(0, 0);
	    }
	 
	 
	 /**
	     * Get List of Faculty with pagination
	     *
	     * @return list : List of faculty
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     * @throws ApplicationException
	     */

	 
	 
	 public List list(int pageNo, int pageSize) throws ApplicationException {
	       
		 log.debug("Model list Started");
	     
		 ArrayList list = new ArrayList();
	     
		 StringBuffer sql = new StringBuffer("select * from ST_FACULTY");
	        	
	     
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
	                FacultyBean bean = new FacultyBean();
	                bean.setId(rs.getLong(1));
	                bean.setCollegeId(rs.getLong(2));
	               
	                bean.setFirstName(rs.getString(3));
	                bean.setLastName(rs.getString(4));
	                bean.setCourseId(rs.getLong(5));
	                bean.setCourseName(rs.getString(6));
	                 bean.setSubjectId(rs.getLong(7));
	                bean.setSubjectName(rs.getString(8));
	                
	                bean.setCollegeName(rs.getString(9));
	                bean.setQualifiaction(rs.getString(10));
	                bean.setEmailId(rs.getString(11));
	               /* bean.setDob(rs.getDate(11));*/
	              bean.setDob(rs.getDate(12));
	                bean.setMobNo(rs.getString(13));
	                bean.setCreatedBy(rs.getString(14));
	                bean.setModifiedBy(rs.getString(15));
	                bean.setCreatedDatetime(rs.getTimestamp(16));
	                bean.setModifiedDatetime(rs.getTimestamp(17));

	                list.add(bean);
	            }
	            
	            rs.close();
	        }
	        catch (Exception e) 
		        {
		            log.error("Database Exception..", e);
		            throw new ApplicationException("Exception : Exception in getting list of Faculty");
		        } 
	        finally 
		        {
		            JDBCDataSource.closeConnection(conn);
		        }

		        log.debug("Model list End");
		        return list;

	    }
	 
	 /**
	     * Find Faculty by PK
	     *
	     * @param pk 
	     * @return bean
	     * @throws ApplicationException
	     */
	 
	 
	 public FacultyBean findByPK(long pk) throws ApplicationException{
			
			log.debug("Model findByPK Started");
			
			StringBuffer sql=new StringBuffer("SELECT * FROM ST_FACULTY WHERE ID=?");
			
			FacultyBean bean=null;
			Connection conn=null;
			
			
			try {
				
				conn=JDBCDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				pstmt.setLong(1, pk);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
						bean=new FacultyBean();
						
						bean.setId(rs.getLong(1));
						bean.setCollegeId(rs.getLong(2));
						
						bean.setFirstName(rs.getString(3));
						bean.setLastName(rs.getString(4));
						bean.setCourseId(rs.getLong(5));
						bean.setCourseName(rs.getString(6));
						bean.setSubjectId(rs.getLong(7));
						bean.setSubjectName(rs.getString(8));
						bean.setCollegeName(rs.getString(9));
						bean.setQualifiaction(rs.getString(10));
						bean.setEmailId(rs.getString(11));
						/*bean.setDob(rs.getDate(11));*/
						bean.setDob(rs.getDate(12));
						bean.setMobNo(rs.getString(13));
		                bean.setCreatedBy(rs.getString(14));
		                bean.setModifiedBy(rs.getString(15));
		                bean.setCreatedDatetime(rs.getTimestamp(16));
		                bean.setModifiedDatetime(rs.getTimestamp(17));

				
				}
				rs.close();
				
			}
			catch (Exception e) {
				e.printStackTrace();
				
				log.error("Database Exception.."+ e);
				 throw new ApplicationException("Exception in getting Faculty by pk");
				 
			}
			finally
				{
					JDBCDataSource.closeConnection(conn);
					
				}
			log.debug("Model findByPK End");
			
			return bean;
		}
	 /**
	     * Find Faculty by emailid
	     *
	     * @param emailid
	     * @return bean
	     * @throws ApplicationException
	     */

	 
	 
	 public FacultyBean findByemailId(String emailid) throws ApplicationException{
			
			log.debug("Model findByEmailId Started");
			
			StringBuffer sql=new StringBuffer("SELECT * FROM ST_FACULTY WHERE EMAIL_ID=?");
			
			FacultyBean bean=null;
			Connection conn=null;
			
			
			try {
				
				conn=JDBCDataSource.getConnection();
				PreparedStatement pstmt=conn.prepareStatement(sql.toString());
				pstmt.setString(1, emailid);
				ResultSet rs=pstmt.executeQuery();
				while(rs.next())
				{
						bean=new FacultyBean();
						
						bean.setId(rs.getLong(1));
						bean.setCollegeId(rs.getLong(2));
						
						bean.setFirstName(rs.getString(3));
						bean.setLastName(rs.getString(4));
						bean.setCourseId(rs.getLong(5));
						bean.setCourseName(rs.getString(6));
						bean.setSubjectId(rs.getLong(7));
						bean.setSubjectName(rs.getString(8));
						bean.setCollegeName(rs.getString(9));
						bean.setQualifiaction(rs.getString(10));
						bean.setEmailId(rs.getString(11));
						/*bean.setDob(rs.getDate(11));*/
						bean.setDob(rs.getDate(12));
						bean.setMobNo(rs.getString(13));
		                bean.setCreatedBy(rs.getString(14));
		                bean.setModifiedBy(rs.getString(15));
		                bean.setCreatedDatetime(rs.getTimestamp(16));
		                bean.setModifiedDatetime(rs.getTimestamp(17));

				
				}
				rs.close();
				
			}
			catch (Exception e) {
				e.printStackTrace();
				
				log.error("Database Exception.."+ e);
				 throw new ApplicationException("Exception in getting Faculty by EmailId");
				 
			}
			finally
				{
					JDBCDataSource.closeConnection(conn);
					
				}
			log.debug("Model findByEmailId End");
			
			return bean;
		}
		

	 
	 
	 
	 
	            

}
