package yal.arbre.expression;

/**
 * @author @author Clément Bellanger, Pierre génard, Valentin Thouvenin
 */
public abstract class Constante extends Expression {

    protected String cste ;
    
    protected Constante(String texte, int n) {
        super(n) ;
        cste = texte ;
    }

    @Override
    public String toString() {
        return cste ;
    }

    public void verifier() {
    	
    }
    
}
