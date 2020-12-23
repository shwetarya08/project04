package in.co.sunrays.proj4.bean;

import java.util.Date;
/**
 * TimeTable JavaBean encapsulates TimeTable attributes
 *
 * @author FrontController
 * @version 1.0
 * 
 *
 */
public class TimeTableBean extends BaseBean {
	

	/**
     * CourseId of TimeTable
     */
	private long courseId;
	/**
     * Course Name of TimeTable
     */
	private String courseName;
	/**
     * SubjectId of TimeTable
     */
	private long subjectId;
	/**
     * Subject Name of TimeTable
     */
	private String subjectName;
	/**
     * Semester of TimeTable
     */
	private String semester;
	/**
     * Exam Date of TimeTable
     */
	private Date examDate;
	/**
     * Time of TimeTable
     */
	private String time;
	
	

	  /**
 * accessor
 */
	
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
	public String getSemester() {
		return semester;
	}
	public void setSemester(String semester) {
		this.semester = semester;
	}
	
	
	
	public Date getExamDate() {
		return examDate;
	}
	public void setExamDate(Date examDate) {
		this.examDate = examDate;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	
	
	
	public String getKey() {
		
		return id+"";
	}
	public String getValue() {
		
		return examDate+"";
	}
	public int compareTo(Object o) {
		
		return 0;
	}
	
	
	

}
