package database;

import org.json.JSONObject;

import pojo.Accounts;
import pojo.Customers;
import pojo.Users;
import utility.BankException;
import utility.InputDefectException;

public interface EmployeeServiceInterface extends CustomerServiceInterface {
	
	
	public void addUsers(Users user) throws BankException;
	
	public void alterUsers(Users user) throws BankException;
	
	public void deleteUsers(long userId) throws BankException;

	public void addCustomers(Customers customer) throws BankException;

	public void createAccount(Accounts account) throws BankException;

	public void deleteAccount(long accountNumber) throws BankException;

	public void deactivateAccount(long accountNumber) throws BankException;

	public void activateAccount(long accountNumber) throws BankException;

	public JSONObject getBranch(long empId) throws BankException ;
	
	public void activateCustomer(long id) throws BankException ;
	
	public void deactivateCustomer(long id) throws BankException ;
	
	public JSONObject getBranchDetail(long bankId) throws BankException ;
	
	public long activeAccount(long bankId) throws BankException, InputDefectException ;
	
	public long inactiveAccount(long bankId) throws BankException, InputDefectException ;
	
	public long deletedAccount(long bankId) throws BankException, InputDefectException ;
	
	public long totalAccounts(long bankId) throws BankException, InputDefectException ;
	
}
