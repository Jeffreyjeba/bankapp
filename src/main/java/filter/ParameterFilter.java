package filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ParameterFilter implements Filter {

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)throws IOException, ServletException {
		 HttpServletRequest httpRequest = (HttpServletRequest) request;
		 	
	        if (httpRequest.getMethod().equalsIgnoreCase("POST")) {
	    			String path = ((HttpServletRequest) request).getPathInfo();
	    			switch (path) {
	    			case "/LoginAuthendicate":
	    				if( checkNull(httpRequest,"id","password")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.setAttribute("errorMessage", "Something Went Wrong ");
	    					request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
						}
	    				break;
	    			case "/primaryAccount":
	    			case "/switchPrimary":
	    				if( checkNull(httpRequest,"primAccount")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/changePassword":
	    				if( checkNull(httpRequest,"oldPass","newPass","confoPass")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.setAttribute("errorMessage", "Something Went Wrong ");
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request, response);
						}
	    				break;
	    			case "/changeAccount":
	    			case "/switchAccount":
	    				if( checkNull(httpRequest,"account")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/credit":
	    				if( checkNull(httpRequest,"amount","description","password")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/debit":
	    				if( checkNull(httpRequest,"amount","description","password")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/transfer":
	    				if( checkNull(httpRequest,"recAccount","ifsc","amount","description","password")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/switchAccountInfo":
	    				if( checkNull(httpRequest,"account")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/historySwitch":
	    				if( checkNull(httpRequest,"account")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/addCustomer":
	    				if( checkNull(httpRequest,"name","emailId","phoneNumber","aadhar","pan","address")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/addAccount":
	    				if( checkNull(httpRequest,"userId","branchId","balance")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/activateAccount":
	    			case "/deactivateAccount":
	    			case "/deleteAccount":
	    				if( checkNull(httpRequest,"accountNumber","password","function")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/activateCustomer":
	    			case "/deactivateCustomer":
	    				if( checkNull(httpRequest,"id","password","function")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/connectCustomer":
	    				if( checkNull(httpRequest,"id")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/addAuthority":
	    				if( checkNull(httpRequest,"name","emailId","phoneNumber","branchId","type")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/employeeStatus":
	    				if( checkNull(httpRequest,"id","status","password")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/addBranch":
	    				if( checkNull(httpRequest,"branchName","address")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/nextPage":
	    				if( checkNull(httpRequest,"page")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			case "/previousPage":
	    				if( checkNull(httpRequest,"page")) {
	    					chain.doFilter(request, response);
	    				}
	    				else {
	    					request.getRequestDispatcher("/WEB-INF/htmlError.jsp").forward(request,response);
						}
	    				break;
	    			default:
	    				chain.doFilter(request, response);
	    			}
	    		}
	        else {
	        	 chain.doFilter(request, response);
	        }
	}
	
	
	private boolean checkNull(HttpServletRequest request, String...fields) {
		int length =fields.length;
		boolean test =true;
		for(int i=0;i<length;i++) {
			if(request.getParameter(fields[i])==null|| request.getParameter(fields[i])=="") {
				test=false;
				break;
			}
		}
		return test;
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
