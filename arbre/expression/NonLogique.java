package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class NonLogique extends Unaire {
    
    public NonLogique(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return " non " ;
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
	    if (expression.getType() != BOOLEEN) {
	    	StringBuilder erreur = new StringBuilder();
	    	
	    	erreur.append("erreur de type : ");
	    	erreur.append(operateur());
	    	erreur.append(expression.getType());
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
	    }		
    }
    
    @Override
	public int getType() {
		return BOOLEEN;
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# Non Logique\n");
		sb.append("# Calcul de l'expression\n");
		sb.append(expression.toMIPS());
		sb.append("# Comparaison de l'expression par rapport a 0\n");
		sb.append("bgtz $v0, alors_"+this.hashCode()+"\n");
		sb.append("# L'expression est inferieure a 0, on met 1 dans $v0\n");
		sb.append("li $v0, 1\n");
		sb.append("j fin_"+this.hashCode()+"\n");
		sb.append("alors_"+this.hashCode()+":\n");
		sb.append("# L'expression est superieure a 0, on met 0 dans $v0\n");
		sb.append("li $v0, 0\n");
		sb.append("fin_"+this.hashCode()+":\n");
		
		return sb.toString();
	}
	
}
