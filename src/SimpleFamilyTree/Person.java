package SimpleFamilyTree;

import java.util.Vector;

public class Person {
	Person father;
	Person mother;
	Vector<Person> children;
	private String name;

	public Person(String name) {
		this.name = name;
		mother = father = null;
		children = new Vector<Person>();
	}

	/**
	 * Link together all members of a family.
	 * 
	 * @param father 
	 * @param mother
	 * @param children
	 */
	public static void linkFamily(Person father, Person mother, Person[] children) {
		for (Person kid : children) {
			father.children.addElement(kid);
			mother.children.addElement(kid);
			kid.father = father;
			kid.mother = mother;
		}
	}

	public String toString() {
		return name;
	}

	public String getName() {
		return name;
	}

	public Person getFather() {
		return father;
	}

	public Person getMother() {
		return mother;
	}

	public int getChildCount() {
		return children.size();
	}

	public Person getChildAt(int i) {
		return (Person) children.elementAt(i);
	}

	public int getIndexOfChild(Person kid) {
		return children.indexOf(kid);
	}
}