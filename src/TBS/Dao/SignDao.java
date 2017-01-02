package TBS.Dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import TBS.Model.User;

public class SignDao extends HibernateDaoSupport {
	
	public User getUser(String username) {
		String hql = "from User where Username = '" + username + "'";
		List<User> users = (List<User>) getHibernateTemplate().find(hql);
		if (users.size() == 0)
			return null;
		return users.get(0);
	}
	
	public void saveUser(User u) {
		this.getHibernateTemplate().save(u);
	}
	
	public void updateUser(User u) {
		this.getHibernateTemplate().update(u);
	}
}
