package yal.arbre.expression;

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
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(150);
		
		sb.append("# Multiplication\n");
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
		sb.append("# Multiplication entre $v0 et $t8 -> $lo\n");
		sb.append("mult $v0, $t8\n");
		sb.append("# Resultat $lo -> $v0\n");
		sb.append("mflo $v0\n");
		
		return sb.toString();
	}

        @Override
	public int getType() {
	    return ENTIER;
    }
    
}
