package com.laomu.graduate.utils;

public class JavaTest2 extends JavaTest {

	String color = "son";
	
	@Override
	void print() {
		// TODO Auto-generated method stub
		super.print();
	}
	
	void print2(){
		System.out.println(super.color);
	}
	public static void main(String[] args) {
		JavaTest2 son = new JavaTest2();
		son.print();
		System.out.println(son.color);

		son.print2();
	}
}
