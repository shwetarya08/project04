package in.co.sunrays.proj4.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DatabaseException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.util.JDBCDataSource;


/**
 * JDBC Implementation of SubjectModel
 *
 * @author FrontController
 * @version 1.0
 *
 */



public class SubjectModel {
	
	private static Logger log = Logger.getLogger(SubjectModel.class);
	
	/**
	 * Find next PK of Subject
	 *
	 * @throws DatabaseException
	 * @return pk + 1
	 */
	
	
	  public Integer nextPK() throws DatabaseException {
	       
		  log.debug("Subject Model nextPK Started");
	      
		  Connection conn = null;
	      
		  int pk = 0;

	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM ST_SUBJECT");
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
			        log.debug("Subject Model nextPK End");
			        return pk + 1;
	  }
	  

	  /**
	   * Add a Subject
	   *
	   * @param bean
	   * @throws ApplicationException DuplicateRecordException
	   *
	   */
	  
	  public long add(SubjectBean bean) throws ApplicationException, DuplicateRecordException{
			log.debug("Subject Model add Started");
			
			Connection conn=null;
			int pk=0;
			
			
			CourseModel courseModel =new CourseModel();
			CourseBean coursebean=courseModel.findByPK(bean.getCourseId());
			bean.setCourseName(coursebean.getName());
			
			
			SubjectBean exitbean=findByName(bean.getName());
			
			if(exitbean !=null){
				throw new DuplicateRecordException("Login Id already exists");
				
			}
			try {
				conn=JDBCDataSource.getConnection();
				pk=nextPK();
				
				System.out.println(pk+"in ModelJDBC");
				conn.setAutoCommit(false);
				PreparedStatement pstmt=conn.prepareStatement("INSERT INTO ST_SUBJECT VALUES(?,?,?,?,?,?,?,?,?)");
				
				pstmt.setInt(1, pk);
				pstmt.setString(2, bean.getName());
				pstmt.setString(3, bean.getDescription());
				pstmt.setLong(4, bean.getCourseId());
				pstmt.setString(5, bean.getCourseName());
				pstmt.setString(6, bean.getCreatedBy());
				pstmt.setString(7, bean.getModifiedBy());
				pstmt.setTimestamp(8, bean.getCreatedDatetime());
				pstmt.setTimestamp(9, bean.getModifiedDatetime());
				
				
				
				int i =pstmt.executeUpdate();
				System.out.println("inserted"+i);
				
				conn.commit();
				pstmt.close();
				
				
				
			} 
			catch (Exception e)
			{
				log.error("Database Exception.."+ e);
				
				try 
				{
					conn.rollback();
					
				} 
				catch (Exception ex) 
				{
					
					ex.printStackTrace();
					
					throw new ApplicationException("Exception: add rollback exception"+ex.getMessage());
					
				}
				
				//throw new ApplicationException("Exception : Exception in add Subject");
			  } 
			
			finally 
				{
		            JDBCDataSource.closeConnection(conn);
		        }
		       
				
				log.debug("Model add End");
		        return pk;
		    }
	  
	  
	  

	  /**
  * Delete a Subject
  *
  * @param bean
  * @throws ApplicationException
  *
  */
	  
		public void delete(SubjectBean bean)throws ApplicationException{
			log.debug("Model delete Started");
			Connection conn=null;
			try {
				
				conn=JDBCDataSource.getConnection();
				conn.setAutoCommit(false);//begin transaction
				
				PreparedStatement pstmt=conn.prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
				
				pstmt.setLong(1, bean.getId());//doubt
				pstmt.executeUpdate();
				conn.commit();//end transaction
				pstmt.close();
				
			}
			catch (Exception e) {
				
				log.error("Database Exception..",e);
				try {
						conn.rollback();
						
					} 
				catch (Exception ex) 
					{
						//throw new ApplicationException("Execption: Delete rollback exception"+ex.getMessage());
						
					}
						throw new ApplicationException("Exception: Exception in delete Subject");
				
			}
				finally
					{
						JDBCDataSource.closeConnection(conn);
					}
	
					log.debug("Model delete End");
			
		}
		
	
		
		 /**
		  * Update a Subject
		  *
		  * @param bean
		  * @throws ApplicationException DuplicateRecordException
		  *
		  */
		
		
		public void update(SubjectBean bean)throws ApplicationException,DuplicateRecordException{
			
			log.debug("Subject Model Updated Started");
			
			Connection conn=null;
			

			CourseModel courseModel =new CourseModel();
			CourseBean coursebean=courseModel.findByPK(bean.getCourseId());
			bean.setCourseName(coursebean.getName());

			
			SubjectBean exitbean=findByName(bean.getName());
			
			if(exitbean !=null){
				throw new DuplicateRecordException("subject already exists");
				
			}
			
			

		//	SubjectBean beanExist=findByPK((bean.getId()));
			/*
			if(beanExist!=null && !(beanExist.getId()==bean.getId()))
				{
					throw new DuplicateRecordException("Id is already exist");
					
				}*/
			try {
				conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	           
	            PreparedStatement pstmt = conn.prepareStatement("UPDATE ST_SUBJECT SET NAME=?,DESCRIPTION=?,COURSE_ID=?,COURSE_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	            
	           
	            pstmt.setString(1, bean.getName());
	            pstmt.setString(2,bean.getDescription());
	            pstmt.setLong(3, bean.getCourseId());
	            pstmt.setString(4, bean.getCourseName());
	            pstmt.setString(5, bean.getCreatedBy());
				pstmt.setString(6, bean.getModifiedBy());
				pstmt.setTimestamp(7, bean.getCreatedDatetime());
				pstmt.setTimestamp(8, bean.getModifiedDatetime());
				pstmt.setLong(9, bean.getId());
	            
	            
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
	            	
	            		throw new ApplicationException("Exception in updating Subject ");
	        } 
			finally 
				{
	            JDBCDataSource.closeConnection(conn);
	        	}
	        		log.debug("Model update End");
	  
	}


		/**
	     * Search Subject
	     *
	     * @param bean : Search Parameters
	     * @throws ApplicationException
	     * 
	     */
		
		
		public List search(SubjectBean bean) throws ApplicationException {
	        return search(bean, 0, 0);
	    }
	    
		 /**
	     * Search Subject with pagination
	     *
	     * @return list : List of Subject
	     * @param bean
	     *            : Search Parameters
	     * @param pageNo
	     *            : Current Page No.
	     * @param pageSize
	     *            : Size of Page
	     *
	     * @throws ApplicationException
	     */
	
		
		
		 public List search(SubjectBean bean, int pageNo, int pageSize) throws ApplicationException {
		      
			 System.out.println(";;;;;"+bean.getCourseId());
			 
		       log.debug("Model search Started");
		        
			 StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1");

		        if (bean != null) {
		        	
		        	/*if (bean.getId() > 0)
					{
						sql.append(" AND id = " + bean.getId());
					}
		           */
		            if (bean.getName() != null && bean.getName().length() > 0) 
			            {
			                sql.append(" AND NAME like '" + bean.getName() + "%'");
			            }
		            
		            if (bean.getDescription() != null && bean.getDescription().length() > 0)
			            {
			                sql.append(" AND DESCRIPTION like '" + bean.getDescription() + "%'");
			            }
		            
		            
		            if (bean.getCourseId() > 0) 
		            {
		                sql.append(" AND COURSE_ID = " + bean.getCourseId());
		            }
			           
		            System.out.println("thursday..........."+bean.getCourseId());
		            
		           /* if (bean.getCourseName()!= null && bean.getCourseName().length() > 0) 
			            {
			                sql.append(" AND COURSE_NAME like '" + bean.getCourseName() + "%'");
			            }
			         */
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
		            
		            	bean = new SubjectBean();
		               
		                bean.setId(rs.getLong(1));
		                bean.setName(rs.getString(2));
		                bean.setDescription(rs.getString(3));
		                bean.setCourseId(rs.getLong(4));
		                bean.setCourseName(rs.getString(5));
		                bean.setCreatedBy(rs.getString(6));
		                bean.setModifiedBy(rs.getString(7));
		                bean.setCreatedDatetime(rs.getTimestamp(8));
		                bean.setModifiedDatetime(rs.getTimestamp(9));

		               
		                
		                list.add(bean);
		            }
		           
		            rs.close();
		        } 
		        catch (Exception e) 
			        {
			            log.error("Database Exception..", e);
			            throw new ApplicationException("Exception : Exception in search user");
			        }
			        
		        finally 
			        {
			            JDBCDataSource.closeConnection(conn);
			        }
	
			        log.debug("Model search End");
			        return list;
		    }
		 
	
		 

		 /**
	     * Get List of Subject
	     *
	     * @return list : List of Subject
	     * @throws ApplicationException
	     */
		 
		
		 public List list() throws ApplicationException
		 	{
		        return list(0, 0);
		    }
		 
		 
		 /**
		     * Get List of Subject with pagination
		     *
		     * @return list : List of Subject
		     * @param pageNo
		     *            : Current Page No.
		     * @param pageSize
		     *            : Size of Page
		     * @throws ApplicationException
		     */
		 
		 public List list(int pageNo, int pageSize) throws ApplicationException {
		       
			 log.debug("Model list Started");
		     
			 ArrayList list = new ArrayList();
		     
			 StringBuffer sql = new StringBuffer("select * from ST_SUBJECT");
		        
		     
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
		                SubjectBean bean = new SubjectBean();
		               
		                bean.setId(rs.getLong(1));
		                bean.setName(rs.getString(2));
		                bean.setDescription(rs.getString(3));
		                bean.setCourseId(rs.getLong(4));
		                bean.setCourseName(rs.getString(5));
		                bean.setCreatedBy(rs.getString(6));
		                bean.setModifiedBy(rs.getString(7));
		                bean.setCreatedDatetime(rs.getTimestamp(8));
		                bean.setModifiedDatetime(rs.getTimestamp(9));

		               
		                
		                list.add(bean);
		            }
		            
		            rs.close();
		        }
		        catch (Exception e) 
			        {
			            log.error("Database Exception..", e);
			            throw new ApplicationException("Exception : Exception in getting list of users");
			        } 
		        finally 
			        {
			            JDBCDataSource.closeConnection(conn);
			        }

			        log.debug("Model list End");
			        return list;

		    }
		 
		 /**
		     * Find Subject by PK
		     *
		     * @param pk : get parameter
		     * @return bean
		     * @throws ApplicationException
		     */

		 public SubjectBean findByPK(long pk) throws ApplicationException{
				
				log.debug("Model findByPK Started");
				
				StringBuffer sql=new StringBuffer("SELECT * FROM ST_SUBJECT WHERE ID=?");
				
				SubjectBean bean=null;
				Connection conn=null;
				
				
				try {
					
					conn=JDBCDataSource.getConnection();
					PreparedStatement pstmt=conn.prepareStatement(sql.toString());
					pstmt.setLong(1, pk);
					ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{
							bean=new SubjectBean();
							
							bean.setId(rs.getLong(1));
							bean.setName(rs.getString(2));
							bean.setDescription(rs.getString(3));
							bean.setCourseId(rs.getLong(4));
							bean.setCourseName(rs.getString(5));
							
							bean.setCreatedBy(rs.getString(6));
			                bean.setModifiedBy(rs.getString(7));
			                bean.setCreatedDatetime(rs.getTimestamp(8));
			                bean.setModifiedDatetime(rs.getTimestamp(9));

					
					}
					rs.close();
					
				}
				catch (Exception e) {
					e.printStackTrace();
					
					log.error("Database Exception.."+ e);
					 throw new ApplicationException("Exception in getting User by pk");
					 
				}
				finally
					{
						JDBCDataSource.closeConnection(conn);
						
					}
				log.debug("Model findByPK End");
				
				return bean;
			}
		 
		 
		 /**
		     * Find Subject by Name
		     *
		     * @param name : get parameter
		     * @return bean
		     * @throws ApplicationException
		     */
		 
		 
		 
		 public SubjectBean findByName(String name) throws ApplicationException{
				
				log.debug("Model findByName Started");
				
				StringBuffer sql=new StringBuffer("SELECT * FROM ST_SUBJECT WHERE NAME=?");
				
				SubjectBean bean=null;
				Connection conn=null;
				
				
				try {
					
					conn=JDBCDataSource.getConnection();
					PreparedStatement pstmt=conn.prepareStatement(sql.toString());
					pstmt.setString(1, name);
					ResultSet rs=pstmt.executeQuery();
					while(rs.next())
					{
							bean=new SubjectBean();
							
							bean.setId(rs.getLong(1));
							bean.setName(rs.getString(2));
							bean.setDescription(rs.getString(3));
							bean.setCourseId(rs.getLong(4));
							bean.setCourseName(rs.getString(5));
							
							bean.setCreatedBy(rs.getString(6));
			                bean.setModifiedBy(rs.getString(7));
			                bean.setCreatedDatetime(rs.getTimestamp(8));
			                bean.setModifiedDatetime(rs.getTimestamp(9));

					
					}
					rs.close();
					
				}
				catch (Exception e) {
					e.printStackTrace();
					
					log.error("Database Exception.."+ e);
					 throw new ApplicationException("Exception in getting User by name");
					 
				}
				finally
					{
						JDBCDataSource.closeConnection(conn);
						
					}
				log.debug("Model findByName End");
				
				return bean;
			}
			

}

		 
		 
			


