package TBS.Service;

import TBS.Dao.SelectDao;
import TBS.Model.Course;
import java.util.List;

public class SelectService {
	
	private SelectDao selectDao;

	public String getCourseList() {
		String json = "{\"total\":";
		List<Course> courses = selectDao.getCourseList();
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
	
	public SelectDao getSelectDao() {
		return selectDao;
	}

	public void setSelectDao(SelectDao selectDao) {
		this.selectDao = selectDao;
	}
}
