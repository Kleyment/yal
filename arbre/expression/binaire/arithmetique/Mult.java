package yal.arbre.expression.binaire.arithmetique;

import yal.arbre.expression.Expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class Mult extends BinaireArithmetique {

    public Mult(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
  
    @Override
    public String operateur() {
        return " * ";
    }

    @Override
	public int getType() {
	    return ENTIER;
    }
    
    @Override
	public String operation() {
		return " Multiplication ";
	}
    
	@Override
	public String toMIPS() {
		StringBuilder mult = new StringBuilder(100);
		
		mult.append(super.toMIPS());
		mult.append("mult $v0, $t8\n");
		
		mult.append("# Résultat $lo -> $v0\n");
		mult.append("mflo $v0\n");
		
		return mult.toString();
	}
    
}
