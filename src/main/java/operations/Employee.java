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

	public long addUsers(JSONObject userJson) throws BankException,InputDefectException{
		UtilityHelper.nullCheck(userJson);
		Users user=josnToUsers(userJson);
		validateUsers(userJson);
		checkEmailIdForAbsence(user.getEmailId());
		checkPhoneNoForAbsence(user.getPhoneNumber());
		setTime();
		setCreationDetails(user);
		long id= employee.addUsers(user);
		Authenticator.user.get().setActiveId(id);
		user.setId(id);
		LogAgent.log("-",OperationType.addUser);
		return id;
	}
	public long addUserCustomer(JSONObject userJson,JSONObject customerJson) throws InputDefectException, BankException {
		UtilityHelper.nullCheck(userJson);
		Users user=josnToUsers(userJson);
		validateUsers(userJson);
		checkEmailIdForAbsence(user.getEmailId());
		checkPhoneNoForAbsence(user.getPhoneNumber());
		setTime();
		setCreationDetails(user);
		UtilityHelper.nullCheck(customerJson);
		validateCustomer(customerJson);
		checkAadharForAbsence(UtilityHelper.getLong(customerJson,"AadharNumber"));
		checkpanForAbsence(UtilityHelper.getString(customerJson,"PanNumber"));
		long id= employee.addUsers(user); //
		Authenticator.user.get().setActiveId(id);
		user.setId(id);
		LogAgent.log("-",OperationType.addUser);
		UtilityHelper.put(customerJson,"Id",id);
		Customers customer=josnToCustomers(customerJson);
		long custId=customer.getId();
		checkIdUserPresence(custId);
		checkIdCustomerAbsence(custId);
		setTime();
		Authenticator.user.get().setActiveId(UtilityHelper.getLong(customerJson,"Id"));
		setCreationDetails(customer);
		employee.addCustomers(customer);
		LogAgent.log("-",OperationType.addCustomer);
		return id;
	}
	

	public void alterUsers(JSONObject userJson) throws BankException,InputDefectException{
		UtilityHelper.nullCheck(userJson);
		Users user=josnToUsers(userJson);
		checkIdUserPresence(user.getId());
		validateUsers(userJson);
		setTime();
		Authenticator.user.get().setActiveId(UtilityHelper.getLong(userJson,"Id"));
		setCreationDetails(user);
		employee.alterUsers(user);
		LogAgent.log("-",OperationType.addUser);
	}

	public void deleteUser(long UserId) throws BankException {
		employee.deleteUsers(UserId);
	}

	public void addCustomers(JSONObject customerJson) throws BankException,InputDefectException{
		UtilityHelper.nullCheck(customerJson);
		Customers customer=josnToCustomers(customerJson);
		validateCustomer(customerJson);
		long id=customer.getId();
		checkIdUserPresence(id);
		checkIdCustomerAbsence(id);
		checkAadharForAbsence(customer.getAadharNumber());
		checkpanForAbsence(customer.getPanNumber());
		setTime();
		Authenticator.user.get().setActiveId(UtilityHelper.getLong(customerJson,"Id"));
		setCreationDetails(customer);
		employee.addCustomers(customer);
		LogAgent.log("-",OperationType.addCustomer);
	}
	public long createAccount(JSONObject accountJson) throws BankException,InputDefectException {
		UtilityHelper.nullCheck(accountJson);
		Accounts account=jsonToAccounts(accountJson);
		validateAccounts(accountJson);
		branchIdPresence(account.getBranchId());
		//checkAccNoForAbsence(account.getAccountNumber());
		checkIdCustomerPresence(account.getId());
		setTime();
		Authenticator.user.get().setActiveId(UtilityHelper.getLong(accountJson,"Id"));
		setCreationDetails(account);
		long accountNumber= employee.createAccount(account);
		LogAgent.log("-",OperationType.addAccount);
		return accountNumber;
	}
	public void deleteAccount(long accountNumber) throws BankException,InputDefectException {
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		findDeleted(accountNumber);
		setTime();
		setUserForAccounts(accountNumber);
		employee.deleteAccount(accountNumber);
		LogAgent.log("Account number : "+accountNumber,OperationType.deleteAccount);
	}
	public void deactivateAccount(long accountNumber) throws BankException,InputDefectException  {
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		findDeleted(accountNumber);
		findInactive(accountNumber);
		setTime();
		setUserForAccounts(accountNumber);
		employee.deactivateAccount(accountNumber);
		LogAgent.log("Account number : "+accountNumber,OperationType.deactivateAccount);
	}
	public void activateAccount(long accountNumber) throws BankException,InputDefectException  {
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		findDeleted(accountNumber);
		findActive(accountNumber);
		setTime();
		setUserForAccounts(accountNumber);
		employee.activateAccount(accountNumber);
		LogAgent.log("Account number : "+accountNumber,OperationType.activateAccount);
	}

	public JSONObject getBranchId(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkIdUserPresence(id);
		return employee.getBranch(id);
	}

	public void activateCustomer(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkIdCustomerPresence(id);
		checkUserActive(id);
		setTime();
		Authenticator.user.get().setActiveId(id);
		employee.activateCustomer(id);
		LogAgent.log("-",OperationType.activateCustomer);
	}

	public void deactivateCustomer(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkIdCustomerPresence(id);
		checkUserInactive(id);
		setTime();
		Authenticator.user.get().setActiveId(id);
		employee.deactivateCustomer(id);
		LogAgent.log("-",OperationType.deactivateCustomer);
	}


	public JSONObject branchDetails(long branchId) throws BankException, InputDefectException {
		UtilityHelper.checkNegative(branchId);
		branchIdPresence(branchId);
		return employee.getBranchDetail(branchId);
	}
	public JSONObject branchAccountDetails(long branchId) throws BankException, InputDefectException {
		UtilityHelper.checkNegative(branchId);
		branchIdPresence(branchId);
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
		//users.setId(UtilityHelper.getLong(userPojo,"Id"));
		users.setName(UtilityHelper.getString(userPojo,"Name"));
		users.setPhoneNumber(UtilityHelper.getLong(userPojo,"PhoneNumber"));
		users.setUserType(UserHirarchy.valueOf(UtilityHelper.getString(userPojo,"UserType")));
		return users;
	}

	protected Customers josnToCustomers(JSONObject customerJson) throws BankException, InputDefectException {
		Customers customer=new Customers();
		customer.setAadharNumber(UtilityHelper.getLong(customerJson,"AadharNumber"));
		customer.setAddress(UtilityHelper.getString(customerJson,"Address"));
		customer.setId(UtilityHelper.getLong(customerJson,"Id"));
		customer.setPanNumber(UtilityHelper.getString(customerJson,"PanNumber"));
		return customer;
	}

	protected Accounts jsonToAccounts(JSONObject accountPojo) throws BankException, InputDefectException {
		Accounts accounts=new Accounts();
		//accounts.setAccountNumber(UtilityHelper.getLong(accountPojo,"AccountNumber"));
		accounts.setBalance(UtilityHelper.getLong(accountPojo,"Balance"));
		accounts.setBranchId(UtilityHelper.getInt(accountPojo,"BranchId"));
		accounts.setId(UtilityHelper.getLong(accountPojo,"Id"));
		return accounts;
	}

	protected void validateUsers(JSONObject json) throws BankException, InputDefectException {
		//Validation.validateUserId(UtilityHelper.getLong(json,"Id"));
		Validation.name(UtilityHelper.getString(json,"Name"));
	
		if(!Validation.validateEmail(UtilityHelper.getString(json,"EmailId"))) {
			throw new BankException("Invalid Email Id");
		}
		if(! Validation.validatePhoenNo(Long.toString(UtilityHelper.getLong(json,"PhoneNumber")))) {
			throw new BankException("Invalid Phone Number");
		}
	}

	protected void validateCustomer(JSONObject json) throws BankException, InputDefectException {
		if(!Validation.validateAdharNumber(Long.toString(UtilityHelper.getLong(json,"AadharNumber")))) {
			throw new BankException("invalid Aadhar Number");
		}
		if(!Validation.validatePanNumber(UtilityHelper.getString(json,"PanNumber"))) {
			throw new BankException("invalid Pan Number");
		}
		
		//Validation.validateUserId(UtilityHelper.getLong(json,"Id"));

		Validation.address(UtilityHelper.getString(json,"Address"));
	}

	protected void validateAccounts(JSONObject json) throws InputDefectException, BankException {
		//Validation.validateAccountNumber(UtilityHelper.getLong(json,"AccountNumber"));
		Validation.validateAmount(UtilityHelper.getLong(json,"Balance"));
		Validation.validateUserId(UtilityHelper.getLong(json,"Id"));
	}

	protected void setUserForAccounts(long accountNumber) throws BankException, InputDefectException {
		Authenticator.user.get().setActiveId(employee.userForAccountNumber(accountNumber));
	}

	protected void branchIdPresence(long id) throws BankException, InputDefectException {
		employee.checkBranchPrecence(id,"BranchId");
	}
	
	protected void findDeleted(long AccountNumber) throws BankException, InputDefectException {
		String status= accountStatus(AccountNumber);
		if(status.equals("deleted")) {
			throw new BankException("account Deleted");
		}
	}
	
	protected void findActive(long AccountNumber) throws BankException, InputDefectException {
		String status= accountStatus(AccountNumber);
		if(status.equals("active")) {
			throw new BankException("Account active");
		}
	}
	
	protected void findInactive(long AccountNumber) throws BankException, InputDefectException {
		String status= accountStatus(AccountNumber);
		if(status.equals("inactive")) {
			throw new BankException("Account Inactive");
		}
	}
	
	protected String UserStatus(long id) throws BankException, InputDefectException {
		JSONObject json= employee.getUserStatus(id);
		return UtilityHelper.getString(json,"Status");
	}

	protected void checkUserActive(long id) throws BankException, InputDefectException {
		if(UserStatus(id).equals("active")) {
			throw new BankException("User Active");
		}
	}
	
	protected void checkUserInactive(long id) throws BankException, InputDefectException {
		if(UserStatus(id).equals("inactive")) {
			throw new BankException("User Inactive");
		}
	}

	protected void setCreationDetails(LogMethods bank) {
		UserData userData=Authenticator.user.get();
		bank.setCreatedTime(userData.getTime());
		bank.setRecentModifiedTime(userData.getTime());
		bank.setModifiedBy(userData.getId());
	}
}
