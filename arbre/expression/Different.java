package yal.arbre.expression;

/**
 * @author brigitte wrobel-dautcourt
 */

public class Different extends Comparaison {

    public Different(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " != ";
    }
    
    @Override
    public int getType() {
    	return BOOLEEN;
    }
  
}
