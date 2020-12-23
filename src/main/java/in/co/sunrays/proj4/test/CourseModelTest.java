package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CourseModel;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.UserModel;

public class CourseModelTest {
	
	public static CourseModel model = new CourseModel();
	
	
	public static void main(String[] args) throws Exception {
		
		//testDelete();
		//testSearch();
		//testList();
		testUpdate();
	}
		
	
	
	
	public static void testDelete() throws Exception{
		
	

		        try {
		            CourseBean bean = new CourseBean();
		            long pk = 2L;
		            bean.setId(pk);
		            model.delete(bean);
		            System.out.println("Test Delete succ" + bean.getId());
		            CourseBean deletedbean = model.findByPK(pk);
		            
		            if (deletedbean == null)
		            {
		                System.out.println("Test Delete fail");
		            }
		        } 
		        
		        catch (ApplicationException e) {
		            e.printStackTrace();
		        }
		  

	}
	

	
	  public static void testSearch() {

		  try {
	            CourseBean bean = new CourseBean();
	            List list = new ArrayList();
	            bean.setName("LLB");;
	            list = model.search(bean, 0, 0);
	           
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	          
	            while (it.hasNext()) {
	                bean = (CourseBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getDescription());
	                System.out.println(bean.getDuration());
	               }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	  }

	
	  
	  
	  public static void testList() {

	        try {
	            CourseBean bean = new CourseBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (CourseBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getDescription());
	                System.out.println(bean.getDuration());
	               
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	  
	  
		 
		 public static void testUpdate() {

		        try {
		        	CourseModel model=new CourseModel();
		            CourseBean bean = model.findByPK(9L);
		            bean.setDuration("2");
		            bean.setDescription("Study of plants");
		           
		            
		            model.update(bean);
		            System.out.println("Test Update succ");
		            CourseBean updateBean = model.findByPK(9L);
		           
		            if (!"3".equals(updateBean.getDuration()))
		            {
		                System.out.println("Test Update succes");
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


	  
	
	
	
}
