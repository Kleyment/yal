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
    public String toString() {
        return "(" + operateur() + expression + ")" ;
    }
    
}
