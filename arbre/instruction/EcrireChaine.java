package yal.arbre.instruction;

public class EcrireChaine extends Instruction {

	private String chaine;
	
	
	public EcrireChaine(String csteChaine, int no) {
		super(no);
		chaine = csteChaine;
		chaine = chaine.substring(1, chaine.length() - 1);
		chaine = chaine.replace("\"\"", "\\\"");
		chaine = chaine.replace("\n", "\\n");
		chaine = "\"" + chaine + "\"";
	}

	@Override
	public void verifier() {
		
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(60);
		int hash = hashCode();
		
		sb.append("# Ecriture d'une chaine \n");
		sb.append(".data\n");
		sb.append("chaine_" + hash + " :\t");
		sb.append(".asciiz " + chaine + "\n");
		sb.append("\n");
		sb.append(".text\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, chaine_" + hash + "\n");
		sb.append("syscall\n");
		
		return sb.toString();
	}
	
}
