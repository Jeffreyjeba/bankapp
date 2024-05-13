package operationsTest;

import org.json.JSONObject;
import org.testng.annotations.Test;

import dataProvider.CustomerDataProvider;
import operations.Customer;
import utility.BankException;
import utility.InputDefectException;

public class customerExceptionTest {
	Customer customer=new Customer();
  @Test(dataProvider = "accountNumberJson",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void balance(JSONObject json) throws BankException, InputDefectException {
	  customer.getBalance(json);
  }

  @Test(dataProvider ="accountNumberLong",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class} )
  public void balance(long accountNumber) throws BankException, InputDefectException {
	  customer.getBalance(accountNumber);
  }
 
  @Test(dataProvider = "idLong",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void getAccounts(long id) throws BankException, InputDefectException {
	  customer.getAccounts(id);
  }
  
  @Test(dataProvider = "idLong",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void accountStatus(long accountNumber) throws BankException, InputDefectException {
	  customer.getAccounts(accountNumber);
  }
  
  @Test(dataProvider = "transactionHistory",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void transactionHistory(int accountNumber,int quantity,int page) throws BankException, InputDefectException {
	  customer.transactionHistory(accountNumber, quantity, page);
  }
  
  @Test(dataProvider = "accountQuantity",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void getPages(long accountNumber,int quantity) throws BankException, InputDefectException {
	  customer.getPages(accountNumber,quantity);
  }
  
  @Test(dataProvider = "idLong",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void viewProfile(long id) throws BankException, InputDefectException {
	  customer.viewProfile(id);
  }
  
  @Test(dataProvider = "idLong",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void primaryAccount(long id) throws BankException, InputDefectException {
	  customer.getPrimaryAccount(id);
  }
  
  @Test(dataProvider = "IdAccountInvalid",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void switchPrimaryAccount(long accountNumber,long id) throws BankException, InputDefectException {
	  customer.switchPrimaryAccount(accountNumber, id);
  }
  
  @Test(dataProvider = "accountNumberLong",
		  dataProviderClass = CustomerDataProvider.class,
		  expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void getAccountDetails(long accountNumber) throws BankException, InputDefectException {
	  customer.getAccountDetail(accountNumber);
  }
  
  
  
}
