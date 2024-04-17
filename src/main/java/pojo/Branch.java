package pojo;

public class Branch implements BankMarker,LogMethods {
	private Integer branchId;
	private String ifscCode;
	private String branchName;
	private String address;
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
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public String getIfscCode() {
		return ifscCode;
	}
	public void setIfscCode(String ifscCode) {
		this.ifscCode = ifscCode;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
