package database;

import org.json.JSONObject;

import com.mysql.cj.x.protobuf.MysqlxCrud.Update;

import pojo.Accounts;
import pojo.Customers;
import pojo.Users;
import utility.BankException;
import utility.InputDefectException;
import utility.UtilityHelper;


public class EmployeeService extends CustomerService implements EmployeeServiceInterface{
	
	protected EmployeeService(String url, String userName, String password) {
		super(url, userName, password);
	}
	
	private static class BillpoughEmployee{
		private static final EmployeeService employeeService=new EmployeeService("jdbc:mysql://localhost:3306/rey_bank", "root", "0000");
	}
	
	public static EmployeeService getEmployeeService() {
		return BillpoughEmployee.employeeService;
	}

	public void addUsers(Users user) throws BankException {
		String tableName = "users";
		generalAdd(tableName, user);
	}

	public void addCustomers(Customers customer) throws BankException {
		String tableName = "customers";
		generalAdd(tableName, customer);
	}

	public void createAccount(Accounts account) throws BankException {
		String tableName = "accounts";
		generalAdd(tableName, account);
	}

	public void deleteAccount(long accountNumber) throws BankException {
		StringBuilder query = builder.setStatus("accounts","deleted","AccountNumber");
		update(query, accountNumber);
	}

	public void deactivateAccount(long accountNumber) throws BankException {
		StringBuilder query = builder.setStatus("accounts", "inactive","AccountNumber");
		update(query, accountNumber);
	}

	public void activateAccount(long accountNumber) throws BankException {
		StringBuilder query= builder.setStatus("accounts","active","AccountNumber");
		update( query,accountNumber);
	}
	
	public JSONObject getBranch(long empId) throws BankException {
		return selectWhere("employees","Id="+empId,"BranchId");
	}
	
	public void activateCustomer(long id) throws BankException {
		StringBuilder query=builder.setStatus("users", "active","Id");
		update(query, id);
	}
	
	public void deactivateCustomer(long id) throws BankException {
		StringBuilder query=builder.setStatus("users", "inactive","Id");
		update(query, id);
	}
	// new
	public JSONObject getBranchDetail(long bankId) throws BankException {
		StringBuilder query=new StringBuilder();
		query.append("select * from branch where BranchId=");
		query.append(bankId);
		return select(query);
	}
	
	public long activeAccount(long bankId) throws BankException, InputDefectException {
		StringBuilder query=builder.selectAllCountFromWherePrep("accounts",
					"BranchId= "+bankId+" and Status='active'");
		return UtilityHelper.getInt(select(query),"count(*)");
	}
	//TODO
	public long inactiveAccount(long bankId) throws BankException, InputDefectException {
		StringBuilder query=builder.selectAllCountFromWherePrep("accounts",
					"BranchId= "+bankId+" and Status='inactive'");
		return UtilityHelper.getInt(select(query),"count(*)");
	}
	
	public long deletedAccount(long bankId) throws BankException, InputDefectException {
		StringBuilder query=builder.selectAllCountFromWherePrep("accounts",
					"BranchId= "+bankId+" and Status='deleted'");
		return UtilityHelper.getInt(select(query),"count(*)");
	}
	
	public long totalAccounts(long bankId) throws BankException, InputDefectException {
		StringBuilder query=builder.selectAllCountFromWherePrep("accounts",
				"BranchId= "+bankId);
	return UtilityHelper.getInt(select(query),"count(*)");
	}

	@Override
	public void alterUsers(Users user) throws BankException {
		long id= user.getId();
		StringBuilder query= builder.pojoToUpadteQuery("users", user, "Id ="+id);
		update(query, user);
	}

	@Override
	public void deleteUsers(long userId) throws BankException {
		 StringBuilder query= builder.deleteFrom("users", "Id");
		  delete(query,userId);
		
	}
}
