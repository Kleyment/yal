package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public abstract class BinaireArithmetique extends Binaire {

    protected BinaireArithmetique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
	    if (gauche.getType() != ENTIER || droite.getType() != ENTIER) {
	    	StringBuilder erreur = new StringBuilder();
	    	
	    	erreur.append("erreur de type : ");
	    	erreur.append(gauche.getType());
	    	erreur.append(operateur());
	    	erreur.append(droite.getType());
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
	    }		
    }
  
}
