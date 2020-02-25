package amphoraFamily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @author Nitin Agrawal
 * @Date 25-Feb-2020
 */
public class FamilyTree {

	private Member root;
	private int countOfMembers;
	private List<Member> listOfMembers;

	public FamilyTree(Member member) {
		this.root = member;
		listOfMembers = new ArrayList<>();
		countOfMembers = 0;
	}

	public Member getRoot() {
		return root;
	}

	/**
	 * Sets the root of the family tree & it can only be one.
	 * @param root
	 */
	public void setRoot(Member root) {
		if(!this.root.equals(root)) {
			Relation rel = new Relation(this.root, root, RelationType.KIDS);
			this.root.setRelation(rel);
			Relation relation = new Relation(root, this.root, RelationType.PARENT);
			root.setRelation(relation);
			this.root = root;
			listOfMembers.add(root);
			countOfMembers++;
		}
	}

	public int getCountOfMembers() {
		return countOfMembers;
	}

	public List<Member> getListOfMembers() {
		return listOfMembers;
	}

	public void addMembers(List<Member> members) {
		members.stream().forEach(member -> addMember(member));
	}

	public void addMember(Member member) {
		if(!listOfMembers.contains(member)) {
			listOfMembers.add(member);
			countOfMembers++;
		}
	}

	// Not tested yet
	public void addMemberOfRelation(Member member, Relation relation) {
		if(listOfMembers.contains(member)) 
			return;
		Member child = relation.getChild();
		List<Relation> parentRelations = child.getRelationMappings().get(RelationType.KIDS);
		for(Relation parRelation : parentRelations) {
			Member parent = parRelation.getChild();
			List<Relation> relations = parent.getRelations();
			List<Relation> newRelations = new ArrayList<>();
			for(Relation relationInner : relations) {
				if(!relationInner.getChild().equals(child))
					newRelations.add(relationInner);
				else
					parent.deleteRelation(relationInner);
			}
			relations.clear();
			parent.setRelation(new Relation(parent, member, RelationType.PARENT));
		}
		Relation rel = new Relation(child, member, RelationType.KIDS);
		if(parentRelations.size() == 1)
			child.deleteRelation(parentRelations.get(0));
		child.setRelation(rel);
	}

	public int getMemberId() {
		return listOfMembers.size()+1;
	}

	/**
	 * Retrieves the list of members as per the relationship with te given member.
	 * @param member
	 * @param relationType
	 * @return
	 */
	public List<Member> getMemberOfRelation(Member member, RelationType relationType) {
		if(member == null)
			return Collections.EMPTY_LIST;
		List<Member> members = new ArrayList<>();
		if(RelationType.GRANDKIDS.equals(relationType)) {
			int level = 2;
			getMembers(member, RelationType.PARENT, level, members);
		} else if(RelationType.KIDS.equals(relationType)) {
			int level = 1;
			getMembers(member, RelationType.PARENT, level, members);
		} else if(RelationType.GRANDPARENT.equals(relationType)) {
			int level = 2;
			getMembers(member, RelationType.KIDS, level, members);
		} else if(RelationType.PARENT.equals(relationType)) {
			int level = 1;
			getMembers(member, RelationType.KIDS, level, members);
		}
		return members;
	}

	private void getMembers(Member member, RelationType relationType, int level, List<Member> members) {
		if(level > 0 && member.getRelationMappings().get(relationType) != null) {
			level--;
			List<Relation> relations = member.getRelationMappings().get(relationType);
			for(Relation relation : relations) {
				Member mem = relation.getChild();
				if(level == 0)
					members.add(mem);
				else
					getMembers(mem, relationType, level, members);
			}
		}
	}
	
	public List<Member> getMemberByName(String name) {
		return listOfMembers.stream()
				            .filter(mem -> mem.getName().equalsIgnoreCase(name))
		                    .collect(Collectors.toList());
	}
	
	public Optional<Member> getMember(Member member) {
		return listOfMembers.stream()
				            .filter(mem -> mem.equals(member))
				            .findFirst();
	}
	
	// This method will be quite complex updating all other related members.
	// Currently not writing this.
	public void deleteMember(Member member) {
		if(listOfMembers.remove(member))
			countOfMembers--;
		member.getRelationMappings().get(RelationType.PARENT);
	}

}
