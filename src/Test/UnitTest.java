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
	
	
//	@Before
	public void init() {
		Configuration config = new Configuration().configure("Test/hibernate.cfg.xml");
		sessionFactory = config.buildSessionFactory();
		session = sessionFactory.openSession();
		transaction = session.beginTransaction();
	}
	
//	@After
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
		String s = "你好，\"明天\"";
		System.out.println(s.replace("\"", "'"));
	}
}
