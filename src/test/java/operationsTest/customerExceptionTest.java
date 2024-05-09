package operationsTest;

import org.json.JSONObject;
import org.testng.annotations.Test;

import operations.Admin;
import operations.Customer;
import utility.BankException;
import utility.InputDefectException;

public class customerExceptionTest {
	Customer customer=new Customer();
	Admin admin = new Admin();
  @Test(dataProvider = "accountNumberJson",expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void balance(JSONObject json) throws BankException, InputDefectException {
	  System.out.println(customer.getBalance(json));
  }

  @Test(dataProvider ="accountNumberLong",expectedExceptions = {utility.BankException.class,utility.InputDefectException.class} )
  public void balance(long accountNumber) throws BankException, InputDefectException {
	  customer.getBalance(accountNumber);
  }
 
  @Test(dataProvider = "idLong",expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void getAccounts(long id) throws BankException, InputDefectException {
	  System.out.println(customer.getAccounts(id));
  }
  
  @Test(dataProvider = "idLong",expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void accountStatus(long accountNumber) throws BankException, InputDefectException {
	  System.out.println(customer.getAccounts(accountNumber));
  }
  
  @Test(dataProvider = "transactionHistory",expectedExceptions = {utility.BankException.class,utility.InputDefectException.class})
  public void transactionHistory(int accountNumber,int quantity,int page) throws BankException, InputDefectException {
	  customer.transactionHistory(accountNumber, quantity, page);
  }
}
