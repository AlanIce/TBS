package Test;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.junit.*;

import TBS.Model.User;

public class UnitTest {
	
	private SessionFactory sessionFactory;
	private Session session;
	private Transaction transaction;
	
	
	@Before
	public void init() {
		Configuration config = new Configuration().configure();
		ServiceRegistry serviceRegistry =new StandardServiceRegistryBuilder().applySettings(config.getProperties()).build();
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
	public void deleteUser() {
		User user = new User();
		user.setAdmin(false);
		user.setUsername("2014301500000");
		user.setPassword("123456");
		user.setEmail("Test@Test.Test");
		user.setName("Test");
		session.delete(user);
 	}
	
	@Test
	public void testAdmin() {
		User user = new User();
		user.setAdmin(true);
		user.setUsername("admin");
		user.setPassword("c759eaf09e4638954f63ace0ce1b53b40f62ccb7");
		user.setEmail("admin@email.com");
		user.setName("系统管理员");
		session.update(user);		
 	}
	
	@Test
	public void testUser() {
		User user = new User();
		user.setAdmin(false);
		user.setUsername("2014301500384");
		user.setPassword("fa39e016576829c8237a2d5a82d428b52d586b22");
		user.setEmail("2198928117@qq.com");
		user.setName("邹文俊");
		session.update(user);		
 	}
}
