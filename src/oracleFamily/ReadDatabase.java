package oracleFamily;

public class ReadDatabase {
	
	/**
	 * Makes the DB connection
	 */
	public void makeDBConnection() {
		/*
		 * Code to make the connection to the provided
		 * database using the given properties. 
		 */
	}
	
	/**
	 * Loads all the required data to create the family
	 * tree in memory from database for the given person ID
	 */
	public void loadFamilyTree(String personId) {
		/*
		 * Code to read all the records in database 
		 * having references of the given personId.
		 * Then it will create & populate all the objects
		 * required for Person & Relationship. It will
		 * also populate the in memory hashmap to store
		 * the related objects of Person & Relationship
		 * for the family.
		 */
	}
	
	/**
	 * Loads all the required data to create the family
	 * tree in memory from database for the given family ID
	 */
	public void loadFamily(String familyId) {
		/*
		 * Code to read all the records in database 
		 * having references of the given familyId.
		 * Then it will create & populate all the objects
		 * required for Person & Relationship. It will
		 * also populate the in memory hashmap to store
		 * the related objects of Person & Relationship
		 * for the family.
		 */
	}
	
	/**
	 * Close the DB connection
	 */
	public void closeDBConnection() {
		/*
		 * Closes the DB connection associated to this instance.
		 */
	}

}
