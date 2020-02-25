package amphoraFamily;

/**
 * @author Nitin Agrawal
 * @Date 25-Feb-2020
 */
public class Relation {

	private Member owner;
	private Member child;
	private RelationType relationType;

	public Relation(Member owner, Member child, RelationType relationType) {
		this.owner = owner;
		this.child = child;
		this.relationType = relationType;
	}

	public Member getOwner() {
		return owner;
	}

	public Member getChild() {
		return child;
	}

	public RelationType getRelationType() {
		return relationType;
	}

	@Override
	public int hashCode() {
		return (owner.getName()+child.getName()+relationType.name()).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Relation) {
			Relation rel = (Relation)obj;
			if(rel.getChild().getName().equalsIgnoreCase(child.getName())
					&& rel.getOwner().getName().equalsIgnoreCase(owner.getName()))
				return true;
		}
		return false;
	}
}
