package operations;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;
import java.util.logging.Logger;
import bank.Authenticator;
import bank.OperationType;
import database.CustomerService;
import pojo.LogData;
import pojo.UserData;
import utility.BankException;
import utility.InputDefectException;

public class LogAgent  {
	
	private static ExecutorService executor= Executors.newFixedThreadPool(10);
	private static Logger logger=Logger.getLogger("logAgent");
	
	public static void log(String description,OperationType opType) throws BankException, InputDefectException {
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

	private static Runnable logTask(LogData logData) {
		return ()-> {
				try {
					CustomerService.getCustomerService().logActivity(logData);
				}
				catch (BankException | InputDefectException e) {
					logger.log(Level.WARNING, "-",e);
				}	
		};
	}
}
