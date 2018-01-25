package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class BinaireLogique extends Binaire {

    protected BinaireLogique(Expression gauche, Expression droite) {
        super(gauche, droite) ;
    }
    
    @Override
	public int getType() {
	    return BOOLEEN;
    }
    
    @Override
	public void verifier() {
		if (gauche.getType() != BOOLEEN) {
			StringBuilder erreur = new StringBuilder(40);
			erreur.append("L'opérande gauche doit être booléenne : ");
	    	erreur.append(gauche.getType());
	    	
	    	throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
		} else if (droite.getType() != BOOLEEN) {
			StringBuilder erreur = new StringBuilder(40);
			erreur.append("L'opérande droite doit être booléenne : ");
	    	erreur.append(droite.getType());
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
		}
	}
    
}
