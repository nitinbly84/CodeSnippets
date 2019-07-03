package oracleFamily;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementation of 'TreeBuilder' to provide the utility
 * to create, update & delete the family from the application.
 *
 */
public class FamilyTreeBuilder implements TreeBuilder {

	private static int counter = 0;
	private static HashMap<String, Family> families = new HashMap<>();

	/**
	 * Adds the person to this family with the given rationship
	 * @param family
	 * @param pId
	 * @param relation
	 * @throws Exception
	 */
	private void addPerson(Person p, Relationship relation) throws Exception {
		if(p == null)
			throw new Exception("Person not yet created...");
		if(relation.getRelationName().equals(RelationshipType.FATHER)
				|| relation.getRelationName().equals(RelationshipType.MOTHER))
			p.setParents(relation);
		else if(relation.getRelationName().equals(RelationshipType.SIBLING)
				|| relation.getRelationName().equals(RelationshipType.SPOUSE))
			p.setSiblings(relation);
		else if(relation.getRelationName().equals(RelationshipType.FRIEND)
				|| relation.getRelationName().equals(RelationshipType.OTHER))
			p.setOthers(relation);

	}

	/**
	 * Creates & add the person to the family with the given relationshiptype.
	 * Relationshiptype 'Null' is considered useless so not creating any such relationship.
	 * One person will be parent & other will be child in the created relationship.
	 */
	@Override
	public Relationship createRelationship(RelationshipType rt, String parentId, String childId, String familyId) throws Exception {
		Family family = families.get(familyId);
		Person parent = PeopleRepository.PEOPLE.getPerson(parentId);
		Person child = PeopleRepository.PEOPLE.getPerson(childId);
		if(rt.equals(RelationshipType.SELF) && parent != null && child == null && family != null) {
			family.setRoot(parentId);
			parent.addFamilyId(familyId);
			return null;
		} else if(rt.equals(RelationshipType.NONE) || parent == null || child == null || family == null)
			return null;
		HashMap<String, String> familyPeople = family.getFamily();
		HashMap<String, Relationship> relations = family.getRelations();
		if(familyPeople.get(parent.getId()) == null && familyPeople.get(child.getId()) == null)
			return null;
		Relationship rs = new Relationship();
		rs.setChild(child.getId());
		rs.setParent(parent.getId());
		rs.setRelationName(rt);
		if(check(parentId,childId))
			return null;
		relations.put(rs.getId(), rs);
		addPerson(parent, rs);
		familyPeople.put(childId, childId);
		if(child.getFamilyId() == null) {
			child.addFamilyId(familyId);
		}
		if(parent.getFamilyId() == null) {
			parent.addFamilyId(familyId);
		}
		//		addPerson(familyPeople, child, rs);
		return rs;
	}

	/**
	 * Inner class representing the 'Family' for this instance.
	 */
	class Family {
		private String familiyId;
		private String familyName;
		private String root = null;

		private HashMap<String, String> family = new HashMap<>();
		private HashMap<String, Relationship> relations = new HashMap<>();

		Family(String name) {
			familyName = name;
			familiyId = "F"+counter;
			counter++;
		}

		public HashMap<String, String> getFamily() {
			return family;
		}
		public void setFamily(HashMap<String, String> family) {
			this.family = family;
		}
		public HashMap<String, Relationship> getRelations() {
			return relations;
		}
		public void setRelations(HashMap<String, Relationship> relations) {
			this.relations = relations;
		}
		public String getFamiliyId() {
			return familiyId;
		}
		public String getFamilyName() {
			return familyName;
		}

		public String getRoot() {
			return root;
		}

		public void setRoot(String root) {
			if(this.root == null)
				this.root = root;
			family.put(root, root);
		}

		@Override
		public int hashCode() {
			return familiyId.hashCode();
		}

		@Override
		public boolean equals(Object o) {
			return ((Family)o).getFamiliyId().equals(familiyId);
		}
	}

	@Override
	public String createFamily(String name) {
		Family family = new Family(name);
		families.put(family.getFamiliyId(), family);
		return family.getFamiliyId();
	}

	@Override
	public String getFamilyPerson(String familyId) {
		Iterator<String> itr = families.get(familyId).getFamily().values().iterator();
		if(itr.hasNext())
			return itr.next();
		return null;
	}

	@Override
	public String getFamilyRoot(String familyId) {
		String root = families.get(familyId).getRoot();
		return root;
	}

	@Override
	public boolean isFamily(String familyId) {
		return families.containsKey(familyId);
	}

	/**
	 * Checks if provided two person are related
	 * @param parentId
	 * @param childId
	 * @return boolean
	 * @throws Exception
	 */
	public static boolean check(String parentId, String childId) throws Exception {

		Set<Relationship> relations = PeopleRepository.PEOPLE.getPerson(parentId).getChildren();
		for(Relationship rs : relations) {
			if(rs.getChild() == childId && rs.getParent() == parentId)
				return true;
			if(rs.getChild() == parentId && rs.getParent() == childId)
				return true;
		}
		relations = PeopleRepository.PEOPLE.getPerson(parentId).getOthers();
		for(Relationship rs : relations) {
			if(rs.getChild() == childId && rs.getParent() == parentId)
				return true;
			if(rs.getChild() == parentId && rs.getParent() == childId)
				return true;
		}
		relations = PeopleRepository.PEOPLE.getPerson(parentId).getParents();
		for(Relationship rs : relations) {
			if(rs.getChild() == childId && rs.getParent() == parentId)
				return true;
			if(rs.getChild() == parentId && rs.getParent() == childId)
				return true;
		}
		relations = PeopleRepository.PEOPLE.getPerson(parentId).getSiblings();
		for(Relationship rs : relations) {
			if(rs.getChild() == childId && rs.getParent() == parentId)
				return true;
			if(rs.getChild() == parentId && rs.getParent() == childId)
				return true;
		}
		return false;
	}

}
