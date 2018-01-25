package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class Inferieur extends Comparaison {

    public Inferieur(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public String operateur() {
        return " < ";
    }

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(100);
		
		sb.append("# Inferieur\n");
		
		Moins gMoinsd = new Moins(gauche, droite);
		
		sb.append(gMoinsd.toMIPS());
		sb.append("# Comparaison du resultat avec 0\n");
		sb.append("bltz $v0, alors_" + this.hashCode() + "\n");
		sb.append("# Si c'est inferieur a 0, on met 0 dans $v0\n");
		sb.append("li $v0, 0\n");
		sb.append("j fin_" + this.hashCode() + "\n");
		sb.append("# Si c'est superieur a 0, on met 1 dans $v0\n");
		sb.append("alors_" + this.hashCode() + " :\n");
		sb.append("li $v0, 1\n");
		sb.append("fin_" + this.hashCode() + " :\n");
		
		return sb.toString();
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
			erreur.append("L'opérande droite doit être entier : ");
	    	erreur.append(droite.getType());
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());	
		}
	}
	
}
