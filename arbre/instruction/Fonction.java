package yal.arbre.instruction;

import yal.analyse.tds.TDS;
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
		fonction.append(idf+": \n");
		fonction.append("# On empile s7 \n");
		fonction.append("sw $s7, 0($sp)\n");
		fonction.append("add $sp, $sp, -4\n");
		
		fonction.append("# On empile le numéro de région \n");
		fonction.append("li $t8, "+TDS.getInstance().numeroRegion()+" \n");
		fonction.append("sw $t8, 0($sp)\n");
		fonction.append("add $sp, $sp, -4\n");
		
		fonction.append("move $s7, $sp \n");
		fonction.append("add $sp, $sp, -"+TDS.getInstance().tailleZoneDesVariables()+" \n");
		fonction.append(li.toMIPS());
		
		fonction.append("# On depile la zone des variables, puis jusqu'à s7 pour enfin faire un jump à l'adresse de retour \n");
		fonction.append("add $sp, $sp, "+TDS.getInstance().tailleZoneDesVariables()+" \n");
		fonction.append("add $sp, $sp, 12 \n");
		fonction.append("lw $sp, 0($sp) \n");
		fonction.append("lw $ra, $sp \n");
		fonction.append("jr $ra \n");
		
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
