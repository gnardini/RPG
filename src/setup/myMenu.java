package setup;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import players.Player;

public class myMenu implements ActionListener{

	String[] lista;
	int[] precio;
	Player p;
	JButton[] buttons;
	JPanel jp;
	JFrame f;
	
	myMenu(Player p, String[] lista, String name, int num){
		f= new JFrame(name);
		f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		f.setSize(400, 400);
		f.setVisible(true);
		this.lista=lista;
		this.p=p;
		buttons= new JButton[num];
		
		jp = new JPanel();
		jp.setLayout(new FlowLayout());
		f.add(jp);
		jp.setBackground(new Color(240,240,180));
		for(int i=0 ; i<num; i++){
			buttons[i] = new JButton(lista[i]);
			buttons[i].addActionListener(this);
			jp.add(buttons[i]);
		}
	}
	
	public void notEnoughMoney(){
		JLabel lab= new JLabel("Not enough money",JLabel.CENTER);
		jp.add(lab);
		try{Thread.sleep(1000);}catch(InterruptedException e1){}
		jp.remove(lab);
	}
	
	public void actionPerformed (ActionEvent e) {     
		  String msg = e.getActionCommand();
			if(msg.equals("Minor Health Potion")){
				if(p.getGold()>=50)p.buyMinorHealthPotion(50);   
				else notEnoughMoney();
			}
			if(msg.equals("Minor Mana Potion")){
				if(p.getGold()>=40)p.buyMinorManaPotion(40);   
				else notEnoughMoney();
			}
	  }
	
}
