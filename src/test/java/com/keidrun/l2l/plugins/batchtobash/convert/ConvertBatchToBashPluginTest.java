package com.keidrun.l2l.plugins.batchtobash.convert;

import static org.hamcrest.CoreMatchers.is;
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
                          "@echo off \n"
                        + "echo hello",
                          "#!/bin/bash \n"
                        + "echo hello")

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
        Combination com = new Combination(Language.BATCH, Language.BASH);

        // exercise
        boolean actual = sut.isSupported(com);

        // verify
        assertTrue(actual);

    }

}
