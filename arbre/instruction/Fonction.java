package yal.arbre.instruction;

import yal.arbre.ArbreAbstrait;
import yal.arbre.BlocDInstructions;

public class Fonction extends ArbreAbstrait {
	
	private String idf;
	private String typeRetour;
	private String[] parametres;
	
	private BlocDInstructions li;
	
	
	public Fonction(int noLigne, BlocDInstructions li) {
		super(noLigne);
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

	@Override
	public String toString() {
		StringBuilder fonction = new StringBuilder(40);
		
		fonction.append(typeRetour);
		fonction.append(" ");
		fonction.append(idf);
		fonction.append("(");
		
		int dernier = parametres.length - 1;
		
		for (int i = 0; i < dernier; i ++) {
		    fonction.append(parametres[i]);
		    fonction.append(", ");
		}
		
		if (parametres.length > 0) {
			fonction.append(parametres[dernier]);
		}
		
		fonction.append("");
		fonction.append(") {");
		fonction.append(li.toString());
		fonction.append("}");
		fonction.append("\n");
		
		return fonction.toString();
	}
	
}
