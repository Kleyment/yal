package yal.arbre;

import java.util.ArrayList;

import yal.analyse.tds.TDS;
import yal.arbre.instruction.Instruction;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class BlocDInstructions extends ArbreAbstrait {
    
	/**
	 * Liste des instructions du bloc
	 */
	private ArrayList<Instruction> bloc;
	
    private int tailleZoneDesVariables;
    
    
    public BlocDInstructions(int n) {
        super(n);
        bloc = new ArrayList<Instruction>();
    }
    
    public void ajouter(Instruction a) {
        bloc.add(a);
    }
       
    @Override
    public void verifier() {
    	tailleZoneDesVariables = TDS.getInstance().tailleZoneDesVariables();
    	
    	for (Instruction instr : bloc) {
    		instr.verifier();
    	}
    }
    
    @Override
    public String toMIPS() {
        StringBuilder mips = new StringBuilder(100);
    	
        mips.append("# Initialisation de la base des variables\n");
        mips.append("move $s7, $sp\n");
        mips.append("\n");
        
        mips.append("# Réservation de l'espace pour ");
        mips.append(tailleZoneDesVariables / 4);
        mips.append(" variable(s)\n");
        mips.append("addi $sp, $sp, ");
        mips.append(- tailleZoneDesVariables);
        mips.append("\n\n");
        
        mips.append("# Initialisations\n");
        mips.append("li $t8, 0\n");
        
        for (int deplacement = 0; deplacement < tailleZoneDesVariables; deplacement += 4) {
        	mips.append("sw $t8, -");
        	mips.append(deplacement);
        	mips.append("($s7)\n");
        }
        
        mips.append("\n");
        
    	for (Instruction instr : bloc) {
			mips.append(instr.toMIPS());
			mips.append("\n");
		}
		
        return mips.toString() ;
    }

    @Override
    public String toString() {
    	StringBuilder yal = new StringBuilder(20);
    	
    	for (Instruction instr : bloc) {
			yal.append(instr.toString());
			yal.append("\n");
		}
		
        return yal.toString() ;
    }
    
}
