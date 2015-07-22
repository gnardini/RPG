package equipment;

import players.Player;


public class WeaponsVendor extends Vendor {

	public WeaponsVendor(){
		name="Weapons Vendor";
		lista=new String[8];
		lista[0]="Big Sword";
		lista[1]="Iron Sword";
		lista[2]="Esmerald Imbeded Sword";
		lista[3]="The Excecutioner";
		lista[4]="Wood Scepter";
		lista[5]="Esmerald Scepter";
		lista[6]="Ruby Staff";
		lista[7]="Dragonskin Staff";
		msg= new String[8];
		msg[0]="Big Sword: Price=500 - AD+10";
		msg[1]="Iron Sword: Price=2200 - AD+50";
		msg[2]="Esmerald Imbeded Sword: Price=7000 - AD+120";
		msg[3]="The Excecutioner: Price=20000 - AD+300";
		msg[4]="Wood Scepter: Price=500 - AP+10";
		msg[5]="Esmerald Scepter: Price=2200 - AP+50";
		msg[6]="Ruby Staff: Price=7000 - AP+120";
		msg[7]="Dragonskin Staff: Price=20000 - AP+300";
	}
	
	public void openMenu(Player p) {
		new WeaponsMenu(p,name, lista, msg, 8);
		
	}

	/*
	public void paintSelf(Graphics g, int x, int y, Image[] img){
		super.paintSelf(g, x, y, img);
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(28.0f)); 
		g.drawString("W",14+x*ESCALA, 36+y*ESCALA);
	}*/
	
}
