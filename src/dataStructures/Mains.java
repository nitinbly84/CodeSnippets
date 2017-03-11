package dataStructures;

public class Mains {
	static {
		System.out.println("In static block!!!!");
		some();
	}
	
	{
		System.out.println("In normal block!!!");
	}
	
	public static void main(String[] args) throws ClassNotFoundException {
		new Mains();
		new Mains();
		Class.forName("dataStructures.Test");
		new Test();
	}
	
	public static void some() {
		System.out.println("Hello");
	}

}

class Test {
	
	static {
		System.out.println("In static bock of class Test");
	}

	{
		System.out.println("In normal block of class Test!!!");
	}
}
