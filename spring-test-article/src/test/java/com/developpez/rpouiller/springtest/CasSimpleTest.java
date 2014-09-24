package com.developpez.rpouiller.springtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@TestExecutionListeners(DependencyInjectionTestExecutionListener.class)
public class CasSimpleTest {

    @Autowired
    private ICasSimple casSimple;

    @Test
    public void testAdditionner() {
        // Arrange
        final int param1 = 2;
        final int param2 = 2;
        final int expected = 4;
        
        // Act
        final int resultat = casSimple.additionner(param1, param2);
        
        // Assert
        assertEquals(expected, resultat);
    }
}