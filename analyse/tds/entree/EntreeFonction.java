package yal.analyse.tds.entree;

public class EntreeFonction extends Entree {

	private int nombreParametres;
		
	
	public EntreeFonction(String idf, int nombre) {
		super(idf);
		nombreParametres = nombre;
	}

	public int nombreParametres() {
		return nombreParametres;
	}
	
}
