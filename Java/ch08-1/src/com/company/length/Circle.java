package com.company.length;

public class Circle {
	
	double rad;
	final double PI;
	
	public Circle(double r) {
		rad = r;
		PI = 3.14;
	}
	
	public double getPerameter() {
		return (rad *2) * PI;
	}

}
