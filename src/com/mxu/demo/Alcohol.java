package com.mxu.demo;

class Water{
	
	private int index = 0;
	
	public int count = 0;
	
	private Water(){}
	
	static Water getWater() {
		System.out.println("getWater");
		return new Water();
	}
}

public class Alcohol {
	
	public static void main(String[] args) {
		//Water water = new Water();//The constructor Water() is not visible

		Water water = Water.getWater();
		//water.index = 2;//The field Water.index is not visible
		water.count = 1;
		
	}

}
