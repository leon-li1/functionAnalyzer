
//This class is run to create a small function object
public class Function {

	//Instance fields
	private double lc; //leading coefficient
	private int exp; //exponent

	//Constructor method
	public Function(double lc, int exp) {
	
		super();
		this.lc = lc;
		this.exp = exp;
	
	}

	//Getters for each field
	public double getLc() {
		
		return lc;
	
	}
	
	public int getExp() {
	
		return exp;
	
	}

	//To string method
	@Override
	public String toString() {
		
		return String.format("%+1.0fx^%d", lc, exp);
	
	}
	
}