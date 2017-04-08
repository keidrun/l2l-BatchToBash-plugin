/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.comverters;

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
        sentence = sentence.replaceAll("(adddrv|ADDDRV)", "#adddrv");
        sentence = sentence.replaceAll("(attrib|ATTRIB)", "#attrib");
        sentence = sentence.replaceAll("(chcp|CHCP)", "#chcp");
        sentence = sentence.replaceAll("(chev|CHEV)", "#chev");
        sentence = sentence.replaceAll("(chkdsk|CHKDSK)", "#chkdsk");
        sentence = sentence.replaceAll("(choice|CHOICE)", "#choice");
        sentence = sentence.replaceAll("(clip|CLIP)", "#clip");
        sentence = sentence.replaceAll("(color|COLOR)", "#color");
        sentence = sentence.replaceAll("(deldrv|DELDRV)", "#deldrv");
        sentence = sentence.replaceAll("(diskcomp|DISKCOMP)", "#diskcomp");
        sentence = sentence.replaceAll("(diskcopy|DISKCOPY)", "#diskcopy");
        sentence = sentence.replaceAll("(doskey|DOSKEY)", "#doskey");
        sentence = sentence.replaceAll("(expand|EXPAND)", "#expand");
        sentence = sentence.replaceAll("(format|FORMAT)", "#format");
        sentence = sentence.replaceAll("(icacls|ICACLS)", "#Icacls");
        sentence = sentence.replaceAll("(label|LABEL)", "#label");
        sentence = sentence.replaceAll("(lh|LH|loadhigh|LOADHIGH)", "#lh");
        sentence = sentence.replaceAll("(mode|MODE)", "#mode");
        sentence = sentence.replaceAll("(pushd|PUSHD)", "#pushd");
        sentence = sentence.replaceAll("(popd|POPD)", "#popd");
        sentence = sentence.replaceAll("(prompt|PROMPT)", "#prompt");
        sentence = sentence.replaceAll("(recover|RECOVER)", "#recover");
        sentence = sentence.replaceAll("(reg|REG)", "#reg");
        sentence = sentence.replaceAll("(ren|REN|rename|RENAME)", "#ren");
        sentence = sentence.replaceAll("(replace|REPLACE)", "#replace");
        sentence = sentence.replaceAll("(robocopy|ROBOCOPY)", "#robocopy");
        sentence = sentence.replaceAll("(scandisk|SCANDISK)", "#scandisk");
        sentence = sentence.replaceAll("(setver|SETVER)", "#setver");
        sentence = sentence.replaceAll("(sort|SORT)", "#sort");
        sentence = sentence.replaceAll("(start|START)", "#start");
        sentence = sentence.replaceAll("(subst|SUBST)", "#subst");
        sentence = sentence.replaceAll("(switch|SWITCH)", "#switch");
        sentence = sentence.replaceAll("(sys|SYS)", "#sys");
        sentence = sentence.replaceAll("(title|TITLE)", "#title");
        sentence = sentence.replaceAll("(verify|VERIFY)", "#verify");
        sentence = sentence.replaceAll("(vol|VOL)", "#vol");

        return sentence;
    }

}
