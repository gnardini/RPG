package equipment;


public class Gloves extends Item {

	double CDR;
	int attspd;

	public Gloves(String name, int attspd, double CDR){
		super(name);
		this.CDR=CDR;
		this.attspd=attspd;
	}
	
	public double getCDR() {
		return CDR;
	}

	public int getAttspd() {
		return attspd;
	}
}
