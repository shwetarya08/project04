package in.co.sunrays.proj4.test;

import in.co.sunrays.proj4.bean.MarksheetBean;
import in.co.sunrays.proj4.bean.SubjectBean;
import in.co.sunrays.proj4.exception.ApplicationException;
import in.co.sunrays.proj4.exception.DuplicateRecordException;
import in.co.sunrays.proj4.model.MarksheetModel;

public class MarksheetModelTest {
	
	
	public static MarksheetModel model = new MarksheetModel();
	
	public static void main(String[] args) throws Exception {
		
		testAdd();
		//testUpdate();
		
		
	}
	
	
		
		 public static void testAdd() {

		        try {
		            MarksheetBean bean = new MarksheetBean();
		           
		            bean.setId(17L);
		            bean.setName("Mahi Singh");
		            bean.setRollNo("50be07");
		            bean.setPhysics(58);
		            bean.setChemistry(87);
		            bean.setMaths(89);
		            bean.setStudentId(16L);
		            bean.setCreatedBy("Admin");
		            bean.setModifiedBy("Admin");
		            bean.setCreatedDatetime(null);
		            bean.setModifiedDatetime(null);
		            
		            long pk = model.add(bean);
		           
		            MarksheetBean addedbean = model.findByPK(pk);
		            
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
		
		 
		   public static void testUpdate() {

		        try {
		        	MarksheetModel model=new MarksheetModel();
		            MarksheetBean bean = model.findByPK(3l);
		           
		            
		            bean.setName("Ankita sharma");
		         //  bean.setPhysics(90);
		          // bean.setChemistry(90);
		        //   bean.setMaths(90);
		           
		           
		         
		            model.update(bean);
		            
		            System.out.println("Test Update succ");
		           
		            MarksheetBean updateBean = model.findByPK(3L);
		           
		            if (!"Ashish Nehra".equals(updateBean.getName()))
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
	

