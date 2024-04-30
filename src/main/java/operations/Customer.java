package operations;

import org.json.JSONArray;
import org.json.JSONObject;

import bank.Authenticator;
import bank.OperationType;
import bank.ServiceFactory;
import bank.TransactionType;
import database.CustomerServiceInterface;
import lock.LockSupplier;
import pojo.TransactionHistory;
import utility.BankException;
import utility.InputDefectException;
import utility.UtilityHelper;
import utility.Validation;


public class Customer {
	
	private CustomerServiceInterface customer = ServiceFactory.getCustomerService();
	
	public long getBalance(JSONObject customerJson) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(customerJson);
		long accountNumber=UtilityHelper.getLong(customerJson,"AccountNumber");
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		return UtilityHelper.getLong(customer.getBalance(accountNumber),"Balance");
	}
	
	public long getBalance(long accountNumber) throws BankException, InputDefectException {
		JSONObject json = new JSONObject();
		UtilityHelper.put(json, "AccountNumber", accountNumber);
		return getBalance(json); 
	}

	public long[] getAccounts(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkIdCustomerPresence(id);
		JSONArray jArray = customer.getAccounts(id);
		if (jArray.length() == 0) {
			throw new BankException("Accounts for this Id dosent exist");
		}
		return jArrayToArray(jArray);
	}

	public void resetPassword(JSONObject customerJson) throws BankException, InputDefectException { //TODO
		UtilityHelper.nullCheck(customerJson);
		long id=UtilityHelper.getLong(customerJson,"Id");
		Validation.validateUserId(id);
		String password = UtilityHelper.getString(customerJson, "Password");
		if(Validation.validatePassword(password)) {
			checkIdUserPresence(id);		
			String newPasswordHash = UtilityHelper.passHasher(password);
			setTime();
			Authenticator.user.get().setActiveId(id);
			LogAgent.log("-",OperationType.resetPassword);
			customer.resetPassword(id,newPasswordHash);
		}
		else {
			throw new BankException("Wrong Password format");
		}
	}

	public String accountStatus(long accountNumber) throws BankException, InputDefectException {
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		JSONObject resultJson = customer.accountStatus(accountNumber);
		return UtilityHelper.getString(resultJson, "Status");
	}
	
	public void debit(JSONObject customerJson) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(customerJson);
		long accountNumber = UtilityHelper.getLong(customerJson, "AccountNumber");
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		resolveAccountStatus(accountNumber);
		long amount = UtilityHelper.getLong(customerJson, "Amount");
		Validation.validateAmount(amount);
		String description = UtilityHelper.getString(customerJson, "Description");
		Validation.description(description);
		long tId = System.currentTimeMillis();
		synchronized (LockSupplier.getLock(accountNumber)) {
			long balanceAmount = getBalance(customerJson);
			balanceCheck(balanceAmount, amount);
			TransactionHistory history = historyPojo("debit", -amount, tId, accountNumber, description, balanceAmount - amount,null);
			setTime(tId);
			customer.creditDebitOutBank(history);
		}
		LogAgent.log(-amount+"",OperationType.debit);
	}

	public void credit(JSONObject customerJson) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(customerJson);
		long accountNumber = UtilityHelper.getLong(customerJson, "AccountNumber");
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		resolveAccountStatus(accountNumber);
		long tId = System.currentTimeMillis();
		long amount = UtilityHelper.getLong(customerJson, "Amount");
		Validation.validateAmount(amount);
		String description = UtilityHelper.getString(customerJson, "Description");
		Validation.description(description);
		synchronized (LockSupplier.getLock(accountNumber)) {
			long balanceAmount = getBalance(customerJson);
			TransactionHistory history = historyPojo("credit", amount, tId, accountNumber, description, balanceAmount + amount,null);
			setTime(tId);
			customer.creditDebitOutBank(history);
		}
		LogAgent.log(amount+"",OperationType.credit);
	}

	public void moneyTransfer(JSONObject customerJson) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(customerJson);
		long accountNumber = UtilityHelper.getLong(customerJson, "AccountNumber");
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		resolveAccountStatus(accountNumber);
		long amount = UtilityHelper.getLong(customerJson, "Amount");
		Validation.validateAmount(amount);
		long trasactionAccountNumber = UtilityHelper.getLong(customerJson, "TransactionAccountNumber");
		Validation.validateAccountNumber(trasactionAccountNumber);
		String description = UtilityHelper.getString(customerJson, "Description");
		Validation.description(description);
		String ifscCode = UtilityHelper.getString(customerJson, "IfscCode");
		Validation.validateIfsc(ifscCode);
		boolean inBank = resolveTransaction(accountNumber, trasactionAccountNumber, ifscCode);
		synchronized (LockSupplier.getLock(accountNumber)) {
			long balanceAmount = getBalance(customerJson);
			balanceCheck(balanceAmount, amount);
			if (!inBank) {
				long tId = System.currentTimeMillis();
				TransactionHistory history = historyPojo("OBMoneyTransfer", -amount, tId, accountNumber, description,
						balanceAmount - amount, null);
				setTime(tId);
				customer.creditDebitOutBank(history);
				LogAgent.log(-amount+"",OperationType.obmoneyTransfer);
			} 
			else {
				checkAccNoForPresence(trasactionAccountNumber);
				inBankTransfer(accountNumber, trasactionAccountNumber, amount, description);
			}
		}
	}	

	public JSONArray transactionHistory(long accountNumber,int quantity,int page,long searchMilli) throws BankException, InputDefectException {
		checkAccNoForPresence(accountNumber);
		JSONArray jArray=customer.getTransactionHistory(accountNumber,quantity ,page,searchMilli);
		if (jArray.length() == 0) {
			throw new BankException("NO transacations made");
		}
		return jArray;
	}
	
	public JSONArray transactionHistory(long accountNumber,int quantity,int page) throws BankException, InputDefectException {
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		UtilityHelper.checkNegative(quantity);
		UtilityHelper.checkNegative(page);
		JSONArray jArray=customer.getTransactionHistory(accountNumber,quantity ,page);
		if (jArray.length() == 0) {
			throw new BankException("NO transacations made");
		}
		return jArray;
	}
	
	public int getPages(long accountNumber,int quantity) throws BankException, InputDefectException {
		Validation.validateAccountNumber(accountNumber);
		UtilityHelper.checkNegative(quantity);
		checkAccNoForPresence(accountNumber);
		return customer.pageCount(accountNumber, quantity);
	}
	
	public JSONObject viewProfile(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkIdUserPresence(id);
		return customer.viewProfile(id);
	}
	
	public int getPages(JSONObject customerJson,int quantity,long searchMilli) throws BankException, InputDefectException {
		long accountNumber=UtilityHelper.getLong(customerJson, "AccountNumber");
		Validation.validateAccountNumber(accountNumber);
		UtilityHelper.checkNegative(quantity);
		checkAccNoForPresence(accountNumber);
		return customer.pageCount(accountNumber, quantity,searchMilli);
	}
	
	public long daystomilly(int day) {
		return (day*86400000l);
		
	}
	
	public long searchRegion(long day) {
		return (System.currentTimeMillis()-day);
	}
	
	public void logout() {
		Authenticator.user.remove();
	}
	
	public JSONObject getPrimaryAccount(long id) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		checkIdCustomerPresence(id);
		JSONObject json= customer.getPrimaryAccount(id);
		if(json==null) {
			tagPrimaryAccount(id);
			getPrimaryAccount(id);
			return customer.getPrimaryAccount(id);
		}
		return json;
	}
	
	public void setPrimaryAccount(long accountNumber) throws BankException, InputDefectException {
		Validation.validateUserId(accountNumber);
		checkAccNoForPresence(accountNumber);
		customer.setPrimaryAccount(accountNumber);
		setTime();
		LogAgent.log("PrimaryAccount :"+accountNumber,OperationType.switchPrimary);
	}
	
	public void switchPrimaryAccount(long accountNumber,long id) throws BankException, InputDefectException {
		Validation.validateAccountNumber(accountNumber);
		Validation.validateUserId(id);
		checkIdCustomerPresence(id);
		checkAccNoForPresence(accountNumber);
		JSONObject primaryJson= getPrimaryAccount(id);
		if(primaryJson==null) {
			setPrimaryAccount(accountNumber);
		}
		else {
			removePrimaryAccount(primaryJson);
			setPrimaryAccount(accountNumber);
		}
	}
	
	public JSONObject getAccountDetail(long accountNumber) throws BankException, InputDefectException {
		Validation.validateAccountNumber(accountNumber);
		checkAccNoForPresence(accountNumber);
		return customer.getAccountDetails(accountNumber);
	
	}
	
	public void checkUserAndAccount(long id,long accountNumber) throws BankException, InputDefectException {
		Validation.validateUserId(id);
		Validation.validateAccountNumber(accountNumber);
		checkIdUserPresence(id);
		checkAccNoForPresence(accountNumber);
		long accounts[]= getAccounts(id);
		boolean accountPresence=false;
		for(int i=0;i<accounts.length;i++) {
			if(accounts[i]==accountNumber) {
				accountPresence=true;
			}
		}
		if(!accountPresence) {
			System.err.println("errorhit");
			throw new BankException("Account and user Miss match");
		}
		
	}
	
	
	private void removePrimaryAccount(JSONObject customerJson) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(customerJson);
		long accountNumber=UtilityHelper.getLong(customerJson, "AccountNumber");
		customer.removePrimaryAccount(accountNumber);
	}
	
	protected void tagPrimaryAccount(long id) throws BankException, InputDefectException {
				long[] accountArray =getAccounts(id);
				setPrimaryAccount(accountArray[0]);
				statusCheck(accountArray[0]);
	}
	
	private void statusCheck(long accountNumber) throws BankException, InputDefectException  {
		String status = accountStatus(accountNumber);
		switch (status) {
		case "inactive":
			throw new BankException("This account is inactive ");
		case "deleted":
			throw new BankException("This account is deleted");
		}
	}
	
	


	// support methods
	protected void balanceCheck(long balanceAmount,long amount) throws BankException {
		if (balanceAmount < amount) {
			throw new BankException("Insuffecient balance");
		}
	}
		
	
	protected TransactionHistory historyPojo(String type, long amount, long transactionId, long accountNumber,
			String description, long balance, Long TransactionAccountNumber) {
		TransactionHistory history = new TransactionHistory();
		history.setTransactionType(TransactionType.valueOf(type));
		history.setTransactionAmount(amount);
		history.setTransactionId(transactionId);
		history.setAccountNumber(accountNumber);
		history.setDescription(description);
		history.setBalance(balance);
		if(TransactionAccountNumber!=null) {
		history.setTransactionAccountNumber(TransactionAccountNumber);
		}
		history.setModifiedBy(Authenticator.user.get().getId());
		history.setCreatedTime(System.currentTimeMillis());
		return history;	
	}

	private boolean checkInBank(String ifscCode) {
		return ifscCode.substring(0, 3).equals("rey");
	}
	
	
	 
	protected boolean resolveTransaction(long accountNumber,long trasactionAccountNumber ,String ifscCode) throws BankException {
		if (accountNumber == trasactionAccountNumber) {
			throw new BankException("money cannot be transfered withiin the same account");
		}
		return checkInBank(ifscCode);
	}

	protected void resolveAccountStatus(long accountNumber) throws BankException, InputDefectException {
		String status = accountStatus(accountNumber);
		switch (status) {
		case "inactive":
			throw new BankException("your account is blocked");
		case "deleted":
			throw new BankException("your account is deleted");
		}
	}

	protected long[] jArrayToArray(JSONArray jArray) throws BankException, InputDefectException {
		UtilityHelper.nullCheck(jArray);
		int size = jArray.length();
		long[] array = new long[size];
		for (int iterator = 0; iterator < size; iterator++) {
			JSONObject json = UtilityHelper.getJsonObject(jArray, iterator);
			array[iterator] = UtilityHelper.getLong(json, "AccountNumber");
		}
		return array;
	}
	
	
	protected void inBankTransfer(long accountNumber, long trasactionAccountNumber, long amount, String description)
			throws BankException, InputDefectException {

		String status = accountStatus(trasactionAccountNumber);
		long tId = System.currentTimeMillis();
		switch (status) {
		case "active":
			long balanceAmount = getBalance(accountNumber);
			long tBalanceAmount = getBalance(trasactionAccountNumber);
			setTime(tId);
			TransactionHistory historySender = historyPojo("moneyTransfer", -amount, tId, accountNumber, description,
					balanceAmount - amount, trasactionAccountNumber);
			TransactionHistory historyReceiver = historyPojo("moneyTransfer", amount, tId, trasactionAccountNumber, description,
					tBalanceAmount + amount, accountNumber);
			customer.inBank(historySender, historyReceiver);
			LogAgent.log(-amount+"",OperationType.obmoneyTransfer);
			break;
		case "inactive":
			throw new BankException("your reciptant account is blocked");
		case "deleted":
			throw new BankException("your reciptant account is deleted");
		}
	}
	
	//util
	
	protected void setTime() {
		Authenticator.user.get().setTime(System.currentTimeMillis());
	}
	
	protected void setTime(long time) {
		Authenticator.user.get().setTime(time);
	}


	// check methods
	
	protected void checkIdUserPresence(long id) throws BankException, InputDefectException {
		customer.checkUserPresence(id, "Id");
	}

	protected void checkIdUserAbsence(long id) throws BankException, InputDefectException {
		customer.checkUserAbsence(id,"Id");
	}

	protected void checkIdCustomerAbsence(long id) throws BankException, InputDefectException {
		customer.checkCustomerAbsence(id,"Id");
	}

	public void checkIdCustomerPresence(long id) throws BankException, InputDefectException {
		customer.checkCustomerPresence(id,"Id");
	}

	protected void checkAccNoForAbsence(long accountNumber) throws BankException, InputDefectException {
		customer.checkAccountAbsence(accountNumber, "AccountNumber");
	}

	protected void checkAccNoForPresence(long accountNumber) throws BankException, InputDefectException {
		customer.checkAccountPresence(accountNumber,"AccountNumber");
	}
}
