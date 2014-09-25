package com.developpez.rpouiller.springtest;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="contextDifferent.xml")
public class ServiceSimpleTest {

    @Autowired
    private IServiceSimple service;
    @Autowired
    @Qualifier(value="mock")
    private DaoSimpleMock dao;

    @Before
    public void initTest() {
        dao.initMock();
    }

    @Test
    public void testCompterCrit() {
        // Arrange
        final String critere = "Crit";
        final int expected = 10;
        
        // Act
        final int valeur = service.compter(critere);
        
        // Assert
        assertEquals(expected, valeur);
        assertEquals(critere, dao.getCritere());
    }

    @Test
    public void testCompterCrit2() {
        // Arrange
        final String critere = "Crit2";
        final int expected = 10;

        // Act
        final int valeur = service.compter(critere);
        
        // Assert
        assertEquals(expected, valeur);
        assertEquals(critere, dao.getCritere());
    }
}