package yal.exceptions;

/**
 * @author brigitte wrobel-dautcourt
 */
@SuppressWarnings("serial")
public class AnalyseSyntaxiqueException extends AnalyseException {
 
    public AnalyseSyntaxiqueException(String m) {
        super("ERREUR SYNTAXIQUE :\n\t" + m) ;
    }

}
