package pojo;

import bank.OperationType;

public class LogData implements BankMarker{

	private Long logId;
	private long userId;
	private long targetUser;
	private OperationType operationType;
	private String description;
	private long time;

	
	
	public long getLogId() {
		return logId;
	}
	public void setLogId(long logId) {
		this.logId = logId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public long getTargetUser() {
		return targetUser;
	}
	public void setTargetUser(long targetUser) {
		this.targetUser = targetUser;
	}
	public OperationType getOperationType() {
		return operationType;
	}
	public void setOperationType(OperationType operationType) {
		this.operationType = operationType;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	
}
