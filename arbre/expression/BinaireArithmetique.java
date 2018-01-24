package yal.arbre.expression;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public abstract class BinaireArithmetique extends Binaire {

    protected BinaireArithmetique(Expression gauche, Expression droite) {
        super(gauche, droite);
    }

    @Override
    public void verifier() throws AnalyseSemantiqueException {
	if (gauche.getType() != ENTIER || droite.getType() != ENTIER) {
	    throw new AnalyseSemantiqueException("erreur de type : " + gauche.getType() + operateur() + droite.getType());
	}		
    }
  
}
