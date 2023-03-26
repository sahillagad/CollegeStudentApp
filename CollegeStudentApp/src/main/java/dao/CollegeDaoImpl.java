package dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import entities.College;
import entities.Student;
import exceptions.CollegeException;
import exceptions.StudentException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import utilities.EMUtil;

public class CollegeDaoImpl implements CollegeDao{

	@Override
	public College registerCollege(College college) throws CollegeException {
    College college2=null;
		 
	EntityManager entityManager=EMUtil.provideEntityManager();
  
	entityManager.getTransaction().begin();
	
	     entityManager.persist(college);
	  college2=college;
	     
	entityManager.getTransaction().commit();
		
	 entityManager.close();
	return college2;
	}

	@Override
	public College getCollegeFromCollegeId(int collegeId) throws CollegeException {
	 College college=null;	
		  
		EntityManager em=EMUtil.provideEntityManager();
		
		 College college2=em.find(College.class, collegeId);
		 
		 if(college2!=null) {
			 college=college2;  
		 }
		 else {
			 
			 throw new CollegeException();
		 }
	 return college; 
	}

	@Override
	public List<College> getAllCollege() throws CollegeException {
		List<College> colleges=null;
		EntityManager em=EMUtil.provideEntityManager();

		
		String nq="Select * From college";
		Query query=em.createNativeQuery(nq,College.class);
		 colleges=query.getResultList();
		
		return colleges;
		
	}

	@Override
	public Student registerStudentToCollege(int studentId, int collegeId) throws CollegeException, StudentException {
		Student student=null;
		EntityManager em=EMUtil.provideEntityManager();
        
		College college=em.find(College.class,collegeId);
	
		if(college!=null) {
			Student student2=em.find(Student.class,studentId);
			if(student2!=null) {
				
				college.getStudents().add(student2);
				
				
				
				
			    em.persist(college); 
			}
			else {
				
				throw new StudentException();
			}
		
			
		}
		else {
		
			throw new  CollegeException();
		}
	
		return student;
	}

	@Override
	public List<Student> getAllStudentsFromCollegeId(int collegeId) throws CollegeException, StudentException {
		
		List<Student> list=null;
		
		EntityManager em=EMUtil.provideEntityManager();
		
	    College college=em.find(College.class, collegeId);
	    
	
	    if(college!=null) {
	    	
	    	Set<Student>  students=college.getStudents();
	    	list=new ArrayList<>();
	    	
	    	for(Student stu:students) {
	    		
	    		list.add(stu);
	    		
	    	}
	    	
	    	
	    }
	    else {
	    	throw new CollegeException();
	    }
		
		
		
	
		return list;
	}
	



}
