package bank;

import org.json.JSONObject;
import database.AuthendicatorServiceInterface;
import utility.BankException;
import utility.InputDefectException;
import utility.UtilityHelper;

public class Authenticator {
	
	AuthendicatorServiceInterface auth=ServiceFactory.getAuthendicatorService();

	public String getAuthority(long id) throws BankException   { 
		return auth.getAuthority(id);
	}
	
	public void validateUser(long id) throws BankException{
		JSONObject resultJson = auth.getUserStatus(id);
		if(UtilityHelper.getString(resultJson, "Status").equals("inactive")) {
			throw new BankException("user blocked contact bank");
		}
	}

	public boolean checkPassword(long userId, String password) throws InputDefectException {
		password = UtilityHelper.passHasher(password);
		try {
		String originalPassword = getPassword(userId);
		return password.equals(originalPassword);
		}catch (BankException e) {
			return false;
		}
	}

	@SuppressWarnings("unused")
	private boolean attemptCheck(long id) throws BankException {
		int attempt = getAttempts(id);
		if (attempt >= 3) {
			throw new BankException("You cannot access this account \n contact bank");
		}
		return true;
	}

	@SuppressWarnings("unused")
	private void attemptUpdate(long id) throws BankException  {
		int attempt = getAttempts(id);
		attempt++;
		auth.attemptUpdate(attempt, id);
	}

	private int getAttempts(long id) throws BankException {
		JSONObject json = auth.getAttempts(id);
		return UtilityHelper.getInt(json, "Attempts");
	}

	private String getPassword(long userId) throws BankException { 
		JSONObject json= auth.getPassword(userId);
		if (json == null) {
			throw new BankException("wrong combination");
		}
		return UtilityHelper.getString(json,"Password");
	}

	public static ThreadLocal<Long> id = new ThreadLocal<Long>();
	public static ThreadLocal<Long> accountNumber = new ThreadLocal<Long>();

	public static void idTag(long userId) {
		id.set(userId);
	}

	public static void accountTag(long accountNum) {
		accountNumber.set(accountNum);
	}

}
