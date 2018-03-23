package yal.arbre.expression;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.exceptions.AnalyseSemantiqueException;

public class Variable extends Expression {

	private String idf;
	private String type;
	private int deplacement;
	
	
	public Variable(String idf, int n) {
		super(n);
		this.idf = idf;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public String operation() {
		return " Variable ";
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
		//System.out.println("dep"+deplacement);
	}

	@Override
	public String toMIPS() {
		//System.out.println("dep2"+deplacement);
		StringBuilder var = new StringBuilder(20);
		
		var.append("lw $v0, ");
		var.append(deplacement);
		var.append("($s7)");
		var.append("\n");
		
		return var.toString();
	}

	@Override
	public String toString() {
		return idf;
	}
	
}
