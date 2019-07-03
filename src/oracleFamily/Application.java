package oracleFamily;

import java.util.List;

public class Application {

	private static MainExecutor me = new MainExecutor();

	public static void main(String[] args) {

		String familyId = me.createFamilyTree("Singhaniya");
		String personId = me.addPerson("Ranjit Singhaniya");
		me.createRelationship(RelationshipType.SELF, personId, null, familyId);
		populateTree(familyId, personId);
		// Reads the descendants of the given person.
		List<String> data = me.readDescendants("P3");
//		data.forEach(a -> System.out.println(a));
		// Reads the whole family tree for the given family ID
		data = me.readFamilyTree(familyId);
//				data.forEach(a -> System.out.println(a));
		// Reads the whole family tree for the given person.
		data = me.readPersonTree("P2");
				data.forEach(a -> System.out.println(a));
	}

	/**
	 * Populates the family tree having given familyID & root as the root
	 * person of this family tree. 
	 * @param familyId
	 * @param root
	 */
	public static void populateTree(String familyId, String root) {

		String mother = me.addPerson("Suchitra Kapoor");
		me.createRelationship(RelationshipType.SPOUSE, root, mother, familyId);
		me.updatePerson(mother, "Suchitra Singhaniya");
		String child1 = me.addPerson("Vaishali Singhaniya");
		me.createRelationship(RelationshipType.FATHER, root, child1, familyId);
		me.createRelationship(RelationshipType.MOTHER, mother, child1, familyId);
		String child2 = me.addPerson("Vishva Singhaniya");
		me.createRelationship(RelationshipType.FATHER, root, child2, familyId);
		me.createRelationship(RelationshipType.MOTHER, mother, child2, familyId);
		String spouse = me.addPerson("Ranjita Singhaniya");
		me.createRelationship(RelationshipType.SPOUSE, child2, spouse, familyId);
		String friend = me.addPerson("Rishi Kapoor");
		me.createRelationship(RelationshipType.OTHER, child2, friend, familyId);
		String friendChild = me.addPerson("Sanket Kapoor");
		me.createRelationship(RelationshipType.FATHER, friend, friendChild, familyId);
		String other = me.addPerson("Reshu Singh");
		me.createRelationship(RelationshipType.FRIEND, child1, other, familyId);
		String grandC = me.addPerson("Sumit Singhaniya");
		me.createRelationship(RelationshipType.FATHER, child2, grandC, familyId);
		me.createRelationship(RelationshipType.MOTHER, spouse, grandC, familyId);
		grandC = me.addPerson("Amit Singhaniya");
		me.createRelationship(RelationshipType.FATHER, child2, grandC, familyId);
		me.createRelationship(RelationshipType.MOTHER, spouse, grandC, familyId);
	}

}
