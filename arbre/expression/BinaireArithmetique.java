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
    	super.verifier();
    	
	    if (gauche.getType() != ENTIER || droite.getType() != ENTIER) {
	    	StringBuilder erreur = new StringBuilder(25);
	    	
	    	erreur.append("erreur de type :\t");
	    	erreur.append(gauche);
	    	erreur.append(operateur());
	    	erreur.append(droite);
	    	erreur.append("\n\tles deux opérandes doivent être des entiers");
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
	    }		
    }
  
    @Override
    public String toMIPS() {
    	return super.toMIPS();
    }
    
}
