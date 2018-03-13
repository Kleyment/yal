package yal.analyse.tds;

import java.util.HashMap;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class TDS {

	private static TDS ourInstance = new TDS();
	
	private HashMap<Entree, Symbole> table;
	private int numeroRegion;
	private int numeroImbrication;
	
	
	private TDS() {
		table = new HashMap<Entree, Symbole>();
		numeroRegion = -1;
		numeroImbrication = -1;
	}
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
	public void ajouter(Entree e, Symbole s, int noLigne) {
		if (table.containsKey(e)) {
			throw new AnalyseSemantiqueException(noLigne, "redéclaration de `" + e.getIdf() + "`");
		}
		
		table.put(e, s);
	}
	
	public Symbole identifier(Entree e) {
		return table.get(e);
	}

	public void entreeBloc() {
		numeroRegion++;
		numeroImbrication++;
	}
	
	public void sortieBloc() {
		numeroImbrication--;
	}
	
	public int numeroRegion() {
		return numeroRegion;
	}
	
	public int numeroImbrication() {
		return numeroImbrication;
	}
	
	public int nbVariables() {
		return table.size();
	}
	
	public int tailleZoneDesVariables() {
		return table.size() * 4;
    }
	
	@Override
	public String toString() {
		return table.toString();
	}
}
