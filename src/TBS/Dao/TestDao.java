package TBS.Dao;

import java.util.List;

import javax.persistence.TypedQuery;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import TBS.Model.Course;
import TBS.Model.Question;
import TBS.Model.Testrecord;

public class TestDao extends HibernateDaoSupport {
	
	public List<Question> getQuestionList(String CourseID, int limit) {
		String hql = "from Question where CourseID='"+CourseID+"' order by rand()";
		TypedQuery<Question> query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setMaxResults(limit);
		List<Question> questions = query.getResultList();
		return questions;
	}
	
	public Course getCourse(String CourseID) {
		String hql = "from Course where CourseID='"+CourseID+"'";
		Course course = (Course) getHibernateTemplate().find(hql).get(0);
		return course;
	}
	
	public Question getQuestion(int ID) {
		String hql = "from Question where ID="+ID;
		List<Question> questions = (List<Question>)getHibernateTemplate().find(hql);
		return questions.get(0);
	}
	
	public void saveTestrecord(Testrecord t) {
		getHibernateTemplate().save(t);
	}
}
