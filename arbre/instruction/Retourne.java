package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class Retourne extends Instruction {

	private Expression exp;
	
	public Retourne(Expression expr) {
		super(expr.getNoLigne());
		this.exp = expr;
	}

	@Override
	public void verifier() {
		exp.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder retour = new StringBuilder();
		int hash = hashCode();
		retour.append(exp.toMIPS()+"\n");
		// ??? retour.append("v0->$s7+16");
		return retour.toString();
	}	
	
}