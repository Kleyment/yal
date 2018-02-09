package yal.arbre.expression.constante;

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
	public String operation() {
		return " Constante Booléenne ";
	}
	
	@Override
	public String constante() {
		if (cste.equals("vrai")) {
			return "1";
		}
		else {
			return "0";
		}
	}
	
	@Override
	public String toMIPS() {
        return super.toMIPS();
	}

}
