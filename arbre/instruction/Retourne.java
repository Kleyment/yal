package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class Retourne extends Instruction {

	private Expression exp;
	
	
	public Retourne(Expression expr) {
		super(expr.getNoLigne());
		exp = expr;
	}

	@Override
	public void verifier() {
		exp.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder retour = new StringBuilder();
		
		retour.append(exp.toMIPS()+"\n");
		// ??? retour.append("v0->$s7+16");
		//Le résultat doit être dans v0
		//Faire un depilement de taille des variables
		//Stocker la valeur dans s7 + 16
		
		return retour.toString();
	}	
	
}