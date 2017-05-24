/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.converters;

/**
 * Command Converter.
 * 
 * @author Keid
 */
public class CommandConverter implements Converter {

    private static final char HALF_WIDTH_SPACE = '\u0020';
    private static final String LINE_SEPARATOR_PATTERN = "\r\n|[\n\r\u2028\u2029\u0085]";

    /*
     * (non-Javadoc)
     * 
     * @see
     * com.keidrun.l2l.plugins.batchtobash.comverters.Converter#convert(java.
     * lang.String)
     */
    @Override
    public String convert(String sentence) {

        // Supported.
        sentence = sentence.replaceAll("\\A(cd|chdir|CD|CHDIR)\\s+/(d|D)\\s+", "cd ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(cd|chdir|CD|CHDIR)\\s+/(d|D)\\s+", " cd ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(cd|chdir|CD|CHDIR)\\s+/(d|D)\\s+", "\ncd ");
        sentence = sentence.replaceAll("\\A(cd|chdir|CD|CHDIR)\\s+", "cd ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(cd|chdir|CD|CHDIR)\\s+", " cd ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(cd|chdir|CD|CHDIR)\\s+", "\ncd ");

        sentence = sentence.replaceAll("\\A(cls|CLS)", "clear");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(cls|CLS)", " clear");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(cls|CLS)", "\nclear");

        sentence = sentence.replaceAll("\\A(cmd|CMD|command|COMMAND)\\s+/(c|C|k|K)\\s+", "bash ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(cmd|CMD|command|COMMAND)\\s+/(c|C|k|K)\\s+", " bash ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(cmd|CMD|command|COMMAND)\\s+/(c|C|k|K)\\s+",
                "\nbash ");

        sentence = sentence.replaceAll("\\A(cmd|CMD|command|COMMAND)\\s+", "bash ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(cmd|CMD|command|COMMAND)\\s+", " bash ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(cmd|CMD|command|COMMAND)\\s+", "\nbash ");

        sentence = sentence.replaceAll("\\A(comp|COMP|fc|FC)\\s+", "diff ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(comp|COMP|fc|FC)\\s+", " diff ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(comp|COMP|fc|FC)\\s+", "\ndiff ");

        sentence = sentence.replaceAll("\\A(copy|COPY|xcopy|XCOPY)\\s+/(s|S)\\s+", "cp -r ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(copy|COPY|xcopy|XCOPY)\\s+/(s|S)\\s+", " cp -r ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(copy|COPY|xcopy|XCOPY)\\s+/(s|S)\\s+", "\ncp -r ");
        sentence = sentence.replaceAll("\\A(copy|COPY|xcopy|XCOPY)\\s+", "cp ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(copy|COPY|xcopy|XCOPY)\\s+", " cp ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(copy|COPY|xcopy|XCOPY)\\s+", "\ncp ");

        sentence = sentence.replaceAll("\\A((echo|ECHO)\\.\\s+\\|\\s+(date|DATE|time|TIME))", "date");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}((echo|ECHO)\\.\\s+\\|\\s+(date|DATE|time|TIME))", " date");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}((echo|ECHO)\\.\\s+\\|\\s+(date|DATE|time|TIME))",
                "\ndate");

        sentence = sentence.replaceAll("\\A(del|DEL|erase|ERASE)\\s+", "rm ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(del|DEL|erase|ERASE)\\s+", " rm ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(del|DEL|erase|ERASE)\\s+", "\nrm ");

        sentence = sentence.replaceAll("\\A(deltree|DELTREE)\\s+/(y|Y)\\s+", "rm -rf ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(deltree|DELTREE)\\s+/(y|Y)\\s+", " rm -rf ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(deltree|DELTREE)\\s+/(y|Y)\\s+", "\nrm -rf ");

        sentence = sentence.replaceAll("\\A(deltree|DELTREE)\\s+", "rm -r ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(deltree|DELTREE)\\s+", " rm -r ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(deltree|DELTREE)\\s+", "\nrm -r ");

        sentence = sentence.replaceAll("\\A(dir|DIR)$", "ls");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(dir|DIR)$", " ls");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(dir|DIR)$", "\nls");

        sentence = sentence.replaceAll("\\A(echo|ECHO)\\s+", "echo ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(echo|ECHO)\\s+", " echo ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(echo|ECHO)\\s+", "\necho ");

        sentence = sentence.replaceAll("\\A(exit|EXIT)\\s+/(b|B)\\s+", "return ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(exit|EXIT)\\s+/(b|B)\\s+", " return ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(exit|EXIT)\\s+/(b|B)\\s+", "\nreturn ");

        sentence = sentence.replaceAll("\\A(exit|EXIT)", "exit");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(exit|EXIT)", " exit");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(exit|EXIT)", "\nexit");

        sentence = sentence.replaceAll("\\A(find|FIND|findstr|FINDSTR)\\s+/(n|N)\\s+", "grep -n ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(find|FIND|findstr|FINDSTR)\\s+/(n|N)\\s+", " grep -n ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(find|FIND|findstr|FINDSTR)\\s+/(n|N)\\s+",
                "\ngrep -n ");

        sentence = sentence.replaceAll("\\A(hostname|HOSTNAME)", "hostname");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(hostname|HOSTNAME)", " hostname");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(hostname|HOSTNAME)", "\nhostname");

        sentence = sentence.replaceAll("\\A(md|MD|mkdir|MKDIR)\\s+", "mkdir ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(md|MD|mkdir|MKDIR)\\s+", " mkdir ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(md|MD|mkdir|MKDIR)\\s+", "\nmkdir ");

        sentence = sentence.replaceAll("\\A(mem|MEM)", "ps");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(mem|MEM)", " ps");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(mem|MEM)", "\nps");

        sentence = sentence.replaceAll("\\A(mklink|MKLINK)\\s+/(h|H)\\s+", "ln ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(mklink|MKLINK)\\s+/(h|H)\\s+", " ln ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(mklink|MKLINK)\\s+/(h|H)\\s+", "\nln ");
        sentence = sentence.replaceAll("\\A(mklink|MKLINK)\\s+/(d|D|j|J)\\s+", "ln -s ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(mklink|MKLINK)\\s+/(d|D|j|J)\\s+", " ln -s ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(mklink|MKLINK)\\s+/(d|D|j|J)\\s+", "\nln -s ");
        sentence = sentence.replaceAll("\\A(mklink|MKLINK)\\s+", "ln -s ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(mklink|MKLINK)\\s+", " ln -s ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(mklink|MKLINK)\\s+", "\nln -s ");

        sentence = sentence.replaceAll("\\A(more|MORE)", "more");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(more|MORE)", " more");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "+(more|MORE)", "\nmore");

        sentence = sentence.replaceAll("\\A(mountvol|MOUNTVOL)", "mount");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(mountvol|MOUNTVOL)", " mount");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(mountvol|MOUNTVOL)", "\nmount");

        sentence = sentence.replaceAll("\\A(move|MOVE)\\s+/(y|Y)\\s+", "mv -f ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(move|MOVE)\\s+/(y|Y)\\s+", " mv -f ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(move|MOVE)\\s+/(y|Y)\\s+", "\nmv -f ");
        sentence = sentence.replaceAll("\\A(move|MOVE)\\s+", "mv ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(move|MOVE)\\s+", " mv ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(move|MOVE)\\s+", "\nmv ");

        // "Path" is the command if lower case, or the variable if upper case.
        sentence = sentence.replaceAll("\\Apath", "env");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}path", " env");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}path", "\nenv");

        sentence = sentence.replaceAll("\\A(pause|PAUSE)", "read -p \"Press [Enter] key to resume.\"");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(pause|PAUSE)",
                " read -p \"Press [Enter] key to resume.\"");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(pause|PAUSE)",
                "\nread -p \"Press [Enter] key to resume.\"");

        sentence = sentence.replaceAll("\\A(print|PRINT)\\s+", "lp ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(print|PRINT)\\s+", " lp ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(print|PRINT)\\s+", "\nlp ");

        sentence = sentence.replaceAll("\\A(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+/(q|Q)\\s+", "rm -rf ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+/(q|Q)\\s+", " rm -rf ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+/(q|Q)\\s+",
                "\nrm -rf ");
        sentence = sentence.replaceAll("\\A(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+", "rm -r ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+", " rm -r ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+", "\nrm -r ");
        sentence = sentence.replaceAll("\\A(rd|RD|rmdir|RMDIR)\\s+", "rm -r ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(rd|RD|rmdir|RMDIR)\\s+", " rm -r ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(rd|RD|rmdir|RMDIR)\\s+", "\nrm -r ");

        sentence = sentence.replaceAll("\\A(systeminfo|SYSTEMINFO)", "uname -a");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(systeminfo|SYSTEMINFO)", " uname -a");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(systeminfo|SYSTEMINFO)", "\nuname -a");

        sentence = sentence.replaceAll("\\A(tasklist|TASKLIST)", "ps");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(tasklist|TASKLIST)", " ps");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(tasklist|TASKLIST)", "\nps");

        sentence = sentence.replaceAll("\\A(taskkill|TASKKILL)", "kill");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(taskkill|TASKKILL)", " kill");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(taskkill|TASKKILL)", "\nkill");

        sentence = sentence.replaceAll("\\A(tree|TREE)\\s+/(a|A)\\s+/(f|F)", "tree");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(tree|TREE)\\s+/(a|A)\\s+/(f|F)", " tree");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(tree|TREE)\\s+/(a|A)\\s+/(f|F)", "\ntree");
        sentence = sentence.replaceAll("\\A(tree|TREE)\\s+/(f|F|a|A)", "tree");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(tree|TREE)\\s+/(f|F|a|A)", " tree");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(tree|TREE)\\s+/(f|F|a|A)", "\ntree");
        sentence = sentence.replaceAll("\\A(tree|TREE)", "tree");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(tree|TREE)", " tree");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(tree|TREE)", "\ntree");

        sentence = sentence.replaceAll("\\A(type|TYPE)\\s+", "cat ");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(type|TYPE)\\s+", " cat ");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(type|TYPE)\\s+", "\ncat ");

        // The version is CentOS/RedHat Enterprise.
        sentence = sentence.replaceAll("\\A(ver|VER)", "cat /etc/redhat-release");
        sentence = sentence.replaceAll(HALF_WIDTH_SPACE + "{1}(ver|VER)", " cat /etc/redhat-release");
        sentence = sentence.replaceAll(LINE_SEPARATOR_PATTERN + "{1}(ver|VER)", "\ncat /etc/redhat-release");

        // Not supported.
        sentence = sentence.replaceAll("(adddrv|ADDDRV)", "#$0");
        sentence = sentence.replaceAll("(attrib|ATTRIB)", "#$0");
        sentence = sentence.replaceAll("(chcp|CHCP)", "#$0");
        sentence = sentence.replaceAll("(chev|CHEV)", "#$0");
        sentence = sentence.replaceAll("(chkdsk|CHKDSK)", "#$0");
        sentence = sentence.replaceAll("(choice|CHOICE)", "#$0");
        sentence = sentence.replaceAll("(clip|CLIP)", "#$0");
        sentence = sentence.replaceAll("(color|COLOR)", "#$0");
        sentence = sentence.replaceAll("(deldrv|DELDRV)", "#$0");
        sentence = sentence.replaceAll("(diskcomp|DISKCOMP)", "#$0");
        sentence = sentence.replaceAll("(diskcopy|DISKCOPY)", "#$0");
        sentence = sentence.replaceAll("(doskey|DOSKEY)", "#$0");
        sentence = sentence.replaceAll("(expand|EXPAND)", "#$0");
        sentence = sentence.replaceAll("(format|FORMAT)", "#$0");
        sentence = sentence.replaceAll("(icacls|ICACLS)", "#$0");
        sentence = sentence.replaceAll("(label|LABEL)", "#$0");
        sentence = sentence.replaceAll("(lh|LH|loadhigh|LOADHIGH)", "#$0");
        sentence = sentence.replaceAll("(mode|MODE)", "#$0");
        sentence = sentence.replaceAll("(pushd|PUSHD)", "#$0");
        sentence = sentence.replaceAll("(popd|POPD)", "#$0");
        sentence = sentence.replaceAll("(prompt|PROMPT)", "#$0");
        sentence = sentence.replaceAll("(recover|RECOVER)", "#$0");
        sentence = sentence.replaceAll("(reg|REG)", "#$0");
        sentence = sentence.replaceAll("(ren|REN|rename|RENAME)", "#$0");
        sentence = sentence.replaceAll("(replace|REPLACE)", "#$0");
        sentence = sentence.replaceAll("(robocopy|ROBOCOPY)", "#$0");
        sentence = sentence.replaceAll("(scandisk|SCANDISK)", "#$0");
        sentence = sentence.replaceAll("(setver|SETVER)", "#$0");
        sentence = sentence.replaceAll("(sort|SORT)", "#$0");
        sentence = sentence.replaceAll("(start|START)", "#$0");
        sentence = sentence.replaceAll("(subst|SUBST)", "#$0");
        sentence = sentence.replaceAll("(switch|SWITCH)", "#$0");
        sentence = sentence.replaceAll("(sys|SYS)", "#$0");
        sentence = sentence.replaceAll("(title|TITLE)", "#$0");
        sentence = sentence.replaceAll("(verify|VERIFY)", "#$0");
        sentence = sentence.replaceAll("(vol|VOL)", "#$0");

        return sentence;
    }

}
