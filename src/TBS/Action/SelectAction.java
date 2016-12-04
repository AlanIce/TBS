package TBS.Action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.opensymphony.xwork2.ActionSupport;

import TBS.Service.SelectService;

public class SelectAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
	
	private SelectService selectService;

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
	
	public String getCourseList() throws Exception {
		String json = selectService.getCourseList();
		outputJson(json);
		return EMPTY;
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
	
	public SelectService getSelectService() {
		return selectService;
	}

	public void setSelectService(SelectService selectService) {
		this.selectService = selectService;
	}	
}
