package SimpleFamilyTree;

public class Generator {

	/**
	 * Constructs the Family Tree used by the model.
	 */
	public static Person getGenealogyGraph() {
		
		// the greatgrandparent generation
		Person a1 = new Person("Ranjit Singhaniya (great-grandfather)");
		Person a2 = new Person("Suchitra Singhaniya (great-grandmother)");

		// the grandparent generation
		Person b1 = new Person("Sudhir Singhaniya (grandfather)");
		Person b2 = new Person("Sujata Singhaniya (grandmother)");

		// the parent generation
		Person c1 = new Person("Vaishali Singhaniya (Aunt)");
		Person c2 = new Person("Vishva Singhaniya (Father)");
		Person c3 = new Person("Ranjita Singhaniya (Mother)");
		Person c4 = new Person("Rishi Kapoor (Friend)");
		Person c5 = new Person("Reshu Singh (Friend)");

		// the youngest generation
		Person d1 = new Person("Sumit Singhaniya (Son)");
		Person d2 = new Person("Amit Singhaniya (Son)");

		Person.linkFamily(a1, a1, new Person[] {a2, b1, b2});
		Person.linkFamily(b1, b1, new Person[] {b2, c1, c2});
		Person.linkFamily(c2, c2, new Person[] {c4, c3, d1, d2});
		Person.linkFamily(c1, c1, new Person[] {c5});

		return a1;
	}

}
