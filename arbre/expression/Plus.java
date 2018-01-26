package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class Plus extends BinaireArithmetique {

    public Plus(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " + " ;
    }

    @Override
    public int getType() {
	    return ENTIER;
    }
    
    @Override
	public String operation() {
		return " Addition ";
	}
    
	@Override
	public String toMIPS() {
		StringBuilder add = new StringBuilder(100);
			
		add.append(super.toMIPS());
		add.append("add $v0, $t8, $v0\n");	
		
		return add.toString();
	}
    
}
