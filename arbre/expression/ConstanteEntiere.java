package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class ConstanteEntiere extends Constante {
    
    public ConstanteEntiere(String texte, int n) {
        super(texte, n) ;
    }

	@Override
	public int getType() {
		return ENTIER;
	}

	@Override
	public String toMIPS() {
        StringBuilder sb = new StringBuilder(20);
		
		sb.append("# Constante Entière\n");
		sb.append("# On met la constante dans $v0\n");
		sb.append("li $v0, " + cste + "\n");
		
		return sb.toString();
	}
	
}
