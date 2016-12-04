package TBS.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import TBS.Service.TestService;

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
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
		out.close();
		System.out.println(json);
	}
	
	public String getQuestionList() throws Exception {
		String CourseID = request.getParameter("CourseID");
		String json = testService.getQuestionList(CourseID, 10);
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
