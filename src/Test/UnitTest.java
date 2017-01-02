package Test;

import java.math.BigInteger;
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
	public void show() {
		String hql = "SELECT count(*) FROM (SELECT t FROM Testrecord t WHERE courseID='001' and t.score=( "
				+ "SELECT max(score) FROM Testrecord WHERE username=t.username ) GROUP BY username)";
		TypedQuery<BigInteger> query = session.createQuery(hql);
		int count = query.getSingleResult().intValue();
		System.out.println("Count : " + count);
	}
	
	@Test
	public void jsonTest() {
		String CourseID = "001";
		int lo = 0;
		int hi = 60;
		String sql = "SELECT count(*) FROM (SELECT * FROM Testrecord t WHERE courseID='"+CourseID+"' AND score=("
				+ "SELECT max(score) FROM Testrecord WHERE username=t.username AND courseID='"+CourseID+"') GROUP BY username) a WHERE a.Score>="+lo+" AND a.Score<"+hi;
		TypedQuery<BigInteger> query = session.createNativeQuery(sql);
		System.out.println(query.getSingleResult().intValue());
	}
}
