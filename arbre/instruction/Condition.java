package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Condition extends Instruction {

	private Expression exp;
    private BlocDInstructions alors;
    private BlocDInstructions sinon;
    
    
	public Condition(Expression expr, BlocDInstructions li) {
		super(expr.getNoLigne());
		exp = expr;
		sinon = li;
	}

	public Condition(Expression expr, BlocDInstructions alors, BlocDInstructions sinon) {
		super(expr.getNoLigne());
		this.alors = alors;
		this.sinon = sinon;
	}
	
	@Override
	public void verifier() {
		if (!exp.getType().equals("booleen")) {
			StringBuilder erreur = new StringBuilder(40);
			
			erreur.append("erreur de type :\t");
			erreur.append(exp);
			erreur.append("\n\t");
			erreur.append("une expression évaluée pour une condition doit être booléenne");
			
			throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
		}

		alors.verifier();
		sinon.verifier();
	}

	@Override
	public String toMIPS() {
        StringBuilder condition = new StringBuilder(50);
		
		return condition.toString();
	}

}
