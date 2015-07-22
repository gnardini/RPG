package equipment;


public class Helm extends Item {

	int HP, Mana;

	public Helm(String name, int HP, int Mana){
		super(name);
		this.HP=HP;
		this.Mana=Mana;
	}
	
	public int getHP() {
		return HP;
	}

	public int getMana() {
		return Mana;
	}
}
