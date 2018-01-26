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
	public String operation() {
		return " Inférieur ";
	}
    
	@Override
	public String toMIPS() {
		StringBuilder inf = new StringBuilder(40);
		
		inf.append(super.toMIPS());
		inf.append("# Si la partie gauche est inférieure à la droite, on met 1 dans $v0, sinon 0\n");
		inf.append("slt $v0, $t8, $v0\n");
		
		return inf.toString();
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
