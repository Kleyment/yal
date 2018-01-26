package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
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
	public String operation() {
		return " Ou Logique ";
	}
    
	@Override
	public String toMIPS() {
		StringBuilder ou = new StringBuilder(20);
		
        ou.append(super.toMIPS());
		ou.append("or $v0, $t8, $v0\n");
		
		return ou.toString();
	}

}
