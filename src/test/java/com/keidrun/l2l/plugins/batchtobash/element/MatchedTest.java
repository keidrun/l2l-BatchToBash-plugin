/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.element;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class MatchedTest {

    private Matched sut;

    @Before
    public void setUp() throws Exception {
        List<MatchedSub> matchedList = new ArrayList<MatchedSub>();
        MatchedSub sub = new MatchedSub();
        sub.setOriginal("original");
        sub.setCommented("commented");
        sub.setConverted("converted");
        matchedList.add(sub);
        sut = new Matched("something", "anything", matchedList);
    }

    @Test
    public void testGettersAndSetters() {

        String actualOriginal = sut.getOriginal();
        String actualCommented = sut.getCommented();
        List<MatchedSub> actualMatchedList = sut.getMatchedList();

        assertThat(actualOriginal, is("something"));
        assertThat(actualCommented, is("anything"));
        for (MatchedSub actualSub : actualMatchedList) {
            assertThat(actualSub.getOriginal(), is("original"));
            assertThat(actualSub.getCommented(), is("commented"));
            assertThat(actualSub.getConverted(), is("converted"));
        }

    }

}
