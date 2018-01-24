package yal.exceptions;

/**
 * @author brigitte wrobel-dautcourt
 */
@SuppressWarnings("serial")
public abstract class AnalyseException extends RuntimeException {
    
    protected AnalyseException(String m) {
        super(m) ;
    }

}
