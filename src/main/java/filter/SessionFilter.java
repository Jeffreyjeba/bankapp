package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import bank.Authenticator;
import utility.BankException;
import utility.InputDefectException;

@WebFilter("/page/customer")
public class SessionFilter implements Filter {

  
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		String path= req.getPathInfo();
		System.out.println(path);
		if(!(path.equals("/LoginAuthendicate") || path.equals("/login")) ) {
			HttpSession session =req.getSession(false);
			System.out.println(session);
			if(session!=null) {
				String auth= (String) session.getAttribute("auth");
				System.out.println(auth);
				if(auth!=null){
					   userValidate(request, response);
					
					chain.doFilter(request, response);
				}
				else {
					logout(request, response);
				}
			}
			else {
				logout(request, response);
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}
	private void logout(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		request.setAttribute("errorMessage","you are logged out");
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}
	private void userValidate(ServletRequest request, ServletResponse response) throws ServletException, IOException {
		HttpServletRequest req= (HttpServletRequest) request;
		long id;
		String authString=(String) req.getSession().getAttribute("auth");
		if(authString.equals("customer")) {
			id=	(long) req.getSession().getAttribute("id");
		}
		else {
			id=(long) req.getSession().getAttribute("empId");
		}
		try {
			getAuth().validateUser(id);
		} 
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage",e.getMessage());
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}

	}
	 private static Authenticator auth=null;
	private Authenticator getAuth() {
		if(auth==null) {
			auth=new Authenticator();
			return auth;
		}
		else {
		return auth;
		}
	}


	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
