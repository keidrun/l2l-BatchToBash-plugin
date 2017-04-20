/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.element;

import static org.hamcrest.CoreMatchers.equalTo;
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

        assertThat(actualOriginal, equalTo("something"));
        assertThat(actualCommented, equalTo("anything"));
        for (MatchedSub actualSub : actualMatchedList) {
            assertThat(actualSub.getOriginal(), equalTo("original"));
            assertThat(actualSub.getCommented(), equalTo("commented"));
            assertThat(actualSub.getConverted(), equalTo("converted"));
        }

    }

}
