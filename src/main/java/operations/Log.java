package operations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import bank.Authenticator;
import bank.OperationType;
import database.CustomerService;
import pojo.LogData;
import pojo.UserData;
import utility.BankException;
import utility.InputDefectException;

public class Log  {
	
	private ExecutorService executor= Executors.newFixedThreadPool(7);
	
	public void log(String description,OperationType opType) throws BankException, InputDefectException {
	 	 UserData userData= Authenticator.user.get();
	 	 LogData logData=new LogData();
	 	 logData.setDescription(description);
	 	 logData.setOperationType(opType);
	 	 logData.setTime(userData.getTime());
	 	 logData.setUserId(userData.getId());
	 	 logData.setTargetUser(userData.getActiveId());
	 	 Runnable runnable= logTask(logData);
	 	 executor.submit(runnable);
	}

	private Runnable logTask(LogData logData) {
		return ()-> {
		
				try {
					CustomerService.getCustomerService().logActivity(logData);
				} catch (BankException | InputDefectException e) {
					e.printStackTrace();
				}	
		};
	}
}
