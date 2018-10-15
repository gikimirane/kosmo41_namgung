package com.study.spring;

import org.springframework.stereotype.Component;

@Component("printerB")
public class PrinterB implements Printer {
	public void print(String message) {
		System.out.println("Printer B : "+message);
	}
}
