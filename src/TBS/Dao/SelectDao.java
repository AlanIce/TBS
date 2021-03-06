package TBS.Dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import TBS.Model.Course;
import TBS.Model.Usercourse;

public class SelectDao extends HibernateDaoSupport {
	
	public Course getCourse(String courseID) {
		String hql = "FROM Course WHERE CourseID = '"+courseID+"'";
		List<Course> courses = (List<Course>) getHibernateTemplate().find(hql);
		if (courses.size() == 0)
			return null;
		return courses.get(0);
	}
	
	public void updateCourse(Course course) {
		getHibernateTemplate().update(course);
	}
	
	public List<Course> getCourseList() {
		String hql = "FROM Course WHERE courseID!=null";
		List<Course> courses = (List<Course>) getHibernateTemplate().find(hql);
		return courses;
	}
	
	public List<Course> getMyCourseList(String username, String type) {
		String hql = "";
		if (type.equals("selected"))
			hql = "FROM	Course a WHERE EXISTS ( FROM Usercourse WHERE username='"+username+"' AND courseId=a.courseId )";
		else if (type.equals("unselected"))
			hql = "FROM	Course a WHERE NOT EXISTS ( FROM Usercourse WHERE username='"+username+"' AND courseId=a.courseId )";
		else if (type.equals("uncompleted"))
			hql = "FROM Course a WHERE EXISTS ( FROM Usercourse WHERE username='"+username+"' AND completed=0 AND courseId=a.courseId)";
		TypedQuery<Course> query = getSessionFactory().getCurrentSession().createQuery(hql);
		List<Course> courses = query.getResultList();
		return courses;
	}
	
	public void addUserCourse(Usercourse u) {
		getHibernateTemplate().save(u);
	}
	
	public void deleteUserCourse(String username, String courseID) {
		String hql = "DELETE Usercourse WHERE username='"+username+"' AND courseID='"+courseID+"'";
		TypedQuery query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
	
	public void deleteTestrecord(String username, String courseID) {
		String hql = "DELETE Testrecord WHERE username='"+username+"' AND courseID='"+courseID+"'";
		TypedQuery query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.executeUpdate();
	}
}
