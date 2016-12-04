package TBS.Service;

import TBS.Dao.TestDao;
import TBS.Model.Course;
import TBS.Model.Question;
import TBS.Model.Testrecord;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class TestService {
	
	private TestDao testDao;

	public String getQuestionList(String CourseID, int limit) {
		List<Question> questions = testDao.getQuestionList(CourseID, limit);
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
			json += "{\"Question\":\"" + question.getQuestion() + "\""
				+ ",\"ImgSrc\":\"" + question.getImgSrc() + "\""
				+ ",\"OptionA\":\"" + question.getOptionA() + "\""
				+ ",\"OptionB\":\"" + question.getOptionB() + "\""
				+ ",\"OptionC\":\"" + question.getOptionC() + "\""
				+ ",\"OptionD\":\"" + question.getOptionD() + "\"" + "}";
			questionIDList += question.getId();
		}
		json += "],\"questionIDList\":\""+questionIDList+"\"}";
		System.out.println(json);
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
				score += 5;
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
	
	public TestDao getTestDao() {
		return testDao;
	}

	public void setTestDao(TestDao testDao) {
		this.testDao = testDao;
	}
}
