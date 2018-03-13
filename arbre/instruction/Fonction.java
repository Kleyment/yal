package yal.arbre.instruction;

import yal.arbre.BlocDInstructions;

public class Fonction extends Instruction{
	
	BlocDInstructions li;
	
	public Fonction(int no, BlocDInstructions li) {
		super(no);
		this.li = li;
	}

	@Override
	public void verifier() {
		li.verifier();
	}

	@Override
	public String toMIPS() {
		StringBuilder fonction = new StringBuilder();
		//int hash = hashCode();
		
		fonction.append("# Fonction\n");
		
		/*	recopier de mes prises de notes a modifier/verifier
		 * 	Appel de fonction: 
		 * 		sp <- sp-4
		 * 		jal étiquette
		 * 		dépiler dans $v0
		 * 
		 * 	fonc3:
		 * 		empiler $s7
		 *		empiler 3
		 * 		$s7 <- $sp
		 * 		$sp <- $sp+place variable
		 *		instruction de la fonction (li.toMIPS())
		 *		sp <- sp+12
		 *		depilé la base
		 *		lw $ra <- $sp
		 *		jr $ra
		 */
		
		return fonction.toString();
	}

}
