package pojo;

public interface BankMarker {
 //this is a marker interface
	
	public Long getCreatedTime();
	public void setCreatedTime(Long createdTime) ;
	public Long getRecentModifiedTime() ;
	public void setRecentModifiedTime(Long recentModifiedTime) ;
	public long getModifiedBy();
	public void setModifiedBy(long modifiedBy);
}
