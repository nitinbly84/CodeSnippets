package oracleFamily;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Interface to represent the responsibilities of Family tree explorer in the application
 *
 */
public interface TreeExplorer extends Iterator<List> {

	/**
	 * Returns the Persons at the root of the family tree
	 * of the given person.
	 * @param pId
	 * @return
	 */
	public Set<String> getRoots(String pId);
	/**
	 * Returns the immediate parents of the given person from
	 * its family tree.
	 * @param pId
	 * @return
	 */
	public Set<String> getImmediateParents(String pId);
	
	/**
	 * Sets the start of the family tree to read from.
	 * @param pId
	 */
	public void setStart(String pId);
	
	/**
	 * Sets the family id to read its tree from roots.
	 * @param familyId
	 */
	public void Start(String familyId);
	/**
	 * Checks if the given person exists in the repository
	 * whether dead or alive.
	 * @param pId
	 * @return
	 */
	public boolean isPresent(String pId);
	/**
	 * Checks if the family exists.
	 * @param familyId
	 * @return
	 */
	public boolean isFamily(String familyId);
	/**
	 * Checks if the given person exists in the given family.
	 * @param personId
	 * @param familyId
	 * @return
	 */
	public boolean inFamily(String personId, String familyId);
	/**
	 * Provides a set of relationtypes, the given person has in
	 * its family tree.
	 * @param pId
	 * @return
	 */
	public Set<RelationshipType> getRelationNames(String pId);
	/**
	 * Provides the relationtype between given two persons.
	 * @param from
	 * @param to
	 * @return
	 */
	public RelationshipType findRelation(String from, String to);
	/**
	 * Provides a list of relationships, the given person has.
	 * @param pId
	 * @return
	 */
	public List<Relationship> getRelations(String pId);

}
