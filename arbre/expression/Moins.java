package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class Moins extends BinaireArithmetique {

    public Moins(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " - ";
    }

    @Override
    public int getType() {
	    return ENTIER;
    }
    
    @Override
	public String operation() {
		return " Soustraction ";
	}
    
	@Override
	public String toMIPS() {
		StringBuilder soustraction = new StringBuilder(20);
		
		soustraction.append(super.toMIPS());		
		soustraction.append("sub $v0, $t8, $v0\n");	
		
		return soustraction.toString();
	}
	
}
