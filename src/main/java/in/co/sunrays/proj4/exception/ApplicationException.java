package in.co.sunrays.proj4.exception;

/**
 * ApplicationException is propogated from Service classes when an business
 * logic exception occurered.
 *
 * @author FrontController
 * @version 1.0
 *
 *
 */
public class ApplicationException extends Exception {
	
	 /**
     * @param msg
     *            : Error message
     */
	public ApplicationException( String msg)
	{
		super(msg);
	}

}
