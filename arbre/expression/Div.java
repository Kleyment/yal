package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
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
	public void verifier() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toMIPS() {
		StringBuilder sb=new StringBuilder();
		sb.append("# Division\n");
		sb.append("# Calcul de la partie gauche\n");
		sb.append(gauche.toMIPS());
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0,0($sp)\n");
		sb.append("add $sp, $sp, -4\n");
		sb.append("# Calcul de la partie droite\n");
		sb.append(droite.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $t8,($sp)\n");
		sb.append("# Division entre $v0 et $t8 -> $v0\n");
		sb.append("div $v0, $t8, $v0\n");		
		return sb.toString();
	}
    
}
