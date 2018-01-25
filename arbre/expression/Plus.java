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
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(100);
		
		sb.append("# Addition\n");
		sb.append("# Calcul de la partie gauche\n");
		sb.append(gauche.toMIPS());
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0, 0($sp)\n");
		sb.append("add $sp, $sp, -4\n");
		sb.append("# Calcul de la partie droite\n");
		sb.append(droite.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $t8,($sp)\n");
		sb.append("# Addition entre $v0 et $t8\n");
		sb.append("add $v0, $t8, $v0\n");	
		
		return sb.toString();
	}
    
}
