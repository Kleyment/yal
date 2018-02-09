package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class EcrireChaine extends Ecrire{

	public EcrireChaine(Expression expr) {
		super(expr);
	}

	@Override
	public void verifier(){
		if (expression.getType() != CHAINE){
			StringBuilder erreur = new StringBuilder(50);
	    	
	    	erreur.append("erreur de type :\t");
	    	erreur.append("type de l'expression\n");
	    	erreur.append(expression);
	    	erreur.append("\n\t");
	    	erreur.append("l'expression doit être une chaîne de caractères");
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
		}
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(50);
		
		sb.append("# Ecriture d'une chaine \n");
		sb.append(expression.toMIPS());
		sb.append("li $v0, 4\n");
		sb.append("la $a0, chaine"+expression.hashCode()+"\n");
		sb.append("syscall\n");
		
		return sb.toString();
	}
	
}
