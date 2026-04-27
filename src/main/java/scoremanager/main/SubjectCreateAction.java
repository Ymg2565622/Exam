package scoremanager.main;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import tool.Action;

public class SubjectCreateAction extends Action {
	
	public String execute(
			HttpServletRequest request, HttpServletResponse response
	) throws Exception {
		
		return "subject_create.jsp";
		
	}
}
