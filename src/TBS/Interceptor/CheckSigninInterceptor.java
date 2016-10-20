package TBS.Interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.SessionAware;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckSigninInterceptor extends AbstractInterceptor {
	
	private HttpSession session;
	private HttpServletRequest request;
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		request = (HttpServletRequest) arg0.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		session = request.getSession();
		if (session.getAttribute("user") != null) {
			System.out.println("return SUCCESS;");
			return "success";
		} else if (request.getMethod().equals("GET") || request.getParameter("username") == null) {
			System.out.println("return INPUT");
			return "input";
		} else {
			System.out.println("return arg0.invoke();");
			return arg0.invoke();
		}
			
	}
}
