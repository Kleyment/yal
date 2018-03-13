package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

public class TDS {

	public enum Analyse {Syntaxique, Semantique};
	
	private Analyse analyse;
	
	private static TDS ourInstance = new TDS();
	
	private Arbre sommet;
	private Arbre blocCourant;
	
	private int numeroRegion;
	private int numeroImbrication;
	
	
	private TDS() {
		sommet = null;
		blocCourant = null;
		numeroRegion = -1;
		numeroImbrication = -1;
	}
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
	public void prepareAnalyseSyntaxique() {
		analyse = Analyse.Syntaxique;
		sommet = null;
		blocCourant = null;
		numeroRegion = -1;
		numeroImbrication = -1;
	}
	
	public void prepareAnalyseSemantique() {
		analyse = Analyse.Semantique;
		numeroRegion = -1;
		numeroImbrication = -1;
	}
    
	public void ajouter(Entree e, Symbole s, int noLigne) {
		blocCourant.ajouter(e, s, noLigne);
	}
	
	public Symbole identifier(Entree e) {
		return blocCourant.identifier(e);
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
		return blocCourant.nbVariables();
	}
	
	public int tailleZoneDesVariables() {
		return blocCourant.tailleZoneDesVariables();
    }
	
	@Override
	public String toString() {
		return blocCourant.toString();
	}
	
}
