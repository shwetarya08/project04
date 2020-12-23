package in.co.sunrays.proj4.exception;

/**
 * DatabaseException is propogated by DAO classes when an unhandled Database
 * exception occurred
 *
 * @author FrontController
 * @version 1.0
 * 
 *
 */
public class DatabaseException extends Exception {
	

	  /**
   * @param msg
   *            : Error message
   */
	
	public DatabaseException(String msg)
	{
		super(msg);
		
	}

}
