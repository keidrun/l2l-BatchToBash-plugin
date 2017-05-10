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
                // clear
                new Fixture("cls","clear"),
                new Fixture(" cls "," clear "),
                new Fixture("CLS","clear"),
                new Fixture(" CLS "," clear "),
                // bash
                new Fixture("cmd ","bash "),
                new Fixture("cmd /c ","bash "),
                new Fixture("cmd /C ","bash "),
                new Fixture("cmd /k ","bash "),
                new Fixture("cmd /K ","bash "),
                new Fixture(" cmd "," bash "),
                new Fixture(" cmd /c "," bash "),
                new Fixture(" cmd /C "," bash "),
                new Fixture(" cmd /k "," bash "),
                new Fixture(" cmd /K "," bash "),
                new Fixture("CMD ","bash "),
                new Fixture("CMD /c ","bash "),
                new Fixture("CMD /C ","bash "),
                new Fixture("CMD /k ","bash "),
                new Fixture("CMD /K ","bash "),
                new Fixture(" CMD "," bash "),
                new Fixture(" CMD /c "," bash "),
                new Fixture(" CMD /C "," bash "),
                new Fixture(" CMD /k "," bash "),
                new Fixture(" CMD /K "," bash "),
                new Fixture("command ","bash "),
                new Fixture("command /c ","bash "),
                new Fixture("command /C ","bash "),
                new Fixture("command /k ","bash "),
                new Fixture("command /K ","bash "),
                new Fixture(" command "," bash "),
                new Fixture(" command /c "," bash "),
                new Fixture(" command /C "," bash "),
                new Fixture(" command /k "," bash "),
                new Fixture(" command /K "," bash "),
                new Fixture("COMMAND ","bash "),
                new Fixture("COMMAND /c ","bash "),
                new Fixture("COMMAND /C ","bash "),
                new Fixture("COMMAND /k ","bash "),
                new Fixture("COMMAND /K ","bash "),
                new Fixture(" COMMAND "," bash "),
                new Fixture(" COMMAND /c "," bash "),
                new Fixture(" COMMAND /C "," bash "),
                new Fixture(" COMMAND /k "," bash "),
                new Fixture(" COMMAND /K "," bash "),
                // diff
                new Fixture("comp ","diff "),
                new Fixture(" comp "," diff "),
                new Fixture("COMP ","diff "),
                new Fixture(" COMP "," diff "),
                new Fixture("fc ","diff "),
                new Fixture(" fc "," diff "),
                new Fixture("FC ","diff "),
                new Fixture(" FC "," diff "),
                // cp
                new Fixture("copy /s ","cp -r "),
                new Fixture(" copy /s "," cp -r "),
                new Fixture("copy /S ","cp -r "),
                new Fixture(" copy /S "," cp -r "),
                new Fixture("COPY /S ","cp -r "),
                new Fixture(" COPY /S "," cp -r "),
                new Fixture("COPY /s ","cp -r "),
                new Fixture(" COPY /s "," cp -r "),
                new Fixture("xcopy /s ","cp -r "),
                new Fixture("xcopy /S ","cp -r "),
                new Fixture(" xcopy /S "," cp -r "),
                new Fixture("XCOPY /S ","cp -r "),
                new Fixture("XCOPY /s ","cp -r "),
                new Fixture(" XCOPY /s "," cp -r "),
                new Fixture("copy ","cp "),
                new Fixture(" copy "," cp "),
                new Fixture("COPY ","cp "),
                new Fixture(" COPY "," cp "),
                new Fixture("xcopy ","cp "),
                new Fixture(" xcopy "," cp "),
                new Fixture("XCOPY ","cp "),
                new Fixture(" XCOPY "," cp "),
                
                
                
                
                // adddrv on DOS
                new Fixture("adddrv","#adddrv"),
                new Fixture(" adddrv "," #adddrv "),
                new Fixture("ADDDRV","#ADDDRV"),
                new Fixture(" ADDDRV "," #ADDDRV "),
                // attrib on DOS
                new Fixture("attrib","#attrib"),
                new Fixture(" attrib "," #attrib "),
                new Fixture("ATTRIB","#ATTRIB"),
                new Fixture(" ATTRIB "," #ATTRIB "),
                // chcp on DOS
                new Fixture("chcp","#chcp"),
                new Fixture(" chcp "," #chcp "),
                new Fixture("CHCP","#CHCP"),
                new Fixture(" CHCP "," #CHCP "),
                // chev on DOS
                new Fixture("chev","#chev"),
                new Fixture(" chev "," #chev "),
                new Fixture("CHEV","#CHEV"),
                new Fixture(" CHEV "," #CHEV "),
                // chkdsk on DOS
                new Fixture("chkdsk","#chkdsk"),
                new Fixture(" chkdsk "," #chkdsk "),
                new Fixture("CHKDSK","#CHKDSK"),
                new Fixture(" CHKDSK "," #CHKDSK "),
                // choice on DOS
                new Fixture("choice","#choice"),
                new Fixture(" choice "," #choice "),
                new Fixture("CHOICE","#CHOICE"),
                new Fixture(" CHOICE "," #CHOICE "),
                // clip on DOS
                new Fixture("clip","#clip"),
                new Fixture(" clip "," #clip "),
                new Fixture("CLIP","#CLIP"),
                new Fixture(" CLIP "," #CLIP "),
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
