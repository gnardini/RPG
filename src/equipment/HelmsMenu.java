package equipment;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import players.Player;

public class HelmsMenu extends VendorMenu{

	private static final long serialVersionUID = 1L;
	String[] lista;
	String[] msg;
	Player p;
	JPanel pane;
	
	public HelmsMenu(Player p, String name, String[] lista, String[] msg, int num){
		try { UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");}
		catch (UnsupportedLookAndFeelException ex) {}
		catch (IllegalAccessException ex) {} 
		catch (InstantiationException ex) {} 
		catch (ClassNotFoundException ex) {}
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setSize(600, 350);
		
		pane=new JPanel();
		pane.setLayout(new BoxLayout(pane, BoxLayout.Y_AXIS));
		pane.setBackground(new Color(240,240,180));
		for(int i=0 ; i<num; i++){
			pane.add(new VendorItem(this,lista[i],msg[i]));
		}
		setContentPane(pane);
		this.lista=lista;
		this.msg=msg;
		this.p=p;
		
		setVisible(true);
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
			if(msg.equals("Big Helm")){
				if(p.getGold()>=350)p.buyHelm(350,msg,50,0);   
				else notEnoughMoney();
			}else if(msg.equals("Wide Hat")){
				if(p.getGold()>=250)p.buyHelm(250,msg,0,50);  
				else notEnoughMoney();
			}
			
	  }
	
}