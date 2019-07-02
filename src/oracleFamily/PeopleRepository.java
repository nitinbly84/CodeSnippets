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

	public String createPerson(String name) {
		Person person = new Person();
		person.setName(name);
		alivePeople.put(person.getId(), person);
		return person.getId();		
	}
	
	public Person getPerson(String pId) {
		if(pId == null)
			return null;
		Person p = alivePeople.get(pId);
		if(p == null)
			p = deadPeople.get(pId);
		return p;
	}
	
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
	
	public boolean exists(String pId) {
		return alivePeople.containsKey(pId) || deadPeople.containsKey(pId);
	}
}
