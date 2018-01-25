package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public abstract class Comparaison extends Binaire {
     
    protected Comparaison(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public int getType() {
    	return BOOLEEN;
    }
    
    @Override
    public void verifier() {
        super.verifier();	
    }
    
}
