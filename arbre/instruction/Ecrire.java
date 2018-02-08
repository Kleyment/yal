package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public abstract class Ecrire extends Instruction {

	protected Expression expression;
	
	public Ecrire(Expression expr) {
		super(expr.getNoLigne());
        expression = expr ;
	}
	
	
	

}
