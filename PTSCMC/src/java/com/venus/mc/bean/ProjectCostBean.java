
/// <summary>
/// Author : phuongtu
/// Created Date : 24/09/2009
/// </summary>

package com.venus.mc.bean;


public class ProjectCostBean {		
	
	//fields region
	
	private int pcId; // primary key
	private int proId; // foreign key : reference to project(pro_id)
	private int conId; // foreign key : reference to contract(con_id)
	private double cost;
	
	//constructure region
	
	public ProjectCostBean(){		
	}
	
	public ProjectCostBean(int pcId){
		this.pcId = pcId;
	}
	
	public ProjectCostBean( int pcId, double cost){		
		this.pcId = pcId;
		this.cost = cost;
	}
	
	//properties region
		
	public int getPcId(){	
		return this.pcId;
	}
	
	public void setPcId(int pcId){	
		this.pcId = pcId;
	}		
		
	public int getProId(){	
		return this.proId;
	}
	
	public void setProId(int proId){	
		this.proId = proId;
	}		
		
	public int getConId(){	
		return this.conId;
	}
	
	public void setConId(int conId){	
		this.conId = conId;
	}		
		
	public double getCost(){	
		return this.cost;
	}
	
	public void setCost(double cost){	
		this.cost = cost;
	}		
}
