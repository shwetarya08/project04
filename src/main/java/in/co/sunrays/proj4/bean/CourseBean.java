package in.co.sunrays.proj4.bean;

/**
 * Course JavaBean encapsulates Course attributes
 *
 * @author FrontController
 * @version 1.0
 * 
 *
 */

public class CourseBean extends BaseBean {
	
	/**
     * Name of Course
     */
	private String Name;
	
	/**
     * Description of Course
     */
	private String description;

	/**
     * Duration of Course
     */
	
	private String duration;
	
	
	
	 /**
	   * accessor
	   */
	
	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	

	public String getKey() {
		
		return ""+id;
	}

	public String getValue() {
		
		return Name;
	}

	public int compareTo(Object o) {
		// TODO Auto-generated method stub
		return 0;
	}

}

