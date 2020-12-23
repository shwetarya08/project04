package in.co.sunrays.proj4.exception;

/**
 * DuplicateRecordException thrown when a duplicate record occurred
 *
 * @author FrontController
 * @version 1.0
 *  
 *
 */

public class DuplicateRecordException extends Exception {
	  /**
     * @param msg
     *            error message
     */

	public DuplicateRecordException(String msg)
	{
		super(msg);
	}

}
