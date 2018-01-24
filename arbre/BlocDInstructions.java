package yal.arbre;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class BlocDInstructions extends ArbreAbstrait {
    
    protected ArbreAbstrait expr;
    
    public BlocDInstructions(int n) {
        super(n) ;
    }
    
    public void ajouter(ArbreAbstrait a) {
        expr = a ;
    }
    
    @Override
    public String toString() {
        return expr.toString() ;
    }
    
    public void verifier() {
    	expr.verifier();
    }
    
    public String toMIPS() {
    	return expr.toMIPS();
    }

}
