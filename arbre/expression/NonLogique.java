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
		return null;
	}
	
}
