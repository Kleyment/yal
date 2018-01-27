package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
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
	public String operation() {
		return " Supérieur ";
	}

	@Override
	public void verifier() {
		super.verifier();
		
		if (gauche.getType() != ENTIER) {
			StringBuilder erreur = new StringBuilder(30);
			
			erreur.append("L'opérande gauche doit être entier : ");
	    	erreur.append(gauche.getType());
	    	
	    	throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
	    	
		} else if (droite.getType() != ENTIER) {
			StringBuilder erreur = new StringBuilder(30);
			
			erreur.append("L'opérande droit doit être entier : ");
	    	erreur.append(droite.getType());
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
		}
	}
	
	@Override
	public String toMIPS() {
        StringBuilder sup = new StringBuilder(100);
		
        sup.append(super.toMIPS());
        sup.append("# Si la partie gauche est supérieure à la droite, on met 1 dans $v0, sinon 0\n");
        sup.append("sgt $v0, $t8, $v0\n");
		
		return sup.toString();
	}
    
}
