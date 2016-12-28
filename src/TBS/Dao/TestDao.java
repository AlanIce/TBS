package TBS.Dao;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.TypedQuery;

import org.hibernate.Query;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.xml.internal.fastinfoset.algorithm.IEEE754FloatingPointEncodingAlgorithm;

import TBS.Model.Course;
import TBS.Model.Question;
import TBS.Model.Testrecord;
import TBS.Model.User;

public class TestDao extends HibernateDaoSupport {
	
	public List<Question> getQuestionList(String CourseID, int limit) {
		String hql = "FROM Question WHERE CourseID='"+CourseID+"' order by rand()";
		TypedQuery<Question> query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setMaxResults(limit);
		List<Question> questions = query.getResultList();
		return questions;
	}
	
	public List<Testrecord> getTestrecordList(String Username, int start, int limit) {
		String hql = "FROM Testrecord WHERE ID!=null";
		hql += " and Username='" + Username + "'";
		TypedQuery<Testrecord> query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Testrecord> testrecords = query.getResultList();
		return testrecords;
	}
	
	public int getTestrecordCount(String Username) {
		String hql = "select count(*) FROM Testrecord WHERE ID!=null";
		hql += " and Username='" + Username + "'";
		TypedQuery<Long> query = getSessionFactory().getCurrentSession().createQuery(hql);
		int count = query.getSingleResult().intValue();
		return count;
	}
	
	public List<Testrecord> getScoresList(String CourseID, int start, int limit) {
		String hql = "FROM Testrecord t WHERE courseID='"+CourseID+"' AND score=("
				+ "SELECT max(score) FROM Testrecord WHERE username=t.username AND courseID='"+CourseID+"') GROUP BY username";
		TypedQuery<Testrecord> query = getSessionFactory().getCurrentSession().createQuery(hql);

		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Testrecord> testrecords = query.getResultList();
		return testrecords;
	}
	
	public int getScoresCount(String CourseID) {
		String sql = "SELECT count(*) FROM (SELECT * FROM Testrecord t WHERE courseID='"+CourseID+"' AND score=("
				+ "SELECT max(score) FROM Testrecord WHERE username=t.username AND courseID='"+CourseID+"') GROUP BY username) a";
		TypedQuery query = getSessionFactory().getCurrentSession().createNativeQuery(sql);
		int count = ((BigInteger)query.getSingleResult()).intValue();
		return count;
	}
	
	public Course getCourse(String CourseID) {
		String hql = "FROM Course WHERE CourseID='"+CourseID+"'";
		Course course = (Course) getHibernateTemplate().find(hql).get(0);
		return course;
	}
	
	public Question getQuestion(int ID) {
		String hql = "FROM Question WHERE ID="+ID;
		List<Question> questions = (List<Question>)getHibernateTemplate().find(hql);
		return questions.get(0);
	}
	
	public User getUser(String Username) {
		String hql = "FROM User WHERE Username='"+Username+"'";
		List<User> users = (List<User>)getHibernateTemplate().find(hql);
		return users.get(0);
	}
	
	public List<Question> getTestBaseList(String CourseID, String findStr, int start, int limit) {
		String hql = "FROM Question WHERE CourseID='" + CourseID + "'";
		if (findStr != null && !findStr.equals(""))
			hql += " AND ( question like '%" + findStr
				+ "%' or optionA like '%" + findStr
				+ "%' or optionB like '%" + findStr
				+ "%' or optionC like '%" + findStr
				+ "%' or optionD like '%" + findStr +  "%' )";
		hql += " ORDER BY ID DESC";
		TypedQuery<Question> query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setFirstResult(start);
		query.setMaxResults(limit);
		List<Question> questions = query.getResultList();
		return questions;
	}
	
	public int getTestBaseCount(String CourseID, String findStr) {
		String hql = "SELECT count(*) FROM Question WHERE CourseID='"+CourseID+"'";
		if (findStr != null && !findStr.equals(""))
			hql += " AND ( question like '%" + findStr
				+ "%' or optionA like '%" + findStr
				+ "%' or optionB like '%" + findStr
				+ "%' or optionC like '%" + findStr
				+ "%' or optionD like '%" + findStr +  "%' )";
		TypedQuery<Long> query = getSessionFactory().getCurrentSession().createQuery(hql);
		int count = query.getSingleResult().intValue();
		return count;
	}
	
	public void saveTestrecord(Testrecord t) {
		getHibernateTemplate().save(t);
	}
	
	public void addTestBase(Question q) {
		getHibernateTemplate().save(q);
	}

	public void editTestBase(Question q) {
		getHibernateTemplate().update(q);
	}

	public void deleteTestBase(Question q) {
		getHibernateTemplate().delete(q);
	}
	
}
