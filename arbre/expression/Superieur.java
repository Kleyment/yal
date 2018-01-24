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
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		sb.append("# Superieur\n");
		Moins gMoinsd = new Moins(gauche, droite);
		sb.append(gMoinsd.toMIPS());
		sb.append("# Comparaison du resultat avec 0\n");
		sb.append("bgtz $v0, alors\n");
		sb.append("# Si c'est inferieur a 0, on met 0 dans $v0");
		sb.append("li $v0, 0\n");
		sb.append("j fin\n");
		sb.append("# Si c'est superieur a 0, on met 1 dans $v0");
		sb.append("alors\n");
		sb.append("li $v0, 1\n");
		sb.append("fin\n");
		return sb.toString();
	}

	@Override
	public void verifier() {
		if (gauche.getType() != ENTIER) {
			StringBuilder erreur = new StringBuilder();
			erreur.append("L'opérande gauche doit être entier : ");
	    	erreur.append(gauche.getType());
	    	
	    	throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
	    	
		} else if (droite.getType() != ENTIER) {
			StringBuilder erreur = new StringBuilder();
			erreur.append("L'opérande droite doit être entier : ");
	    	erreur.append(droite.getType());
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
		}
	}
    
}
