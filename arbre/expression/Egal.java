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
            StringBuilder erreur = new StringBuilder(30);
	    	
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
		StringBuilder sb = new StringBuilder(150);
		
		sb.append("# Egal\n");
		
		sb.append("# Calcul de la partie gauche\n");
		sb.append(gauche.toMIPS());
		sb.append("# Empilement de la partie gauche\n");
		sb.append("sw $v0, 0($sp)\n");
		sb.append("add $sp, $sp, -4\n");
		sb.append("# Calcul de la partie droite\n");
		sb.append(droite.toMIPS());
		sb.append("# Dépilement de la partie gauche\n");
		sb.append("add $sp, $sp, 4\n");
		sb.append("lw $t8,($sp)\n");
		
		sb.append("# Comparaison entre $v0 et $t8\n");
		sb.append("beq $v0,$t8, alors_"+this.hashCode()+"\n");
		sb.append("# Si c'est different, on met 0 dans $v0\n");
		sb.append("li $v0, 0\n");
		sb.append("j fin_"+this.hashCode()+"\n");
		sb.append("# Si c'est egal, on met 1 dans $v0\n");
		sb.append("alors_"+this.hashCode()+":\n");
		sb.append("li $v0, 1\n");
		sb.append("fin_"+this.hashCode()+":\n");
		
		return sb.toString();
	}
    
}
