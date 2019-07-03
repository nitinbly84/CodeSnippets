package oracleFamily;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of Person object in the application.
 *
 */
public class Person {
	
	private String id;
	private String familyId;
	private String name;
	private boolean isActive;
	private static int counter = 0;
	
	/*
	 * Below represent the 4 set of 4 kind of relationships
	 * with which this person can be associated in a family.
	 */
	private Set<Relationship> parents = new HashSet<>();
	private Set<Relationship> siblings = new HashSet<>();
	private Set<Relationship> children = new HashSet<>();
	private Set<Relationship> others = new HashSet<>();
	
	Person() {
		id = "P"+counter;
		counter++;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Set<Relationship> getParents() {
		return parents;
	}

	public void setParents(Relationship relationship) throws Exception {
		parents.add(relationship);
	}

	public Set<Relationship> getSiblings() {
		return siblings;
	}

	public void setSiblings(Relationship relationship) throws Exception {
		siblings.add(relationship);
	}

	public Set<Relationship> getChildren() {
		return children;
	}

	public void setChildren(Relationship relationship) throws Exception {
		children.add(relationship);
	}
	
	public Set<Relationship> getOthers() {
		return others;
	}

	public void setOthers(Relationship relationship) throws Exception {
		others.add(relationship);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return ((Person)o).id.equals(id);
	}
	
	public String getId() {
		return id;
	}
	
	/**
	 * Returns familyId associated to this person.
	 * @return Set<String>
	 */
	public String getFamilyId() {
		return familyId;
	}
	
	/**
	 * Adds the given familyID to this person.
	 * @param familyId
	 */
	public void addFamilyId(String familyId) {
		this.familyId = familyId;
	}
	
	/**
	 * Removes the given familyID from this person.
	 * @param id
	 * @return
	 */
	public boolean removeFamilyId(String id) {
		familyId = null;
		return true;
	}
	
}
