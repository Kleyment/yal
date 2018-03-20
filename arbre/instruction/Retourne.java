package yal.arbre.instruction;

import yal.analyse.tds.TDS;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class Retourne extends Instruction {

	private Expression exp;
	
	
	public Retourne(Expression expr) {
		super(expr.getNoLigne());
		exp = expr;
	}

	@Override
	public void verifier() {
		exp.verifier();
		
		if (exp.getType().equals("booleen")) {
			StringBuilder erreur = new StringBuilder(40);
			
			erreur.append("erreur sur retourne :\t");
			erreur.append(exp.toString());
			erreur.append("\n\t");
	    	erreur.append("retourne doit retourner un entier");
	    	
			throw new AnalyseSemantiqueException(getNoLigne(),erreur.toString());
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder retourne = new StringBuilder();
		
		retourne.append(exp.toMIPS());
		/*retourne.append("# On depile la zone des variables, puis jusqu'à s7 pour enfin faire un jump à l'adresse de retour \n");
		retourne.append("add $sp, $sp, "+TDS.getInstance().tailleZoneDesVariables()+" \n");*/
		retourne.append("add $sp, $sp, 16 \n");
		retourne.append("sw $v0, 0($sp) \n");
			
		retourne.append("add $sp, $sp, -4 \n");
		retourne.append("lw $ra, 0($sp) \n");
		retourne.append("jr $ra \n");
		
		return retourne.toString();
	}	
	
}