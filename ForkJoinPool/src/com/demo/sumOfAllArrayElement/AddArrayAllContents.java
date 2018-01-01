package com.demo.sumOfAllArrayElement;

import java.util.Arrays;

public class AddArrayAllContents {
	public static void main(String[] args) {
		int[] arr = new int [] {
			1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18	
		};
		
		System.out.println("using normal additipon result = " + Arrays.stream(arr).sum());
		
		ContentAdder addContents = new ContentAdder(arr);
		int totalResult = addContents.calculateArrayConetntSum();
		
		System.out.println("With fork join pool result = " + totalResult);
	}
}
