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
        sentence = sentence.replaceAll("(cd|chdir|CD|CHDIR)\\s+/(d|D)\\s+", "cd ");
        sentence = sentence.replaceAll("(cd|chdir|CD|CHDIR)\\s+", "cd ");
        sentence = sentence.replaceAll("(cls|CLS)", "clear");
        sentence = sentence.replaceAll("(cmd|CMD|command|COMMAND)\\s+/(c|C|k|K)\\s+", "bash ");
        sentence = sentence.replaceAll("(cmd|CMD|command|COMMAND)\\s+", "bash ");
        sentence = sentence.replaceAll("(comp|COMP|fc|FC)\\s+", "diff ");
        sentence = sentence.replaceAll("(copy|COPY|xcopy|XCOPY)\\s+/(s|S)\\s+", "cp -r ");
        sentence = sentence.replaceAll("(copy|COPY|xcopy|XCOPY)\\s+", "cp ");
        sentence = sentence.replaceAll("((echo|ECHO)\\.\\s+\\|\\s+(date|DATE|time|TIME))", "date");
        sentence = sentence.replaceAll("(del|DEL|erase|ERASE)\\s+", "rm ");
        sentence = sentence.replaceAll("(deltree|DELTREE)\\s+/(y|Y)\\s+", "rm -rf ");
        sentence = sentence.replaceAll("(deltree|DELTREE)\\s+", "rm -r ");
        sentence = sentence.replaceAll("(dir|DIR)$", "ls");
        sentence = sentence.replaceAll("(echo|ECHO)\\s+", "echo ");
        sentence = sentence.replaceAll("(exit|EXIT)\\s+/(b|B)\\s+", "return ");
        sentence = sentence.replaceAll("(exit|EXIT)", "exit");
        sentence = sentence.replaceAll("(find|FIND|findstr|FINDSTR)\\s+/(n|N)\\s+", "grep -n ");
        sentence = sentence.replaceAll("(hostname|HOSTNAME)", "hostname");
        sentence = sentence.replaceAll("(md|MD|mkdir|MKDIR)\\s+", "mkdir ");
        sentence = sentence.replaceAll("(mem|MEM)", "ps");
        sentence = sentence.replaceAll("(mklink|MKLINK)\\s+/(h|H)\\s+", "ln ");
        sentence = sentence.replaceAll("(mklink|MKLINK)\\s+/(d|D|j|J)\\s+", "ln -s ");
        sentence = sentence.replaceAll("(mklink|MKLINK)\\s+", "ln -s ");
        sentence = sentence.replaceAll("(more|MORE)", "more");
        sentence = sentence.replaceAll("(mountvol|MOUNTVOL)", "mount");
        sentence = sentence.replaceAll("(move|MOVE)\\s+/(y|Y)\\s+", "mv -f ");
        sentence = sentence.replaceAll("(move|MOVE)\\s+", "mv ");
        // "Path" is the command if lower case, or the variable if upper case.
        sentence = sentence.replaceAll("path", "env");
        sentence = sentence.replaceAll("(pause|PAUSE)", "read -p \"Press [Enter] key to resume.\"");
        sentence = sentence.replaceAll("(print|PRINT)\\s+", "lp ");
        sentence = sentence.replaceAll("(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+/(q|Q)\\s+", "rm -rf ");
        sentence = sentence.replaceAll("(rd|RD|rmdir|RMDIR)\\s+/(s|S)\\s+", "rm -r ");
        sentence = sentence.replaceAll("(rd|RD|rmdir|RMDIR)\\s+", "rm -r ");
        sentence = sentence.replaceAll("(systeminfo|SYSTEMINFO)", "uname -a");
        sentence = sentence.replaceAll("(tasklist|TASKLIST)", "ps");
        sentence = sentence.replaceAll("(taskkill|TASKKILL)", "kill");
        sentence = sentence.replaceAll("(tree|TREE)\\s+/(a|A)\\s+/(f|F)", "tree");
        sentence = sentence.replaceAll("(tree|TREE)\\s+/(f|F|a|A)", "tree");
        sentence = sentence.replaceAll("(tree|TREE)", "tree");
        sentence = sentence.replaceAll("(type|TYPE)\\s+", "cat ");
        // The version is CentOS/RedHat Enterprise.
        sentence = sentence.replaceAll("(ver|VER)", "cat /etc/redhat-release");

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
