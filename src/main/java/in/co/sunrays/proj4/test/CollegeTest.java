package in.co.sunrays.proj4.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj4.bean.CollegeBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.CollegeModel;

public class CollegeTest {
	
	
	 public static CollegeModel model = new CollegeModel();
	public static void main(String[] args) throws DuplicateRecordException {
		//testAdd();
		//testDelete();
		//testList();
		testUpdate();
		
	}
	
	
	
		
		  public static void testAdd() throws DuplicateRecordException {

		        try {
		            CollegeBean bean = new CollegeBean();
		           // bean.setId(2L);
		            bean.setName("jsp");
		            bean.setAddress("borawan");
		            bean.setState("mp");
		            bean.setCity("indore");
		            bean.setPhoneNo("073124244");
		            bean.setCreatedBy("Admin");
		            bean.setModifiedBy("Admin");
		            bean.setCreatedDatetime(null);
		            bean.setModifiedDatetime(null);
		            long pk = model.add(bean);
		            System.out.println("Test add succ");
		            CollegeBean addedBean = model.findByPK(pk);
		            if (addedBean == null) {
		                System.out.println("Test add fail");
		            }
		        } catch (ApplicationException e) {
		            e.printStackTrace();
		        }

		    }
		
		
		  
		  
		  public static void testDelete() {

		        try {
		            CollegeBean bean = new CollegeBean();
		            
		            long pk = 9L;
		            
		            bean.setId(pk);
		            
		            model.delete(bean);
		            
		            System.out.println("Test Delete succ");
		           
		            CollegeBean deletedBean = model.findByPK(pk);
		            
		            if (deletedBean != null) 
		            {
		                System.out.println("Test Delete fail");
		            }
		        } 
		        
		        catch (ApplicationException e)
		        {
		            e.printStackTrace();
		        }
		    }

	
		  
		  
		  public static void testList() {

		        try {
		            CollegeBean bean = new CollegeBean();
		           
		            List list = new ArrayList();
		            
		            list = model.list(1, 10);
		            
		            if (list.size() < 0) 
		            {
		                System.out.println("Test list fail");
		            }
		            
		            Iterator it = list.iterator();
		            
		            while (it.hasNext()) {
		                bean = (CollegeBean) it.next();
		                System.out.println(bean.getId());
		                System.out.println(bean.getName());
		                System.out.println(bean.getAddress());
		                System.out.println(bean.getState());
		                System.out.println(bean.getCity());
		                System.out.println(bean.getPhoneNo());
		                System.out.println(bean.getCreatedBy());
		                System.out.println(bean.getCreatedDatetime());
		                System.out.println(bean.getModifiedBy());
		                System.out.println(bean.getModifiedDatetime());
		                
		                System.out.println("hello");
		            }

		        }
		        
		        catch (ApplicationException e) 
		        
		        {
		            e.printStackTrace();
		        }
		    }

		  
		  
		  public static void testUpdate() {

		        try {
		            CollegeBean bean = model.findByPK(6L);
		            bean.setName("krg");
		            bean.setAddress("Bhopal");
		            bean.setPhoneNo("8987583895");
		            model.update(bean);
		            System.out.println("Test Update succ");
		            CollegeBean updateBean = model.findByPK(6L);
		           
		            if (!"Krg".equals(updateBean.getName()))
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

		  
		  
	


