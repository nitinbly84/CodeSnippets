package oracleFamily;

import java.util.HashMap;

/**
 * It creates, updates 'Person' objects in the application
 *
 */
public enum PeopleRepository {
	
	PEOPLE;
	
	private HashMap<String, Person> alivePeople = new HashMap<>();
	private HashMap<String, Person> deadPeople = new HashMap<>();

	/**
	 * It creates the person as Alive one in the system with the
	 * given name.
	 * @param name
	 * @return - Person ID
	 */
	public String createPerson(String name) {
		Person person = new Person();
		person.setName(name);
		alivePeople.put(person.getId(), person);
		return person.getId();		
	}
	
	/**
	 * Gets the person from the system, whether it is alive or dead.
	 * @param pId
	 * @return - Person
	 */
	public Person getPerson(String pId) {
		if(pId == null)
			return null;
		Person p = alivePeople.get(pId);
		if(p == null)
			p = deadPeople.get(pId);
		return p;
	}
	
	/**
	 * Updates the given person with the given name.
	 * @param pId
	 * @param name
	 * @return - boolean
	 */
	public boolean updatePerson(String pId, String name) {
		Person p = alivePeople.get(pId);
		if(p != null) {
			p.setName(name);
			return true;
		}
		return false;
	}
	
	/**
	 * It will not remove the person object from the application
	 * rather it will move the person object from 'alivePeople'
	 * hashmap to 'deadPeople' hashmap.
	 * @param pId
	 * @return
	 */
	public boolean deletePerson(String pId) {
		Person p = deadPeople.put(pId, alivePeople.remove(pId));
		return p != null ? true : false;
	}
	
	/**
	 * Checks if the given person exists in the system.
	 * @param pId
	 * @return - boolean
	 */
	public boolean exists(String pId) {
		return alivePeople.containsKey(pId) || deadPeople.containsKey(pId);
	}
}
