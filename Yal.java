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
                    
            /* Vérification du code */
            arbre.verifier(); 
            System.out.println("COMPILATION OK");
            
            /* Concaténation du code */
            StringBuilder code = new StringBuilder(200);
            
            code.append(".data\n");
            code.append("err_div:\t.asciiz \"ERREUR EXECUTION :\\n\\t division par zéro\"\n");
            code.append(".text\n");
            code.append("main :\n");
            code.append("# initialiser s7 avec sp (initialisation de la base des variables)\n");
            code.append("move $s7, $sp\n");
            code.append(arbre.toMIPS());
            code.append("end :\n");
            code.append("# fin du programme\n");
            code.append("move $v1, $v0\t# copie de v0 dans v1 pour permettre les tests de yal0\n");
            code.append("li $v0, 10\t# retour au système\n");
            code.append("syscall\n");
    
            /* Crée le nom du fichier de sortie */
            StringBuilder sortie = new StringBuilder(10);
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
            	BufferedWriter tampon = new BufferedWriter(flot);
            	
                tampon.write(code.toString());
            	
                tampon.close();
    		    flot.close();          
            }
            catch (IOException e) { 
            	System.err.println("Erreur lors de l'écriture du code MIPS dans le fichier " + sortie);
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
            System.err.println("Nombre incorrect d'arguments");
            System.err.println("\tjava -jar yal.jar <fichierSource.yal>");
            System.exit(1);
        }
        
        new Yal(args[0]);
    }
    
}
