package com.keidrun.l2l.plugins.batchtobash.comverters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.keidrun.l2l.plugins.batchtobash.comverters.Converter;
import com.keidrun.l2l.plugins.batchtobash.comverters.GrammerConverter;
import com.keidrun.l2l.plugins.exception.BadGrammarException;

@RunWith(Enclosed.class)
public class GrammerConverterTest {

    @RunWith(Theories.class)
    public static class convert {
        static Converter sut;

        @Before
        public void setup() throws Exception {
            sut = new GrammerConverter();
        }

        // @formatter:off
        @DataPoints
        public static Fixture[] FIXTUREs = {
                // head
                new Fixture("@echo off","#!/bin/bash"),
                new Fixture(" @echo off ","#!/bin/bash "),
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
                // current
                new Fixture("@echo off\necho %~dp0","#!/bin/bash\necho ."),
                new Fixture("@echo off\necho %~dp0 ","#!/bin/bash\necho . "),
                new Fixture("@echo off\necho %~dp0\necho %~dp0","#!/bin/bash\necho .\necho .")

                
        };
        // @formatter:on

        @Theory
        public void converted(Fixture fixture) {

            // exercise
            String actual = sut.convert(fixture.origin);

            // verify
            assertThat(actual, is(fixture.dest));

        }

        @Test(expected = BadGrammarException.class)
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
