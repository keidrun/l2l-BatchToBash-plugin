/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.element;

import java.util.List;

/**
 * Matched sentence.
 * 
 * @author Keid
 */
public class Matched {

    private String original;
    private String commented;
    private List<MatchedSub> matchedList;

    /**
     * Constructor.
     * 
     * @param original
     *            the original sentence
     * @param commented
     *            the commented sentence
     * @param matchedList
     *            the MatchedSub list
     */
    public Matched(String original, String commented, List<MatchedSub> matchedList) {
        this.original = original;
        this.commented = commented;
        this.matchedList = matchedList;
    }

    /**
     * @param matchedList
     *            the matchedList to set
     */
    public void setMatchedList(List<MatchedSub> matchedList) {
        this.matchedList = matchedList;
    }

    /**
     * @return the original
     */
    public String getOriginal() {
        return original;
    }

    /**
     * @return the commented
     */
    public String getCommented() {
        return commented;
    }

    /**
     * @return the matchedList
     */
    public List<MatchedSub> getMatchedList() {
        return matchedList;
    }

}
