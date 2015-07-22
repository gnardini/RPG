package players;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class PlayerInfo extends JFrame {
	
	private static final long serialVersionUID = 1L;
	Player p;
	
	public PlayerInfo(Player p){
		try { UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");}
		catch (UnsupportedLookAndFeelException ex) {}
		catch (IllegalAccessException ex) {} 
		catch (InstantiationException ex) {} 
		catch (ClassNotFoundException ex) {}
        UIManager.put("swing.boldMetal", Boolean.FALSE);
		
		this.p=p;
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
		pane.add(createLabel("Name: " , p.getName(),""));
		pane.add(createLabel("Physical Damage: " , p.getAD(),""));
		pane.add(createLabel("Magical Damage: " , p.getAP(),""));
		pane.add(createLabel("Armor: " , p.getArmor(),""));
		pane.add(createLabel("Magical Resistance: " , p.getMR(),""));
		pane.add(createLabel("Max HP: " , p.getHP(),""));
		pane.add(createLabel("HP Regeneration: " , p.getHPregen() , "/sec"));
		if(p.usesMana()){
		pane.add(createLabel("Max Mana: " , p.getMana(),""));
		pane.add(createLabel("Mana Regeneration: " , p.getManaregen() , "/sec"));}
		pane.add(createLabel("Attack Speed: " , p.getAttSpd() , " hits/sec"));
		pane.add(createLabel("Cooldown Reduction: " , p.getCDR() , "%"));
		
		setContentPane(pane);
		setVisible(true);
	}

	public JPanel createLabel(String s1, Object n, String s2){
		JPanel jp = new JPanel();
		jp.setBackground(new Color(240,240,180));
		jp.setPreferredSize(new Dimension(600,20) );
		jp.setLayout(new FlowLayout());
		JLabel l1= new JLabel(s1);
		JLabel l2= new JLabel(n + s2);
		l2.setFont(l2.getFont().deriveFont(15.0f));
		l1.setForeground(Color.BLUE);
		l2.setForeground(Color.RED);
		jp.add(l1);	
		jp.add(l2);
		return jp;
	}
	
}
