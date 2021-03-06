package yal.arbre;

import yal.analyse.tds.TDS;
import yal.arbre.instruction.Fonction;
import yal.arbre.instruction.Instruction;

public class Programme extends ArbreAbstrait {

	private String nom;
	private BlocDInstructions declarations;
	private BlocDInstructions instructions;
	private int tailleZoneDesVariables;
	
	public Programme(BlocDInstructions ld, BlocDInstructions li, String nom, int no) {
		super(no);
		declarations = ld;
		instructions = li;
		this.nom = nom;
	}
	
	public Programme(BlocDInstructions li, String nom, int no) {
		super(no);
		instructions = li;
		this.nom = nom;
	}

	public void base(StringBuilder mips) {
		this.tailleZoneDesVariables=TDS.getInstance().tailleZoneDesVariablesMain();
		mips.append("# Initialisation de la base des variables\n");
        mips.append("move $s7, $sp\n");
        mips.append("\n");
        
        mips.append("# Réservation de l'espace pour ");
        mips.append(tailleZoneDesVariables / 4);
        mips.append(" variable(s)");
        mips.append("\n");
        
        mips.append("addi $sp, $sp, ");
        mips.append(- tailleZoneDesVariables);
        mips.append("\n\n");
        
        mips.append("# Initialisations\n");
        mips.append("li $t8, 0\n");
        
        for (int i=1;i*4<=tailleZoneDesVariables;i++) {
        	mips.append("sw $t8, ");
        	mips.append(-i*4);
        	mips.append("($s7)\n");
        }
        
        /*for (int depl = 0; depl > - tailleZoneDesVariables; depl -= 4) {
        	mips.append("sw $t8, ");
        	mips.append(depl);
        	mips.append("($s7)\n");
        }*/
        
        mips.append("\n");
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
	
	public void end(StringBuilder mips) {
    	mips.append("end :\n");
        mips.append("# fin du programme\n");
        mips.append("move $v1, $v0\t");
        mips.append("# copie de $v0 dans $v1 pour permettre les tests de yal0\n");
        mips.append("li $v0, 10\t");
        mips.append("# retour au système\n");
        mips.append("syscall\n");
    }
	
	public void instructions(StringBuilder mips) {
		mips.append(instructions.toMIPS());	
	}
	
	public void fonctions(StringBuilder mips){	        
		mips.append(declarations.toMIPS());
	}
	
	public void main(StringBuilder mips) {
		mips.append(".text");
		mips.append("\n");
		mips.append("main :");
		mips.append("\n\n");
	}
	
	@Override
	public void verifier() {
		declarations.verifier();
		instructions.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder mips = new StringBuilder(200);
		
		data(mips);		
		main(mips);
		base(mips);
		instructions(mips);	
		end(mips);
		
		if (this.declarations != null) {
			fonctions(mips);
		}
		
		return mips.toString();
	}

	@Override
	public String toString() {
		StringBuilder yal = new StringBuilder(30);
		
		yal.append("Programme ");
		yal.append(nom);
		yal.append("\n");
		yal.append(instructions.toString());
		
		return yal.toString();
	}

}
