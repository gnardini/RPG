package equipment;

import players.Player;


public class GlovesVendor extends Vendor {

	public GlovesVendor(){
		name="Gloves Vendor";
		lista=new String[2];
		lista[0]="Wool Gloves";
		lista[1]="Brass Gloves";
		msg=new String[2];
		msg[0]="Wool Gloves: Price=350 - CDR+10%";
		msg[1]="Brass Gloves: Price=350 - Attack Speed+10%";
	}
	
	public void openMenu(Player p) {
		new GlovesMenu(p,name, lista, msg, 2);
		
	}

	/*
	public void paintSelf(Graphics g, int x, int y, Image[] img){
		super.paintSelf(g, x, y,img);
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(28.0f)); 
		g.drawString("G",14+x*ESCALA, 36+y*ESCALA);
	}*/

}
