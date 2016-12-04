package TBS.Interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class CheckUserInterceptor extends AbstractInterceptor {
	
	private HttpSession session;
	private HttpServletRequest request;
	
	@Override
	public String intercept(ActionInvocation arg0) throws Exception {
		request = (HttpServletRequest) arg0.getInvocationContext().get(ServletActionContext.HTTP_REQUEST);
		session = request.getSession();
		if (session.getAttribute("user") == null)
			return "input";
		else
			return arg0.invoke();
	}
}
