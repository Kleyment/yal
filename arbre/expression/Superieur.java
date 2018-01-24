package yal.arbre.expression;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public class Superieur extends Comparaison {

    public Superieur(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " > ";
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
		sb.append("# Superieur\n");
		Moins gMoinsd = new Moins(gauche, droite);
		sb.append(gMoinsd.toMIPS());
		sb.append("# Comparaison du resultat avec 0\n");
		sb.append("bgtz $v0, alors\n");
		sb.append("# Si c'est inferieur a 0, on met 0 dans $v0");
		sb.append("li $v0, 0\n");
		sb.append("j fin\n");
		sb.append("# Si c'est superieur a 0, on met 1 dans $v0");
		sb.append("alors\n");
		sb.append("li $v0, 1\n");
		sb.append("fin\n");
		return sb.toString();
	}
    
}
