package yal.arbre.instruction;

import yal.analyse.tds.TDS;
import yal.analyse.tds.entree.EntreeVariable;
import yal.analyse.tds.symbole.Symbole;
import yal.arbre.expression.Expression;
import yal.exceptions.AnalyseSemantiqueException;

public class AffectationTableau extends Instruction {

	private Expression expIndice;
	private Expression exp;
	private String idf;
	private String type;
	private int deplacement;
	
	
	public AffectationTableau(String idf, Expression expIndice, Expression exp) {
		super(exp.getNoLigne());
		this.expIndice=expIndice;
		this.exp = exp;
		this.idf = idf;
	}


	@Override
	public void verifier() {
		if (!expIndice.getType().equals("entier")) {
			throw new AnalyseSemantiqueException(getNoLigne(), "Erreur l'expression de l'indice n'est pas entier");
		}
		//System.out.println("Affect verifier()"+idf);
		EntreeVariable e = new EntreeVariable(idf);
		Symbole s = TDS.getInstance().identifier(e);
		if (s == null) {
			throw new AnalyseSemantiqueException(getNoLigne(), "aucune déclaration de `" + idf + "`");
		}
		
		type = s.getType();
		deplacement = s.getDeplacement();	
		exp.verifier();
		expIndice.verifier();
		
		if (!type.equals(exp.getType())) {
			StringBuilder erreur = new StringBuilder(40);
			
			erreur.append("erreur de type :\t");
			erreur.append(idf);
			erreur.append(" = ");
			erreur.append(exp.toString());
			erreur.append("\n\t");
	    	erreur.append("les deux opérandes doivent être de même type");
	    	
			throw new AnalyseSemantiqueException(getNoLigne(),erreur.toString());
		}
	}


	@Override
	public String toMIPS() {
		StringBuilder aff = new StringBuilder(50);
		//System.out.println("AFFECT"+deplacement);
		aff.append("# Affectation\n");
		aff.append("# On calcule la valeur de l'indice");
		aff.append(expIndice.toMIPS());
		aff.append("# On multiplie la valeur de l'indice par 4");
		aff.append("li $t8, 4 \n");
		aff.append("mult $v0, $t8\n");
		aff.append("mflo $v0\n");
		aff.append("# Empilement de la partie gauche\n");
		aff.append("sw $v0, 0($sp)\n");
		aff.append("add $sp, $sp, -4\n");   
		aff.append("# Calcul de la partie droite\n");
		aff.append(exp.toMIPS());
		aff.append("# Dépilement de la partie gauche\n");
		aff.append("add $sp, $sp, 4\n");
		aff.append("lw $t8, 0($sp)\n");
		//TODO
		//aff.append("add")
		aff.append("sw $v0, ");
		aff.append(deplacement);
		aff.append("($s7)\n");
		
        return aff.toString();
	}
}
