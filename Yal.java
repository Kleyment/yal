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
            
            StringBuilder mips = new StringBuilder(200);
            
            data(mips);
            text(mips, arbre);            
            end(mips);
            
            ecriture(mips.toString(), sortie(source));
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
            System.exit(1);
        }
    }

    public void data(StringBuilder mips) {
    	mips.append(".data\n");
        mips.append("err_div :\t");
        mips.append(".asciiz \"ERREUR EXECUTION :\\n\\t division par zéro\"\n");
        mips.append("vrai :\t");
        mips.append(".asciiz \"vrai\"\n");
        mips.append("faux :\t");
        mips.append(".asciiz \"faux\"\n");
        mips.append("\n");
    }
    
    public void text(StringBuilder mips, ArbreAbstrait arbre) {
    	mips.append(".text\n");
        mips.append("main :\n");      
        mips.append(arbre.toMIPS());
    }
    
    public void end(StringBuilder mips) {
    	mips.append("\n");
    	mips.append("end :\n");
        mips.append("# fin du programme\n");
        mips.append("move $v1, $v0\t");
        mips.append("# copie de $v0 dans $v1 pour permettre les tests de yal0\n");
        mips.append("li $v0, 10\t");
        mips.append("# retour au système\n");
        mips.append("syscall\n");
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
