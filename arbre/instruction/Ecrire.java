package yal.arbre.instruction;

import yal.arbre.expression.Expression;

public class Ecrire extends Instruction {

	private Expression exp;
	
	
	public Ecrire(Expression expr) {
		super(expr.getNoLigne());
        exp = expr;
	}
	
	@Override
	public void verifier() {
		exp.verifier();		
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		int hash = hashCode();
		
		if (exp.getType().equals("booleen")) {		
			sb.append(exp.toMIPS());
			sb.append("# Ecriture d'un booléen \n");
			sb.append("beqz $v0, alors_" + hash + "\n");
			sb.append("la $a0, vrai\n");
			sb.append("j end_" + hash + "\n");
			sb.append("alors_" + hash + " :\n");
			sb.append("la $a0, faux \n");
			sb.append("end_" + hash + " :\n");
			sb.append("li $v0, 4\n");
			sb.append("syscall\n");
		} 
		else {
			sb.append("# Ecriture d'un entier \n");
			sb.append(exp.toMIPS());
			sb.append("move $a0, $v0\n");
			sb.append("li $v0, 1\n");
			sb.append("syscall\n");
		}
		
		return sb.toString();
	}

	//@Override
	/*public String toString() {
		return "ecrire " + exp.toString();
	}*/
	
}
