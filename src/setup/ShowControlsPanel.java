package setup;

import java.awt.Color;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import players.Player;

public class ShowControlsPanel extends JFrame {
	
	private static final long serialVersionUID = 1L;
	
	public ShowControlsPanel(Player p){
		try { UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");}
		catch (UnsupportedLookAndFeelException ex) {}
		catch (IllegalAccessException ex) {} 
		catch (InstantiationException ex) {} 
		catch (ClassNotFoundException ex) {}
        UIManager.put("swing.boldMetal", Boolean.FALSE);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 350);
		
		JPanel pane= new JPanel();
		pane.setBackground(new Color(240,240,180));
		pane.setForeground(Color.RED);
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		JLabel title = new JLabel("Character Information:");
		title.setFont(title.getFont().deriveFont(20.0f));
		title.setForeground(Color.RED);
		pane.add(title);
		//pane.add(createLabel("Name: " , p.getName(),""));
		pane.add(createLabel("Physical Damage: " , p.getAD(),""));
		pane.add(createLabel("Magical Damage: " , p.getAP(),""));
		pane.add(createLabel("Armor: " , p.getArmor(),""));
		pane.add(createLabel("Magical Resistance: " , p.getMR(),""));
		pane.add(createLabel("Max HP: " , p.getHP(),""));
		pane.add(createLabel("HP Regeneration: " , p.getHPregen() , "/sec"));
		if(p.usesMana()){
		pane.add(createLabel("Max Mana: " , p.getMana(),""));
		pane.add(createLabel("Mana Regeneration: " , p.getManaregen() , "/sec"));}
		//pane.add(createLabel("Attack Speed: " , p.getAttSpd() , " hits/sec"));
		pane.add(createLabel("Cooldown Reduction: " , p.getCDR() , "%"));
		
		setContentPane(pane);
		setVisible(true);
	}
	
	private JPanel createLabel(String a, int b, String c){
		return new JPanel();
	}

}
