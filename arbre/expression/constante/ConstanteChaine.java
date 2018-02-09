package yal.arbre.expression.constante;

public class ConstanteChaine extends Constante {

	protected ConstanteChaine(String texte, int n) {
		super(texte, n);
	}

	@Override
	public String constante() {
		return cste;
	}

	@Override
	public int getType() {
		return CHAINE;
	}

	@Override
	public String operation() {
		return " Constante Chaine ";
	}
	
	public String toMIPS(){
		StringBuilder csteChaine = new StringBuilder(30);
		int hash = hashCode();
		
		csteChaine.append("\n");
		csteChaine.append("#" + operation() + "\n");
		csteChaine.append(".data\n");
		csteChaine.append("chaine"+hash+":	.asciiz "+this.cste+"\n");
		csteChaine.append(".text\n");
		csteChaine.append("\n");
		
		return csteChaine.toString();
	}

}
