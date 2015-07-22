package map;

import java.awt.Graphics;
import java.awt.Image;

import setup.myConstants;

public class WallVertical extends Wall implements myConstants {

	@Override
	public void paintSelf(Graphics g, int x, int y, Image[] img) {
		g.drawImage(img[8],10+x*ESCALA,10+y*ESCALA,null);
		/*g.setColor(new Color(90,0,0));
		g.fillRect(16+x*ESCALA, 10+y*ESCALA, ESCALA-20, ESCALA);*/
	}
	
}
