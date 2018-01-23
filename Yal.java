package yal ;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import yal.analyse.AnalyseurLexical;
import yal.analyse.AnalyseurSyntaxique;
import yal.arbre.ArbreAbstrait;
import yal.exceptions.AnalyseException;

/**
 * 24 mars 2015 
 * 
 * @author brigitte wrobel-dautcourt
 */

public class Yal {
    
    public Yal(String fichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(fichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
            System.err.println("expression stockée dans l'arbre : " + arbre);
            
            // à écrire pour yal0
            // arbre.verifier() ; 
            // System.out.println(arbre.toMIPS());
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + fichier + " inexistant") ;
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
        }
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Nombre incorrect d'arguments") ;
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>") ;
            System.exit(1) ;
        }
        new Yal(args[0]) ;
    }
    
}
