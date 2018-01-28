package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public abstract class Unaire extends Expression {
    
    protected Expression expression ;

    
    protected Unaire(Expression expr) {
        super(expr.getNoLigne());
        expression = expr ;
    }
    
    public abstract String operateur() ;
    
    @Override
    public void verifier() {
    	expression.verifier();
    }
    
    @Override
    public String toMIPS() {
    	StringBuilder unaire = new StringBuilder(40);
    	
    	unaire.append("#" + operation() + "\n");
    	
    	unaire.append("# Calcul de l'expression\n");
		unaire.append(expression.toMIPS());
		
        return unaire.toString();
    }
    
    @Override
    public String toString() {
        return "(" + operateur() + expression + ")" ;
    }
    
}
