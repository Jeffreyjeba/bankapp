package servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import bank.Authenticator;
import operations.Customer;
import utility.BankException;
import utility.InputDefectException;

public class ControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path= request.getPathInfo();
		System.out.println(path);
		switch (path) {
		case "/LoginAuthendicate": 
			try {
				loginAuthendicate(request, response);
			} catch (InputDefectException e) {
				e.printStackTrace();
			} catch (BankException e) {
				e.printStackTrace();
			}
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}	
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path= request.getPathInfo();
		System.out.println(path);
		switch (path) {
		case"/logout":
			logout(request, response);
			break;
		case "/login": 
			login(request, response);
			break;
		case "/home": 
			home(request, response);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}	
	}
	
	
	
	protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/login.jsp");
		dispatcher.forward(request, response);
	}
	
	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher=request.getRequestDispatcher("/home.jsp");
		dispatcher.forward(request, response);
	}
	
	Authenticator auth= new Authenticator();
	protected void loginAuthendicate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, InputDefectException, BankException {
		int id=Integer.parseInt(request.getParameter("ID"));
		String password= request.getParameter("PASSWORD");
		boolean access = auth.checkPassword(id,password);
		if(access) {
			request.getSession().setAttribute("Id",id);
			authorize(id, request, response);
		}
		else {
			request.setAttribute("errorMessage","wrong combination try again");
			login(request, response);
		}
	}
	
	protected void authorize( int id,HttpServletRequest request, HttpServletResponse response) throws BankException, ServletException, IOException, InputDefectException {
		String authority =auth.getAuthority(id);
		request.getSession().setAttribute("Authority", authority);
		switch(authority) {
		case "admin":
			
			break;
		case "employee":
			break;
		case "customer":
			customerDashboard(request, response);
			break;
		}
	}
	
	protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		 HttpSession session= request.getSession();
		 session.setAttribute("ID",null);
		 session.setAttribute("AUTHORITY",null);
		 login(request, response);
	}
	
	
	protected void customerDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, BankException, InputDefectException{
		Customer customer=new Customer();
		int id= (int) request.getSession().getAttribute("Id");
		JSONObject profile= customer.viewProfile(id);
		long[] accounts= customer.getAccounts(profile);
		request.setAttribute("accounts", accounts);
		request.setAttribute("profile", profile);
		RequestDispatcher dispatcher=request.getRequestDispatcher("/WEB-INF/customer.jsp");
		dispatcher.forward(request, response);
	}
	
}
