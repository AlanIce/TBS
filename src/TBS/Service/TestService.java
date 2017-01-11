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

import org.hibernate.query.criteria.internal.expression.function.AggregationFunction.COUNT;

public class TestService {
	
	private TestDao testDao;

	public String getQuestionList_Test(String CourseID) {
		Course course = testDao.getCourse(CourseID);
		int QuestionNum = course.getQuestionNum();getClass();
		String questionIDList = course.getQuestionIdlist();
		int total = QuestionNum;
		String json = "{\"Project\":\""+course.getCourseName()+"\",\"total\":"+total+",\"data\":[";
		if (course.isAuto()) {
			List<Question> questions = testDao.getQuestionList(CourseID, QuestionNum);
			questionIDList = "";
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
		} else {
			String[] questionIDArray = questionIDList.split(",");
			for (int i = 0; i < total; i++) {
				if (i > 0) json += ",";
				Question question = testDao.getQuestion(Integer.parseInt(questionIDArray[i]));
				json += "{\"Question\":\"" + question.getQuestion().replace("\"", "\\\"") + "\""
						+ ",\"ImgSrc\":\"" + question.getImgSrc() + "\""
						+ ",\"OptionA\":\"" + question.getOptionA().replace("\"", "\\\"") + "\""
						+ ",\"OptionB\":\"" + question.getOptionB().replace("\"", "\\\"") + "\""
						+ ",\"OptionC\":\"" + question.getOptionC().replace("\"", "\\\"") + "\""
						+ ",\"OptionD\":\"" + question.getOptionD().replace("\"", "\\\"") + "\"" + "}";
			}
		}
		json += "],\"questionIDList\":\""+questionIDList+"\"}";
		System.out.println(json);
		return json;
	}
	
	public String getQuestionList_Review(int TestrecordID) {
		Testrecord testrecord = testDao.getTestrecord(TestrecordID);
		String QuestionIDList = testrecord.getQuestionIdlist();
		String AnswerList = testrecord.getAnswerList();
		String[] QuestionIDArray = QuestionIDList.split(",");
		String[] AnswerArray = AnswerList.split(",");
		int total = QuestionIDArray.length;
		String CourseID = testrecord.getCourseId();
		Course course = testDao.getCourse(CourseID);
		String json = "{\"Project\":\""+course.getCourseName()+"\",\"total\":"+total+",\"data\":[";
		String questionIDList = "";
		for (int i = 0; i < total; i++) {
			Question question = testDao.getQuestion(Integer.parseInt(QuestionIDArray[i]));
			if (i > 0) json += ",";
			json += "{\"Question\":\"" + question.getQuestion().replace("\"", "\\\"") + "\""
				+ ",\"ImgSrc\":\"" + question.getImgSrc() + "\""
				+ ",\"OptionA\":\"" + question.getOptionA().replace("\"", "\\\"") + "\""
				+ ",\"OptionB\":\"" + question.getOptionB().replace("\"", "\\\"") + "\""
				+ ",\"OptionC\":\"" + question.getOptionC().replace("\"", "\\\"") + "\""
				+ ",\"OptionD\":\"" + question.getOptionD().replace("\"", "\\\"") + "\"" 
				+ ",\"MyAnswer\":\"" + AnswerArray[i] + "\"" 
				+ ",\"StdAnswer\":\"" + question.getAnswer() + "\"" + "}";
			questionIDList += question.getId();
		}
		json += "]}";
		System.out.println(json);
		return json;
	}
	
	public String getQuestionList_Preview(String CourseID) {
		Course course = testDao.getCourse(CourseID);
		int QuestionNum = course.getQuestionNum();getClass();
		String questionIDList = course.getQuestionIdlist();
		int total = QuestionNum;
		String json = "{\"Project\":\""+course.getCourseName()+"\",\"total\":"+total+",\"data\":[";
		if (course.isAuto()) {
			List<Question> questions = testDao.getQuestionList(CourseID, QuestionNum);
			questionIDList = "";
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
		} else {
			String[] questionIDArray = questionIDList.split(",");
			for (int i = 0; i < total; i++) {
				if (i > 0) json += ",";
				Question question = testDao.getQuestion(Integer.parseInt(questionIDArray[i]));
				json += "{\"Question\":\"" + question.getQuestion().replace("\"", "\\\"") + "\""
						+ ",\"ImgSrc\":\"" + question.getImgSrc() + "\""
						+ ",\"OptionA\":\"" + question.getOptionA().replace("\"", "\\\"") + "\""
						+ ",\"OptionB\":\"" + question.getOptionB().replace("\"", "\\\"") + "\""
						+ ",\"OptionC\":\"" + question.getOptionC().replace("\"", "\\\"") + "\""
						+ ",\"OptionD\":\"" + question.getOptionD().replace("\"", "\\\"") + "\"" + "}";
			}
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
			json += "{\"TestrecordID\":" + testrecord.getId() + ""
				+ ",\"CourseID\":\"" + course.getCourseId() + "\""
				+ ",\"CourseName\":\"" + course.getCourseName() + "\""
				+ ",\"TestDate\":\"" + TestDate + "\""
				+ ",\"Score\":" + testrecord.getScore() + "}";
		}
		json += "]}";
		return json;		
	}
	
	public String getFinalTestrecordList(String Username, int start, int limit) {
		List<Testrecord> testrecords = testDao.getFinalTestrecordList(Username, start, limit);
		int total = testDao.getFinalTestrecordCount(Username);
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
			json += "{\"TestrecordID\":" + testrecord.getId() + ""
				+ ",\"CourseID\":\"" + course.getCourseId() + "\""
				+ ",\"CourseName\":\"" + course.getCourseName() + "\""
				+ ",\"TestDate\":\"" + TestDate + "\""
				+ ",\"Score\":" + testrecord.getScore() + "}";
		}
		json += "]}";
		return json;		
	}
	
	public String submitPaper(String username, String courseID, String questionIDList, String answerList) {
		String[] questionIDArray = questionIDList.split(",");
		String[] answerArray = answerList.split(",");
		String json = "";
		if (questionIDArray.length != answerArray.length) {
			json = "{\"result\":\"failed\"}";
			return json;
		}
		int score = 0;
		int length = questionIDArray.length;
		int QuestionNum = testDao.getCourse(courseID).getQuestionNum();
		int SingleScore = 100 / QuestionNum;
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
		testrecord.setAnswerList(answerList);
		testrecord.setQuestionIdlist(questionIDList);
		testDao.saveTestrecord(testrecord);
		testDao.updateUserCourse(username, courseID);
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
			User user = testDao.getUser(testrecord.getUsername());
			json += "{\"Username\":\"" + user.getUsername() + "\""
				+ ",\"Name\":\"" + user.getName() + "\""
				+ ",\"TestDate\":\"" + TestDate + "\""
				+ ",\"Score\":" + testrecord.getScore() + "}";
		}
		json += "]}";
		return json;
	}
	
	public String getUnUserList(String CourseID, int start, int limit) {
		List<User> users = testDao.getUnUserList(CourseID, start, limit);
		int total = testDao.getUnUserCount(CourseID);
		String json = "{\"total\":" + total + ",\"data\":[";
		int n = users.size();
		for (int i = 0; i < n; i++) {
			User user = users.get(i);
			if (i > 0) 
				json += ",";
			json += "{\"Username\":\"" + user.getUsername() + "\""
				+ ",\"Name\":\"" + user.getName() + "\"}";
		}
		json += "]}";
		return json;
	}
	
	public String getPieChart(String CourseID) {
		String json = "";
		int count = 0;
		int num = testDao.getPieChart(CourseID, 0, 59);
		if (num > 0) {
			count++;
			json += "{\"Section\":\"0-59\",\"Num\":" + num + "}";
		}
		for (int i = 6; i < 9; i++) {
			num = testDao.getPieChart(CourseID, i*10, (i+1)*10-1);
			if (num > 0) {
				if (count > 0) json += ",";
				count++;
				json += "{\"Section\":\"" + i*10 + "-" + ((i+1)*10-1) + "\",\"Num\":" + num + "}";
			}
		}
		num = testDao.getPieChart(CourseID, 90, 100);
		if (num > 0) {
			if (count > 0) json += ",";
			count++;
			json += "{\"Section\":\"90-100\",\"Num\":" + num + "}";
		}
		json += "]}";
		json = "{\"total\":"+count+",\"data\":[" + json;
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
