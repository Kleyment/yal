package yal.arbre;

import java.util.ArrayList;

import yal.arbre.instruction.Instruction;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
public class BlocDInstructions extends ArbreAbstrait {
    
	/**
	 * Liste des instructions du bloc
	 */
	private ArrayList<Instruction> bloc;
	
    
    public BlocDInstructions(int n) {
        super(n);
        bloc = new ArrayList<Instruction>();
    }
    
    public void ajouter(Instruction a) {
        bloc.add(a);
    }
       
    @Override
    public void verifier() {
    	for (Instruction instr : bloc) {
    		instr.verifier();
    	}
    }
    
    @Override
    public String toMIPS() {
        StringBuilder mips = new StringBuilder(50);
    	
        mips.append("# initialisation de la base des variables\n");
        mips.append("move $s7, $sp");
        mips.append("\n\n");
        
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
