package com.demo.forkjoinpool;

import java.io.File;

public class FindAllFileSizeInDir {

	public static void main(String[] args) {
		SizeOfFileTask sizeOfTask = new SizeOfFileTask(new File("demo"));
		System.out.println(sizeOfTask.getSize());
	}
}
