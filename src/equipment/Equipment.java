package equipment;

import players.Player;

public class Equipment {

	Player p;
	Weapon sw;
	Chest ch;
	Gloves gl;
	Helm hl;
	
	public Equipment(Player pl){
		p=pl;
		sw = new Weapon(" ",0,0);
		ch= new Chest(" ",0,0);
		gl= new Gloves(" ",0,0);
		hl= new Helm(" ",0,0);
	}
	
	public void newSword(String name, int ADdmg, int APdmg){
		sw=new Weapon(name, ADdmg, APdmg);
	}
	
	public void newChest(String name, int armor, int MR){
		ch=new Chest(name,armor,MR);
	}
	
	public void newGloves(String name, int attspd, double CDR){
		gl=new Gloves(name, attspd, CDR);
	}
	
	public void newHelm(String name, int HP, int Mana){
		hl=new Helm(name, HP, Mana);
	}
	
	public int getEquipAPdmg(){
		return sw.getAPdmg();
	}
	
	public int getEquipADdmg(){
		return sw.getADDamage();
	}
	
	public int getEquipArmor(){
		return ch.getArmor();
	}
	
	public int getEquipMR(){
		return ch.getMR();
	}
	
	public double getEquipCDR(){
		return gl.getCDR();
	}
	
	public int getEquipAttspd(){
		return gl.getAttspd();
	}
	
	public int getEquipHP(){
		return hl.getHP();
	}
	
	public int getEquipMana(){
		return hl.getMana();
	}
	
	public int getEquipHPregen(){
		return 0;
	}
	
	public int getEquipManaregen(){
		return 0;
	}
}
