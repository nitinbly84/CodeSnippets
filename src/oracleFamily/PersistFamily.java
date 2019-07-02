package oracleFamily;

public class PersistFamily {
	
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
	 * Will persist in the Family table.
	 * @param familyId
	 * @return
	 */
	public boolean persistFamily(String familyId) {
		return true;
	}
	
	/**
	 * Will persist the Person data in 'Person' table.
	 * @param personId
	 * @return
	 */
	public boolean persistPerson(String personId) {
		return true;
	}
	
	/**
	 * Will persist in the 'Relationship' table.
	 * @param relationshipId
	 * @return
	 */
	public boolean persistRelationship(String relationshipId) {
		return true;
	}
	
	/**
	 * Will delete the given family record from 'Family' table.
	 * @param familyId
	 * @return
	 */
	public boolean deleteFamily(String familyId) {
		return true;
	}
	
	/**
	 * Will update the 'Person' table with the given data.
	 * Person records will not be deleted & only be updated.
	 * @param personId
	 * @return
	 */
	public boolean updatePerson(String personId) {
		return true;
	}
	
	/**
	 * Will delete the Relationship record from 'Relationship'
	 * table.
	 * @param relationshipId
	 * @return
	 */
	public boolean deleteRelationship(String relationshipId) {
		return true;
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
