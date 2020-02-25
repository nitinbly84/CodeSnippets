package amphoraFamily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Nitin Agrawal
 * @Date 25-Feb-2020
 */
public class Member implements Comparable<Member> {

	private int memberID;
	private String name;
	private int age;
	private boolean isAlive;
	private List<Relation> relations;
	private Map<RelationType, List<Relation>> relationMappings;

	public int getMemberID() {
		return memberID;
	}

	public Member(String name) {
		this.name = name;
		relations = new ArrayList<>();
		relationMappings = new HashMap<>();
	}

	// This is not the correct way while sorting based on integer values.
	// But here age will not be overflowing the int range, so using this way.
	@Override
	public int compareTo(Member o) {
		if(o.age-this.age == 0)
			return name.compareTo(o.getName());
		return o.age-this.age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public boolean isAlive() {
		return isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}

	public List<Relation> getRelations() {
		return relations;
	}

	public void setRelation(Relation relation) {
		List<Relation> list = null;
		if((list=relationMappings.get(relation.getRelationType())) == null) {
			list = new ArrayList<>();
			relationMappings.put(relation.getRelationType(), list);
		}
		if(list.contains(relation))
			return;
		list.add(relation);
		relations.addAll(list);
	}

	public Map<RelationType, List<Relation>> getRelationMappings() {
		return relationMappings;
	}
	
	// Not yet tested
	public void deleteRelation(Relation relation) {
		relations.remove(relation);
		relationMappings.get(relation.getRelationType()).remove(relation);
	}

	@Override
	public int hashCode() {
		return (name+age+isAlive).hashCode();
	}

	@Override 
	public boolean equals(Object obj) {
		if(obj instanceof Member) {
			Member mem = (Member)obj;
			if(mem.getName().equalsIgnoreCase(name) 
					&& mem.getAge() == age
					&& mem.isAlive == isAlive)
				return true;
		}
		return false;
	}

}
