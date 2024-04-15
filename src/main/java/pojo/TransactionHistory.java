package pojo;

import bank.TransactionType;

public class TransactionHistory implements BankMarker{
	private Long transactionId;
	private Long accountNumber;
	private Long transactionAccountNumber;
	private TransactionType transactionType;
	private Long transactionAmount;
	private Long balance;
	private String description;
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
	
	public long getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(long transactionId) {
		this.transactionId = transactionId;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public long getTransactionAccountNumber() {
		return transactionAccountNumber;
	}
	public void setTransactionAccountNumber(long transactionAccountNumber) {
		this.transactionAccountNumber = transactionAccountNumber;
	}
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
		this.transactionType = transactionType;
	}
	public long getTransactionAmount() {
		return transactionAmount;
	}
	public void setTransactionAmount(long transactionAmount) {
		this.transactionAmount = transactionAmount;
	}
	public long getBalance() {
		return balance;
	}
	public void setBalance(long balance) {
		this.balance = balance;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
