package yal.analyse.tds;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;

public class TDS {

	public enum Analyse {Syntaxique, Semantique};
	
	private Analyse analyse;
	
	private static TDS ourInstance = new TDS();
	
	private Arbre blocPrincipal;
	private Arbre blocCourant;
	
	private int numeroRegion;
	private int numeroImbrication;
	
	
	private TDS() {
		prepareAnalyseSyntaxique();
	}
	
	public static TDS getInstance() {
		return ourInstance;
	}
	
	public void prepareAnalyseSyntaxique() {
		analyse = Analyse.Syntaxique;
		blocPrincipal = null;
		blocCourant = null;
		numeroRegion = -1;
		numeroImbrication = -1;
	}
	
	public void prepareAnalyseSemantique() {
		assert blocPrincipal != null;
		
		analyse = Analyse.Semantique;
		numeroRegion = -1;
		numeroImbrication = -1;
	}
    
	public void ajouter(Entree e, Symbole s, int noLigne) {
		assert e != null;
		
		blocCourant.ajouter(e, s, noLigne);
	}
	
	public Symbole identifier(Entree e) {
		return blocCourant.identifier(e);
	}

	public void entreeBloc() {
		numeroRegion ++;
		numeroImbrication ++;
		
		switch (analyse) {
		    case Syntaxique:
		    	if (numeroRegion < 0) {
		    		Arbre premier = new Arbre(numeroRegion);
		    		blocPrincipal = premier;
		    		blocCourant = premier;
		    	}
		    	else {
		    		Arbre nouveauBloc = new Arbre(numeroRegion, blocCourant);
		    		blocCourant.ajouterFils(nouveauBloc);
		    		blocCourant = nouveauBloc;
		    	}
		    	
		        break;
		    case Semantique:
		    	if (numeroRegion < 0) {
		    		blocCourant = blocPrincipal;
		    	}
		    	else {
		    		Arbre fils = blocCourant.recupererFils(numeroRegion);
		    		blocCourant = fils;
		    	}
		    	
			    break;
		}
	}
	
	public void sortieBloc() {
		Arbre parent = blocCourant.getParent();
		
		blocCourant = parent;
		numeroImbrication--;
	}
	
	public int numeroRegion() {
		return blocCourant.numeroRegion();
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
