package equipment;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import players.Player;

public class PotionsMenu extends VendorMenu{

	private static final long serialVersionUID = 1L;
	String[] lista;
	String[] msg;
	Player p;
	Container pane;
	
	public PotionsMenu(Player p,String name, String[] lista, String[] msg, int num){
		try { UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");}
		catch (UnsupportedLookAndFeelException ex) {}
		catch (IllegalAccessException ex) {} 
		catch (InstantiationException ex) {} 
		catch (ClassNotFoundException ex) {}
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
		this.lista=lista;
		this.p=p;
		this.msg=msg;
		
		
		setVisible(true);
		setName(name);
		JPanel pane=new JPanel();
		pane.setVisible(true);
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBackground(new Color(240,240,180));
		for(int i=0 ; i<num; i++){
			pane.add(new VendorItem(this,lista[i],msg[i]));
		}
		setContentPane(pane);
		
		setSize(600, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	public void notEnoughMoney(){
		JLabel lab= new JLabel("Not enough money");
		lab.setAlignmentX(Component.CENTER_ALIGNMENT);
		pane.add(lab);
		try{Thread.sleep(1000);}catch(InterruptedException e1){}
		pane.remove(lab);
	}
	
	public void actionPerformed (ActionEvent e) {     
		  String msg = e.getActionCommand();
			if(msg.equals("Minor Health Potion")){
				if(p.getGold()>=50)p.buyMinorHealthPotion(50);   
				//else notEnoughMoney();
			}else if(msg.equals("Minor Mana Potion")){
				if(p.getGold()>=40)p.buyMinorManaPotion(40);    
				//else notEnoughMoney();
			}
			
	  }
	
}
