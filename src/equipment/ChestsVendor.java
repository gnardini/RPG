package equipment;

import players.Player;


public class ChestsVendor extends Vendor {

	public ChestsVendor(){
		name="Chest Equipment Vendor";
		lista=new String[2];
		lista[0]="Brass Chestplate";
		lista[1]="Linen Robe";
		msg=new String[2];
		msg[0]="Brass Chestplate: Price=400 - Armor+10";
		msg[1]="Linen Robe: Price=250 - MR+10";
	}
	
	public void openMenu(Player p) {
		new ChestsMenu(p,name,lista,msg, 2);
		
	}

	/*
	public void paintSelf(Graphics g, int x, int y, Image[] img){
		super.paintSelf(g, x, y,img);
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(28.0f)); 
		g.drawString("C",14+x*ESCALA, 36+y*ESCALA);
	}*/

}
