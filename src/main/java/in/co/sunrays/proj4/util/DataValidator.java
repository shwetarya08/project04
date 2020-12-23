package in.co.sunrays.proj4.util;

import java.text.ParseException;
import java.util.Date;


/**
 * This class validates input data
 *
 * @author FrontController
 * @version 1.0
 * 
 */



public class DataValidator {
	
    /**
     * Checks if value is Null
     *
     * @param val
     * @return
     */
	
	 public static boolean isNull(String val) {
	        if (val == null || val.trim().length() == 0) {
	            return true;
	        } else {
	            return false;
	        }
	 }
	 
	 
	 /**
	     * Checks if value is NOT Null
	     *
	     * @param val
	     * @return
	     */
	 
	 
	  public static boolean isNotNull(String val) {
	        return !isNull(val);
	  }
	  
	  
	  /**
	     * Checks if value is an Integer
	     *
	     * @param val
	     * @return
	     */
	  
	  public  static boolean isInteger(String val) 
	  
	  {

	        if (isNotNull(val)) 
	        {
	            try {
	                int i = Integer.parseInt(val);
	                return true;
	            } 
	            catch (NumberFormatException e) 
	            {
	                return false;
	            }

	        } 
	        else {
	            return false;
	        }
	    }
	  
	  

	    /**
	     * Checks if value is Long
	     *
	     * @param val
	     * @return
	     */
	  
	  public static boolean isLong(String val) {
	        if (isNotNull(val)) 
	        {
	            try
	            {
	                long i = Long.parseLong(val);
	                return true;
	            } 
	            catch (NumberFormatException e) 
	            {
	                return false;
	            }

	        } else {
	            return false;
	        }
	    }
	  
	  
	  /**
	     * Checks if value is valid Email ID
	     *
	     * @param val
	     * @return
	     */
	  
	  public static boolean isEmail(String val) {

	        String emailreg ="^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

	        if (isNotNull(val))
	        {
	            try 
		            {
		                return val.matches(emailreg);
		             } 
	            catch (NumberFormatException e) 
		            {
		                return false;
		            }

	        } else
	        {
	            return false;
	        }
	    }
	  
	  
	  /**
	     * Checks if value is valid Email ID
	     *
	     * @param val
	     * @return
	     */
	  
	  
	  public static boolean isPassword(String val) {

	        String pwd = "^.*(?=.{6,})(?=..*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$";

	        if (isNotNull(val)) {
	            try 
		            {
		                return val.matches(pwd);
		            } 
	            catch (NumberFormatException e) 
		            {
		                return false;
		            }

	        } else {
	            return false;
	        }
	    }
	 
	 
	  /**
	     * Checks if value is String
	     *
	     * @param val
	     * @return
	     */
	  
	  
	  public static boolean isString(String val) {

	        String stringreg = "^[a-zA-Z]+$";

	        if (isNotNull(val)) {
	           
	        	try 
	            {
	                return val.matches(stringreg);
	            } 
	            
	            catch (NumberFormatException e) 
	            
	            {
	                return false;
	            }

	        } 
	        
	        else 
	        {
	            return false;
	        }
	    }
	  
	  
	  
	  
	  
	  public static boolean isName(String val) {

	        String name ="^[a-zA-Z_ ',.-]+$";

	        if (isNotNull(val)) {
	           
	        	try 
	            {
	                return val.matches(name);
	            } 
	            
	            catch (NumberFormatException e) 
	            
	            {
	                return false;
	            }

	        } 
	        
	        else 
	        {
	            return false;
	        }
	    }
	  
	  
	  
	  /**
	     * Checks if value is Mobile No
	     *
	     * @param val
	     * @return
	     */
	  
	  public static boolean isMobNo(String val) {

	        String stringreg = "^[7-9][0-9]{9}$";

	        if (isNotNull(val))
	        {
	           
	        	try 
	            {
	                return val.matches(stringreg);
	            } 
	            
	            catch (NumberFormatException e) 
	            
	            {
	                return false;
	            }

	        } 
	        
	        else 
	        {
	            return false;
	        }
	    }
	  
	  
	  
	  public static boolean isRollNo(String val) {

	      //  String stringreg = "^[7-9][0-9]{9}$";

		  String stringreg = "^[0-9]{2}[a-zA-Z]{2,3}[0-9]{2}";
		  
	        if (isNotNull(val)) {
	           
	        	try 
	            {
	                return val.matches(stringreg);
	            } 
	            
	            catch (NumberFormatException e) 
	            
	            {
	                return false;
	            }

	        } 
	        
	        else 
	        {
	            return false;
	        }
	    }
	  
	  
	    /**
	     * Checks if value is Date
	     *
	     * @param val
	     * @return
	     */
	  
	  
	  public static boolean isDate(String val) {
           System.out.println("value.."+val);
	        
           Date d = null;
	       
           if (isNotNull(val)) 
           {
	        	System.out.println("inside not null");
	            d = DataUtility.getDate(val);
	        }
	        return d != null;
	    }
	  
	  
	  
	  public static boolean isValidAge(String val)
		{
			
			boolean pass = false;
			if (isDate(val)) {
				Date cdate = new Date();
				try {
					Date userdate = DataUtility.formatter.parse(val);
					int age = cdate.getYear()-userdate.getYear();
					System.out.println("final age  "+age);
					if(age>=18){
						pass=true;
					}
				} catch (ParseException e) {
					
				}
			}
			
			return pass;
		}
/**
	  
	  
	  
	  
	  /**
	     * Test above methods
	     *
	     * @param args
	     */
	  
	  
	  
	public static void main(String[] args) {
		
		  	/*System.out.println("Not Null 2" + isNotNull("ABC"));
	        System.out.println("Not Null 3" + isNotNull(null));
	        System.out.println("Not Null 4" + isNull("123"));

	        System.out.println("Is Int " + isInteger(null));
	        System.out.println("Is Int " + isInteger("ABC1"));
	        System.out.println("Is Int " + isInteger("123"));
	        System.out.println("Is Int " + isNotNull("123"));*/
		
	       // System.out.println(isString("Harshu2345"));
		
		  System.out.println(isName("harshu.,tnanu"));
			
		
		//System.out.println(isMobNo("998958484"));
		//System.out.println(isRollNo("11aoyu49"));
		
		
	//	System.out.println(isPassword("H13@hghrt"));
	}


}
