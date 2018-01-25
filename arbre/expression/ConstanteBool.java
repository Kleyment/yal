package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class ConstanteBool extends Constante {
    
    public ConstanteBool(String texte, int n) {
        super(texte, n) ;
    }

	@Override
	public int getType() {
		return BOOLEEN;
	}
	
	@Override
	public String toMIPS() {
        StringBuilder sb = new StringBuilder(20);
		
		sb.append("# Constante Booléenne\n");
		sb.append("# On met la constante dans $v0\n");
		
		if (cste.equals("vrai")) {
			sb.append("li $v0, 1\n");
		}
		else {
		    sb.append("li $v0, 0\n");
		}
		
		return sb.toString();
	}

}
