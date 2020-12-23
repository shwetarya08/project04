package in.co.sunrays.proj4.test;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.UserModel;

public class UserModelTest {

	   public static UserModel model = new UserModel();

	public static void main(String[] args) throws Exception {
		//testAdd();
		//testDelete();
		//testList();
		testUpdate();
		//testSearch();
	}

	//public static void testAdd() throws Exception {


      /*  try {
            UserBean bean = new UserBean();
            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");

         //   bean.setId(54L);
            bean.setFirstName("prashant");
            bean.setLastName("verma");
            bean.setLogin("prashant25@g.com");
            bean.setPassword("pass25");
            bean.setDob((Date) sdf.parse("25-10-2000"));
            bean.setMobileNo("9988776655");
            bean.setRoleId(3L);
            bean.setUnSuccessfulLogin(4);
            bean.setGender("male");
            bean.setLastLogin(null);
            bean.setLock("Yes");
            bean.setConfirmPassword("pass25");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(null);
            bean.setModifiedDatetime(null);
           
            UserModel model = new UserModel();
             model.add(bean);
            
            System.out.println("added");
            
            
            
            UserBean addedbean = model.findByPK(pk);
            
            if (addedbean == null) 
            {
                System.out.println("Test add fail");
            }
        } 
        
        catch (ApplicationException e) 
        {
            e.printStackTrace();
        } 
        
        catch (DuplicateRecordException e) {
            e.printStackTrace();
        }

        }
	




 *//** UserBean addedbean = model.findByPK(pk); System.out.println("Test add succ");
 * if (addedbean == null) { System.out.println("Test add fail"); }
 * @throws DuplicateRecordException 
 *//*
// } /*catch (ApplicationException e) { // e.printStackTrace();

*/
		
		 public static void testAdd() throws DuplicateRecordException
        {

     try {
         UserBean bean = new UserBean();
         SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");

         // bean.setId(5234L);
         bean.setFirstName("cjhsghbdswv");
         bean.setLastName("kumevbebawat");
         bean.setLogin("abhi12@g.com");
         bean.setPassword("pass1234");
         bean.setDob(null);
         bean.setRoleId(1L);
         bean.setUnSuccessfulLogin(2);
         bean.setGender("Male");
         bean.setLastLogin(null);
         bean.setLock("Yes");
         bean.setConfirmPassword("pass1234");
         model.add(bean);
         long pk = model.add(bean);
         
         UserBean addedbean = model.findByPK(pk);
         System.out.println("Test add succ");
         if (addedbean == null) {
             System.out.println("Test add fail");
         }
     } catch (ApplicationException e) {
         e.printStackTrace();
     }

 }
		
		
		
		
	 public static void testDelete() {

	        try {
	            UserBean bean = new UserBean();
	            long pk = 46L;
	            bean.setId(pk);
	            model.delete(bean);
	            System.out.println("Test Delete succ" + bean.getId());
	            UserBean deletedbean = model.findByPK(pk);
	            
	            if (deletedbean == null)
	            {
	                System.out.println("Test Delete fail");
	            }
	        } 
	        
	        catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    



}
	 
	 public static void testList() {

	        try {
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getMobileNo());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	                System.out.println(bean.getMobileNo());
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	 
	 
	 
	  public static void testUpdate() throws ParseException {

	        try {
	            UserBean bean = model.findByPK(55L);
	            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");
	            
	            
	            bean.setFirstName("Gujju");
	            bean.setLastName("Verma");
	            bean.setLogin("Gujju06@gmail.com");
	            bean.setPassword("7654321");
	           // bean.setDob("06-02-1998");
	            bean.setMobileNo("8989102609");
	            bean.setRoleId(1L);
	            bean.setUnSuccessfulLogin(2);
	            bean.setGender("female");
	            bean.setLastLogin(null);
	            bean.setLock("Yes");
	            bean.setConfirmPassword("pass123");
	            bean.setCreatedBy("Harshu");
	            bean.setModifiedBy("Harshu");
	            bean.setCreatedDatetime(null);
	            bean.setModifiedDatetime(null);

	            model.update(bean);

	            UserBean updatedbean = model.findByPK(55L);
	           
	            if (!"Rano".equals(updatedbean.getLogin()))
	            
	            {
	                System.out.println("Test Update fail");
	            }
	        } 
	        
	        catch (ApplicationException e)
	        {
	            e.printStackTrace();
	        }
	        catch (DuplicateRecordException e) 
	        {
	            e.printStackTrace();
	        }
	    }
	  
	  
	  public static void testSearch() {

		  try {
	            UserBean bean = new UserBean();
	            List list = new ArrayList();
	            bean.setFirstName("Arpita");
	            list = model.search(bean, 0, 0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (UserBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getLogin());
	                System.out.println(bean.getPassword());
	                System.out.println(bean.getDob());
	                System.out.println(bean.getRoleId());
	                System.out.println(bean.getUnSuccessfulLogin());
	                System.out.println(bean.getGender());
	                System.out.println(bean.getLastLogin());
	                System.out.println(bean.getLock());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	  }

}