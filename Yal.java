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
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */

public class Yal {
    
    public Yal(String fichier) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(fichier)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
                     
            arbre.verifier(); 
            
            StringBuilder code = new StringBuilder();
            
            code.append(".text\n");
            code.append("main :\n");
            code.append("# initialiser s7 avec sp (initialisation de la base des variables)");
            code.append("move $s7, $sp");
            code.append(arbre.toMIPS());
            code.append("end :\n");
            code.append("# fin du programme");
            code.append("li $v0, 10      # retour au système");
            code.append("syscall");
    
            System.out.println(code.toString());
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
