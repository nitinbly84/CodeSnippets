package dataStructures;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ReflectionClass {
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, NoSuchFieldException {
		Class<Sample> cl = Sample.class;
		
		Constructor<Sample> ct = cl.getDeclaredConstructor(new Class[0]);
		Field fl = cl.getDeclaredField("sam");
		Field fl1 = cl.getDeclaredField("sam1");
		Method[] met = cl.getDeclaredMethods();
		met[0].setAccessible(true);
		met[1].setAccessible(true);
		fl.setAccessible(true);
		fl1.setAccessible(true);
		fl1.set(fl1, "Changed");
		ct.setAccessible(true);
		System.out.println(ct.getModifiers());
		Sample sam = ct.newInstance();
		
		System.out.println(fl.get(fl));
		System.out.println(fl1.get(fl1));
		// For instance method first argument must be class instance 
		System.out.println(met[0].invoke(sam));
		// For static method arguments are ignored
		System.out.println(met[1].invoke(cl));
	}

}
