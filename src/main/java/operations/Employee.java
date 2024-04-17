package operations;


import org.json.JSONObject;

import bank.Authenticator;
import bank.OperationType;
import bank.ServiceFactory;
import bank.UserHirarchy;
import database.EmployeeServiceInterface;
import pojo.Accounts;
import pojo.Customers;
import pojo.LogMethods;
import pojo.UserData;
import pojo.Users;
import utility.BankException;
import utility.InputDefectException;
import utility.UtilityHelper;
import utility.Validation;

public class Employee extends Customer{
	
	EmployeeServiceInterface employee=ServiceFactory.getEmployeeService();
	
	public void addUsers(JSONObject userJson) throws BankException,InputDefectException{
		UtilityHelper.nullCheck(userJson);
		Users user=josnToUsers(userJson);
		checkIdUserAbsence(user.getId());
		validateUsers(userJson);
		setTime();
		Authenticator.user.get().setActiveId(UtilityHelper.getLong(userJson,"Id"));
		setCreationDetails(user);
		employee.addUsers(user);
		log.log("-",OperationType.addUser);
	}
	public void addCustomers(JSONObject customerJson) throws BankException,InputDefectException{
		UtilityHelper.nullCheck(customerJson);
		Customers customer=josnToCustomers(customerJson);
		long id=customer.getId();
		checkIdUserPresence(id);
		checkIdCustomerAbsence(id);
		validateCustomer(customerJson);
		setTime();
		Authenticator.user.get().setActiveId(UtilityHelper.getLong(customerJson,"Id"));
		setCreationDetails(customer);
		employee.addCustomers(customer);
		log.log("-",OperationType.addCustomer);
	}
	public void createAccount(JSONObject accountJson) throws BankException,InputDefectException {
		UtilityHelper.nullCheck(accountJson);
		Accounts account=jsonToAccounts(accountJson);
		checkAccNoForAbsence(account.getAccountNumber());
		checkIdCustomerPresence(account.getId());
		setTime();
		Authenticator.user.get().setActiveId(UtilityHelper.getLong(accountJson,"Id"));
		setCreationDetails(account);
		employee.createAccount(account);
		log.log("-",OperationType.addAccount);
	}
	public void deleteAccount(long accountNumber) throws BankException,InputDefectException {
		checkAccNoForPresence(accountNumber);
		setTime();
		setUserForAccounts(accountNumber);
		employee.deleteAccount(accountNumber);
		log.log("Account number : "+accountNumber,OperationType.deleteAccount);
	}
	public void deactivateAccount(long accountNumber) throws BankException,InputDefectException  {
		checkAccNoForPresence(accountNumber);
		setTime();
		setUserForAccounts(accountNumber);
		employee.deactivateAccount(accountNumber);
		log.log("Account number : "+accountNumber,OperationType.deactivateAccount);
	}
	public void activateAccount(long accountNumber) throws BankException,InputDefectException  {
		checkAccNoForPresence(accountNumber);
		setTime();
		setUserForAccounts(accountNumber);
		employee.activateAccount(accountNumber);
		log.log("Account number : "+accountNumber,OperationType.activateAccount);
	}
	
	public JSONObject getBranchId(long id) throws BankException {
		return employee.getBranch(id);
	}
	
	public void activateCustomer(long id) throws BankException, InputDefectException {
		checkIdCustomerPresence(id);
		setTime();
		Authenticator.user.get().setActiveId(id);
		employee.activateCustomer(id);
		log.log("-",OperationType.activateCustomer);
	}
	
	public void deactivateCustomer(long id) throws BankException, InputDefectException {
		checkIdCustomerPresence(id);
		setTime();
		Authenticator.user.get().setActiveId(id);
		employee.deactivateCustomer(id);
		log.log("-",OperationType.deactivateCustomer);
	}
	
	
	public JSONObject branchDetails(long branchId) throws BankException {
	 	 return employee.getBranchDetail(branchId);
	}
	public JSONObject branchAccountDetails(long branchId) throws BankException, InputDefectException {
	 	 long active=employee.activeAccount(branchId);
	 	 long inactive=employee.inactiveAccount(branchId);
	 	 long deleted=employee.deletedAccount(branchId);
	 	 long total=employee.totalAccounts(branchId);
	 	 JSONObject count =UtilityHelper.put(new JSONObject(),"active",active);
	 	 UtilityHelper.put(count,"inactive",inactive);
	 	 UtilityHelper.put(count,"total",total);
	 	 return UtilityHelper.put(count,"deleted",deleted);
	}
	
	protected Users josnToUsers(JSONObject userPojo) throws BankException, InputDefectException {
		Users users=new Users();
		users.setEmailId(UtilityHelper.getString(userPojo,"EmailId"));
		users.setId(UtilityHelper.getLong(userPojo,"Id"));
		users.setName(UtilityHelper.getString(userPojo,"Name"));
		users.setPhoneNumber(UtilityHelper.getLong(userPojo,"PhoneNumber"));
		users.setUserType(UserHirarchy.valueOf(UtilityHelper.getString(userPojo,"UserType")));
		return users;
	}
	
	protected Customers josnToCustomers(JSONObject customerPojo) throws BankException, InputDefectException {
		Customers customer=new Customers();
		customer.setAadharNumber(UtilityHelper.getLong(customerPojo,"AadharNumber"));
		customer.setAddress(UtilityHelper.getString(customerPojo,"Address"));
		customer.setId(UtilityHelper.getLong(customerPojo,"Id"));
		customer.setPanNumber(UtilityHelper.getString(customerPojo,"PanNumber"));
		return customer;
	}
	
	protected Accounts jsonToAccounts(JSONObject accountPojo) throws BankException, InputDefectException {
		Accounts accounts=new Accounts();
		accounts.setAccountNumber(UtilityHelper.getLong(accountPojo,"AccountNumber"));
		accounts.setBalance(UtilityHelper.getLong(accountPojo,"Balance"));
		accounts.setBranchId(UtilityHelper.getInt(accountPojo,"BranchId"));
		accounts.setId(UtilityHelper.getLong(accountPojo,"Id"));
		return accounts;
	}
	
	protected void validateUsers(JSONObject json) throws BankException, InputDefectException {
		 if(!Validation.validateEmail(UtilityHelper.getString(json,"EmailId"))) {
			 throw new BankException("invalid Email Id");
		 }
		 if(! Validation.validatePhoenNo(Long.toString(UtilityHelper.getLong(json,"PhoneNumber")))) {
			 throw new BankException("invalid Phone Number");
		 }
	}
	
	protected void validateCustomer(JSONObject json) throws BankException, InputDefectException {
		 if(!Validation.validateAdharNumber(Long.toString(UtilityHelper.getLong(json,"AadharNumber")))) {
			 throw new BankException("invalid Aadhar Number");
		 }
		 if(!Validation.validatePanNumber(UtilityHelper.getString(json,"PanNumber"))) {
			 throw new BankException("invalid Pan Number");
		 }
	}
	
	protected void setUserForAccounts(long accountNumber) throws BankException, InputDefectException {
		Authenticator.user.get().setActiveId(employee.userForAccountNumber(accountNumber));
	}
	
	
	protected void setCreationDetails(LogMethods bank) {
		UserData userData=Authenticator.user.get();
		bank.setCreatedTime(userData.getTime());
		bank.setRecentModifiedTime(userData.getTime());
		bank.setModifiedBy(userData.getId());
	}
}
