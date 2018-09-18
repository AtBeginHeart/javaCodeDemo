package jasontestDemo;

import test.demo.ChildClass;
import test.demo.ParentClass;

public class Inherance {

	public Inherance() {
		// TODO Auto-generated constructor stub
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ChildClass clazz = new ChildClass();
		ChildClass.staticMethod();
		clazz.method();

	}
	static class ParentClass {

		public ParentClass() {
			System.out.println("A");
		}
		public static void staticMethod() {
			System.out.println("B");
		}
		public void method() {
			System.out.println("C");
		}
	}
	
	static class ChildClass extends ParentClass{
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

}
