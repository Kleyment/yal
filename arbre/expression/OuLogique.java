package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class OuLogique extends BinaireLogique {

    public OuLogique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " ou " ;
    }

	@Override
	public int getType() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void verifier() {
		// TODO Auto-generated method stub
		
	}

	 @Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# Ou logique\n");
		sb.append("# Calcul de la partie gauche\n");
		sb.append(gauche.toMIPS());
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0, 0($sp)");
		sb.append("add $sp, $sp, -4");
		sb.append("# Calcul de la partie droite\n");
		sb.append(droite.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4");
		sb.append("lw $t8,($sp)");
		sb.append("# Ou logique entre $v0 et $t8\n");
		sb.append("or $v0, $t8, $v0");
		
		return sb.toString();
	}
    
   

}
