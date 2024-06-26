package oracleFamily;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class FamilyTreeExplorer implements TreeExplorer {

	private Person person;
	private String familyId;
	private TreeBuilder ftb;
	private Iterator<Relationship> children;
	private Iterator<Relationship> parents;
	private Iterator<Relationship> siblings;
	private Iterator<Relationship> others;

	public FamilyTreeExplorer(String familyId, TreeBuilder ftb) throws Exception {
		this.familyId = familyId;
		if(!ftb.isFamily(familyId))
			throw new Exception("Family doesn't exist...");
		this.ftb = ftb;
	}

	/**
	 * Provides root Person of the family tree, this
	 * person belongs to.
	 */
	@Override
	public String getRoots(String pId) {
		Person p = PeopleRepository.PEOPLE.getPerson(pId);
		return ftb.getFamilyRoot(p.getFamilyId());
	}

	/**
	 * Returns the immediate parents of the given person from its family tree.
	 */
	@Override
	public Set<String> getImmediateParents(String pId) {
		Person person = PeopleRepository.PEOPLE.getPerson(pId);
		Set<String> parents = new HashSet<>();
		person.getParents().forEach(rs -> parents.add(rs.getParent()));
		return parents;
	}

	@Override
	public boolean hasNext() {
		if((parents != null && parents.hasNext()) || (siblings != null && siblings.hasNext())
				|| (children != null && children.hasNext()) || (others != null && others.hasNext()))
			return true;
		return false;
	}

	@Override
	public List<Object> next() {

		List<Object> result = new ArrayList<>();
		if(siblings.hasNext()) {
			Relationship rs = siblings.next();
			result.add(rs.getRelationName());
			result.add(PeopleRepository.PEOPLE.getPerson(rs.getChild()));
//			result.put(rs.getRelationName().toString(), PeopleRepository.PEOPLE.getPerson(rs.getChild()));
			//			return rs.getParent() + "-" + rs.getRelationName() + " of-" + rs.getChild(); 
		} else if(parents.hasNext()) {
			Relationship rs = parents.next();
			result.add(rs.getRelationName());
			result.add(PeopleRepository.PEOPLE.getPerson(rs.getChild()));
//			result.put(rs.getRelationName().toString(), PeopleRepository.PEOPLE.getPerson(rs.getChild()));
			//			return rs.getParent() + "-" + rs.getRelationName() + " of-" + rs.getChild();
		} else if(others.hasNext()) {
			Relationship rs = others.next();
			result.add(rs.getRelationName());
			result.add(PeopleRepository.PEOPLE.getPerson(rs.getChild()));
//			result.put(rs.getRelationName().toString(), PeopleRepository.PEOPLE.getPerson(rs.getChild()));
//			return rs.getParent() + "-" + rs.getRelationName() + " of-" + rs.getChild();
		} /*
			 * else { result.add(RelationshipType.SELF.toString()); result.add(person); }
			 */

		return result;
	}

	/**
	 * Sets the start of the family tree to read from.
	 * @param pId
	 */
	@Override
	public void setStart(String pId) {
		this.person = PeopleRepository.PEOPLE.getPerson(pId);
		if(person == null)
			return;
		children = person.getChildren().iterator();
		parents = person.getParents().iterator();
		siblings = person.getSiblings().iterator();
		others = person.getOthers().iterator();
	}

	/**
	 * Takes the familyID to explore its tree.
	 * @param familyId
	 */
	public void Start(String familyId) {
		this.familyId = familyId;
		String root = getRoots(ftb.getFamilyPerson(familyId));

		this.person = PeopleRepository.PEOPLE.getPerson(root);
	}

	@Override
	public boolean isPresent(String pId) {
		return (PeopleRepository.PEOPLE.exists(pId));
	}

	@Override
	public boolean isFamily(String familyId) {
		return ftb.isFamily(familyId);
	}

	@Override
	public boolean inFamily(String personId, String familyId) {
		return (PeopleRepository.PEOPLE.getPerson(personId).getFamilyId().contains(familyId));
	}

}
