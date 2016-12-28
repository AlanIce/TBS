package TBS.Service;

import TBS.Dao.TestDao;
import TBS.Model.Course;
import TBS.Model.Question;
import TBS.Model.Testrecord;
import TBS.Model.User;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class TestService {
	
	private TestDao testDao;	
	private final int QuestionNum = 10;		//卷子题目数量
	private final int SingleScore = 10;		//一题的分数

	public String getQuestionList(String CourseID) {
		List<Question> questions = testDao.getQuestionList(CourseID, QuestionNum);
		int total = questions.size();
		Course course = testDao.getCourse(CourseID);
		String json = "{\"Project\":\""+course.getCourseName()+"\",\"total\":"+total+",\"data\":[";
		String questionIDList = "";
		for (int i = 0; i < total; i++) {
			Question question = questions.get(i);
			if (i > 0) {
				questionIDList += ",";
				json += ",";
			}
			json += "{\"Question\":\"" + question.getQuestion().replace("\"", "\\\"") + "\""
				+ ",\"ImgSrc\":\"" + question.getImgSrc() + "\""
				+ ",\"OptionA\":\"" + question.getOptionA().replace("\"", "\\\"") + "\""
				+ ",\"OptionB\":\"" + question.getOptionB().replace("\"", "\\\"") + "\""
				+ ",\"OptionC\":\"" + question.getOptionC().replace("\"", "\\\"") + "\""
				+ ",\"OptionD\":\"" + question.getOptionD().replace("\"", "\\\"") + "\"" + "}";
			questionIDList += question.getId();
		}
		json += "],\"questionIDList\":\""+questionIDList+"\"}";
		System.out.println(json);
		return json;
	}
	
	public String getTestrecordList(String Username, int start, int limit) {
		List<Testrecord> testrecords = testDao.getTestrecordList(Username, start, limit);
		int total = testDao.getTestrecordCount(Username);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String json = "{\"total\":" + total + ",\"data\":[";
		int n = testrecords.size();
		for (int i = 0; i < n; i++) {
			Testrecord testrecord = testrecords.get(i);
			if (i > 0) 
				json += ",";
			String TestDate = sdf.format(testrecord.getTestDate());
			String courseID = testrecord.getCourseId();
			Course course = testDao.getCourse(courseID);
			json += "{\"Project\":\"" + course.getCourseName() + "\""
				+ ",\"Teacher\":\"" + course.getTeacher() + "\""
				+ ",\"TestDate\":\"" + TestDate + "\""
				+ ",\"Score\":" + testrecord.getScore() + "}";
		}
		json += "]}";
		return json;
		
	}
	
	public String submitPaper(String username, String courseID,String questionIDList, String answerList) {
		String[] questionIDArray = questionIDList.split(",");
		String[] answerArray = answerList.split(",");
		String json = "";
		if (questionIDArray.length != answerArray.length) {
			json = "{\"result\":\"failed\"}";
			return json;
		}
		int score = 0;
		int length = questionIDArray.length;
		for (int i = 0; i < length; i++) {
			int ID = Integer.parseInt(questionIDArray[i]);
			Question question = testDao.getQuestion(ID);
			String StdAnswer = question.getAnswer();
			String MyAnswer  = answerArray[i];
			if (StdAnswer.equals(MyAnswer)) 
				score += SingleScore;
		}
		Date date = new Date();
		Timestamp t = new Timestamp(date.getTime());
		Testrecord testrecord = new Testrecord();
		testrecord.setUsername(username);
		testrecord.setCourseId(courseID);
		testrecord.setTestDate(t);
		testrecord.setScore(score);
		testDao.saveTestrecord(testrecord);
		json = "{\"result\":\"success\",\"score\":\"" + score + "\"}";
		return json;			
	}
	
	public String getScoresList(String CourseID, int start, int limit) {
		List<Testrecord> testrecords = testDao.getScoresList(CourseID, start, limit);
		int total = testDao.getScoresCount(CourseID);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm"); 
		String json = "{\"total\":" + total + ",\"data\":[";
		int n = testrecords.size();
		for (int i = 0; i < n; i++) {
			Testrecord testrecord = testrecords.get(i);
			if (i > 0) 
				json += ",";
			String TestDate = sdf.format(testrecord.getTestDate());
			System.out.println(i + " : " + testrecord.getUsername());
			User user = testDao.getUser(testrecord.getUsername());
			json += "{\"Username\":\"" + user.getUsername() + "\""
				+ ",\"Name\":\"" + user.getName() + "\""
				+ ",\"TestDate\":\"" + TestDate + "\""
				+ ",\"Score\":" + testrecord.getScore() + "}";
		}
		json += "]}";
		return json;
	}
	
	public String getTestBaseList(String CourseID, String findStr, int start, int limit) {
		List<Question> questions = testDao.getTestBaseList(CourseID, findStr, start, limit);
		int total = testDao.getTestBaseCount(CourseID, findStr);
		String json = "{\"total\":" + total + ",\"data\":[";
		int n = questions.size();
		for (int i = 0; i < n; i++) {
			Question question = questions.get(i);
			if (i > 0) 
				json += ",";
			json += "{\"ID\":\"" + question.getId() + "\""
				+ ",\"Question\":\"" + question.getQuestion().replace("\"", "\\\"") + "\""
				+ ",\"OptionA\":\"" + question.getOptionA().replace("\"", "\\\"") + "\""
				+ ",\"OptionB\":\"" + question.getOptionB().replace("\"", "\\\"") + "\""
				+ ",\"OptionC\":\"" + question.getOptionC().replace("\"", "\\\"") + "\""
				+ ",\"OptionD\":\"" + question.getOptionD().replace("\"", "\\\"") + "\""
				+ ",\"Answer\":\"" + question.getAnswer() + "\"}";
		}
		json += "]}";
		return json;
	}
	
	public String addTestBase(Question question) {
		testDao.addTestBase(question);
		String json = "{\"result\":\"success\"}";
		return json;
	}
	
	public String editTestBase(Question question) {
		testDao.editTestBase(question);
		String json = "{\"result\":\"success\"}";
		return json;
	}
	
	public String deleteTestBase(String IDList) {
		String[] IDArray = IDList.split(",");
		for (String ID : IDArray) {
			Question question = testDao.getQuestion(Integer.parseInt(ID));
			testDao.deleteTestBase(question);
		}
		String json = "{\"result\":\"success\"}";
		return json;
	}
	
	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
}
