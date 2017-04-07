/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.element;

/**
 * Matched sub sentence.
 * 
 * @author Keid
 */
public class MatchedSub {

    private String original = "";
    private String commented = "";
    private String converted = "";

    /**
     * @return the original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @param original
     *            the original to set
     */
    public void setOriginal(String original) {
        this.original = original;
    }

    /**
     * @return the commented
     */
    public String getCommented() {
        return commented;
    }

    /**
     * @param commented
     *            the commented to set
     */
    public void setCommented(String commented) {
        this.commented = commented;
    }

    /**
     * @return the converted
     */
    public String getConverted() {
        return converted;
    }

    /**
     * @param converted
     *            the converted to set
     */
    public void setConverted(String converted) {
        this.converted = converted;
    }

}
