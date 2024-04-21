package servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputFilter.Status;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.jni.User;
import org.json.JSONObject;

import bank.Authenticator;
import operations.Admin;
import operations.Customer;
import operations.Employee;
import pojo.UserData;
import utility.BankException;
import utility.InputDefectException;

public class Api extends HttpServlet {

	private static final long serialVersionUID = 1L;
	Admin admin = new Admin();
	
	@Override
	public void doGet(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		if(checkApiKey(request)) {
		try {
			Long id=Long.parseLong(request.getHeader("id"));
			JSONObject jsonObject= admin.viewProfile(id);
			response.getWriter().println(jsonObject.toString());
		} catch (BankException | InputDefectException e) {
			e.printStackTrace();
		}
		}
		
	}
	
	public void doPost(HttpServletRequest request,HttpServletResponse response) throws IOException {
		response.setContentType("application/json");
		if(checkApiKey(request)) {
	        try(BufferedReader reader = request.getReader();){ 
	        StringBuilder requestBody = new StringBuilder();
	        String line;
	        while ((line = reader.readLine())!= null) {
	            requestBody.append(line);
	        }
	        JSONObject jsonObject=new JSONObject(requestBody.toString());
	        setLocal(request);
	        admin.addUsers(jsonObject);
	        }
	        catch (BankException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        catch (InputDefectException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	 @Override
	    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws IOException {
		 System.out.println("in");
		 response.setContentType("application/json");
			if(checkApiKey(request)) {
		        try(BufferedReader reader = request.getReader();){ 
		        StringBuilder requestBody = new StringBuilder();
		        String line;
		        while ((line = reader.readLine())!= null) {
		            requestBody.append(line);
		        }
		        JSONObject jsonObject=new JSONObject(requestBody.toString());
		        setLocal(request);
		        admin.alterUsers(jsonObject);
		        }
		        catch (BankException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		        catch (InputDefectException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	    }

	    @Override
	    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
	       try {
			admin.deleteUser(16);
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    }
	
	
	
	private boolean checkApiKey(HttpServletRequest request) {
		String keyString= request.getHeader("key");
		return keyString.equals("reyBank");
		
	}
	
	
	private void setLocal(HttpServletRequest request) {
	 long id=Long.parseLong(request.getHeader("id"));
     UserData data= new UserData();
     data.setId(id);
     Authenticator.user.set(data);
	}
	        
	
}
