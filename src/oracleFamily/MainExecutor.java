package oracleFamily;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

/**
 * It is the entry class for activities on the family tree.
 *
 */
public class MainExecutor {

	private TreeBuilder ftb = new FamilyTreeBuilder();
	private TreeExplorer fte;
	private ReadDatabase readDatabase = new ReadDatabase();
	private PersistFamily persistFamily = new PersistFamily();
	private DisplayTree displayTree = new DisplayTree();
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

	public void deleteFamilyTree(String familyId) {
		ftb.deleteFamily(familyId);
	}

	public String addPerson(String personName) {
		String personId = PeopleRepository.PEOPLE.createPerson(personName);
		return personId;
	}

	public void updatePerson(String personId, String name) {
		PeopleRepository.PEOPLE.updatePerson(personId, name);
	}

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

	public void deleteRelationship(Relationship relationship, String familyId) {
		ftb.deleteRelationship(relationship, familyId);
	}

	public void loadData(String personId) {
		readDatabase.loadFamilyTree(personId);
	}

	public void loadFamilyTree(String familyId) {
		readDatabase.loadFamily(familyId);
	}

	public void persistFamilyTree(String familyId) {
		persistFamily.persistFamily(familyId);
	}

	public void deleteFamilyFromDB(String familyId) {
		persistFamily.deleteFamily(familyId);
	}

	/**
	 * Reads all the persons connected to the family tree
	 * of the given person.
	 * @param personId
	 */
	public List<String> readPersonTree(String personId) {
		Set<String> roots = fte.getRoots(personId);
		List<String> people = new ArrayList<>();
		while(fte.hasNext()) {
			people.add(((Person)fte.next().get(1)).getName());
		}
		return people;
	}

	/**
	 * Reads all the persons connected to the given family.
	 * @param familyId
	 */
	public List<String> readFamilyTree(String familyId) {
		String personId = ftb.getFamilyPerson(familyId);
		Set<String> roots = fte.getRoots(personId);
		List<String> people = new ArrayList<>();
		boolean start = true;
		for(String id : roots) {
			fte.setStart(personId);
			while(fte.hasNext()) {
				String str = ((Person)fte.next().get(1)).getName();
				if(start || str.contains(RelationshipType.OTHER.toString()))
					people.add(str);
			}
			start = false;
		}
		return people;
	}

	/**
	 * Reads all the descendant persons or the person which are at the
	 * same level as this person.
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

	public List<String> readAnscestors(String personId) {
		Set<String> roots = fte.getRoots(personId);
		String endName = PeopleRepository.PEOPLE.getPerson(personId).getName();
		List<String> people = new ArrayList<>();
		boolean start = true;
		for(String str : roots) {
			fte.setStart(personId);
			while(fte.hasNext()) {
				String text = ((Person)fte.next().get(1)).getName();
				if(start || !text.startsWith(endName))
					people.add(str);
				else
					break;
			}
			start = false;
		}
		return people;
	}

	/**
	 * Displays the Persons & their relationships in the
	 * family tree of the given person.
	 * @param personId
	 */
	public void showPersonTree(String personId) {
		readPersonTree(personId);
		List<String> people = readPersonTree(personId);
		/*
		 * Process the above list to convert the data to list of
		 * hashmap with person & its relation
		 */
		List<HashMap<String, String>> tree = new ArrayList<>();
		displayTree.showPersonTree(tree);
	}

	/**
	 * Displays the Persons & their relationships in the
	 * given family tree.
	 * @param familyId
	 */
	public void showFamilyTree(String familyId) {
		List<String> persons = readFamilyTree(familyId);
		/*
		 * Convert the above list to populate the below map.
		 */
		Map<String, List<String>> people = new HashMap<>();
		displayTree.showfamilyTree(people);
	}

}
