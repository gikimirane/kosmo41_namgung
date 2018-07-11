package com.study.pattern01.singleton;
//데이터의 공유를 위해 주로 사용됨..(아직 모르게따!)
public class Main {
	public static void main(String[] args) {
//		MySingletonClass sc1 = new MySingletonClass(); 어차피 못만듬 private라서..
		
		ClassOne class1 = new ClassOne();
		ClassTwo class2 = new ClassTwo();
	}
}
