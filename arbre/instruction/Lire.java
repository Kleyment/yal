package yal.arbre.instruction;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class Lire extends Instruction {

	private String idf;
	private String type;
	private int deplacement;
	
	
	public Lire(String idf, int no) {
		super(no);
		this.idf = idf;
	}

	@Override
	public void verifier() {
		EntreeVariable e = new EntreeVariable(idf);
		Symbole s = TDS.getInstance().identifier(e);
		
		if (s == null) {
			throw new AnalyseSemantiqueException(getNoLigne(), "aucune déclaration de `" + idf + "`");
		}
		
		type = s.getType();
		deplacement = s.getDeplacement();
		
		if (!type.equals("entier")) {
			StringBuilder erreur = new StringBuilder(40);
			
			erreur.append("erreur de type :\t");
			erreur.append("lire ");
			erreur.append(idf);
			erreur.append("\n\t");
			erreur.append("`");
			erreur.append(idf);
			erreur.append("` doit être un entier");
			
			throw new AnalyseSemantiqueException(getNoLigne(),erreur.toString());
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder lire = new StringBuilder(40);
		
		lire.append("# Lecture d'un entier\n");
		lire.append("li $v0, 5\n");
		lire.append("syscall\n");
		lire.append("sw $v0, ");
		lire.append(deplacement);
		lire.append("($s7)\n");
		lire.append("\n");
		
		return lire.toString();
	}
	
	@Override
	public String toString() {
		return "lire " + idf;
	}
	
}
