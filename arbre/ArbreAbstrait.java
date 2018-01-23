package yal.arbre;

/**
 * 3 déc. 2015
 *
 * @author brigitte wrobel-dautcourt
 */

public abstract class ArbreAbstrait {
    
    // numéro de ligne du début de l'instruction
    protected int noLigne ;
    
    protected ArbreAbstrait(int no) {
        noLigne = no ;
    }
    
    public int getNoLigne() {
            return noLigne ;
    }

    // public abstract void verifier() ;   à écrire pour yal0
    // public abstract String toMIPS() ;   à écrire pour yal0

}
