package yal.arbre.expression.binaire.arithmetique;

import yal.arbre.expression.Expression;
import yal.arbre.expression.binaire.Binaire;
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
    	
	    if (!gauche.getType().equals("entier") || !droite.getType().equals("entier")) {
	    	StringBuilder erreur = new StringBuilder(40);
	    	
	    	erreur.append("erreur de type :\t");
	    	erreur.append(gauche);
	    	erreur.append(operateur());
	    	erreur.append(droite);
	    	erreur.append("\n\t");
	    	erreur.append("les deux opérandes doivent être des entiers");
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
	    }		
    }
  
    @Override
    public String toMIPS() {
    	return super.toMIPS();
    }
    
}
