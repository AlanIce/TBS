package TBS.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import TBS.Model.Question;
import TBS.Service.TestService;
import sun.invoke.empty.Empty;

public class TestAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	private TestService testService;

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	private final String EMPTY = null;
	
	@Override
	public String execute() throws Exception {		
		return SUCCESS;
	}
	
	private void outputJson(String json) throws Exception {
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
		out.close();
		System.out.println(json);
	}
	
	public String getQuestionList() throws Exception {
		String json = "";
		String type = request.getParameter("type");
		if (type.equals("test")) {
			String courseID = request.getParameter("courseID");
			json = testService.getQuestionList_Test(courseID);
		} else if (type.equals("review")) {
			String testrecordID = request.getParameter("testrecordID");
			json = testService.getQuestionList_Review(Integer.parseInt(testrecordID));
		} else if (type.equals("preview")) {
			String courseID = request.getParameter("courseID");
			json = testService.getQuestionList_Preview(courseID);
		}
		outputJson(json);
		return EMPTY;
	}
	
	public String getTestrecordList() throws Exception {
		String username = request.getParameter("username");
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String json = testService.getTestrecordList(username, start, limit);
		outputJson(json);
		return EMPTY;
	}
	
	public String getFinalTestrecordList() throws Exception {
		String username = request.getParameter("username");
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String json = testService.getFinalTestrecordList(username, start, limit);
		outputJson(json);
		return EMPTY;
	}
	
	public String submitPaper() throws Exception {
		String username = request.getParameter("username");
		String courseID = request.getParameter("courseID");
		String questionIDList = request.getParameter("questionIDList");
		String answerList = request.getParameter("answerList");
		String json = testService.submitPaper(username, courseID, questionIDList, answerList);
		outputJson(json);
		return SUCCESS;
	}
	
	public String getScoresList() throws Exception {
		String courseID = request.getParameter("courseID");
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String json = testService.getScoresList(courseID, start, limit);
		outputJson(json);
		return SUCCESS;
	}
	
	public String getUnUserList() throws Exception {
		String courseID = request.getParameter("courseID");
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String json = testService.getUnUserList(courseID, start, limit);
		outputJson(json);
		return SUCCESS;
	}
	
	public String getPieChart() throws Exception {
		String courseID = request.getParameter("courseID");
		String json = testService.getPieChart(courseID);
		outputJson(json);
		return SUCCESS;
	}
	
	public String getTestBaseList() throws Exception {
		String courseID = request.getParameter("courseID");
		String findStr = request.getParameter("findStr");
		int start = Integer.parseInt(request.getParameter("start"));
		int limit = Integer.parseInt(request.getParameter("limit"));
		String json = testService.getTestBaseList(courseID, findStr, start, limit);
		outputJson(json);
		return SUCCESS;
	}
	
	public Question getTestBase() {
		Question question = new Question();
		question.setCourseId(request.getParameter("courseID"));
		question.setQuestion(request.getParameter("Question"));
		question.setOptionA(request.getParameter("OptionA"));
		question.setOptionB(request.getParameter("OptionB"));
		question.setOptionC(request.getParameter("OptionC"));
		question.setOptionD(request.getParameter("OptionD"));
		question.setAnswer(request.getParameter("Answer"));
		String ID = request.getParameter("ID");
		if (ID == null || ID.equals("")) ;
		else question.setId(Integer.parseInt(ID));
		return question;
	}
	
	public String addTestBase() throws Exception {
		Question question = getTestBase();
		String json = testService.addTestBase(question);
		outputJson(json);
		return SUCCESS;
	}
	
	public String editTestBase() throws Exception {
		Question question = getTestBase();
		int ID = Integer.parseInt(request.getParameter("ID"));
		question.setId(ID);
		String json = testService.editTestBase(question);
		outputJson(json);
		return SUCCESS;
	}
	
	public String deleteTestBase() throws Exception {
		String IDList = request.getParameter("IDList");
		String json = testService.deleteTestBase(IDList);
		outputJson(json);
		return SUCCESS;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = request.getSession();
	}
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {		
		this.response = arg0;
	}
	
	public TestService getTestService() {
		return testService;
	}

	public void setTestService(TestService testService) {
		this.testService = testService;
	}	
}
