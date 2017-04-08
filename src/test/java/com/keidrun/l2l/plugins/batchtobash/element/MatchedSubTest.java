/**
 * Copyright 2017 Keid
*/
package com.keidrun.l2l.plugins.batchtobash.element;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MatchedSubTest {

    private MatchedSub sut;

    @Before
    public void setUp() throws Exception {
        sut = new MatchedSub();
        sut.setOriginal("original");
        sut.setCommented("commented");
        sut.setConverted("converted");
    }

    @Test
    public void testGettersAndSetters() {
        assertThat(sut.getOriginal(), is("original"));
        assertThat(sut.getCommented(), is("commented"));
        assertThat(sut.getConverted(), is("converted"));
    }

}
