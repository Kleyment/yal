package yal.arbre.instruction;

import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class EcrireChaine extends Instruction{

	private String str;
	
	public EcrireChaine(int no, String string) {
		super(no);
		this.str=string;
		
	}

	@Override
	public void verifier() {
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
		int hash = hashCode();
		
		sb.append("# Ecriture d'une chaine \n");
		sb.append(".data\n");
		sb.append("chaine_"+hash+":	.asciiz "+this.str+"\n");
		sb.append(".text\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, chaine_"+hash+"\n");
		sb.append("syscall\n");
		
		return sb.toString();
	}
	
}
