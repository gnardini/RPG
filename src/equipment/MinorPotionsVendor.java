package equipment;

import players.Player;



public class MinorPotionsVendor extends Vendor {
	
	public MinorPotionsVendor(){
		name="Potions Vendor";
		lista=new String[2];
		lista[0]="Minor Health Potion";
		lista[1]="Minor Mana Potion";
		msg=new String[2];
		msg[0]="Minor Health Potion: Price=50 - HP+20";
		msg[1]="Minor Mana Potion: Price=40 - Mana +20";
	}
	
	public void openMenu(Player p) {
		new PotionsMenu(p,name, lista, msg, 2);
		
	}
	/*
	public void paintSelf(Graphics g, int x, int y, Image[] img){
		super.paintSelf(g, x, y,img);
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(28.0f)); 
		g.drawString("P",14+x*ESCALA, 36+y*ESCALA);
	}*/
	
}
