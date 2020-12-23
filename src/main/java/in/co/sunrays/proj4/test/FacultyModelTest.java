package in.co.sunrays.proj4.test;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.bean.FacultyBean;
import in.co.sunrays.proj4.bean.UserBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.FacultyModel;
import in.co.sunrays.proj4.model.UserModel;

public class FacultyModelTest {
	
	 public static FacultyModel model = new FacultyModel();
	 
	 
	 public static void main(String[] args) throws Exception {
		//testAdd();
		// testList();
		// testDelete();
		 testUpdate();
	}
	 
	
	public static void testAdd() throws Exception {


	     /*   try {*/
	            FacultyBean bean = new FacultyBean();
	            SimpleDateFormat sdf = new SimpleDateFormat("MM-DD-yyy");

	            bean.setId(1L);
	            bean.setCollegeId(15l);
	            bean.setCourseId(102l);
	            bean.setCourseName("Bsc");
	            bean.setFirstName("Prashant");
	            bean.setLastName("verma");
	            bean.setSubjectId(130l);
	            bean.setSubjectName("Biotec");
	            bean.setCollegeName("Itm University");
	            bean.setQualifiaction("Mtec");
	            bean.setEmailId("Arpita06@gmail.com");
	            bean.setDob(null);
	            bean.setMobNo("898989898");
	            bean.setCreatedBy("Admin");
	            bean.setModifiedBy("Admin");
	            bean.setCreatedDatetime(null);
	            bean.setModifiedDatetime(null);
	            // UserModel model = new UserModel();
	             model.add(bean);
	            
	            System.out.println("Data added");
	        }
		


	
	
	
	 
	 public static void testList() {

	        try {
	            FacultyBean bean = new FacultyBean();
	            List list = new ArrayList();
	            list = model.list(1, 10);
	            if (list.size() < 0) {
	                System.out.println("Test list fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                bean = (FacultyBean) it.next();
	                System.out.println(bean.getId());
	                System.out.println(bean.getCollegeId());
	                System.out.println(bean.getCourseId());
	                System.out.println(bean.getCourseName());
	                System.out.println(bean.getFirstName());
	                System.out.println(bean.getLastName());
	                System.out.println(bean.getSubjectId());
	                System.out.println(bean.getSubjectName());
	                System.out.println(bean.getCollegeName());
	                System.out.println(bean.getQualification());
	              
	                
	                System.out.println(bean.getEmailId());
	                
	                System.out.println(bean.getDob());
	                System.out.println(bean.getMobNo());
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
	            FacultyBean bean = new FacultyBean();
	            long pk = 7L;
	            bean.setId(pk);
	            model.delete(bean);
	            System.out.println("Test Delete succ" + bean.getId());
	            FacultyBean deletedbean = model.findByPK(pk);
	            
	            if (deletedbean == null)
	            {
	                System.out.println("Test Delete fail");
	            }
	        } 
	        
	        catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	    
	 }
	 
	 public static void testUpdate() {

	        try {
	        	FacultyModel model=new FacultyModel();
	            FacultyBean bean = model.findByPK(13L);
	            bean.setFirstName("Divya");
	            bean.setLastName("Singh");
	          //  bean.setPhoneNo("8987583895");
	           
	            
	            model.update(bean);
	            System.out.println("Test Update succ");
	            FacultyBean updateBean = model.findByPK(13L);
	           
	            if (!"Divya".equals(updateBean.getFirstName()))
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


	 
	 
	 

