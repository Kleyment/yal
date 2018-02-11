package yal.arbre.expression.binaire;

import yal.arbre.expression.Expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public abstract class Binaire extends Expression {
    
    protected Expression gauche;
    protected Expression droite;

    
    protected Binaire(Expression gauche, Expression droite) {
        super(gauche.getNoLigne());
        this.gauche = gauche;
        this.droite = droite;
    }
    
    public abstract String operateur();

    @Override
    public void verifier() {
    	gauche.verifier();
    	droite.verifier();
    }
    
    @Override
    public String toMIPS() {
        StringBuilder code = new StringBuilder(100);
		
        code.append("#" + operation() + "\n");
        
        code.append("# Calcul de la partie gauche\n");
        code.append(gauche.toMIPS());
		code.append("\n");
		
        code.append("# Empilement de la partie gauche\n");
        code.append("sw $v0, 0($sp)\n");
        code.append("add $sp, $sp, -4\n");
        code.append("\n");
        
		code.append("# Calcul de la partie droite\n");
		code.append(droite.toMIPS());
		code.append("\n");
		
		code.append("# Dépilement de la partie gauche\n");
		code.append("add $sp, $sp, 4\n");
		code.append("lw $t8, 0($sp)\n");
		code.append("\n");
		
		code.append("#" + operation() + "entre $v0 et $t8\n");
		
		return code.toString();
    }
    
    @Override
    public String toString() {
        return "(" + gauche + operateur() + droite + ")" ;
    }

}
