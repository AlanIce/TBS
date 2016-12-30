package TBS.Service;

import TBS.Dao.SelectDao;
import TBS.Dao.TestDao;
import TBS.Model.Course;
import java.util.List;

public class SelectService {
	
	private SelectDao selectDao;

	public String getCourseList() {
		List<Course> courses = selectDao.getCourseList();
		String json = "{\"total\":";
		int total = courses.size();
		json += (total + ",\"data\":[");
		for (int i = 0; i < total; i++) {
			Course course = courses.get(i);
			if (i > 0)
				json += ",";
			json += "{\"CourseID\":\"" + course.getCourseId() + "\""
				+ ",\"CourseName\":\"" + course.getCourseName() + "\""
				+ ",\"CourseImgSrc\":\"" + course.getCourseImgSrc() + "\"" + "}";
		}
		json += "]}";
		return json;
	}
	
	public String getMyCourseList(String username, String type) {
		List<Course> courses = selectDao.getMyCourseList(username, type);
		int total = courses.size();
		String json = "{\"total\":";
		json += (total + ",\"data\":[");
		for (int i = 0; i < total; i++) {
			if (i > 0) json += ",";
			Course course = courses.get(i);
			json += "{\"CourseID\":\"" + course.getCourseId() + "\""
				+ ",\"CourseName\":\"" + course.getCourseName() + "\""
				+ ",\"Teacher\":\"" + course.getTeacher() + "\"" + "}";
		}
		json += "]}";
		return json;
	}
	
	public SelectDao getSelectDao() {
		return selectDao;
	}

	public void setSelectDao(SelectDao selectDao) {
		this.selectDao = selectDao;
	}
}
