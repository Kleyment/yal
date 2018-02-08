package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class EcrireChaine extends Ecrire{

	public EcrireChaine(Expression expr) {
		super(expr);
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Ecriture d'une chaine \n");
		sb.append(expression.toMIPS());
		sb.append("li $v0, 4\n");
		sb.append("la $a0, chaine"+expression.hashCode()+"\n");
		sb.append("syscall\n");
		return sb.toString();
	}

	
}
