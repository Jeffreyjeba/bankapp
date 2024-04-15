package pojo;

import bank.AccountStatus;
import bank.Priority;

public class Accounts implements BankMarker {
	private Long accountNumber;
	private Long id;
	private Integer branchId;
	private Long balance;
	private AccountStatus status;
	private Priority priority;
	private Long createdTime;
	private Long recentModifiedTime;
	private long modifiedBy;
	
	
	public Long getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Long createdTime) {
		this.createdTime = createdTime;
	}
	public Long getRecentModifiedTime() {
		return recentModifiedTime;
	}
	public void setRecentModifiedTime(Long recentModifiedTime) {
		this.recentModifiedTime = recentModifiedTime;
	}
	public long getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(long modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public AccountStatus getStatus() {
		return status;
	}
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	public Priority getPriority() {
		return priority;
	}
	public void setPriority(Priority priority) {
		this.priority = priority;
	}
	
}
