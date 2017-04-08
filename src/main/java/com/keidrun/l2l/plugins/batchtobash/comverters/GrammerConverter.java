/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.comverters;

import java.util.ArrayList;
import java.util.List;

import com.keidrun.l2l.plugins.batchtobash.element.Matched;
import com.keidrun.l2l.plugins.batchtobash.element.MatchedSub;

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
        sentence = convertKeywords(sentence);
        sentence = convertIf(sentence);
        sentence = convertForIn(sentence);

        return sentence;

    }

    private String convertHead(String sentence) {

        String keyword = "@echo off";
        String bashKeyword = "#!/bin/bash";

        String regEx = "\\s*" + keyword + "\\s*";
        Matched matched = matchAndCommentOut(sentence, regEx);
        List<MatchedSub> matchedList = matched.getMatchedList();
        if (matchedList.size() > 1) {
            throw new IllegalArgumentException("\"" + keyword + "\" must be only one.");
        } else if (matchedList.size() == 0) {
            return bashKeyword + "\n" + sentence;
        }

        MatchedSub sub = matchedList.get(0);
        String converted = sub.getOriginal();
        converted = converted.replaceAll(keyword, bashKeyword);
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

        String regEx = "\\s*" + startWord + "\\w+" + endWord + "\\s*";
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

        String startWord = "(set|SET)";
        String midWord = "=";
        String bashStartWord = "";
        String bashMidWord = "=";

        String regEx = "\\s*" + startWord + "\\s+" + "\\S+" + midWord;
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

    private String convertIf(String sentence) {

        // if elseif else
        String startWord = "(if|IF)";
        String closeWord = "\\(";
        String midWord = "\\)\\s+(else|ELSE)\\s+(if|IF)";
        String midLastWord = "\\)\\s+(else|ELSE)\\s+\\(";
        String endWord = "\\)";
        String bashStartWord = "if \\[";
        String bashCloseWord = "\\]; then";
        String bashMidWord = "elif \\[";
        String bashMidLastWord = "else";
        String bashEndWord = "fi";
        String regExOfElseIF = startWord + "\\s+\\S+\\s*\\S+\\s*\\S+\\s+" + closeWord + "\\s*\\n" + ".+\\n" + "("
                + midWord + "\\s+\\S+\\s*\\S+\\s*\\S+\\s+" + closeWord + "\\s*\\n" + ".+\\n" + ")*" + "(" + midLastWord
                + "\\s*\\n" + ".+\\n" + ")*" + endWord;

        Matched matched = matchAndCommentOut(sentence, regExOfElseIF);
        List<MatchedSub> matchedList = matched.getMatchedList();

        List<MatchedSub> convertedList = new ArrayList<MatchedSub>();
        for (MatchedSub sub : matchedList) {
            String converted = sub.getOriginal();
            converted = converted.replaceAll(midWord, bashMidWord);
            converted = converted.replaceAll(midLastWord, bashMidLastWord);
            converted = converted.replaceAll("^" + startWord, bashStartWord);
            converted = converted.replaceAll(closeWord, bashCloseWord);
            converted = converted.replaceAll(endWord, bashEndWord);

            sub.setConverted(converted);
            convertedList.add(sub);
        }
        matched.setMatchedList(convertedList);

        return uncomment(matched);
    }

    private String convertForIn(String sentence) {

        // for no option
        String startWord = "(for|FOR)";
        String midWord = "in\\s+\\(";
        String midCloseWord = "\\)\\s+do\\s+\\(";
        String endWord = "\\)";
        String bashStartWord = "for";
        String bashMidWord = "in `ls ";
        String bashMidCloseWord = "`; do";
        String bashEndWord = "done";
        String redExOfNoOpt = startWord + "\\s+\\S+\\s+" + midWord + "\\s*\\S+\\s*" + midCloseWord + "(\\n|.)+"
                + endWord;

        Matched matched = matchAndCommentOut(sentence, redExOfNoOpt);
        List<MatchedSub> matchedList = matched.getMatchedList();

        List<MatchedSub> convertedList = new ArrayList<MatchedSub>();
        for (MatchedSub sub : matchedList) {
            String converted = sub.getOriginal();
            converted = converted.replaceAll("^" + startWord, bashStartWord);
            converted = converted.replaceAll(midWord, bashMidWord);
            converted = converted.replaceAll(midCloseWord, bashMidCloseWord);
            converted = converted.replaceAll(endWord, bashEndWord);

            sub.setConverted(converted);
            convertedList.add(sub);
        }
        matched.setMatchedList(convertedList);

        return uncomment(matched);
    }

    private String convertKeywords(String sentence) {

        // comment
        sentence = sentence.replaceAll("(rem|REM)", "#");
        // file
        sentence = sentence.replaceAll("(exist|EXIST)", "-e");
        // path
        sentence = sentence.replaceAll("\\\\", "/");
        // arguments
        sentence = sentence.replaceAll("\\%0", "\\$0");
        sentence = sentence.replaceAll("\\%1", "\\$1");
        sentence = sentence.replaceAll("\\%2", "\\$2");
        sentence = sentence.replaceAll("\\%3", "\\$3");
        sentence = sentence.replaceAll("\\%4", "\\$4");
        sentence = sentence.replaceAll("\\%5", "\\$5");
        sentence = sentence.replaceAll("\\%6", "\\$6");
        sentence = sentence.replaceAll("\\%7", "\\$7");
        sentence = sentence.replaceAll("\\%8", "\\$8");
        sentence = sentence.replaceAll("\\%9", "\\$9");
        sentence = sentence.replaceAll("\\%\\*", "\\$\\@");
        // if condition
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*!==", "!==", " -ne ");
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*==", "==", " -eq ");
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*<=", "<=", " -le ");
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*<", "<", " -lt ");
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*>=", ">=", " -ge ");
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*>", ">", " -gt ");
        sentence = matchAndReplace(sentence, "if\\s*(not|NOT)", "(not|NOT)", "!");
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*&&", "&&", " -a ");
        sentence = matchAndReplace(sentence, "if\\s*\\S+\\s*\\|\\|", "\\|\\|", " -o ");
        // for variable
        sentence = sentence.replaceAll("\\%\\%", "");
        // others
        sentence = sentence.replaceAll("\\%\\~dp0", "`dirname \\$0`");

        return sentence;
    }

}
