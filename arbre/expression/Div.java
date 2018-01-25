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
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(200);
		
		sb.append("# Division\n");
		
		sb.append("# Calcul de la partie gauche\n");
		sb.append(gauche.toMIPS());
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0, 0($sp)\n");
		sb.append("add $sp, $sp, -4\n");
		
		sb.append("# Calcul de la partie droite\n");
		sb.append(droite.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $t8, ($sp)\n");
		
		sb.append("# Gestion de la division par 0\n");
		sb.append("beqz $v0, alors_" + this.hashCode() + "\n");
		
		sb.append("# Division entre $v0 et $t8 -> $v0\n");
		sb.append("div $v0, $t8, $v0\n");
		sb.append("j fin_" + this.hashCode() + "\n");
		sb.append("alors_" + this.hashCode() + " :\n");
		sb.append("# Message d'erreur car l'expression droite est egale a 0\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, err_div\n");
		sb.append("syscall\n");
		sb.append("j end\n");
		sb.append("fin_" + this.hashCode() + " :\n");
				
		return sb.toString();
	}
	
}
