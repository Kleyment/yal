package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre génard, Valentin Thouvenin
 */
public class Div extends BinaireArithmetique {

    public Div(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " / ";
    }

    @Override
	public int getType() {
	    return ENTIER;
    }
    
    @Override
	public String operation() {
		return " Division ";
	}
    
	@Override
	public String toMIPS() {
		StringBuilder div = new StringBuilder(150);
		int hash = hashCode();
		
		div.append(super.toMIPS());		
		
		div.append("# Gestion de la division par 0\n");
		div.append("beqz $v0, alors_" + hash + "\n");
		
		div.append("div $v0, $t8, $v0\n");
		div.append("j fin_" + hash + "\n");
		div.append("alors_" + hash + " :\n");
		div.append("# Message d'erreur car l'expression droite est egale a 0\n");
		div.append("li $v0, 4\n");
		div.append("la $a0, err_div\n");
		div.append("syscall\n");
		div.append("j end\n");
		div.append("fin_" + hash + " :\n");
				
		return div.toString();
	}
	
}
