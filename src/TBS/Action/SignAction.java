package TBS.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;
import TBS.Service.SignService;

public class SignAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	private SignService signService;	

	private HttpServletRequest request;
	private HttpServletResponse response;
	private HttpSession session;
	
	private final String EMPTY = null;
	
	@Override
	public String execute() throws Exception {		
		return SUCCESS;
	}
	
	private void outputJson(String json) throws Exception{
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.write(json);
		out.flush();
		out.close();
		System.out.println(json);
	}
	
	public String signin() throws Exception {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		boolean role = request.getParameter("role").equals("admin");
		System.out.println(username + "  " + password + "  " + role);
		if (signService.checkUserEmpty(username)) {
			String json = "{\"result\":\"failed\",\"message\":\"账号未注册！\"}";
			outputJson(json);
			return EMPTY;
		} else if (signService.checkUser(username, password, role)) {
			session.setAttribute("username", username);
			String json = "{\"result\":\"success\",\"message\":\"success\"}";
			outputJson(json);
			return EMPTY;
		} else {
			String json = "{\"result\":\"failed\",\"message\":\"密码错误！\"}";
			outputJson(json);
			return EMPTY;
		}
	}
	
	public String signup() throws Exception {
		String username = request.getParameter("username");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		if (signService.checkUserEmpty(username)) {
			System.out.println("sign start");
			session.setAttribute("username", username);
			signService.signupUser(username, name, email, password);
			String json = "{\"result\":\"success\",\"message\":\"success\"}";
			outputJson(json);
			return EMPTY;		
		} else {
			String json = "{\"result\":\"failed\",\"message\":\"账号已注册！\"}";
			outputJson(json);
			return EMPTY;
		}		
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		this.session = request.getSession();
		signService.setSession(session);
	}
	
	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}
	
	public SignService getSignService() {
		return signService;
	}

	public void setSignService(SignService signService) {
		this.signService = signService;
	}	
}
