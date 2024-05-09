package operationsTest;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import operations.Admin;
import operations.Customer;
import utility.BankException;
import utility.InputDefectException;

public class CustomerTest {
	
	Customer customer=new Customer();
	Admin admin = new Admin();
  
	 @Test(dataProvider = "accountNumberJson")
	  public void balance(JSONObject json) throws BankException, InputDefectException {
		  System.out.println(customer.getBalance(json));
	  }

	  @Test(dataProvider ="accountNumberLong")
	  public void balance(long accountNumber) throws BankException, InputDefectException {
		  customer.getBalance(accountNumber);
	  }
	 
	  @Test(dataProvider = "idLong")
	  public void getAccounts(long id) throws BankException, InputDefectException {
		  System.out.println(customer.getAccounts(id));
	  }
	  
	  @Test(dataProvider = "idLong")
	  public void accountStatus(long accountNumber) throws BankException, InputDefectException {
		  System.out.println(customer.getAccounts(accountNumber));
	  }
	  
	  @Test(dataProvider = "transactionHistory")
	  public void transactionHistory(int accountNumber,int quantity,int page) throws BankException, InputDefectException {
		  customer.transactionHistory(accountNumber, quantity, page);
	  }
	@Test(dataProvider = "IdAccount")
  public void idToAccount(long id,long accountNumber) throws BankException, InputDefectException {
	  customer.checkUserAndAccount(id, accountNumber);
  }
	
	@Test(dataProvider = "branchId")
	  public void brnachId(long branchId) throws BankException, InputDefectException {
		 admin.validateBranchId(branchId);
	  }
  

	 @DataProvider(name = "branchId")
	  private Long[][] branchId(){
		  return new Long [][] {
			  {1L},
			  {2L},
			  {51L}
		  };
	  }
  
  @DataProvider(name = "accountNumberLong")
  private Long[][] accountNumberLong(){
	  return new Long [][] {
		  {-1L},
		  {0L},
		  {100000L},
		  {1000L},
	  };
  }
  
  @DataProvider(name = "transactionHistory")
  private Integer[][] transactionHistory(){
	  return new Integer[][] {
		  {-1,10000,10},
		  {0,0,0},
		  {100000,20,200},
		  {1000,788,90},
	  };
  }
  
  @DataProvider(name = "IdAccount")
  private Long[][] idAccount(){
	  return new Long[][] {
		  {7L,4L},
		  {7L,5L},
		  {7L,1L}
	  };
  }
  
  
  
  
  @DataProvider(name = "idLong")
  private Long[][] idLong(){
	  return new Long [][] {
		  {-1L},
		  {0L},
		  {100000L},
		  {1000L},
	  };
  }
  
  @DataProvider(name = "accountNumberJson")
  private JSONObject[][] accountNumberJson(){
	  return  new JSONObject [][] {
		  {new JSONObject().put("AccountNumber",-1)},
		  {new JSONObject().put("AccountNumber",0.00001)},
		  {new JSONObject().put("AccountNumber",100000)},
	  };
  }
  
}
