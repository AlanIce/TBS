package TBS.Dao;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import TBS.Model.Course;

public class SelectDao extends HibernateDaoSupport {
	
	public Course getCourse(String CourseID) {
		String hql = "from Course where CourseID = '" + CourseID + "'";
		List<Course> courses = (List<Course>) getHibernateTemplate().find(hql);
		if (courses.size() == 0)
			return null;
		return courses.get(0);
	}
	
	public List<Course> getCourseList() {
		String hql = "from Course where CourseID != null";
		List<Course> courses = (List<Course>) getHibernateTemplate().find(hql);
		return courses;
	}
}
