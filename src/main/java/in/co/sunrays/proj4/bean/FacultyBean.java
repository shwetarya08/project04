package in.co.sunrays.proj4.bean;

/**
 * Faculty JavaBean encapsulates Faculty attributes
 *
 * @author FrontController
 * @version 1.0
 * 
 *
 */

import java.util.Date;

import in.co.sunrays.proj4.util.DataUtility;

public class FacultyBean  extends BaseBean{
	
	/**
     * CollegeId of Faculty
     */
	private long collegeId;
	/**
     * CollegeId of Faculty
     */
	private long courseId;
	
	/**
     * CourseName of Faculty
     */
	private String courseName;
	/**
     * First Name of Faculty
     */
	private String firstName;
	/**
     * Last Name of Faculty
     */
	private String lastName;
	/**
     * SubjectId of Faculty
     */
	private long subjectId;
	/**
     * Subjecct Name of Faculty
     */
	private String subjectName;
	/**
     * College Name of Faculty
     */
	private String collegeName;
	/**
     * Qualification of Faculty
     */
	private String qualification;
	/**
     * EmailId of Faculty
     */
	private String emailId;
	/**
     * Date Of Birth of faculty
     */
	private Date Dob;
	/**
     *Mobile of Faculty
     */
	private String mobNo;
	
	

	  /**
 * accessor
 */
	
	public long getCollegeId() {
		return collegeId;
	}
	public void setCollegeId(long collegeId) {
		this.collegeId = collegeId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public long getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(long subjectId) {
		this.subjectId = subjectId;
	}
	public String getSubjectName() {
		return subjectName;
	}
	public void setSubjectName(String subjectName) {
		this.subjectName = subjectName;
	}
	public String getCollegeName() {
		return collegeName;
	}
	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}
	public String getQualification() {
		return qualification;
	}
	public void setQualifiaction(String qualifiaction) {
		this.qualification = qualifiaction;
	}
	
	
	
	public Date getDob() {
		return Dob;
	}
	public void setDob(Date dob) {
		Dob = dob;
	}
	
	public String getEmailId() {
		return emailId;
	}
	
	
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	public String getMobNo() {
		return mobNo;
	}
	public void setMobNo(String mobNo) {
		this.mobNo = mobNo;
	}
	
	
	
	public String getKey() {
		
		return ""+id;
	}
	public String getValue() {
		
		return DataUtility.getDateString(Dob);
	}
	public int compareTo(Object o) {
		
		return 0;
	}
	
	
	
	
	
	
	
	

}
