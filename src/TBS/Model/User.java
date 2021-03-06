package TBS.Model;
// Generated Oct 15, 2016 10:12:44 PM by Hibernate Tools 5.1.0.Beta1

/**
 * User generated by hbm2java
 */
public class User implements java.io.Serializable {

	private String username;
	private String password;
	private String name;
	private String email;
	private Boolean admin;

	public User() {
	}

	public User(String username, String email) {
		this.username = username;
		this.email = email;
	}

	public User(String username, String password, String name, String email, Boolean admin) {
		this.username = username;
		this.password = password;
		this.name = name;
		this.email = email;
		this.admin = admin;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getAdmin() {
		return this.admin;
	}

	public void setAdmin(Boolean admin) {
		this.admin = admin;
	}

}
