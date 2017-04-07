/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.exception;

/**
 * Bad grammar exception.
 * 
 * @author Keid
 */
public class BadGrammarException extends RuntimeException {

    /**
     * Serial version.
     */
    private static final long serialVersionUID = -7305960139938155448L;

    /**
     * Constructor.
     * 
     * @param message
     *            the reason of the exception
     */
    public BadGrammarException(String message) {
        super(message);
    }

}
