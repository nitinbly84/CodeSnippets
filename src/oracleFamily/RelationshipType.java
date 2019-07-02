package oracleFamily;


/**
 * Declaring the nearest relationships only.
 * Relationships with other entities, need to
 * be calculated programmatically.
 */
public enum RelationshipType {
	SELF,
	SPOUSE,
	MOTHER,
	FATHER,
	SIBLING,
	FRIEND,
	OTHER,
	NONE
}
