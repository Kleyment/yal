package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Boucle extends Instruction {

    private Expression exp;
    private BlocDInstructions instructions;
    
    
	public Boucle(Expression expr, BlocDInstructions li) {
		super(expr.getNoLigne());
		expr = exp;
		instructions = li;
	}

	@Override
	public void verifier() {
		if (!exp.getType().equals("booleen")) {
			StringBuilder erreur = new StringBuilder(40);
			
			erreur.append("erreur de type :\t");
			erreur.append(exp);
			erreur.append("\n\t");
			erreur.append("une expression évaluée pour une boucle doit être booléenne");
			
			throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
		}

		instructions.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder boucle = new StringBuilder(150);
		int hash = hashCode();
		
		boucle.append("# Boucle\n");
		
		boucle.append("tq_");
		boucle.append(hash);
		boucle.append(" :\n");
		boucle.append(exp.toMIPS());
		
		boucle.append("beqz $v0, fintq_");
		boucle.append(hash);
		boucle.append("\n");
		
		boucle.append("iter_");
		boucle.append(hash);
		boucle.append(" :\n");
		boucle.append(instructions.toMIPS());
		boucle.append("j tq_");
		boucle.append(hash);
		boucle.append("\n");
		
		boucle.append("fintq_");
		boucle.append(hash);
		boucle.append(" :\n");
		
		return boucle.toString();
	}

}
