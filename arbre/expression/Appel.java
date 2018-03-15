package yal.arbre.expression;

import java.util.ArrayList;

public class Appel extends Expression {

	private String idf;
	private String type;
	
	
	public Appel(int n) {
		super(n);
	}

	public Appel(ArrayList<Expression> par, int n) {
		super(n);
	}
	
	@Override
	public String getType() {
		return type;
	}

	@Override
	public String operation() {
		return " Appel ";
	}

	@Override
	public void verifier() {

	}

	@Override
	public String toMIPS() {
		StringBuilder appel = new StringBuilder(50);
		appel.append("# Appel de fonction "+idf+" \n");
		appel.append("add $sp, -4 \n");
		appel.append("# On sauvegarde l'adresse de la prochaine instruction dans ra et on saute à l'adresse de la fonction \n");
		appel.append("jal "+idf+" \n");
		 
		appel.append("# On depile la valeur de retour de la fonction dans v0 \n");
		appel.append("add $sp, $sp, 4\n");
		appel.append("lw $v0, 0($sp)\n");
		appel.append("jr $ra\n");
		
		 /* 	Appel de fonction: 
		 * 		sp <- sp-4
		 * 		jal étiquette
		 * 		dépiler dans $v0
		 */
		return appel.toString();
	}

	@Override
	public String toString() {
		StringBuilder appel = new StringBuilder(20);
		
		appel.append(idf);
		appel.append("(");
		// paramètres
		appel.append(")");
		
		return appel.toString();
	}

}
