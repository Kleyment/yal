package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class EcrireBooleen extends Ecrire {

	public EcrireBooleen(Expression expr) {
		super(expr);
	}

	@Override
	public void verifier() {
		this.expression.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder sb=new StringBuilder(100);
		int hash=this.hashCode();
		
		sb.append(this.expression.toMIPS());
		sb.append("# Ecriture d'un booléen \n");
		sb.append("beqz $v0, alors_" + hash + "\n");
		sb.append("la $a0, vrai\n");
		sb.append("j end_" + hash + "\n");
		sb.append("alors_" + hash + " :\n");
		sb.append("la $a0, faux \n");
		sb.append("end_" + hash + " :\n");
		sb.append("li $v0, 4\n");
		sb.append("syscall\n");
		return sb.toString();
	}

}
