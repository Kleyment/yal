package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class EcrireEntier extends Ecrire {

	public EcrireEntier(Expression expr) {
		super(expr);
	}

	@Override
	public String toMIPS() {
		StringBuilder sb=new StringBuilder(20);
		sb.append("# Ecriture d'un entier \n");
		sb.append(this.expression.toMIPS());
		sb.append("move $a0, $v0\n");
		sb.append("li $v0, 1\n");
		sb.append("syscall\n");
		return sb.toString();
	}

}
