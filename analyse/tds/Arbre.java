package yal.analyse.tds;

import java.util.ArrayList;
import java.util.HashMap;

import yal.analyse.tds.entree.Entree;
import yal.analyse.tds.symbole.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class Arbre {
	
	private Arbre parent;
	private ArrayList<Arbre> fils;
	
	private HashMap<Entree, Symbole> table;
	
    
	public Arbre() {
		parent = null;
		fils = new ArrayList<Arbre>();
		table = new HashMap<Entree, Symbole>();
	}
	
	public Arbre(Arbre parent) {
		this.parent = parent;
		fils = new ArrayList<Arbre>();
		table = new HashMap<Entree, Symbole>();
	}
	
	public void ajouter(Entree e, Symbole s, int noLigne) {
		if (table.containsKey(e)) {
			throw new AnalyseSemantiqueException(noLigne, "redéclaration de `" + e.getIdf() + "`");
		}
		
		table.put(e, s);
	}
	
	public void ajouterFils(Arbre f) {
		fils.add(f);
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
