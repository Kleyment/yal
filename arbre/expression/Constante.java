package yal.arbre.expression;

/**
 * @author @author Clément Bellanger, Pierre génard, Valentin Thouvenin
 */
public abstract class Constante extends Expression {

    protected String cste;
    
    protected Constante(String texte, int n) {
        super(n);
        cste = texte;
    }

    @Override
    public void verifier() {
    	
    }
    
    @Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder();
		
		sb.append("# Constante\n");
		sb.append("# On met la constante dans $v0\n");
		sb.append("li $v0, " + cste + "\n");
		
		return sb.toString();
    }
    
    @Override
    public String toString() {
        return cste ;
    }
    
}
