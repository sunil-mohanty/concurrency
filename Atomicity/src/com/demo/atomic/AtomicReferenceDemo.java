package com.demo.atomic;

import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

public class AtomicReferenceDemo {

	public static void main(String[] args) {
		String initialReference = "initial value referenced";

		AtomicReference<String> atomicStringReference =
		    new AtomicReference<String>(initialReference);

		String newReference = "new value referenced";
		boolean exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
		System.out.println("exchanged: " + exchanged);

		exchanged = atomicStringReference.compareAndSet(initialReference, newReference);
		System.out.println("exchanged: " + exchanged);

		String result1 = atomicStringReference.get();
		System.out.println(atomicStringReference.get()); 
		
		Student student1 = new Student(198 , "sunil");
		Student student2 = new Student(225 , "deb");

		AtomicReference atomicStudent1 = new AtomicReference<Student>(student1);
		AtomicReference atomicStudent2 = new AtomicReference<Student>(student2);
		
		atomicStudent1.compareAndSet(student2, student2);
		
		Student result = (Student) atomicStudent1.get();
		System.out.println(result.getName());

		// Stamped reference
		String initialRef   = "text";
		int initialStamp = 2;

		AtomicStampedReference atomicStampedReference =
		    new AtomicStampedReference(
		        initialRef, initialStamp
		    );

		int[] stampHolder = new int[1];
		Object ref = atomicStampedReference.get(stampHolder);

		System.out.println("ref = " + ref);
		System.out.println("stamp = " + stampHolder[0]);
		
	}
}

class Student{
	int roll;
	String name;
	
	public Student(int roll, String name){
		this.roll  = roll;
		this.name = name;
	}
	public int getRoll() {
		return roll;
	}
	public void setRoll(int roll) {
		this.roll = roll;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}