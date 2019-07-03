package oracleFamily;

/**
 * Interface to represent the responsibilities of Family tree builder in the application
 *
 */
public interface TreeBuilder {

	/**
	 * Creates the relationship for the give persons in the provided family with given relationshiptype.
	 * @param rt
	 * @param parentId
	 * @param childId
	 * @param familyId
	 * @return
	 * @throws Exception
	 */
	public Relationship createRelationship(RelationshipType rt, String parentId, String childId, String familyId) throws Exception;
	/**
	 * Creates a family with given name.
	 * @param name
	 * @return
	 */
	public String createFamily(String name);
	/**
	 * Provides any one person from the given family.
	 * @param familyId
	 * @return
	 */
	public String getFamilyPerson(String familyId);
	/**
	 * Provides the Person Id of the root member of this family.
	 * @param familyId
	 * @return
	 */
	public String getFamilyRoot(String familyId);
	/**
	 * Checks if the family exists with given ID.
	 * @param familyId
	 * @return
	 */
	public boolean isFamily(String familyId);
}
