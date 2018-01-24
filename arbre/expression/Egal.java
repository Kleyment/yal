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
	public void verifier() throws AnalyseSemantiqueException {
		if (gauche.getType() != droite.getType()) {
            StringBuilder erreur = new StringBuilder();
	    	
	    	erreur.append("erreur de type : ");
	    	erreur.append(gauche.getType());
	    	erreur.append(operateur());
	    	erreur.append(droite.getType());
	    	
			throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
		}
	}

	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# Egal\n");
		
		sb.append("# Calcul de la partie gauche\n");
		sb.append(gauche.toMIPS());
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0, 0($sp)");
		sb.append("add $sp, $sp, -4");
		sb.append("# Calcul de la partie droite\n");
		sb.append(droite.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4");
		sb.append("lw $t8,($sp)");
		
		sb.append("# Comparaison entre $v0 et $t8\n");
		sb.append("beq $v0,$t8, alors\n");
		sb.append("# Si c'est different, on met 0 dans $v0");
		sb.append("li $v0, 0\n");
		sb.append("j fin\n");
		sb.append("# Si c'est egal, on met 1 dans $v0");
		sb.append("alors\n");
		sb.append("li $v0, 1\n");
		sb.append("fin\n");
		
		return sb.toString();
	}
    
}
