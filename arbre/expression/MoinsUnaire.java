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
	    	StringBuilder erreur = new StringBuilder();
	    	
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
		return null;
	}

}
