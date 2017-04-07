/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.comverters;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.RandomStringUtils;

import com.keidrun.l2l.plugins.batchtobash.element.Matched;
import com.keidrun.l2l.plugins.batchtobash.element.MatchedSub;

/**
 * Converter.
 * 
 * @author Keid
 */
public interface Converter {

    /**
     * Convert.
     * 
     * @param sentence
     */
    /**
     * Convet.
     * 
     * @param the
     *            sentence
     * @return the converted sentence
     */
    public String convert(String sentence);

    /**
     * Match the regular expression and comment out them.
     * 
     * @param sentence
     *            the sentence of the program
     * @param regEx
     *            the regular expression
     * @return the matched object
     */
    default Matched matchAndCommentOut(String sentence, String regEx) {
        Matcher m = Pattern.compile(regEx).matcher(sentence);
        List<MatchedSub> matchedList = new ArrayList<MatchedSub>();

        String commentedSentende = sentence;
        while (m.find()) {
            MatchedSub sub = new MatchedSub();
            String temp = m.group();
            String randomValue = RandomStringUtils.randomAlphabetic(10);
            String commented = new StringBuilder().append("###").append(randomValue).append("###").toString();

            commentedSentende = commentedSentende.replaceFirst(temp, commented);

            sub.setOriginal(temp);
            sub.setCommented(commented);
            matchedList.add(sub);
        }

        return new Matched(sentence, commentedSentende, matchedList);
    }

    /**
     * Uncomment the commented sentence to the converted sentence.
     * 
     * @param matched
     *            the Matched object
     * @return
     */
    default String uncomment(Matched matched) {

        String convertedSentence = matched.getCommented();
        List<MatchedSub> subs = matched.getMatchedList();

        for (MatchedSub sub : subs) {
            convertedSentence = convertedSentence.replace(sub.getCommented(), sub.getConverted());
        }
        return convertedSentence;
    }

}
