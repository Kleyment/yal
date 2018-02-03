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
    	
    	if (gauche.getType() != BOOLEEN || droite.getType() != BOOLEEN) {
	    	StringBuilder erreur = new StringBuilder(40);
	    	
	    	erreur.append("erreur de type :\t");
	    	erreur.append(gauche);
	    	erreur.append(operateur());
	    	erreur.append(droite);
	    	erreur.append("\n\t");
	    	erreur.append("les deux opérandes doivent être des booléens");
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
	    }	
	}
    
    @Override
    public String toMIPS() {
    	return super.toMIPS();
    }
    
}
