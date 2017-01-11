package TBS.Service;

import TBS.Dao.SelectDao;
import TBS.Dao.TestDao;
import TBS.Model.Course;
import TBS.Model.Usercourse;

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
				+ ",\"CourseImgSrc\":\"" + course.getCourseImgSrc() + "\""
				+ ",\"Teacher\":\"" + course.getTeacher() + "\"" + "}";
		}
		json += "]}";
		return json;
	}
	
	public String pickupCourse(String type, String username, String courseID) {
		String json = "{\"result\":\"success\"}";
		selectDao.deleteUserCourse(username, courseID);
		if (type.equals("add")) {
			Usercourse usercourse = new Usercourse();
			usercourse.setUsername(username);
			usercourse.setCourseId(courseID);
			usercourse.setCompleted(false);
			selectDao.addUserCourse(usercourse);
		} else if (type.equals("del")) {
			selectDao.deleteTestrecord(username, courseID);
		}
		return json;		
	}
	
	public String getPaper(String courseID) {
		Course course = selectDao.getCourse(courseID);
		String json = "{\"success\": \"true\", \"data\":{\"Auto\":\""+ (course.isAuto() ? "true" : "false") + "\""
				+ ",\"QuestionNum\":" + course.getQuestionNum()
				+ ",\"QuestionIDList\":\"" + course.getQuestionIdlist() + "\"}}";
		return json;
		
	}
	
	public String editPaper(String courseID, Boolean auto, int questionNum, String questionIDList) {
		String json = "{\"result\":\"success\"}";
		Course course = selectDao.getCourse(courseID);
		course.setAuto(auto);
		course.setQuestionNum(questionNum);
		course.setQuestionIdlist(questionIDList);
		selectDao.updateCourse(course);
		return json;
		
	}	
	
	public SelectDao getSelectDao() {
		return selectDao;
	}

	public void setSelectDao(SelectDao selectDao) {
		this.selectDao = selectDao;
	}
}
