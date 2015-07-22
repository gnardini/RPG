package equipment;

import players.Player;


public class HelmsVendor extends Vendor {

	public HelmsVendor(){
		name="Head Equipment Vendor";
		lista=new String[8];
		lista[0]="Big Helm";
		lista[1]="Wide Hat";
		msg=new String[2];
		msg[0]="Big Helm: Price=350 - HP+50";
		msg[1]="Wide Hat: Price=250 - Mana+50";
	}
	
	public void openMenu(Player p) {
		new HelmsMenu(p,name, lista, msg, 2);
		
	}

	/*
	public void paintSelf(Graphics g, int x, int y, Image[] img){
		super.paintSelf(g, x, y,img);
		g.setColor(Color.RED);
		g.setFont(g.getFont().deriveFont(28.0f)); 
		g.drawString("G",14+x*ESCALA, 36+y*ESCALA);
	}*/

}
