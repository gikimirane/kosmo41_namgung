package com.study.spring;

public class PrinterB implements Printer {
	public void print(String message) {
		System.out.println("Printer B : "+message);
	}
}
