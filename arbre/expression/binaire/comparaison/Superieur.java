package yal.arbre.expression.binaire.comparaison;

import yal.arbre.expression.Expression;
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
		
		if (gauche.getType() != ENTIER || droite.getType() != ENTIER) {
	    	StringBuilder erreur = new StringBuilder(40);
	    	
	    	erreur.append("erreur de type :\t");
	    	erreur.append(gauche);
	    	erreur.append(operateur());
	    	erreur.append(droite);
	    	erreur.append("\n\t");
	    	erreur.append("les deux opérandes doivent être des entiers");
	    	
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
