package yal ;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
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
    
    public Yal(String source) {
        try {
            AnalyseurSyntaxique analyseur = new AnalyseurSyntaxique(new AnalyseurLexical(new FileReader(source)));
            ArbreAbstrait arbre = (ArbreAbstrait) analyseur.parse().value;
                     
            arbre.verifier(); 
            System.out.println("COMPILATION OK");
            
            StringBuilder code = new StringBuilder();
            
            code.append(".data\n");
            code.append("errDiv:\t.asciiz \"erreur division par zéro\"\n");
            code.append(".text\n");
            code.append("main :\n");
            code.append("# initialiser s7 avec sp (initialisation de la base des variables)\n");
            code.append("move $s7, $sp\n");
            code.append(arbre.toMIPS());
            code.append("end :\n");
            code.append("# fin du programme\n");
            code.append("li $v0, 10\t# retour au système\n");
            code.append("syscall\n");
    
            StringBuilder sortie = new StringBuilder();
            int suffixe = source.lastIndexOf('.');
            
            if (suffixe < 1) {
            	sortie.append(source);
            }
            else {
            	sortie.append(source.substring(0, suffixe));
            }
            
            sortie.append(".mips");
            
            /* Ecriture du code MIPS */
            try {
            	FileWriter flot = new FileWriter(sortie.toString());
            	BufferedWriter flotFiltre = new BufferedWriter(flot);
            	
            	flotFiltre.write(code.toString());
            	
            	flotFiltre.close();
    		    flot.close();          
            }
            catch (IOException e) { 
            	System.err.println("Erreur lors de l'écriture du code MIPS dans un fichier");
            }
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + source + " inexistant") ;
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
        
        new Yal(args[0]);
    }
    
}
