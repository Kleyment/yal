package yal.arbre.instruction;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;
import yal.exceptions.AnalyseSemantiqueException;

public class Fonction extends Instruction {
	
	private String idf;
	private String typeRetour;
	private String[] parametres;
	
	private BlocDInstructions li;
	
	
	public Fonction(int noLigne, String idf, BlocDInstructions li) {
		super(noLigne);
		this.li = li;
		this.idf=idf;
	}

	@Override
	public void verifier() {
		li.verifier();
		int compteur=0;
		for (Instruction i : li.getBloc()) {
			if (i instanceof Retourne) {
				compteur++;
			}
		}
		if (compteur == 0) {
			throw new AnalyseSemantiqueException(getNoLigne(), "aucune instruction retourne");
		}
		EntreeVariable e = new EntreeVariable(idf);
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null) {
			throw new AnalyseSemantiqueException(getNoLigne(), "aucune déclaration de `" + idf + "`");
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder fonction = new StringBuilder();
		int hash = hashCode();
		
		fonction.append("# Fonction\n");
		fonction.append(idf+": \n");
		
		fonction.append("# On réserve de l'espace pour la valeur de retour \n");
		fonction.append("add $sp, $sp, -4 \n");
		
		fonction.append("# On empile l'adresse de retour \n");
		fonction.append("sw $ra, 0($sp) \n");
		fonction.append("add $sp, $sp, -4");
		
		fonction.append("# On empile s7 \n");
		fonction.append("sw $s7, 0($sp)\n");
		fonction.append("add $sp, $sp, -4\n");
		
		fonction.append("# On empile le numéro de région \n");
		fonction.append("li $t8, "+TDS.getInstance().numeroRegion()+" \n");
		fonction.append("sw $t8, 0($sp)\n");
		fonction.append("add $sp, $sp, -4\n");
		
		/*fonction.append("move $s7, $sp \n");
		fonction.append("add $sp, $sp, -"+TDS.getInstance().tailleZoneDesVariables()+" \n");*/
		
		fonction.append(li.toMIPS());
				
		return fonction.toString();
	}

	@Override
	public String toString() {
		StringBuilder fonction = new StringBuilder(40);
		
		fonction.append(typeRetour);
		fonction.append(" ");
		fonction.append(idf);
		fonction.append("(");
		
		int dernier = parametres.length - 1;
		
		for (int i = 0; i < dernier; i ++) {
		    fonction.append(parametres[i]);
		    fonction.append(", ");
		}
		
		if (parametres.length > 0) {
			fonction.append(parametres[dernier]);
		}
		
		fonction.append("");
		fonction.append(") {");
		fonction.append(li.toString());
		fonction.append("}");
		fonction.append("\n");
		
		return fonction.toString();
	}
	
}
