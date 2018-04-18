
/// <summary>
/// Author : phuongtu
/// Created Date : 19/08/2009
/// </summary>

package com.venus.mc.bean;

public class BidOpeningReportBean {		
	
	//fields region
	
	private int borId; // primary key
	private int tenId; // foreign key : reference to tender_plan(ten_id)
	private String reportNumber;
	private String createdDate;
	private String startDate;
	private String startTime;
	private String endDate;
	private String endTime;
	private String comments;
	
	//constructure region
	
	public BidOpeningReportBean(){		
	}
	
	public BidOpeningReportBean(int borId){
		this.borId = borId;
	}
	
	public BidOpeningReportBean( int borId, String reportNumber, String createdDate, String startDate, String startTime, String endDate, String endTime, String comments){		
		this.borId = borId;
		this.reportNumber = reportNumber;
		this.createdDate = createdDate;
		this.startDate = startDate;
		this.startTime = startTime;
		this.endDate = endDate;
		this.endTime = endTime;
		this.comments = comments;
	}
	
	//properties region
		
	public int getBorId(){	
		return this.borId;
	}
	
	public void setBorId(int borId){	
		this.borId = borId;
	}		
		
	public int getTenId(){	
		return this.tenId;
	}
	
	public void setTenId(int tenId){	
		this.tenId = tenId;
	}		
		
	public String getReportNumber(){	
		return this.reportNumber;
	}
	
	public void setReportNumber(String reportNumber){	
		this.reportNumber = reportNumber;
	}		
		
	public String getCreatedDate(){	
		return this.createdDate;
	}
	
	public void setCreatedDate(String createdDate){	
		this.createdDate = createdDate;
	}		
		
	public String getStartDate(){	
		return this.startDate;
	}
	
	public void setStartDate(String startDate){	
		this.startDate = startDate;
	}		
		
	public String getStartTime(){	
		return this.startTime;
	}
	
	public void setStartTime(String startTime){	
		this.startTime = startTime;
	}		
		
	public String getEndDate(){	
		return this.endDate;
	}
	
	public void setEndDate(String endDate){	
		this.endDate = endDate;
	}		
		
	public String getEndTime(){	
		return this.endTime;
	}
	
	public void setEndTime(String endTime){	
		this.endTime = endTime;
	}		
		
	public String getComments(){	
		return this.comments;
	}
	
	public void setComments(String comments){	
		this.comments = comments;
	}		
}
