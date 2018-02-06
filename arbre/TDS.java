package yal.arbre;

public class TDS {

	private static TDS ourInstance = new TDS();
	
	
	private TDS() {
		
	}
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
}
