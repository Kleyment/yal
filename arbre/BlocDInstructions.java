package yal.arbre;

import java.util.ArrayList;

import yal.arbre.instruction.Fonction;
import yal.arbre.instruction.Instruction;

/**
 * Un bloc est une suite d'instructions à laquelle 
 * il est possible d'attacher une liste de déclarations.
 * Dans notre classe, on ne lui attache aucune déclaration.
 * 
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
    
      
    public ArrayList<Instruction> getBloc() {
		return bloc;
	}

	@Override
    public String toMIPS() {
        StringBuilder mips = new StringBuilder(100);
        
    	for (Instruction instr : bloc) {
    		//System.out.println(instr);
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
