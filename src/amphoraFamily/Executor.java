package amphoraFamily;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Nitin Agrawal
 * @Date 25-Feb-2020
 */
public class Executor {
	private static FamilyTree amphora;
	
	public static void main(String[] args) {
		setup();
		System.out.println("Initial Number of members in Family : " + amphora.getCountOfMembers());
		// Adding a child
		System.out.println("\n-------Adding Meher Das as child of Ankit Das & Aneeta Das-----------");
		Member member13 = new Member("Meher Das");
		member13.setAge(24);
		member13.setAlive(true);
		Member memberFather = new Member("Ankit Das");
		memberFather.setAge(55);
		memberFather.setAlive(true);
		Member memberMother = new Member("Aneeta Das");
		memberMother.setAge(50);
		memberMother.setAlive(true);
		Member parent1 = amphora.getMember(memberFather).orElse(null);
		Member parent2 = amphora.getMember(memberMother).orElse(null);
		member13.setRelation(new Relation(member13, parent1, RelationType.KIDS));
		member13.setRelation(new Relation(member13, parent2, RelationType.KIDS));
		parent1.setRelation(new Relation(parent1, member13, RelationType.PARENT));
		parent2.setRelation(new Relation(parent2, member13, RelationType.PARENT));
		amphora.addMember(member13);
		System.out.println("Number of members in Family after adding Meher Das : " + amphora.getCountOfMembers());
		
		// Adding grand parent
		System.out.println("\n--------Adding Grand parent Sushila Kumari as spouse of Sunil Jamuna Das in the family-----------");
		Member member4 = new Member("Sushila Kumari");
		member4.setAge(81);
		member4.setAlive(true);
		Member spouse = amphora.getMemberByName("Sunil Jamuna Das").get(0);
		spouse.setRelation(new Relation(spouse, member4, RelationType.SPOUSE));
		member4.setRelation(new Relation(member4, spouse, RelationType.SPOUSE));
		Member kid = amphora.getMemberByName("Sanchita Das").get(0);
		member4.setRelation(new Relation(member4, kid, RelationType.PARENT));
		kid.setRelation(new Relation(kid, member4, RelationType.KIDS));
		amphora.addMember(member4);
		
		System.out.println("Number of members in Family after adding Sushila Kumari : " + amphora.getCountOfMembers());
		
		// Printing the family members' names in the increasing order of their age
		System.out.println("\n--------Members of the family in the sorted oredr of their age---------");
		Collections.sort(amphora.getListOfMembers());
		amphora.getListOfMembers().stream()
		                          .sorted()
		                          .map(mem -> mem.getName())
		                          .collect(Collectors.toList())
		                          .forEach(System.out::println);
		
		// Q3 : Can be done using the binary search to find the position of insertion
		// But this will not be an accepted way to add a member in the family & member
		// must be added in a manner like shown above.
		
		// Name of GrandChildren
		System.out.println("\n---------Grand Kids of Jamuna Das--------------");
		Member greatGrandFather = new Member("Jamuna Das"); //GreatGrandParent
		greatGrandFather.setAge(104);
		greatGrandFather.setAlive(false);
		greatGrandFather = amphora.getMember(greatGrandFather).orElse(null);
		List<Member> members = amphora.getMemberOfRelation(greatGrandFather, RelationType.GRANDKIDS);
		members.stream()
			   .map(m -> m.getName())
		       .forEach(System.out::println);
		
		System.out.println("\n---------Grand Kids of Amar Jamuna Das--------------");
		Member grandFather = new Member("Amar Januna Das"); //GrandParent
		grandFather.setAge(81);
		grandFather.setAlive(false);
		grandFather = amphora.getMember(grandFather).orElse(null);
		members = amphora.getMemberOfRelation(grandFather, RelationType.GRANDKIDS);
		members.stream()
			   .map(m -> m.getName())
		       .forEach(System.out::println);
		
		System.out.println("\n---------Grand Parents of Mridul Das----------------");
		Member kids = new Member("Mridul Das"); //Kids
		kids.setAge(27);
		kids.setAlive(true);
		kids = amphora.getMember(kids).orElse(null);
		List<Member> grandParents = amphora.getMemberOfRelation(kids, RelationType.GRANDPARENT);
		grandParents.stream()
		       .map(m -> m.getName())
		       .forEach(System.out::println);
		
		
	}
	
	private static void setup() {
		List<Member> members = new ArrayList<>();
		Member member1 = new Member("Jamuna Das"); //GreatGrandParent
		member1.setAge(104);
		member1.setAlive(false);
		Member member2 = new Member("Sheela Devi"); //GreatGrandParent
		member2.setAge(100);
		member2.setAlive(false);
		Member member3 = new Member("Sunil Jamuna Das"); //GrandParent
		member3.setAge(82);
		member3.setAlive(true);
//		Member member4 = new Member("Sushila Kumari"); //GrandParent
//		member4.setAge(80);
//		member4.setAlive(true);
		Member member5 = new Member("Amar Januna Das"); //GrandParent
		member5.setAge(81);
		member5.setAlive(false);
		Member member6 = new Member("Meena Pandit"); //GrandParent
		member6.setAge(77);
		member6.setAlive(true);
		Member member7 = new Member("Sanchita Das"); //Parent & kid of Sunil
		member7.setAge(58);
		member7.setAlive(true);
		Member member8 = new Member("Sanchit Das"); //Parent & spouse of Sanchita 
		member8.setAge(60);
		member8.setAlive(true);
		Member member9 = new Member("Ankit Das"); //Parent & Kid of Amar
		member9.setAge(55);
		member9.setAlive(true);
		Member member10 = new Member("Aneeta Das"); //Parent & wife of Ankit
		member10.setAge(50);
		member10.setAlive(true);
		Member member11 = new Member("Mridul Das"); //Kids
		member11.setAge(27);
		member11.setAlive(true);
		Member member12 = new Member("Mehak Das"); //Wife of Mridul
		member12.setAge(25);
		member12.setAlive(true);
		members.add(member1);
		members.add(member2);
		members.add(member3);
//		members.add(member4);
		members.add(member5);
		members.add(member6);
		members.add(member7);
		members.add(member8);
		members.add(member9);
		members.add(member10);
		members.add(member11);
		members.add(member12);
		
		amphora = new FamilyTree(member1);
		amphora.addMembers(members);
		
		//Setting Relations & adding each member to family
		member1.setRelation(new Relation(member1, member2, RelationType.SPOUSE));
		member2.setRelation(new Relation(member2, member1, RelationType.SPOUSE));
		member1.setRelation(new Relation(member1, member3, RelationType.PARENT));
		member3.setRelation(new Relation(member3, member1, RelationType.KIDS));
		member1.setRelation(new Relation(member1, member5, RelationType.PARENT));
		member5.setRelation(new Relation(member5, member1, RelationType.KIDS));
		member2.setRelation(new Relation(member2, member3, RelationType.PARENT));
		member3.setRelation(new Relation(member3, member2, RelationType.KIDS));
		member2.setRelation(new Relation(member2, member5, RelationType.PARENT));
		member5.setRelation(new Relation(member5, member2, RelationType.KIDS));
//		member3.setRelation(new Relation(member3, member4, RelationType.SPOUSE));
//		member4.setRelation(new Relation(member4, member3, RelationType.SPOUSE));
		member5.setRelation(new Relation(member5, member6, RelationType.SPOUSE));
		member6.setRelation(new Relation(member6, member5, RelationType.SPOUSE));
		member3.setRelation(new Relation(member3, member7, RelationType.PARENT));
//		member4.setRelation(new Relation(member4, member7, RelationType.PARENT));
		member7.setRelation(new Relation(member7, member3, RelationType.KIDS));
//		member7.setRelation(new Relation(member7, member4, RelationType.KIDS));
		member7.setRelation(new Relation(member7, member8, RelationType.SPOUSE));
		member8.setRelation(new Relation(member8, member7, RelationType.SPOUSE));
		member5.setRelation(new Relation(member5, member9, RelationType.PARENT));
		member6.setRelation(new Relation(member6, member9, RelationType.PARENT));
		member9.setRelation(new Relation(member9, member5, RelationType.KIDS));
		member9.setRelation(new Relation(member9, member6, RelationType.KIDS));
		member9.setRelation(new Relation(member9, member10, RelationType.SPOUSE));
		member10.setRelation(new Relation(member10, member9, RelationType.SPOUSE));
		member9.setRelation(new Relation(member9, member11, RelationType.PARENT));
		member10.setRelation(new Relation(member10, member11, RelationType.PARENT));
		member11.setRelation(new Relation(member11, member9, RelationType.KIDS));
		member11.setRelation(new Relation(member11, member10, RelationType.KIDS));
		member11.setRelation(new Relation(member11, member12, RelationType.SPOUSE));
		member12.setRelation(new Relation(member12, member11, RelationType.SPOUSE));
	}

}
