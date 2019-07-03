package oracleFamily;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * It is the entry class for activities on the family tree.
 *
 */
public class MainExecutor {

	private TreeBuilder ftb = new FamilyTreeBuilder();
	private TreeExplorer fte;
	String familyId;

	public String createFamilyTree(String familyName) {
		familyId = ftb.createFamily(familyName);
		try {
			fte = new FamilyTreeExplorer(familyId, ftb);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return familyId;
	}

	/**Adds the person with the given name to the system.
	 * @param personName
	 * @return - Person ID
	 */
	public String addPerson(String personName) {
		String personId = PeopleRepository.PEOPLE.createPerson(personName);
		return personId;
	}

	/**
	 * Updates the name of the given person.
	 * @param personId
	 * @param name
	 */
	public void updatePerson(String personId, String name) {
		PeopleRepository.PEOPLE.updatePerson(personId, name);
	}

	/**
	 * Creates the relationship in the given family for the given type & persons.
	 * @param rt
	 * @param parentId
	 * @param childId
	 * @param familyId
	 * @return - Relationship ID
	 */
	public String createRelationship(RelationshipType rt, String parentId, String childId, String familyId) {
		try {
			Relationship rs = ftb.createRelationship(rt, parentId, childId, familyId);
			if(rs != null)
				return rs.getId();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Reads all the persons connected to the family tree
	 * of the given person.
	 * @param personId
	 */
	public List<String> readPersonTree(String personId) {
		String root = fte.getRoots(personId);
		return readDescendants(root);
	}

	/**
	 * Reads all the persons connected to the given family.
	 * @param familyId
	 */
	public List<String> readFamilyTree(String familyId) {
		String personId = ftb.getFamilyPerson(familyId);
		String root = fte.getRoots(personId);
		return readDescendants(personId);
	}

	/**
	 * Reads all the descendant persons.
	 * @param personId
	 * @return
	 */
	public List<String> readDescendants(String personId) {
		List<String> people = new ArrayList<>();
		Queue<String> persons = new PriorityQueue<>();
		persons.add(personId);
		while(persons.peek() != null) {
			Person person = PeopleRepository.PEOPLE.getPerson(persons.poll());
			String temp = person.getId();
			fte.setStart(temp);
			people.add(person.getName());
			while(fte.hasNext()) {
				List member = fte.next();
				if(member.get(0).equals(RelationshipType.FRIEND) ||
						member.get(0).equals(RelationshipType.SPOUSE) ||
						member.get(0).equals(RelationshipType.OTHER) ||
						member.get(0).equals(RelationshipType.SIBLING)) {
					people.add(" " + ((Person)member.get(1)).getName() + "(" + member.get(0) + ")");
				}
				else if(member.get(0).equals(RelationshipType.FATHER) ||
						member.get(0).equals(RelationshipType.MOTHER)) {
					String space = "    ";
					try {
						getNext((Person)member.get(1), people, space);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		return people;
	}

	private void getNext(Person person, List<String> people, String space) throws Exception {
		Queue<String> persons = new PriorityQueue<>();
		FamilyTreeExplorer fte = new FamilyTreeExplorer(familyId, ftb);
		persons.add(person.getId());
		while(persons.peek() != null) {
			String temp = persons.poll();
			fte.setStart(temp);
			people.add(space + person.getName() + "(CHILD)");
			while(fte.hasNext()) {
				List member = fte.next();
				if(member.get(0).equals(RelationshipType.FRIEND) ||
						member.get(0).equals(RelationshipType.SPOUSE) ||
						member.get(0).equals(RelationshipType.OTHER) ||
						member.get(0).equals(RelationshipType.SIBLING)) {
					people.add(space + " " + ((Person)member.get(1)).getName() + "(" + member.get(0) + ")");
				}
				else if(member.get(0).equals(RelationshipType.FATHER) ||
						member.get(0).equals(RelationshipType.MOTHER)) {
					String nextSpace = space + "    ";
					getNext((Person)member.get(1), people, nextSpace);
				}
			}
		}
	}

}
