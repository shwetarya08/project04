package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.CourseBean;
import in.co.sunrays.proj4.bean.StudentBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.StudentModel;
import in.co.sunrays.proj4.model.SubjectModel;
import in.co.sunrays.proj4.model.TimeTableModel;

public class SubjectModelTest {
	
	
	public static SubjectModel model = new SubjectModel();
	
	
	public static void main(String[] args) throws Exception {
		
		//testAdd();
		//testDelete();
		//testList();
		//testSearch();
		testUpdate();
	}
	
	
	public static void testAdd() {

        try {
           SubjectBean bean = new SubjectBean();
           
            //bean.setId(3L);
            bean.setName("DSA");
            bean.setDescription("computer");
            bean.setCourseId(110l);
            bean.setCourseName("MCA");
            bean.setCreatedBy("Admin");
            bean.setModifiedBy("Admin");
            bean.setCreatedDatetime(null);
            bean.setModifiedDatetime(null);
           
            model.add(bean);
            long pk = model.add(bean);
           
           SubjectBean addedbean = model.findByPK(pk);
            
            if (addedbean == null)
            
            {
                System.out.println("Test add fail");
            }
            else{
            	System.out.println("Data added");
            }
        } 
        
        
        catch (ApplicationException e) {
            e.printStackTrace();
        } catch (DuplicateRecordException e) {
            e.printStackTrace();
        }

    }


	 public static void testDelete() {

	        try {
	            SubjectBean bean = new SubjectBean();
	          
	            long pk = 9L;
	            
	            bean.setId(pk);
	            
	            model.delete(bean);
	           
	            System.out.println("Test Delete succ" + bean.getId());
	           
	            SubjectBean deletedbean = model.findByPK(pk);
	            
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
	            SubjectBean bean = new SubjectBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (SubjectBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getName());
	                System.out.println(bean.getDescription());
	                System.out.println(bean.getCourseId());
	                System.out.println(bean.getCourseName());
	                
	               
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }


	  

	   public static void testSearch() throws Exception {

	  		  try {
	  	            SubjectBean bean = new SubjectBean();
	  	            List list = new ArrayList();
	  	            bean.setCourseId(1L);
	  	          //  bean.setId(2L);
	  	            list = model.search(bean, 0, 0);
	  	            if (list.size() < 0) {
	  	                System.out.println("Test Serach fail");
	  	            }
	  	            Iterator it = list.iterator();
	  	            while (it.hasNext()) {
	  	                bean = (SubjectBean) it.next();
	  	                System.out.println(bean.getId());
	  	              	             
	  	                System.out.println(bean.getName());
		                System.out.println(bean.getDescription());
		                System.out.println(bean.getCourseId());
		                System.out.println(bean.getCourseName());
		                
		               
	  	            }

	  	        } catch (ApplicationException e) {
	  	            e.printStackTrace();
	  	        }




	   }
	   

	/*   public static void testUpdate() 
       {

	        try {
	           
	           // SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");
	            SubjectModel model=new SubjectModel();
	          //  SubjectBean bean =new SubjectBean();
	            SubjectBean bean=model.findByPK(13l);
	            bean.setName("Hindi");
	           // bean.setLastName("Verma");
	           

	            model.update(bean);

	            SubjectBean updatedbean = model.findByPK(13L);
	           
	            if (!"Hindi".equals(updatedbean.getName()))
	            
	            {
	                System.out.println("Test Update succes");
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
	  


*/



	   public static void testUpdate() {

	        try {
	            SubjectBean bean = model.findByPK(12L);
	           
	            bean.setName("English");
	            bean.setCourseName("BA");
	            bean.setDescription("English is a very usefull language.");
	         
	            model.update(bean);
	            
	            System.out.println("Test Update succ");
	           
	            SubjectBean updateBean = model.findByPK(12L);
	           
	            if (!"Geography".equals(updateBean.getName()))
	            {
	                System.out.println("Test Update fail");
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


	
	

