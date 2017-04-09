package com.keidrun.l2l.plugins.batchtobash.convert;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.keidrun.l2l.face.element.Combination;
import com.keidrun.l2l.face.element.Language;
import com.keidrun.l2l.face.plugins.ConvertPlugin;

@RunWith(Enclosed.class)
public class ConvertBatchToBashPluginTest {

    @RunWith(Theories.class)
    public static class convert {
        static ConvertPlugin sut;
        static Combination com;

        @Before
        public void setup() throws Exception {
            sut = new ConvertBatchToBashPlugin();
            com = new Combination(Language.BATCH, Language.BASH);
        }

        // @formatter:off
        @DataPoints
        public static Fixture[] FIXTUREs = {
                new Fixture(
                          "@echo off\n"
                        + "cd /d %~dp0\n\n"
                        + "set ARG=%1\n\n"
                        + "if %ARG%==1 (\n"
                        + "    echo 1\n"
                        + ") else if %ARG%==2 (\n"
                        + "    echo 2\n"
                        + ") else (\n"
                        + "    echo 3\n"
                        + ")\n\n"
                        + "for %%i in (*.txt) do (\n"
                        + "    type %%i\n"
                        + ")\n\n"
                        + "pause",
                          "#!/bin/bash\n"
                        + "cd `dirname $0`\n\n"
                        + "ARG=$1\n\n"
                        + "if [ ${ARG} -eq 1 ]; then\n"
                        + "    echo 1\n"
                        + "elif [ ${ARG} -eq 2 ]; then\n"
                        + "    echo 2\n"
                        + "else\n"
                        + "    echo 3\n"
                        + "fi\n\n"
                        + "for i in `ls *.txt`; do\n"
                        + "    cat $i\n"
                        + "done\n\n"
                        + "read -p \"Press [Enter] key to resume.\""
                        ),
        };
        // @formatter:on

        @Theory
        public void converted(Fixture fixture) {

            // exercise
            String actual = sut.convert(com, fixture.origin);

            // verify
            assertThat(actual, is(fixture.dest));

        }

        @Test(expected = IllegalArgumentException.class)
        public void badCombination() {

            // setup
            Combination com = new Combination(Language.C, Language.JAVA);

            // exercise
            sut.convert(com, "");
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

    @Test
    public void isSupportedTest() {

        // setup
        ConvertPlugin sut = new ConvertBatchToBashPlugin();
        Combination comTrue = new Combination(Language.BATCH, Language.BASH);
        Combination comFalse1 = new Combination(Language.BATCH, Language.JAVA);
        Combination comFalse2 = new Combination(Language.JAVA, Language.BASH);

        // exercise
        boolean actualTrue = sut.isSupported(comTrue);
        boolean actualFalse1 = sut.isSupported(comFalse1);
        boolean actualFalse2 = sut.isSupported(comFalse2);

        // verify
        assertTrue(actualTrue);
        assertFalse(actualFalse1);
        assertFalse(actualFalse2);

    }

}
