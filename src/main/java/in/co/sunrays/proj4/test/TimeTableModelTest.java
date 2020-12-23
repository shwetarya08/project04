package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.bean.TimeTableBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.RoleModel;
import in.co.sunrays.proj4.model.TimeTableModel;

public class TimeTableModelTest {
	
	public static TimeTableModel model = new TimeTableModel();
	
	
	public static void main(String[] args) throws Exception {
		//testAdd();
		//testList();
		//testSearch();
		//testUpdate();
		//testdupli();
		
	}
	
	
	/* public static void testAdd() {

	        try {
	            TimeTableBean bean = new TimeTableBean();
	           
	            bean.setId(10L);
	            bean.setCourseId(101L);
	            bean.setCourseName("bsc");
	            bean.setSubjectId(201L);
	            bean.setSubjectName("biology");
	            bean.setSemester("5th");
	            bean.setExamDate(null);
	            bean.setTime("4-7");
	            bean.setCreatedBy("Admin");
	            bean.setModifiedBy("Admin");
	            bean.setCreatedDatetime(null);
	            bean.setModifiedDatetime(null);
	            
	       //     long pk = model.add(bean);
	           
	          //  TimeTableBean addedbean = model.findByPK(pk);
	            
	            if (addedbean == null)
	            
	            {
	                System.out.println("Test add fail");
	            }
	        } 
	        
	        
	        catch (ApplicationException e) {
	            e.printStackTrace();
	        } catch (DuplicateRecordException e) {
	            e.printStackTrace();
	        }

	    }

*/	
	 
	 
	 public static void testList() {

	        try {
	            TimeTableBean bean = new TimeTableBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (TimeTableBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getCourseId());
	                System.out.println(bean.getCourseName());
	                System.out.println(bean.getSubjectId());
	                System.out.println(bean.getSubjectName());
	                System.out.println(bean.getSemester());
	                System.out.println(bean.getExamDate());
	                System.out.println(bean.getTime());
	               
	                System.out.println(bean.getCreatedBy());
	                System.out.println(bean.getModifiedBy());
	                System.out.println(bean.getCreatedDatetime());
	                System.out.println(bean.getModifiedDatetime());
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    }

	 public static void testDelete() {

	        try {
	            TimeTableBean bean = new TimeTableBean();
	            long pk = 2L;
	            bean.setId(pk);
	            model.delete(bean);
	            System.out.println("Test Delete succ" + bean.getId());
	            TimeTableBean deletedbean = model.findByPK(pk);
	            
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
	            TimeTableBean bean = new TimeTableBean();
	            List list = new ArrayList();
	            bean.setCourseName("MCA");
	            list = model.search(bean, 0, 0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (TimeTableBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getCourseId());
	                System.out.println(bean.getCourseName());
	                System.out.println(bean.getSemester());
	               
	            }

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	  }

	  
	  
	  
	  public static void testUpdate() throws Exception {

	        try {
	        	TimeTableModel model=new TimeTableModel();
	            TimeTableBean bean = model.findByPK(11L);
	            bean.setCourseName("Msc");
	           // bean.setSemester("4");
	           // bean.setExamDate(14-10-2019);
	          //  bean.setPhoneNo("8987583895");
	           
	            
	            model.update(bean);
	            System.out.println("Test Update succ");
	            TimeTableBean updateBean = model.findByPK(10L);
	           
	            if (!"BBA".equals(updateBean.getCourseName()))
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


	/*  public static void testdupli() {

		  try {
	            TimeTableBean bean = new TimeTableBean();
	            List list = new ArrayList();
	          //  bean.setCourseName("MCA");
	             bean    = model.findBySubCourseName("B.A", "statistics");
	                
	               
	                System.out.println(bean.getCourseId());
	                System.out.println(bean.getCourseName());
	                System.out.println(bean.getSemester());
	               
	            

	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }

	  }
*/
	  
	  
	  

	  
	  
	  
}
