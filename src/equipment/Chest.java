package equipment;


public class Chest extends Item {

	int armor;
	int MR;

	public Chest(String name, int armor, int MR){
		super(name);
		this.armor=armor;
		this.MR=MR;
	}
	
	public int getArmor() {
		return armor;
	}

	public int getMR() {
		return MR;
	}
}
