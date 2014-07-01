package com.example.poedemo.sort;

public class food implements Comparable{

	private String name;
	private String type;//酒水、主食、冷菜
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public int compareTo(Object arg0) {
		// TODO Auto-generated method stub
		food f = (food) arg0;
		return this.type.compareTo(f.getType());
	}
	
}
