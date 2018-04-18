
/// <summary>
/// Author : phuongtu
/// Created Date : 11/07/2009
/// </summary>

package com.venus.mc.bean;


public class PositionBean {		
	
	//fields region
	
	private int posId; // primary key
	private String name;
	
	//constructure region
	
	public PositionBean(){		
	}
	
	public PositionBean(int posId){
		this.posId = posId;
	}
	
	public PositionBean( int posId, String name){		
		this.posId = posId;
		this.name = name;
	}
	
	//properties region
		
	public int getPosId(){	
		return this.posId;
	}
	
	public void setPosId(int posId){	
		this.posId = posId;
	}		
		
	public String getName(){	
		return this.name;
	}
	
	public void setName(String name){	
		this.name = name;
	}		
}
