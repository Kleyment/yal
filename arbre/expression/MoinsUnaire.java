package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class MoinsUnaire extends Unaire {
    
    public MoinsUnaire(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return "- " ;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
	    if (expression.getType() != ENTIER) {
	    	StringBuilder erreur = new StringBuilder(25);
	    	
	    	erreur.append("erreur de type : ");
	    	erreur.append(operateur());
	    	erreur.append(expression.getType());
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
	    }		
    }
    
	@Override
	public int getType() {
		return ENTIER;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(100);
		
		sb.append("# Moins Unaire\n");
		sb.append("# On met 0 a gauche de l'expression\n");
		sb.append("li $v0, 0\n");
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0, 0($sp)\n");
		sb.append("add $sp, $sp, -4\n");
		sb.append("# Calcul de l'expression\n");
		sb.append(expression.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $t8,($sp)\n");
		sb.append("# Difference entre 0 et l'expression\n");
		sb.append("sub $v0, $t8, $v0\n");	
		
		return sb.toString();
	}

}
