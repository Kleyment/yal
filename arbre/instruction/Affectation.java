package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class Affectation extends Instruction {

	private Expression exp;
	private String idf;
	
	
	public Affectation(String idf, Expression exp) {
		super(exp.getNoLigne());
		this.exp = exp;
		this.idf = idf;
	}

	@Override
	public void verifier() {

	}

	@Override
	public String toMIPS() {
		// TODO Auto-generated method stub
		return null;
	}

}
