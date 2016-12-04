package Test;

import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.*;
import org.hibernate.cfg.Configuration;
import org.junit.*;

import TBS.Model.*;

public class UnitTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	
	@Before
	public void init() {
		Configuration config = new Configuration().configure("Test/hibernate.cfg.xml");
		sessionFactory = config.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
	@After
	public void destroy() {
		transaction.commit();
		session.close();
		sessionFactory.close();
	}
	
	@Test
	public void createCourse() {
		Course course = new Course();
		course.setCourseId("004");
		course.setCourseName("计算机系统结构");
		course.setCourseImgSrc("/TBS/lib/image/courseCover/004.jpg");
		course.setTeacher("葛辉");
		session.update(course);
 	}
	
	@Test
	public void testQuestion() {
		String hql = "from Question where CourseID='001' order by rand()";
		TypedQuery<Question> query = session.createQuery(hql);
		query.setMaxResults(5);
		List<Question> questiones = query.getResultList();
		for (Question question:questiones) {
			System.out.println(question.getId());
		}
		
		
	}
}
