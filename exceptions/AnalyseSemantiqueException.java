package yal.exceptions;

/**
 * @author Clément Bellanger, Pierre Génard, Valentin Thouvenin
 */
@SuppressWarnings("serial")
public class AnalyseSemantiqueException extends AnalyseException {

	public AnalyseSemantiqueException(int ligne, String m) {
		super("ERREUR SEMANTIQUE :\n\tligne " + ligne + "\n\t" + m);
	}

}
