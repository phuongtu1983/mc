
/// <summary>
/// Author : phuongtu
/// Created Date : 14/07/2009
/// </summary>

package com.venus.mc.bean;


public class GroupMaterialBean {		
	
	//fields region
	
	private int groId; // primary key
	private String name;
	private String note;
	
	//constructure region
	
	public GroupMaterialBean(){		
	}
	
	public GroupMaterialBean(int groId){
		this.groId = groId;
	}
	
	public GroupMaterialBean( int groId, String name, String note){		
		this.groId = groId;
		this.name = name;
		this.note = note;
	}
	
	//properties region
		
	public int getGroId(){	
		return this.groId;
	}
	
	public void setGroId(int groId){	
		this.groId = groId;
	}		
		
	public String getName(){	
		return this.name;
	}
	
	public void setName(String name){	
		this.name = name;
	}		
		
	public String getNote(){	
		return this.note;
	}
	
	public void setNote(String note){	
		this.note = note;
	}		
}
