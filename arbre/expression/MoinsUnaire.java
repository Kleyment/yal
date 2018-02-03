package yal.arbre.expression;

import yal.exceptions.AnalyseSemantiqueException;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class MoinsUnaire extends Unaire {
    
    public MoinsUnaire(Expression expr) {
        super(expr);
    }

    @Override
    public String operateur() {
        return "- " ;
    }
 
	@Override
	public int getType() {
		return ENTIER;
	}

	@Override
	public String operation() {
		return " Moins Unaire ";
	}
	
	@Override
    public void verifier() throws AnalyseSemantiqueException {   	
    	super.verifier();
    	
	    if (expression.getType() != ENTIER) {
	    	StringBuilder erreur = new StringBuilder(25);
	    	
	    	erreur.append("erreur de type :\t");
	    	erreur.append(operateur());
	    	erreur.append(expression);
	    	erreur.append("\n\t");
	    	erreur.append("l'opérande doit être entier");
	    	
	        throw new AnalyseSemantiqueException(getNoLigne(), erreur.toString());
	    }		
    }
	
	@Override
	public String toMIPS() {
		StringBuilder moins = new StringBuilder(100);
		
		moins.append(super.toMIPS());
		
		moins.append("#" + operation() + "sur $v0\n");
		moins.append("# Equivalent à sub $v0, 0, $v0\n");
		moins.append("neg $v0, $v0\n");
		
		return moins.toString();
	}

}
