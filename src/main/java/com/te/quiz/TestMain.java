package com.te.quiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.te.quiz.bean.Category;
import com.te.quiz.bean.Questions;
import com.te.quiz.configuration.CategoryConfig;

public class TestMain {
	static EntityManagerFactory emf = null;
	static EntityManager em = null;
	static	EntityTransaction t=null;
	
public static void main(String[] args) {
	
	ApplicationContext context = new AnnotationConfigApplicationContext(CategoryConfig.class);

	Category c1= context.getBean(Category.class);
	c1.setCid(10);
	c1.setCname("Entertainment");
	
	Category c2= context.getBean(Category.class);
	c2.setCid(20);
	c2.setCname("Sports");
	
	ApplicationContext context1 = new AnnotationConfigApplicationContext(CategoryConfig.class);
	
Questions qe1	= context1.getBean(Questions.class);
	qe1.setId(1);
	qe1.setQues("Who is the Director of Chennai Express?");
	qe1.setOptionA("A-Rohit Shetty");
	qe1.setOptionB("B-Kabir singh");
	qe1.setOptionC("C-Yah raj");
	qe1.setOptionD("D-Bonny Kapoor");
	qe1.setCorrectOption("A");
	qe1.setCategory(c1);
	
	Questions qe2	= context1.getBean(Questions.class);
	qe2.setId(2);
	qe2.setQues("ShahRukh khan Home Town");
	qe2.setOptionA("A-Delhi");
	qe2.setOptionB("B-mumbai");
	qe2.setOptionC("C-Allahabad");
	qe2.setOptionD("D-none of above");
	qe2.setCorrectOption("A");
	qe2.setCategory(c1);
	
	
	
	
	ArrayList<Questions> eq = new ArrayList<Questions>();
	eq.add(qe1);
	eq.add(qe2);
	
	
	Questions qt1	= context1.getBean(Questions.class);
	qt1.setId(6);
	qt1.setQues("Who is the winner of ICC WTC 2021 ?");
	qt1.setOptionA("A-Australia");
	qt1.setOptionB("B-England");
	qt1.setOptionC("C-NewZeland");
	qt1.setOptionD("D-India");
	qt1.setCorrectOption("C");
	qt1.setCategory(c2);
	
	Questions qt2	= context1.getBean(Questions.class);
	qt2.setId(7);
	qt2.setQues("Who is the Winner of CWC 2019 ?");
	qt2.setOptionA("A-Australia");
	qt2.setOptionB("B-England");
	qt2.setOptionC("C-NewZeland");
	qt2.setOptionD("D-India");
	qt2.setCorrectOption("B");
	qt2.setCategory(c2);
	
	
	
	
	ArrayList<Questions> tq = new ArrayList<Questions>();
	tq.add(qt1);
	tq.add(qt2);
	
	
	
	c1.setQuestions(eq);
	c2.setQuestions(tq);
	

		emf=Persistence.createEntityManagerFactory("Quiz");
		em=emf.createEntityManager();
		 t=em.getTransaction();
		 
		 
		 
		 Scanner sc = new Scanner(System.in);
		
		 System.out.println("Enter your name");
         String name = sc.nextLine();
         
         System.out.println("choose your category");
         System.out.println("10-Entertainment");
         System.out.println("20-Technology");
		 int id = Integer.parseInt(sc.nextLine());
		 getQuetions(id);
		 
}
		
	 
		 
		public static void getQuetions(int id) {
			 Scanner sc = new Scanner(System.in);
			 
			 int result=0;
	        
	     
			 String s = "from Category where cid=:id ";
			 Query q = em.createQuery(s);
			 q.setParameter("id", id);
			 Category c = (Category) q.getSingleResult();
			 System.out.println(c.getCname());
			 List<Questions> que = c.getQuestions();
			 
			 for (Questions m : que) {
				 System.out.println(m.getQues());
				 System.out.println(m.getOptionA());
				 System.out.println(m.getOptionB());
				 System.out.println(m.getOptionC());
				 System.out.println(m.getOptionD());
				 System.out.println("enter your ans");
				 String ans = sc.nextLine();
				String corret= m.getCorrectOption();
				 if(corret.equalsIgnoreCase(ans)) {
					 System.out.println("correct ans");
					 result ++;
				 }
				 else
					 System.err.println("worng ans");
				
			}
			 System.out.println("your score is5/"+result);
			 
		}
		 

		 
		 

}