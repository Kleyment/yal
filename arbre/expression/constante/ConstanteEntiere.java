package yal.arbre.expression.constante;

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
	public String operation() {
		return " Constante Entière ";
	}
	
	@Override
	public String constante() {
		return cste;
	}
	
	@Override
	public String toMIPS() {
        return super.toMIPS();
	}
	
}
