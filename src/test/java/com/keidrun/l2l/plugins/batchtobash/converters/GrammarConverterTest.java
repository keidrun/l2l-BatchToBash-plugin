package com.keidrun.l2l.plugins.batchtobash.converters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.keidrun.l2l.plugins.batchtobash.converters.Converter;
import com.keidrun.l2l.plugins.batchtobash.converters.GrammarConverter;

@RunWith(Enclosed.class)
public class GrammarConverterTest {

    @RunWith(Theories.class)
    public static class convert {
        static Converter sut;

        @Before
        public void setup() throws Exception {
            sut = new GrammarConverter();
        }

        // @formatter:off
        @DataPoints
        public static Fixture[] FIXTUREs = {
                // head
                new Fixture("@echo off","#!/bin/bash"),
                new Fixture(" @echo off "," #!/bin/bash "),
                new Fixture("@echo off \nxxx","#!/bin/bash \nxxx"),
                new Fixture("xxx","#!/bin/bash\nxxx"),
                // env
                new Fixture("@echo off\n%AAA%","#!/bin/bash\n${AAA}"),
                new Fixture("@echo off\n %bbb% ","#!/bin/bash\n ${bbb} "),
                new Fixture("@echo off\ncd %CcC%\ncd %d001%","#!/bin/bash\ncd ${CcC}\ncd ${d001}"),
                // set
                new Fixture("@echo off\nset AAA=%BBB%","#!/bin/bash\nAAA=${BBB}"),
                new Fixture("@echo off\n set bbb=%ccc% ","#!/bin/bash\n bbb=${ccc} "),
                new Fixture("@echo off\nset DDD=EEE\nset fff=eee","#!/bin/bash\nDDD=EEE\nfff=eee"),
                new Fixture("@echo off\nSET GGG=HHH\nSET ggg=hhh","#!/bin/bash\nGGG=HHH\nggg=hhh"),
                // current
                new Fixture("@echo off\necho %~dp0","#!/bin/bash\necho `dirname $0`"),
                new Fixture("@echo off\necho %~dp0 ","#!/bin/bash\necho `dirname $0` "),
                new Fixture("@echo off\necho %~dp0\necho %~dp0","#!/bin/bash\necho `dirname $0`\necho `dirname $0`"),
                // comment
                new Fixture("@echo off\nrem aaa","#!/bin/bash\n# aaa"),
                new Fixture("@echo off\nrem BBB ","#!/bin/bash\n# BBB "),
                new Fixture("@echo off\nrem ccc\nrem DDD","#!/bin/bash\n# ccc\n# DDD"),
                // file exist
                new Fixture("@echo off\nif exist aaaaa.txt","#!/bin/bash\nif -e aaaaa.txt"),
                new Fixture("@echo off\nif EXIST aaaaa.txt ","#!/bin/bash\nif -e aaaaa.txt "),
                new Fixture("@echo off\nif exist aaaaa.txt\nif exist bbbbb.txt","#!/bin/bash\nif -e aaaaa.txt\nif -e bbbbb.txt"),
                new Fixture("@echo off\nif not exist ccccc.txt","#!/bin/bash\nif ! -e ccccc.txt"),
                new Fixture("@echo off\n if NOT EXIST ccccc.txt ","#!/bin/bash\n if ! -e ccccc.txt "),
                new Fixture("@echo off\nif not exist ccccc.txt\nif not exist ddddd.txt","#!/bin/bash\nif ! -e ccccc.txt\nif ! -e ddddd.txt"),
                new Fixture("@echo off\nif not %E%\n if NOT %F% ","#!/bin/bash\nif ! ${E}\n if ! ${F} "),
                // file path
                new Fixture("@echo off\ncd \"C:\\dir\\sub\"","#!/bin/bash\ncd \"C:/dir/sub\""),
                // arguments
                new Fixture("@echo off\nset AAA=%0","#!/bin/bash\nAAA=$0"),
                new Fixture("@echo off\nset AAA=%1","#!/bin/bash\nAAA=$1"),
                new Fixture("@echo off\nset AAA=%2","#!/bin/bash\nAAA=$2"),
                new Fixture("@echo off\nset AAA=%3","#!/bin/bash\nAAA=$3"),
                new Fixture("@echo off\nset AAA=%4","#!/bin/bash\nAAA=$4"),
                new Fixture("@echo off\nset AAA=%5","#!/bin/bash\nAAA=$5"),
                new Fixture("@echo off\nset AAA=%6","#!/bin/bash\nAAA=$6"),
                new Fixture("@echo off\nset AAA=%7","#!/bin/bash\nAAA=$7"),
                new Fixture("@echo off\nset AAA=%8","#!/bin/bash\nAAA=$8"),
                new Fixture("@echo off\nset AAA=%9","#!/bin/bash\nAAA=$9"),
                new Fixture("@echo off\nset AAA=%*","#!/bin/bash\nAAA=$@"),
                // condition
                new Fixture("@echo off\nif %1==1 echo %1","#!/bin/bash\nif $1 -eq 1 echo $1"),
                new Fixture("@echo off\nif %2!==2 echo %2","#!/bin/bash\nif $2 -ne 2 echo $2"),
                new Fixture("@echo off\nif %3<3 echo %3","#!/bin/bash\nif $3 -lt 3 echo $3"),
                new Fixture("@echo off\nif %4<=4 echo %4","#!/bin/bash\nif $4 -le 4 echo $4"),
                new Fixture("@echo off\nif %5>5 echo %5","#!/bin/bash\nif $5 -gt 5 echo $5"),
                new Fixture("@echo off\nif %6>=6 echo %6","#!/bin/bash\nif $6 -ge 6 echo $6"),
                new Fixture("@echo off\nif %7&&7 echo %7","#!/bin/bash\nif $7 -a 7 echo $7"),
                new Fixture("@echo off\nif %8||8 echo %8","#!/bin/bash\nif $8 -o 8 echo $8"),
                // if
                new Fixture(
                        "@echo off\n"
                       + "set value=%1\n"
                       + "if %value%==1 (\n"
                       + "echo 1\n"
                       + ") else if %value%==2 (\n"
                       + "echo 2\n"
                       + ") else if %value%==3 (\n"
                       + "echo 3\n"
                       + ") else (\n"
                       + "echo 4\n"
                       + ")\n"
                       + "echo %value%",
                         "#!/bin/bash\n"
                       + "value=$1\n"
                       + "if [ ${value} -eq 1 ]; then\n"
                       + "echo 1\n"
                       + "elif [ ${value} -eq 2 ]; then\n"
                       + "echo 2\n"
                       + "elif [ ${value} -eq 3 ]; then\n"
                       + "echo 3\n"
                       + "else\n"
                       + "echo 4\n"
                       + "fi\n"
                       + "echo ${value}"
                        ),
                new Fixture(
                        "@echo off\n"
                       + "set value=%1\n"
                       + "if %value%==1 (\n"
                       + "echo 1\n"
                       + ") else if %value%==2 (\n"
                       + "echo 2\n"
                       + ") else (\n"
                       + "echo 3\n"
                       + ")\n"
                       + "echo %value%",
                         "#!/bin/bash\n"
                       + "value=$1\n"
                       + "if [ ${value} -eq 1 ]; then\n"
                       + "echo 1\n"
                       + "elif [ ${value} -eq 2 ]; then\n"
                       + "echo 2\n"
                       + "else\n"
                       + "echo 3\n"
                       + "fi\n"
                       + "echo ${value}"
                        ),
                new Fixture(
                        "@echo off\n"
                       + "set value=%1\n"
                       + "if %value%==1 (\n"
                       + "echo 1\n"
                       + ") else (\n"
                       + "echo 2\n"
                       + ")\n"
                       + "echo %value%",
                         "#!/bin/bash\n"
                       + "value=$1\n"
                       + "if [ ${value} -eq 1 ]; then\n"
                       + "echo 1\n"
                       + "else\n"
                       + "echo 2\n"
                       + "fi\n"
                       + "echo ${value}"
                        ),
                new Fixture(
                        "@echo off\n"
                       + "set value=%1\n"
                       + "if %value%==1 (\n"
                       + "echo 1\n"
                       + ")\n"
                       + "echo %value%",
                         "#!/bin/bash\n"
                       + "value=$1\n"
                       + "if [ ${value} -eq 1 ]; then\n"
                       + "echo 1\n"
                       + "fi\n"
                       + "echo ${value}"
                        ),
                // for
                new Fixture(
                        "@echo off\n"
                       + "for %%i in (*.txt) do (\n"
                       + "echo %%i\n"
                       + ")\n",
                         "#!/bin/bash\n"
                       + "for i in `ls *.txt`; do\n"
                       + "echo $i\n"
                       + "done\n"
                        ),
                new Fixture(
                        "@echo off\n"
                       + "set V=%1"
                       + "for %%i in (*.txt) do (\n"
                       + "echo %%i\n"
                       + "echo %V%\n"
                       + ")\n",
                         "#!/bin/bash\n"
                       + "V=$1"
                       + "for i in `ls *.txt`; do\n"
                       + "echo $i\n"
                       + "echo ${V}\n"
                       + "done\n"
                        ),

        };
        // @formatter:on

        @Theory
        public void converted(Fixture fixture) {

            // exercise
            String actual = sut.convert(fixture.origin);

            // verify
            assertThat(actual, is(fixture.dest));

        }

        @Test(expected = IllegalArgumentException.class)
        public void throwBadGrammar() {

            // setup
            String badSentence = "@echo off\n@echo off";

            // exercise
            sut.convert(badSentence);

        }

        static class Fixture {
            String origin;
            String dest;

            Fixture(String origin, String dest) {
                this.origin = origin;
                this.dest = dest;
            }
        }

    }

}
