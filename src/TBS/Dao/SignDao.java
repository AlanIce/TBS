package TBS.Dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import TBS.Model.User;

public class SignDao extends HibernateDaoSupport {
	
	public User getUser(String username) {
		String hql = "from User where Username = '" + username + "'";
		List<User> userList = (List<User>) getHibernateTemplate().find(hql);
		if (userList.size() == 0)
			return null;
		return userList.get(0);
	}
	
	public void saveUser(String username, String name, String email, String password) {
		User user = new User();
		user.setAdmin(false);
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		this.getHibernateTemplate().save(user);		
	}
}
