package com.keidrun.l2l.plugins.batchtobash.converters;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.experimental.runners.Enclosed;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import com.keidrun.l2l.plugins.batchtobash.converters.CommandConverter;
import com.keidrun.l2l.plugins.batchtobash.converters.Converter;

@RunWith(Enclosed.class)
public class CommandConverterTest {

    @RunWith(Theories.class)
    public static class convert {
        static Converter sut;

        @Before
        public void setup() throws Exception {
            sut = new CommandConverter();
        }

        // @formatter:off
        @DataPoints
        public static Fixture[] FIXTUREs = {
                // cd
                new Fixture("cd /d D","cd D"),
                new Fixture("CD /D D","cd D"),
                new Fixture("cd D","cd D"),
                new Fixture("CD D","cd D"),
                new Fixture(" cd D "," cd D "),
                new Fixture("chdir D","cd D"),
                new Fixture("CHDIR D","cd D"),

        };
        // @formatter:on

        @Theory
        public void converted(Fixture fixture) {

            // exercise
            String actual = sut.convert(fixture.origin);

            // verify
            assertThat(actual, equalTo(fixture.dest));

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
