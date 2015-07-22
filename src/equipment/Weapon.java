package equipment;


public class Weapon extends Item {
	
	int ADdmg;
	int APdmg;

	public Weapon(String name, int ADdmg, int APdmg){
		super(name);
		this.ADdmg=ADdmg;
		this.APdmg=APdmg;
	}
	
	public int getADDamage() {
		return ADdmg;
	}

	public int getAPdmg() {
		return APdmg;
	}
	
	
	
}
