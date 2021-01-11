package com.mxu.demo;

class Fruit {
	private String name;

	protected void setName(String fruitName) {
		this.name = fruitName;
	}

	Fruit(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return " 我是一个水果，我的名称是：" + name;
	}

}

public class Apple extends Fruit {

	private int number;

	public Apple(String name, int fruitNum) {
		super(name);
		this.number = fruitNum;
	}

	public void changeNum(String name, int fruitNum) {
		setName(name);
		this.number = fruitNum;
	}

	@Override
	public String toString() {
		return "序号：" + number + ": " + super.toString();
	}

	public static void main(String[] args) {
		Apple fruit = new Apple("Apple", 1);
		System.out.println(fruit);
		fruit.changeNum("Peach", 2);
		System.out.println(fruit);
	}
	
}
