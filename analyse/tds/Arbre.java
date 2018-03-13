package yal.analyse.tds;

import java.util.HashMap;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class Arbre {
	
    private int numeroRegion;
	
	private HashMap<Entree, Symbole> table;
	
	private Arbre parent;
	private HashMap<Integer, Arbre> fils;
	
    
	public Arbre(int numeroRegion) {
		parent = null;
		fils = new HashMap<Integer, Arbre>();
		table = new HashMap<Entree, Symbole>();
	}
	
	public Arbre(int numeroRegion, Arbre parent) {
		this.parent = parent;
		fils = new HashMap<Integer, Arbre>();
		table = new HashMap<Entree, Symbole>();
	}
	
	public void ajouter(Entree e, Symbole s, int noLigne) {
		assert e != null;
		
		if (table.containsKey(e)) {
			throw new AnalyseSemantiqueException(noLigne, "redéclaration de `" + e.getIdf() + "`");
		}
		
		table.put(e, s);
	}
	
	public void ajouterFils(Arbre f) {
		Arbre prev = fils.put(f.numeroRegion(), f);
		
		assert prev == null;
	}
	
	public Arbre recupererFils(int numeroRegion) {
		Arbre fils = this.fils.get(numeroRegion);
		
		assert fils != null;
		
		return fils;
	}
	
	public Symbole identifier(Entree e) {
		Symbole s = table.get(e);
		
		if (s == null) {
			if (parent != null) {
				s = parent.identifier(e);
			}
		}
		
		return s;
	}
	
	public Arbre getParent() {
		return parent;
	}
	
	public int numeroRegion() {
		return numeroRegion;
	}
	
	public int nbVariables() {
		return table.size();
	}
	
	public int nbFils() {
		return fils.size();
	}
	
	public int tailleZoneDesVariables() {
		return table.size() * 4;
    }
	
	@Override
	public String toString() {
		return table.toString();
	}
	
}
