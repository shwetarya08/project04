package in.co.sunrays.proj4.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.RoleBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.RoleModel;

public class RoleModelTest {
   
	public static RoleModel model = new RoleModel();
	
	public static void main(String[] args) throws ParseException {
		testAdd();
		//testDelete();
		//testList();
	}
	
	
	
	 public static void testAdd() throws ParseException {

	        try {
	            RoleBean bean = new RoleBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

	            bean.setId(1L);
	            bean.setName("lklj");
	            bean.setDescription("jhguhgh");
	            
	            long pk = model.add(bean);
	           
	            RoleBean addedbean = model.findByPK(pk);
	            
	            if (addedbean == null) 
	            {
	                System.out.println("Test add fail");
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

	 
	 
	 
	 
	 public static void testDelete() {

	        try {
	            RoleBean bean = new RoleBean();
	            
	            long pk = 7L;
	            
	            bean.setId(pk);
	            
	            model.delete(bean);
	            
	            RoleBean deletedbean = model.findByPK(pk);
	            
	            if (deletedbean != null) {
	                System.out.println("Test Delete fail");
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 
	 
	 
	 public static void testList() {

	        try {
	            RoleBean bean = new RoleBean();
	            
	            List list = new ArrayList();
	           
	            list = model.list(1, 10);
	            
	            if (list.size() < 0) 
	            
	            {
	                System.out.println("Test list fail");
	            }
	            
	            Iterator it = list.iterator();
	            
	            while (it.hasNext()) {
	               
	            	bean = (RoleBean) it.next();
	                
	            	System.out.println(bean.getId());
	                
	            	System.out.println(bean.getName());
	                
	            	System.out.println(bean.getDescription());
	            }

	        } catch (ApplicationException e)
	        
	        {
	            e.printStackTrace();
	        }
	    }
	}

