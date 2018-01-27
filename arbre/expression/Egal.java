package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class Egal extends Comparaison {

    public Egal(Expression gauche, Expression droite) {
        super(gauche, droite);
    }
    
    @Override
    public String operateur() {
        return " == ";
    }

    @Override
	public String operation() {
		return " Egal ";
	}
    
	@Override
	public void verifier() throws AnalyseSemantiqueException {
		super.verifier();
		
		if (gauche.getType() != droite.getType()) {
            StringBuilder erreur = new StringBuilder(50);
	    	
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
		StringBuilder egal = new StringBuilder(100);
		
		egal.append(super.toMIPS());	
		egal.append("# En cas d'égalité, on met 1 dans $v0, sinon 0\n");
		egal.append("seq $v0, $v0, $t8\n");
		
		return egal.toString();
	}
    
}
