package yal.arbre.instruction;

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
		StringBuilder retour = new StringBuilder();
		
		retour.append(exp.toMIPS()+"\n");
		// ??? retour.append("v0->$s7+16");
		//Le résultat doit être dans v0
		//Faire un depilement de taille des variables
		//Stocker la valeur dans s7 + 16
		
		return retour.toString();
	}	
	
}