/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.converters;

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
     * Convet.
     * 
     * @param sentence
     *            the sentence
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

        String commentedSentence = sentence;
        while (m.find()) {
            MatchedSub sub = new MatchedSub();
            String temp = m.group();
            String randomValue = RandomStringUtils.randomAlphabetic(10);
            String comment = new StringBuilder().append("###").append(randomValue).append("###").toString();

            commentedSentence = commentedSentence.replaceFirst(Pattern.quote(temp), comment);

            sub.setOriginal(temp);
            sub.setCommented(comment);
            matchedList.add(sub);
        }

        return new Matched(sentence, commentedSentence, matchedList);
    }

    /**
     * Uncomment the commented sentence to the converted sentence.
     * 
     * @param matched
     *            the Matched object
     * @return the converted sentence
     */
    default String uncomment(Matched matched) {

        String convertedSentence = matched.getCommented();
        List<MatchedSub> subs = matched.getMatchedList();

        for (MatchedSub sub : subs) {
            convertedSentence = convertedSentence.replace(sub.getCommented(), sub.getConverted());
        }

        return convertedSentence;
    }

    /**
     * Match and replace.
     * 
     * @param sentence
     *            the sentence
     * @param regEx
     *            the regular expression
     * @param beforeWord
     *            the word before replace
     * @param afterWord
     *            the word after replace
     * @return the replaced sentence
     */
    default String matchAndReplace(String sentence, String regEx, String beforeWord, String afterWord) {

        Matched matched = matchAndCommentOut(sentence, regEx);
        List<MatchedSub> matchedList = matched.getMatchedList();

        List<MatchedSub> convertedList = new ArrayList<MatchedSub>();
        for (MatchedSub sub : matchedList) {
            String converted = sub.getOriginal();
            converted = converted.replaceFirst(beforeWord, afterWord);
            sub.setConverted(converted);
            convertedList.add(sub);
        }
        matched.setMatchedList(convertedList);

        return uncomment(matched);
    }

}
