package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class Different extends Comparaison {

    public Different(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " != ";
    }

    @Override
	public String operation() {
		return " Différent ";
	}
    
    @Override
	public void verifier() throws AnalyseSemantiqueException {
    	super.verifier();
    	
		if (gauche.getType() != droite.getType()) {
            StringBuilder erreur = new StringBuilder(40);
	    	
	    	erreur.append("erreur de type :\t");
            erreur.append(gauche);
	    	erreur.append(operateur());
	    	erreur.append(droite);
	    	erreur.append("\n\tles opérandes à gauche et à droite doivent être de même type");
	    	
			throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder diff = new StringBuilder(40);
		
		diff.append(super.toMIPS());
		diff.append("# Si c'est différent, on met 1 dans $v0, sinon 0\n");
		diff.append("sne $v0, $v0, $t8\n");
		
		return diff.toString();
	}
  
}
