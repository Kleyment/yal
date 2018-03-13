package yal.arbre.expression.binaire.comparaison;

import yal.arbre.expression.Expression;
import yal.arbre.expression.binaire.Binaire;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public abstract class Comparaison extends Binaire {
     
    protected Comparaison(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String getType() {
    	return "booleen";
    }
    
    @Override
    public void verifier() {
        super.verifier();	
    }
    
    @Override
    public String toMIPS() {
    	return super.toMIPS();
    }
    
}
