package equipment;

import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;

public class VendorItem extends JPanel {
	
	private static final long serialVersionUID = 1L;

	VendorMenu p;
	
	public VendorItem(VendorMenu p, String butt, String desc) {
	    this.p=p;  
		setLayout(null);
	      setPreferredSize(new Dimension(300,15));
	      setOpaque(false);

	      JLabel label = new JLabel(desc);
	      label.setBounds(230, 5,330,35);

	      JButton calc = new JButton(butt);
	      calc.setBounds(20,5,200,35);
	      calc.addActionListener(p);

	      add(label);
	      add(calc);
	      
	      setBorder(new SoftBevelBorder(BevelBorder.RAISED));
		}

}
