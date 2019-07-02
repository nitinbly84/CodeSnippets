package oracleFamily;

/**
 * Representation of 'Relationship' object in the application.
 *
 */
public class Relationship {
	
	private String id;
	private static int counter = 0;
	
	private RelationshipType relationName;
	private String parentId;
	private String childId;
	
	Relationship() {
		id = "R"+counter;
		counter++;
	}
	
	public RelationshipType getRelationName() {
		return relationName;
	}
	public void setRelationName(RelationshipType relationName) {
		this.relationName = relationName;
	}
	public String getParent() {
		return parentId;
	}
	public void setParent(String parent) {
		this.parentId = parent;
	}
	public String getChild() {
		return childId;
	}
	public void setChild(String child) {
		this.childId = child;
	}
	public String getId() {
		return id;
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public boolean equals(Object o) {
		return (((Relationship)o).id.equals(id) 
				|| (((Relationship)o).getParent().equals(parentId)
				&& ((Relationship)o).getChild().equals(childId)));
	}
}
