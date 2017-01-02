package TBS.Service;

import TBS.Dao.SignDao;
import TBS.Model.User;
import java.util.List;

import javax.servlet.http.HttpSession;

public class SignService {
	
	private SignDao signDao;
	private HttpSession session;

	public boolean checkUser(String username, String password, boolean role) {
		User user = signDao.getUser(username);
		boolean result = password.equals(user.getPassword()) && role == user.getAdmin();
		if (result) {
			session.setAttribute("user", user.getName());
			session.setAttribute("username", username);
			session.setAttribute("role", role);
		}			
		return result;
	}
	
	public boolean checkUserEmpty(String username) {
		User user = signDao.getUser(username);
		return (user == null);
	}
	
	public void signupUser(String username, String name, String email, String password) {
		session.setAttribute("user", name);
		session.setAttribute("role", false);
		User user = new User();
		user.setAdmin(false);
		user.setUsername(username);
		user.setName(name);
		user.setEmail(email);
		user.setPassword(password);
		signDao.saveUser(user);
	}
	
	public void changePassword(String username, String newpassword) {
		User user = signDao.getUser(username);
		user.setPassword(newpassword);
		signDao.updateUser(user);
	}
	
	public SignDao getSignDao() {
		return signDao;
	}

	public void setSignDao(SignDao signDao) {
		this.signDao = signDao;
	}
	
	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}
}
