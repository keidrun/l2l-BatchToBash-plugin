/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.comverters;

import java.util.ArrayList;
import java.util.List;

import com.keidrun.l2l.plugins.batchtobash.element.Matched;
import com.keidrun.l2l.plugins.batchtobash.element.MatchedSub;
import com.keidrun.l2l.plugins.exception.BadGrammarException;

/**
 * Grammar Converter.
 * 
 * @author Keid
 */
public class GrammerConverter implements Converter {

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.keid.l2l.plugins.batchtobash.comverters.Converter#convert(java.lang.
     * String)
     */
    @Override
    public String convert(String sentence) {

        sentence = convertHead(sentence);
        sentence = convertEnv(sentence);
        sentence = convertSet(sentence);
        sentence = convertCurrent(sentence);

        return sentence;

    }

    private String convertHead(String sentence) {

        String keyword = "@echo off";
        String bashKeyword = "#!/bin/bash";

        String regEx = "\\s*?" + keyword + "\\s*?";
        Matched matched = matchAndCommentOut(sentence, regEx);
        List<MatchedSub> matchedList = matched.getMatchedList();
        if (matchedList.size() > 1) {
            throw new BadGrammarException("\"" + keyword + "\" must be only one.");
        } else if (matchedList.size() == 0) {
            return bashKeyword + "\n" + sentence;
        }

        MatchedSub sub = matchedList.get(0);
        String converted = bashKeyword;
        sub.setConverted(converted);

        List<MatchedSub> convertedList = new ArrayList<MatchedSub>();
        convertedList.add(sub);
        matched.setMatchedList(convertedList);

        return uncomment(matched);

    }

    private String convertEnv(String sentence) {

        String startWord = "\\%";
        String endWord = "\\%";
        String bashStartWord = "\\${";
        String bashEndWord = "}";

        String regEx = "\\s*?" + startWord + "\\w+" + endWord + "\\s*?";
        Matched matched = matchAndCommentOut(sentence, regEx);
        List<MatchedSub> matchedList = matched.getMatchedList();

        List<MatchedSub> convertedList = new ArrayList<MatchedSub>();
        for (MatchedSub sub : matchedList) {
            String converted = sub.getOriginal();
            converted = converted.replaceFirst(startWord, bashStartWord);
            converted = converted.replaceFirst(endWord, bashEndWord);
            sub.setConverted(converted);
            convertedList.add(sub);
        }
        matched.setMatchedList(convertedList);

        return uncomment(matched);

    }

    private String convertSet(String sentence) {

        String startWord = "set";
        String midWord = "=";
        String bashStartWord = "";
        String bashMidWord = "=";

        String regEx = "\\s*?" + startWord + "\\s+" + "\\S+" + midWord;
        Matched matched = matchAndCommentOut(sentence, regEx);
        List<MatchedSub> matchedList = matched.getMatchedList();

        List<MatchedSub> convertedList = new ArrayList<MatchedSub>();
        for (MatchedSub sub : matchedList) {
            String converted = sub.getOriginal();
            converted = converted.replaceFirst(startWord + "\\s+", bashStartWord);
            converted = converted.replaceFirst(midWord, bashMidWord);
            sub.setConverted(converted);
            convertedList.add(sub);
        }
        matched.setMatchedList(convertedList);

        return uncomment(matched);

    }

    private String convertCurrent(String sentence) {

        String keyword = "\\%\\~dp0";
        String bashKeyword = "\\.";

        String regEx = "\\s*?" + keyword + "\\s*?";
        Matched matched = matchAndCommentOut(sentence, regEx);
        List<MatchedSub> matchedList = matched.getMatchedList();

        List<MatchedSub> convertedList = new ArrayList<MatchedSub>();
        for (MatchedSub sub : matchedList) {
            String converted = sub.getOriginal();
            converted = converted.replaceFirst(keyword, bashKeyword);
            sub.setConverted(converted);
            convertedList.add(sub);
        }
        matched.setMatchedList(convertedList);

        return uncomment(matched);

    }

}
