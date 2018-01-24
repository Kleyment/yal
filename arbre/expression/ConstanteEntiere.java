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

}
