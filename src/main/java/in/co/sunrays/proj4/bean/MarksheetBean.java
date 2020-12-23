package in.co.sunrays.proj4.bean;

/**
 * Marksheet JavaBean encapsulates Marksheet attributes
 *
 * @author FrontController
 * @version 1.0
 * 
 *
 */

public class MarksheetBean extends BaseBean{
	
	
	 /**
     * Rollno of Student
     */
	private String rollNo;
	
	 /**
     * ID of Student
     */
	private long studentId;
	
	  /**
     * Name of Student
     */
	private String name;
	  /**
     * Physics marks of Student
     */
	private int physics;
	
	 /**
     * Chemistry marks of Student
     */
	private int chemistry;
	
	   /**
     * Mathematics marks of Student
     */
	private int maths;
	
	
	  /**
     * PhysicsS marks of Student
     */
	private String physicsS;
	
	
	 /**
     * ChemistryY marks of Student
     */
	private String chemistryY;
	
	   /**
     * MathematicsS marks of Student
     */
	private String mathsS;
	
	
	
	
	

	  /**
   * accessor
   */
	
	
	public String getPhysicsS() {
		return physicsS;
	}

	public void setPhysicsS(String physicsS) {
		this.physicsS = physicsS;
	}

	public String getChemistryY() {
		return chemistryY;
	}

	public void setChemistryY(String chemistryY) {
		this.chemistryY = chemistryY;
	}

	public String getMathsS() {
		return mathsS;
	}

	public void setMathsS(String mathsS) {
		this.mathsS = mathsS;
	}

	
	
	
	public String getRollNo() {
		return rollNo;
	}

	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}

	public long getStudentId() {
		return studentId;
	}

	public void setStudentId(long studentID) {
		this.studentId = studentID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPhysics() {
		return physics;
	}

	public void setPhysics(int physics) {
		this.physics = physics;
	}

	public int getChemistry() {
		return chemistry;
	}

	public void setChemistry(int chemistry) {
		this.chemistry = chemistry;
	}

	public int getMaths() {
		return maths;
	}

	public void setMaths(int maths) {
		this.maths = maths;
	}

	

	public String getKey() {


		return id+"";
	}

	public String getValue() {


		return rollNo;
	}

	public int compareTo(Object o) {
		
		return 0;
	}

}
