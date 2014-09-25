package com.developpez.rpouiller.springtest;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.ExpectedDatabase;
import com.github.springtestdbunit.assertion.DatabaseAssertionMode;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="DaoSimpleTest-context.xml")
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
    DbUnitTestExecutionListener.class })
@ActiveProfiles(profiles="implementation")
public class DaoSimpleTestAvecSpringTestDBUnit {

    @Autowired
    private IDaoSimple dao;

    @Test
    @DatabaseSetup("donnees.xml")
    public void testCompterAvecC() {
        // Arrange
        final String critere = "C";
        final int expected = 5;

        // Act
        final int valeur = dao.compter(critere);

        // Assert
        assertEquals(expected, valeur);
    }

    @Test
    @DatabaseSetup("donnees.xml")
    public void testCompterAvecCh() {
        // Arrange
        final String critere = "Ch";
        final int expected = 2;

        // Act
        final int valeur = dao.compter(critere);

        // Assert
        assertEquals(expected, valeur);
    }

    @Test
    @DatabaseSetup("donnees.xml")
    @ExpectedDatabase("donnees-prevues.xml")
    public void testSupprimer() {
        // Arrange
        final int identifiant = 2;

        // Act
        dao.supprimer(identifiant);
    }

    @Test
    @DatabaseSetup("donnees.xml")
    @ExpectedDatabase(assertionMode=DatabaseAssertionMode.NON_STRICT,
            value="donnees-prevues-creer2.xml")
    public void testCreer() {
        // Arrange
        final String nom = "Test";
    
        // Act
        dao.creer(nom);
    }
}