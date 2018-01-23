package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
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
	public void verifier() {
		//TODO
	}

	@Override
	public String toMIPS() {
		StringBuilder sb=new StringBuilder();
		sb.append(gauche.toMIPS());
		sb.append("sw $v0,0($sp)");
		sb.append("add $sp, $sp, -4");
		sb.append(droite.toMIPS());
		sb.append("add $sp, $sp, 4");
		sb.append("lw $t8,($sp)");
		sb.append("$v0, $t8, $v0");		
		return sb.toString();
	}

}
