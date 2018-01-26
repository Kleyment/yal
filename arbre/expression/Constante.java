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

    public abstract String constante();
    
    @Override
    public void verifier() {
    	/* Rien à faire */
    }
    
    @Override
    public String toMIPS() {
    	StringBuilder constante = new StringBuilder(30);
    	
    	constante.append("#" + operation() + "\n");
    	constante.append("# On met la constante dans $v0\n");
    	constante.append("li $v0, " + constante() + "\n");
    	
    	return constante.toString();
    }
    
    @Override
    public String toString() {
        return cste;
    }
    
}
