package algorithms;

/**
 * @author Nitin
 *
 */
public class Singletons {

	public static void main(String[] args) {
		
		Singleton1 sing11 = Singleton1.getSingleton1();
		Singleton1 sing12 = Singleton1.getSingleton1();
		sing11.name = "Nitin";
		System.out.println(sing12.name);
		Singleton7 enum11 = Singleton7.INSTANCE;
		Singleton7 enum12 = Singleton7.INSTANCE;
		enum11.name = "Nitin";
		System.out.println(enum12.name);
	}

}

/**
 * Eager initialization of instance as soon as the class is loaded.
 * Issue is that instance will be created even if it is not required
 * at the time of loading or may be required later.
 * @author Nitin
 *
 */
class Singleton1 {
	public String name;

	private static Singleton1 sing = new Singleton1();

	private Singleton1() {
	}

	public static Singleton1 getSingleton1() {
		return sing;
	}
}

/**
 * Lazy initialization as instance will be created only when the method for it is called.
 * It is not suggested to use in multithreaded environment.
 * @author Nitin
 *
 */
class Singleton2 {
	public String name;

	private static Singleton2 sing;

	private Singleton2() {
	}

	public static Singleton2 getSingleton2() {
		if(sing == null) {
			sing = new Singleton2();
		}
		return sing;
	}
}


/**
 * It is also eager initialization of the instance as it is also being created in static block.
 * It can be used when exception handling is required while instance creation.
 * @author Nitin
 *
 */
class Singleton3 {
	public String name;

	private static Singleton3 sing;

	static {
		try {
			sing = new Singleton3();
		} catch (Exception e) {
			System.out.println("Failed during the instance creation of Singleton3...");
		}
	}

	private Singleton3() {
	}

	public static Singleton3 getSingleton3() {
		return sing;
	}
}



/**
 * Below way can be used when eager initialization is not required & multithreading
 * environment is also present. But it is not performance efficient as whole method is locked.
 * @author Nitin
 *
 */
class Singleton4 {
	public String name;

	private static Singleton4 sing = null;

	private Singleton4() {
	}

	public static synchronized Singleton4 getSingleton4() {
		if(sing == null)
			sing = new Singleton4();
		return sing;
	}
}


/**
 * Below way given by Bill Pugh 
 * When the Singleton7 class is loaded, Inner class is not loaded into memory and only when someone calls
 * the getSingleton7 method, this class gets loaded and creates the Singleton7 class instance.
 * So it provides Lazy loading flavor plus also works in multithreaded environment.
 * @author Nitin
 *
 */
class Singleton5 {
	public String name;

	private Singleton5() {
	}

	private static class Inner {
		private static final Singleton5 sing = new Singleton5();
	}

	public static Singleton5 getSingleton5() {
		return Inner.sing;
	}
}


/**
 * To avoid this extra overhead every time as method level synchronization,
 * double checked locking principle is used. In this approach, the synchronized
 * block is used inside the if condition with an additional check to ensure that
 * only one instance of singleton class is created.
 * @author Nitin
 *
 */
class Singleton6 {
	public String name;

	private static Singleton6 sing = null;

	private Singleton6() {
	}

	public static Singleton6 getSingleton6() {
		if(sing == null) {
			synchronized(Singleton6.class) {
				if(sing == null) {
					sing = new Singleton6();
				}
			}
		}
		return sing;
	}
}

/*
 * There is one more way to create the Singleton classes & retain their Singleton property
 * And that way is to override readResolve & writeReplace methods, and this will be used
 * when we are doing serialization/deserialization of the Singleton classes or sending
 * Singleton objects across the network
 */

/**
 * To overcome the situation with Reflection where Singleton can be initialized multiple times,
 * Joshua Bloch suggests the use of Enum to implement Singleton design pattern as Java ensures
 * that any enum value is instantiated only once in a Java program. Since Java Enum values are
 * globally accessible, so is the singleton. The drawback is that the enum type is somewhat inflexible;
 * for example, it does not allow lazy initialization.
 * @author Nitin
 *
 */
enum Singleton7 {
	INSTANCE;
	
	public String name;
	
	private Singleton7() {
		
	}
}
