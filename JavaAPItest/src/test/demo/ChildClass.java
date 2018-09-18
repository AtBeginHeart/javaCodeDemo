package test.demo;


public class ChildClass extends ParentClass{
	public ChildClass() {
		System.out.println("X");
	}
	public static void staticMethod() {
		System.out.println("Y");
	}
	
	public void method() {
		System.out.println("Z");
	}

}
