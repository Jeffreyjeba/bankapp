package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import bank.Authenticator;
import operations.Admin;
import operations.Customer;
import operations.Employee;
import utility.BankException;
import utility.InputDefectException;
import utility.UtilityHelper;

public class ControllServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		String path = request.getPathInfo();
		switch (path) {
		case "/LoginAuthendicate":
			loginAuthendicate(request, response);
			break;
		case "/switchPrimary":
			switchPrimary(request, response);
			break;
		case "/changePassword":
			changePassword(request, response);
			break;
		case "/switchAccount":
			switchAccount(request, response);
			break;
		case "/credit":
			credit(request, response);
			break;
		case "/debit":
			debit(request, response);
			break;
		case "/transfer":
			moneyTransfer(request, response);
			break;
		case "/changeAccount":
			switchAccount(request, response);
			break;
		case "/primaryAccount":
			switchPrimary(request, response);
			break;
		case "/switchAccountBalance":
			request.getSession().setAttribute("currentAccount",  Long.parseLong(request.getParameter("account")));
			request.setAttribute("path","switchAccountBalance");
			balance(request, response);
			break;
		case "/switchAccountInfo":
			request.getSession().setAttribute("currentAccount",  Long.parseLong(request.getParameter("account")));
			request.setAttribute("path","switchAccountInfo");
			accountDetail(request, response);
			break;
		case "/switchCredit":
			request.getSession().setAttribute("currentAccount",  Long.parseLong(request.getParameter("account")));
			request.setAttribute("path","switchCredit");
			request.getRequestDispatcher("/WEB-INF/CCredit.jsp").forward(request, response);
			break;
		case "/switchDebit":
			request.getSession().setAttribute("currentAccount",  Long.parseLong(request.getParameter("account")));
			request.setAttribute("path","switchDebit");
			request.getRequestDispatcher("/WEB-INF/CDebit.jsp").forward(request, response);
			break;
		case "/switchMoneyTransfer":
			request.getSession().setAttribute("currentAccount",  Long.parseLong(request.getParameter("account")));
			request.setAttribute("path","switchMoneyTransfer");
			request.getRequestDispatcher("/WEB-INF/CTransfer.jsp").forward(request, response);
			break;
		case "/initialDetail":
			if(request.getSession().getAttribute("auth").equals("employee")){
				employeeDashboard(request, response);
			}
			else {
				request.getSession().setAttribute("branchId",Integer.parseInt(request.getParameter("branchId")));
				adminDashboard(request, response);
			}
			break;
		case "/addCustomer":
			addCustomer(request, response);
			break;
		case "/addAccount":
			addAccount(request, response);
			break;
		case "/activateAccount":
		case "/deactivateAccount":
		case "/deleteAccount":
			accountStatus(request, response);
			break;
		case "/activateCustomer":
		case "/deactivateCustomer":
			customerStatus(request, response);
			break;
		case "/connectCustomer":
			bridge(request, response);
			break;
		case "/addAuthority":
			addAuthority(request, response);
			break;
		case "/employeeStatus":
			employeeStatus(request, response);
			break;
		case "/addBranch":
			addBranch(request, response);
			break;
		case "/nextPage":
			historyNext(request, response);
			break;
		case "/previousPage":
			historyPrevious(request, response);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String path = request.getPathInfo();
		// System.out.println(path);
		switch (path) {
		case "/logout":
			logout(request, response);
			break;
		case "/login":
			login(request, response);
			break;
		case "/home":
			home(request, response);
			break;
		case "/balance":
		case "/viewBalance":
			request.setAttribute("path","switchAccountBalance"); 
			balance(request, response);
			break;
		case "/credit":
			 request.setAttribute("path","switchCredit"); 
			request.getRequestDispatcher("/WEB-INF/CCredit.jsp").forward(request, response);
			break;
		case "/debit":
			 request.setAttribute("path","switchDebit"); 
			request.getRequestDispatcher("/WEB-INF/CDebit.jsp").forward(request, response);
			break;
		case "/moneyTransfer":
			request.setAttribute("path","switchMoneyTransfer");
			request.getRequestDispatcher("/WEB-INF/CTransfer.jsp").forward(request, response);
			break;
		case "/resetPassword":
			request.getRequestDispatcher("/WEB-INF/CPassword.jsp").forward(request, response);
			break;
		case "/changeAccount":
			request.getRequestDispatcher("/WEB-INF/accountSwitch.jsp").forward(request, response);
			break;
		case "/primaryAccount":
			primaryAccount(request, response);
			break;
		case "/profile":
			profile(request, response);
			break;
		case "/transactionHistory":
				history(request, response,1);
			break;
		case "/addCustomer":
			request.getRequestDispatcher("/WEB-INF/employee/addCustomer.jsp").forward(request, response);
			break;
		case "/addAccount":
			addAccountGet(request, response);
			break;
		case "/activateAccount":
			request.setAttribute("function","activate");
			request.getRequestDispatcher("/WEB-INF/employee/accountStatus.jsp").forward(request, response);
			break;
		case "/deactivateAccount":
			request.setAttribute("function","inactivate");
			request.getRequestDispatcher("/WEB-INF/employee/accountStatus.jsp").forward(request, response);
			break;
		case "/deleteAccount":
			request.setAttribute("function","delete");
			request.getRequestDispatcher("/WEB-INF/employee/accountStatus.jsp").forward(request, response);
			break;
		case "/activateCustomer":
			request.setAttribute("function","activate");
			request.getRequestDispatcher("/WEB-INF/employee/customerStatus.jsp").forward(request, response);
			break;
		case "/deactivateCustomer":
			request.setAttribute("function","inactivate");
			request.getRequestDispatcher("/WEB-INF/employee/customerStatus.jsp").forward(request, response);
			break;
		case "/initialDetail":
			if(request.getSession().getAttribute("auth").equals("employee")){
				employeeDashboard(request, response);
			}
			else {
				adminDashboard(request, response);
			}
			break;
		case "/bridge":
			request.getRequestDispatcher("/WEB-INF/employee/bridge.jsp").forward(request, response);
			break;
		case "/Eprofile":
			employeeProfile(request, response);
			break;
		case "/addAuthority":
			request.getRequestDispatcher("/WEB-INF/admin/addEmployee.jsp").forward(request, response);
			break;
		case "/manageEmployee":
			request.getRequestDispatcher("/WEB-INF/admin/manageEmployee.jsp").forward(request, response);
			break;
		case "/addBranch":
			request.getRequestDispatcher("/WEB-INF/admin/addBranch.jsp").forward(request, response);
			break;
		case "/accountInfo":
			request.setAttribute("path","switchAccountInfo");
			accountDetail(request, response);
			break;
		default:
			throw new IllegalArgumentException("Unexpected value: " + path);
		}
	}

	protected void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
	}

	protected void home(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/home.jsp").forward(request, response);
	}

	Authenticator auth = new Authenticator();

	protected void loginAuthendicate(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		try {
			long id = Integer.parseInt(request.getParameter("id"));
			String password = request.getParameter("password");
			boolean access = auth.checkPassword(id, password);
			if (access) {
				request.getSession().setAttribute("id", id);
				auth.validateUser(id);
				authorize(id, request, response);
			}
			else {
				request.setAttribute("errorMessage", "wrong combination try again");
				login(request, response);
			}
		}
		catch(InputDefectException | BankException e) {
			request.setAttribute("errorMessage", e.getMessage());
			login(request, response);
		}
	}

	protected void authorize(long id, HttpServletRequest request, HttpServletResponse response)
			throws BankException, ServletException, IOException, InputDefectException {
		String authority = auth.getAuthority(id);
		// request.getSession().setAttribute("authority", authority);
		switch (authority) {
		case "admin":
			adminDetail(request, response);
			break;
		case "employee":
			employeeDetail(request, response);
			break;
		case "customer":
			customerDetail(request, response);
			break;
		}
	}

	protected void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		session.invalidate();
		customer.logout();
		login(request, response);
	}

	protected void customerDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = (long) request.getSession().getAttribute("id");
		try {
			long[] accounts = customer.getAccounts(id);
			long primary = UtilityHelper.getLong(customer.getPrimaryAccount(id), "AccountNumber");
			HttpSession session = request.getSession();
			if(session.getAttribute("auth")==null) {
			 session.setAttribute("auth", "customer");
			 }
			session.setAttribute("currentAccount", primary);
			session.setAttribute("accounts", accounts);
			customerDashboard(request, response);
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
			login(request, response);
		}
	}

	Customer customer = new Customer();
	
	


	protected void customerDashboard(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = (long) request.getSession().getAttribute("id");
		try {
			JSONObject profile = customer.viewProfile(id);
			request.setAttribute("profile", profile);
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/CDash.jsp").forward(request, response);
		}
	}

	protected void primaryAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = (long) request.getSession().getAttribute("id");
		try {
			long primary = UtilityHelper.getLong(customer.getPrimaryAccount(id), "AccountNumber");
			request.setAttribute("primaryAccount", primary);
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/CPriAccount.jsp").forward(request, response);
		}
		
	}

	protected void switchPrimary(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			long accountNumber = Long.parseLong(request.getParameter("primAccount"));
			long id = (long) request.getSession().getAttribute("id");
			customer.switchPrimaryAccount(accountNumber, id);
			HttpSession session = request.getSession();
			request.setAttribute("primaryAccount", accountNumber);
			session.setAttribute("currentAccount", accountNumber);
			request.setAttribute("successMessage", "Primary account switched");
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		} finally {
			request.getRequestDispatcher("/WEB-INF/CPriAccount.jsp").forward(request, response);
		}
	}

	protected void switchAccount(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long accountNumber = Long.parseLong(request.getParameter("account"));
		HttpSession session = request.getSession();
		session.setAttribute("currentAccount", accountNumber);
		request.setAttribute("successMessage", "account switched");
		request.getRequestDispatcher("/WEB-INF/accountSwitch.jsp").forward(request, response);
	}

	protected void credit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			long accountNumber = (long) session.getAttribute("currentAccount");
			long id = (long) session.getAttribute("id");
			long amount = Long.parseLong(request.getParameter("amount"));
			String description = (String) request.getParameter("description");
			String password = (String) request.getParameter("password");
			boolean access = auth.checkPassword(id, password);
			if (!access) {
				throw new BankException("Wrong password try again");
			}

			JSONObject json = new JSONObject();
			UtilityHelper.put(json, "AccountNumber", accountNumber);
			UtilityHelper.put(json, "Amount", amount);
			UtilityHelper.put(json, "Description", description);
			customer.credit(json);
			request.setAttribute("successMessage", "credit successfull");
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		} finally {
			request.getRequestDispatcher("/WEB-INF/CCredit.jsp").forward(request, response);
		}
	}

	protected void debit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			long accountNumber = (long) session.getAttribute("currentAccount");
			long id = (long) session.getAttribute("id");
			long amount = Long.parseLong(request.getParameter("amount"));
			String description = (String) request.getParameter("description");
			String password = (String) request.getParameter("password");
			boolean access = auth.checkPassword(id, password);
			if (!access) {
				throw new BankException("Wrong password try again");
			}
			JSONObject json = new JSONObject();
			UtilityHelper.put(json, "AccountNumber", accountNumber);
			UtilityHelper.put(json, "Amount", amount);
			UtilityHelper.put(json, "Description", description);
			customer.debit(json);
			request.setAttribute("successMessage", "debit successfull");
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		} finally {
			request.getRequestDispatcher("/WEB-INF/CDebit.jsp").forward(request, response);
		}
	}

	protected void moneyTransfer(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			long accountNumber = (long) session.getAttribute("currentAccount");
			long id = (long) session.getAttribute("id");
			long amount = Long.parseLong(request.getParameter("amount"));
			String description = (String) request.getParameter("description");
			String password = (String) request.getParameter("password");
			long recevingAccount = Long.parseLong(request.getParameter("account"));
			String ifscCode = (String) request.getParameter("ifsc");
			boolean access = auth.checkPassword(id, password);
			if (!access) {
				throw new BankException("Wrong password try again");
			}
			JSONObject json = new JSONObject();
			UtilityHelper.put(json, "AccountNumber", accountNumber);
			UtilityHelper.put(json, "Amount", amount);
			UtilityHelper.put(json, "Description", description);
			UtilityHelper.put(json, "TransactionAccountNumber", recevingAccount);
			UtilityHelper.put(json, "IfscCode", ifscCode);
			customer.moneyTransfer(json);
			request.setAttribute("successMessage", "money transfer successfull");
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		} finally {
			request.getRequestDispatcher("/WEB-INF/CTransfer.jsp").forward(request, response);
		}
	}

	protected void balance(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			long accountNumber = (long) session.getAttribute("currentAccount");
			long id = (long) session.getAttribute("id");
			long balance = customer.getBalance(accountNumber);
			request.setAttribute("balance", balance);
			request.setAttribute("type","debit");
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		} finally {
			request.getRequestDispatcher("/WEB-INF/CBalance.jsp").forward(request, response);
		}
	}

	protected void changePassword(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			long id = (long) session.getAttribute("id");
			String oldPass = request.getParameter("oldPass");
			String newPass = request.getParameter("NewPass");
			String confoPass = request.getParameter("confoPass");
			if (auth.checkPassword(id, oldPass)) {
				if (newPass != confoPass) {
					throw new BankException("new pass words do not match");
				}
				JSONObject pass = UtilityHelper.put(new JSONObject(), "Password", newPass);
				UtilityHelper.put(pass, "Id", id);
				customer.resetPassword(pass);
			}
			request.setAttribute("successMessage", "password Changed successfull");
		} 
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/CPassword.jsp").forward(request, response);
		}
	}

	protected void profile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = (long) request.getSession().getAttribute("id");
		try {
			JSONObject profile = customer.viewProfile(id);
			request.setAttribute("profile", profile);
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/CProfile.jsp").forward(request, response);
		}
			
	}

	protected void history(HttpServletRequest request, HttpServletResponse response,int page )throws IOException, ServletException {
		JSONArray jArray=null;
		try{
			long accountNumber = (long) request.getSession().getAttribute("currentAccount");
			int maxPages=customer.getPages(accountNumber,10);
			if(page<=0) {
				page=1;
				jArray = customer.transactionHistory(accountNumber, 10,page);
			}
			else if(page>maxPages) {
				page=maxPages;
				jArray = customer.transactionHistory(accountNumber, 10,page);
			}
			else {
				jArray = customer.transactionHistory(accountNumber, 10,page);	
			}
		}
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.setAttribute("jArray", jArray);
			request.setAttribute("currentPage",page);
			request.getRequestDispatcher("/WEB-INF/CHistory.jsp").forward(request, response);
		}
	}
	
	protected void historyNext(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		int page= Integer.parseInt(request.getParameter("page"));
		history(request, response, ++page);
	}
	
	protected void historyPrevious(HttpServletRequest request, HttpServletResponse response)throws IOException, ServletException {
		int page= Integer.parseInt(request.getParameter("page"));
			history(request, response, --page);
	}
	
	protected void accountDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long accountNumber = (long) request.getSession().getAttribute("currentAccount");
		try {
			JSONObject account= customer.getAccountDetail(accountNumber);
			System.out.println(account);
			request.setAttribute("accountDetail", account);
		}
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/accountDetail.jsp").forward(request, response);
		}
	}


	// EMPLOYEE
	
	
	Employee employee = new Employee();
	Admin admin=new Admin();
	
	protected void employeeDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = (long) request.getSession().getAttribute("id");
		try {
			HttpSession session = request.getSession();
			session.setAttribute("auth", "employee");
			JSONObject branchId = employee.getBranchId(id);
			session.setAttribute("branchId", UtilityHelper.getInt(branchId, "BranchId"));
			session.setAttribute("empId",id);
			employeeDashboard(request, response);
		} 
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
			login(request, response);
		}
	}
	

	protected void employeeDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int branchId=(int) request.getSession().getAttribute("branchId");
			JSONObject branch= employee.branchDetails(branchId);
			JSONObject branchAccount=employee.branchAccountDetails(branchId);
			request.setAttribute("branch", branch);
			request.setAttribute("branchAccount", branchAccount);
		}
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employee/EDash.jsp");
		dispatcher.forward(request, response);
		}
	}
	
	protected void employeeProfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int branchId=(int) request.getSession().getAttribute("branchId");
			JSONObject branch= employee.branchDetails(branchId);
			request.setAttribute("branch", branch);
		}
		catch (BankException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/employee/Eprofile.jsp");
		dispatcher.forward(request, response);
		}
	}
	
	protected void addCustomer(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json=new JSONObject();
		try {
		UtilityHelper.put(json,"Id",Long.parseLong(request.getParameter("id")));
		UtilityHelper.put(json,"Name",request.getParameter("name"));
		UtilityHelper.put(json,"EmailId",request.getParameter("emailId"));
		UtilityHelper.put(json,"PhoneNumber",Long.parseLong(request.getParameter("phoneNumber")));
		UtilityHelper.put(json,"UserType","customer");
		
		JSONObject json2=new JSONObject();
		UtilityHelper.put(json2,"Id",Long.parseLong(request.getParameter("id")));
		UtilityHelper.put(json2,"AadharNumber",Long.parseLong(request.getParameter("aadhar")));
		UtilityHelper.put(json2,"PanNumber",request.getParameter("pan"));
		UtilityHelper.put(json2,"Address",request.getParameter("address"));
		
		employee.addUsers(json);
		employee.addCustomers(json2);
		request.setAttribute("successMessage", "Customer Added ");
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/employee/addCustomer.jsp").forward(request, response);;
			
		}
	}
	
	protected void addAccountGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			if( request.getSession().getAttribute("auth").equals("employee")) {
				request.setAttribute("branchId",employee.getBranchId((long) request.getAttribute("empId")));
			}
			else if (request.getSession().getAttribute("auth").equals("admin")) {
					request.setAttribute("allBranch",admin.getAllBranchId());
			}
			} catch (BankException | InputDefectException e) {
				request.setAttribute("errorMessage",e.getMessage());
			}
			finally {
				request.getRequestDispatcher("/WEB-INF/employee/addAccount.jsp").forward(request, response);
			}
	}
	
	protected void addAccount(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONObject json=new JSONObject();
		try {
			UtilityHelper.put(json,"AccountNumber",Long.parseLong(request.getParameter("accountNumber")));
			UtilityHelper.put(json,"Id",Long.parseLong(request.getParameter("userId")));
			UtilityHelper.put(json,"BranchId",Integer.parseInt(request.getParameter("branchId")));
			UtilityHelper.put(json,"Balance",Long.parseLong(request.getParameter("balance")));
			UtilityHelper.put(json,"Status","active");	
			employee.createAccount(json);
			request.setAttribute("successMessage", "account created successfully");
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			addAccountGet(request, response);
		}
	}
	
	protected void accountStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getParameter("function");
		String password=request.getParameter("password");
		long empId=(long) request.getSession().getAttribute("empId");
		try {
			boolean access = auth.checkPassword(empId, password);
			if (access) {
				switch (function) {
				case "activate" : 
					try {
						long accountNumber= Long.parseLong(request.getParameter("accountNumber"));
						employee.activateAccount(accountNumber);
						request.setAttribute("successMessage", "account Activated");
					}catch (BankException | InputDefectException e) {
						request.setAttribute("errorMessage", e.getMessage());
					}
					finally {
						request.setAttribute("function","activate");
						request.getRequestDispatcher("/WEB-INF/employee/accountStatus.jsp").forward(request, response);
					}
					break;
				case "inactivate" : 
					try {
						long accountNumber= Long.parseLong(request.getParameter("accountNumber"));
						employee.deactivateAccount(accountNumber);
						request.setAttribute("successMessage", "account Deactivated");
					}catch (BankException | InputDefectException e) {
						request.setAttribute("errorMessage", e.getMessage());
					}
					finally {
						request.setAttribute("function","inactivate");
						request.getRequestDispatcher("/WEB-INF/employee/accountStatus.jsp").forward(request, response);
					}
					break;
				case "delete" : 
					try {
						long accountNumber= Long.parseLong(request.getParameter("accountNumber"));
						employee.deleteAccount(accountNumber);
						request.setAttribute("successMessage", "account deleted");
					}catch (BankException | InputDefectException e) {
						request.setAttribute("errorMessage", e.getMessage());
					}
					finally {
						request.setAttribute("function","delete");
						request.getRequestDispatcher("/WEB-INF/employee/accountStatus.jsp").forward(request, response);
					}
				}	
			}
			else {
				throw new BankException("Wrong Password try Again ");
			}
		}
		catch (BankException | InputDefectException e){
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("function",function);
			request.getRequestDispatcher("/WEB-INF/employee/accountStatus.jsp").forward(request, response);
		}
	}
		
	protected void customerStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String function=request.getParameter("function");
		String password=request.getParameter("password");
		long empId=(long) request.getSession().getAttribute("empId");
		try {
			boolean access = auth.checkPassword(empId, password);
			if (access) {
				switch (function) {
				case "activate" : 
					try {
						long id= Long.parseLong(request.getParameter("id"));
						employee.activateCustomer(id);
						request.setAttribute("successMessage", "customer Activated");
					}catch (BankException | InputDefectException e) {
						request.setAttribute("errorMessage", e.getMessage());
					}
					finally {
						request.setAttribute("function","activate");
						request.getRequestDispatcher("/WEB-INF/employee/customerStatus.jsp").forward(request, response);
					}
					break;
				case "inactivate" : 
					try {
						long id= Long.parseLong(request.getParameter("id"));
						employee.deactivateCustomer(id);
						request.setAttribute("successMessage", "Customer Deactivated");
					}catch (BankException | InputDefectException e) {
						request.setAttribute("errorMessage", e.getMessage());
					}
					finally {
						request.setAttribute("function","inactivate");
						request.getRequestDispatcher("/WEB-INF/employee/customerStatus.jsp").forward(request, response);
					}
					break;
				}	
			}
			else {
				throw new BankException("Wrong Password try Again ");
			}
		}catch (BankException | InputDefectException e){
			request.setAttribute("errorMessage", e.getMessage());
			request.setAttribute("function",function);
			request.getRequestDispatcher("/WEB-INF/employee/customerStatus.jsp").forward(request, response);
	}
	}
	
	
	
	protected void bridge(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long id =Long.parseLong(request.getParameter("id"));
		request.getSession().setAttribute("id",id);
		try {
			employee.checkIdCustomerPresence(id);
			customerDetail(request, response);
		} catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/employee/bridge.jsp").forward(request, response);
		}
	}
	
//	admin
	protected void adminDetail(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		long id = (long) request.getSession().getAttribute("id");
		try {
			HttpSession session = request.getSession();
			session.setAttribute("auth", "admin");
			JSONObject branchId = employee.getBranchId(id);
			session.setAttribute("branchId", UtilityHelper.getInt(branchId, "BranchId"));
			session.setAttribute("empId",id);
			adminDashboard(request, response);
		} 
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
			login(request, response);
		}
	}
	
	protected void adminDashboard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			int branchId=(int) request.getSession().getAttribute("branchId");
			System.out.println(branchId);
			JSONObject branch= admin.branchDetails(branchId);
			JSONObject branchAccount=admin.branchAccountDetails(branchId);
			request.setAttribute("branch", branch);
			request.setAttribute("branchAccount", branchAccount);	
			request.setAttribute("allBranch",admin.getAllBranchId());
		}
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
			request.getRequestDispatcher("/WEB-INF/employee/EDash.jsp").forward(request, response);
		}
	}
	
	
	protected void addAuthority(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
		JSONObject json=new JSONObject();
		String type=request.getParameter("type");
		UtilityHelper.put(json,"Id",Long.parseLong(request.getParameter("id")));
		UtilityHelper.put(json,"Name",request.getParameter("name"));
		UtilityHelper.put(json,"EmailId",request.getParameter("emailId"));
		UtilityHelper.put(json,"PhoneNumber",Long.parseLong(request.getParameter("phoneNumber")));
		UtilityHelper.put(json,"UserType",type);
		System.out.println(json);
		admin.addUsers(json);
		
		JSONObject json2= new JSONObject();
		UtilityHelper.put(json2,"Id",Long.parseLong(request.getParameter("id")));
		UtilityHelper.put(json2,"BranchId",request.getParameter("branchId") );
		UtilityHelper.put(json2,"Type",type);
		admin.addEmployee(json2);
		request.setAttribute("successMessage", type+" Added Successfully");
		}
		catch (BankException | InputDefectException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
		request.getRequestDispatcher("/WEB-INF/admin/addEmployee.jsp").forward(request, response);
		}
	}
	
	protected void addBranch(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		try {
		JSONObject json= new JSONObject();
		int branchId=Integer.parseInt(request.getParameter("branchId"));
		UtilityHelper.put(json,"BranchId",branchId);
		UtilityHelper.put(json,"IfscCode","rey"+String.format("%05d",branchId) );
		UtilityHelper.put(json,"BranchName",request.getParameter("branchName"));
		UtilityHelper.put(json,"Address",request.getParameter("address"));
		admin.createBranch(json);
		request.setAttribute("successMessage","Branch Added");
		}
		catch (BankException | InputDefectException e) {
			request.setAttribute("errorMessage", e.getMessage());
		}
		finally {
		request.getRequestDispatcher("/WEB-INF/admin/addBranch.jsp").forward(request, response);
		}
	}
	
	protected void employeeStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String status=(String) request.getParameter("status");
		String password=request.getParameter("password");
		long empId=(long) request.getSession().getAttribute("empId");
		try {
			boolean access = auth.checkPassword(empId, password);
			if (access) {
				switch (status) {
				case "Active":
					try {
						long id= Long.parseLong(request.getParameter("id"));
						admin.activateEmployee(id);
						request.setAttribute("successMessage", "Employee Activated");
					}catch (BankException | InputDefectException e) {
						request.setAttribute("errorMessage", e.getMessage());
					}
					finally {
						request.setAttribute("function","activate");
						request.getRequestDispatcher("/WEB-INF/admin/manageEmployee.jsp").forward(request, response);
					}
					break;
				case "Inactive":
					try {
						long id= Long.parseLong(request.getParameter("id"));
						admin.inactivateEmployee(id);
						request.setAttribute("successMessage", "Employee Deactivated");
					}catch (BankException | InputDefectException e) {
						request.setAttribute("errorMessage", e.getMessage());
					}
					finally {
						request.setAttribute("function","inactivate");
						request.getRequestDispatcher("/WEB-INF/admin/manageEmployee.jsp").forward(request, response);
					}
					break;
				}	
			}
			else {
				throw new BankException("Wrong Password try Again ");
			}
		}catch (BankException | InputDefectException e){
			request.setAttribute("errorMessage", e.getMessage());
			request.getRequestDispatcher("/WEB-INF/admin/manageEmployee.jsp").forward(request, response);
	}
	}
}
