import java.util.HashSet;

import dao.CollegeDao;
import dao.CollegeDaoImpl;
import entities.College;
import exceptions.CollegeException;

public class Runner {

	public static void main(String[] args) {
		College college = new College();
        college.setCollegeName("Sk College");
        college.setCollegeAddress("Mumbai");
      

     
        CollegeDao collegeService = new CollegeDaoImpl();
        
        try {
			college = collegeService.registerCollege(college);
			
		} catch (CollegeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        System.out.println("Collge Added with college id " + college.getCollegeId());
	
	}
}
