package yal.arbre.instruction;

public class EcrireChaine extends Instruction {

	private String chaine;
	
	
	public EcrireChaine(String chaine, int no) {
		super(no);
		this.chaine = chaine;	
	}

	@Override
	public void verifier() {
		
	}
	
	@Override
	public String toMIPS() {
		StringBuilder sb = new StringBuilder(50);
		int hash = hashCode();
		
		sb.append("# Ecriture d'une chaine \n");
		sb.append(".data\n");
		sb.append("chaine_" + hash + " :\t");
		sb.append(".asciiz \"" + chaine + "\"\n");
		sb.append(".text\n");
		sb.append("li $v0, 4\n");
		sb.append("la $a0, chaine_" + hash + "\n");
		sb.append("syscall\n");
		
		return sb.toString();
	}
	
}
