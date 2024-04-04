package filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.mysql.cj.Session;

@WebFilter("/page/customer")
public class SessionFilter implements Filter {

  
	
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req= (HttpServletRequest) request;
		String path= req.getPathInfo();
		System.out.println(path);
		if(!path.equals("/LoginAuthendicate")) {
			 HttpSession session =req.getSession(false);
			 String auth= (String) session.getAttribute("Auth");
			if(auth==null){
				request.setAttribute("errorMessage","you are logged out");
				RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/login.jsp");
				dispatcher.forward(request, response);
			}
			else {
				chain.doFilter(request, response);
			}
		}
		else {
			chain.doFilter(request, response);
		}
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
