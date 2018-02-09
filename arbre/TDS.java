package yal.arbre;

import java.util.ArrayList;
import java.util.HashMap;

public class TDS {

	private static TDS ourInstance = new TDS();
	private HashMap<Entree, Symbole> table;
	private ArrayList<BlocDInstructions> listeBloc;
	private int noBloc = 0;
	private BlocDInstructions blocActuel;
	
	private TDS() {
		table = new HashMap<Entree, Symbole>();
		blocActuel = null;
	}
	
	public void ajouter(Entree e, Symbole s){
		table.put(e, s);
	}
	
	public Symbole identifier (Entree e){
		Symbole s = table.get(e);
		return s;
	}
	
	public int getTailleMemoire() {
		return table.size() * 4;
	}
	
	/* POUR PLUS TARD 
	
		BlocDInstructions newBloc = new BlocDInstructions(noBloc);
		noBloc++;
		listeBloc.add(newBloc);
		blocActuel = newBloc;
	}
	
	public void sortieBloc(){
		if (noBloc != 0){
			blocActuel = listeBloc.get(noBloc - 1);
		} else {
			blocActuel = null;
		}
	}
	
	*/
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
}
