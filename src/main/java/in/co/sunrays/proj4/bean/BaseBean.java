package in.co.sunrays.proj4.bean;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 * Parent class of all Beans in application. It contains generic attributes.
 *
 * @author FrontController
 * @version 1.0
 * 
 *
 */




public abstract class BaseBean implements Comparable,Serializable, DropdownListBean {
	  /**
     * Non Business primary key
     */
	protected long id;
	
	 /**
     * Contains USER ID who created this database record
     */
	protected String createdBy ;
	
	  /**
     * Contains USER ID who modified this database record
     */
	protected String modifiedBy;
	
	
	  /**
     * Contains Created Timestamp of database record
     */
	protected Timestamp createdDatetime;
	
	
	 /**
     * Contains Modified Timestamp of database record
     */
	protected Timestamp modifiedDatetime;
	
	
	   /**
     * accessor
     * Gets Id
     */
	
	public long getId() {
		return id;
	}
	
	
	 /**
     * accessor 
     * 
     * Sets Id
     */
	
	public void setId(long id) {
		this.id = id;
	}
	
	 /**
     * accessor
     * 
     * Get createdBy
     * 
     */
	public String getCreatedBy() {
		return createdBy;
	}
	

	 /**
    * accessor
    * 
    * Set createdBy
    * 
    */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	
	

	 /**
    * accessor
    * 
    * Get ModifiedBy
    * 
    */
	public String getModifiedBy() {
		return modifiedBy;
	}
	
	 /**
	    * accessor
	    * 
	    * set ModifiedBy
	    * 
	    */
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	 /**
	    * accessor
	    * 
	    * Get CreatedDatetime
	    * 
	    */
	public Timestamp getCreatedDatetime() {
		return createdDatetime;
	}
	/**
	    * accessor
	    * 
	    * set CreatedDatetime
	    * 
	    */
	public void setCreatedDatetime(Timestamp createdDatetime) {
		this.createdDatetime = createdDatetime;
	}
	
	
	/**
	    * accessor
	    * 
	    * Get ModifiedDatetime
	    * 
	    */
	public Timestamp getModifiedDatetime() {
		return modifiedDatetime;
	}
	
	/**
	    * accessor
	    * 
	    * set ModifiedDatetime
	    * 
	    */
	public void setModifiedDatetime(Timestamp modifiedDatetime) {
		this.modifiedDatetime = modifiedDatetime;
	}
	
	
	
}
