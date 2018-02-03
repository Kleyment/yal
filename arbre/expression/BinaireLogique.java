package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
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
    	super.verifier();
    	
		if (gauche.getType() != BOOLEEN) {
			StringBuilder erreur = new StringBuilder(40);
			erreur.append("L'opérande gauche doit être booléen : ");
	    	erreur.append(gauche);
	    	
	    	throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
		} else if (droite.getType() != BOOLEEN) {
			StringBuilder erreur = new StringBuilder(40);
			erreur.append("L'opérande droit doit être booléen : ");
	    	erreur.append(droite);
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
		}
	}
    
    @Override
    public String toMIPS() {
    	return super.toMIPS();
    }
    
}
