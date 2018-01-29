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
                    
            /* Vérification de l'arbre abstrait */
            arbre.verifier(); 
            System.out.println("COMPILATION OK");
            
            /* Concaténation du code */
            StringBuilder code = new StringBuilder(200);
            
            code.append(".data\n");
            code.append("err_div:\t.asciiz \"ERREUR EXECUTION :\\n\\t division par zéro\"\n\n");
            code.append(".text\n");
            code.append("main :\n");
            code.append("# initialiser s7 avec sp (initialisation de la base des variables)\n");
            code.append("move $s7, $sp\n");
            code.append(arbre.toMIPS());
            code.append("\nend :\n");
            code.append("# fin du programme\n");
            code.append("move $v1, $v0\t# copie de $v0 dans $v1 pour permettre les tests de yal0\n");
            code.append("li $v0, 10\t# retour au système\n");
            code.append("syscall\n");
            
            /* Ecriture du code */
            ecriture(code.toString(), sortie(source));
        } 
        catch (FileNotFoundException ex) {
            System.err.println("Fichier " + source + " inexistant");
	        System.exit(1);
        }
        catch (AnalyseException ex) {
            System.err.println(ex.getMessage());
            System.exit(1);
        }
        catch (Exception ex) {
            Logger.getLogger(Yal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Crée le nom du fichier de sortie
     * @param source
     * @return sortie
     */
    public String sortie(String source) {
        StringBuilder sortie = new StringBuilder(10);
        int suffixe = source.lastIndexOf('.');
        
        if (suffixe < 1) {
        	sortie.append(source);
        }
        else {
        	sortie.append(source.substring(0, suffixe));
        }
        
        sortie.append(".mips");
        
        return sortie.toString();
    }
  
    /**
     * Ecriture du code MIPS
     * @param code
     * @param sortie
     */
    public void ecriture(String code, String sortie) {
    	try {
        	FileWriter flot = new FileWriter(sortie);
        	BufferedWriter tampon = new BufferedWriter(flot);
        	
            tampon.write(code);
        	
            tampon.close();
		    flot.close();          
        }
        catch (IOException e) { 
        	System.err.println("Erreur lors de l'écriture du code MIPS dans le fichier " + sortie);
	        System.exit(1);
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
