package dataProvider;

import org.json.JSONObject;
import org.testng.annotations.DataProvider;

public class CustomerDataProvider extends dataProvider.DataProvider{
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
 
 @DataProvider(name = "accountQuantity")
 private Object[][] accountQuantity(){
	  return new Object[][] {
		  {-1L,10},
		  {0L,10},
		  {10000000000000000L,10},
		  {1000L,10},
		  {7L,-10},
		  {7L,0},
		  {7L,1000000000}
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
 
 @DataProvider(name = "IdAccountInvalid")
 private Object[][] idAccountInvalid(){
	  return new Object[][] {
		  {-1,4L},
		  {0,5L},
		  {1000000000,1L},
		  {7,-1},
		  {7,0},
		  {7,1000000}
	  };
 }
 
}
