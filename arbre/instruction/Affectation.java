package yal.arbre.instruction;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Affectation extends Instruction {

	private Expression exp;
	private String idf;
	private String type;
	private int deplacement;
	
	
	public Affectation(String idf, Expression exp) {
		super(exp.getNoLigne());
		this.exp = exp;
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
		exp.verifier();
		
		if (!type.equals(exp.getType())) {
			StringBuilder erreur = new StringBuilder(40);
			
			erreur.append("erreur de type :\t");
			erreur.append(idf);
			erreur.append(" = ");
			erreur.append(exp.toString());
			erreur.append("\n\t");
	    	erreur.append("les deux opérandes doivent être de même type");
	    	
			throw new AnalyseSemantiqueException(getNoLigne(),erreur.toString());
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder aff = new StringBuilder(50);
		
		aff.append(exp.toMIPS());
		aff.append("sw $v0, ");
		aff.append(deplacement);
		aff.append("($s7)\n");
		aff.append("\n");
		
        return aff.toString();
	}

	@Override
	public String toString() {
		return idf + " = " + exp.toString();
	}

}
