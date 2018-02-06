package yal.arbre;

import java.util.HashMap;

public class TDS {

	private static TDS ourInstance = new TDS();
	private HashMap<Entree, Symbole> table;
	
	private TDS() {
		table = new HashMap<Entree, Symbole>();
	}
	
	public void ajouter(Entree e, Symbole s){
		table.put(e, s);
	}
	
	public Symbole identifier (Entree e){
		Symbole s = table.get(e);
		return s;
	}
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
}
