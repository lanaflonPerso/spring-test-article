package com.developpez.rpouiller.springtest;

import static org.junit.Assert.assertEquals;

import javax.sql.DataSource;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExternalResource;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ninja_squad.dbsetup.DbSetup;
import com.ninja_squad.dbsetup.DbSetupTracker;
import com.ninja_squad.dbsetup.Operations;
import com.ninja_squad.dbsetup.destination.DataSourceDestination;
import com.ninja_squad.dbsetup.operation.Operation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="DaoSimpleTest-context.xml")
@ActiveProfiles(profiles="implementation")
public class DaoSimpleTestAvecDbSetup {

    private static DbSetupTracker dbSetupTracker = new DbSetupTracker();

    @Autowired
    private IDaoSimple dao;

    @Autowired
    @Qualifier(value="dataSource")
    private DataSource dataSource;

    @Rule
    public ExternalResource externalResource = new ExternalResource() {

        @Override
        protected void before() throws Throwable {
            final Operation operation =
                Operations.sequenceOf(
                    Operations.deleteAllFrom("EXEMPLE"),
                    Operations.insertInto("EXEMPLE")
                        .columns("identifiant", "nom")
                        .values("1", "Chocolat")
                        .values("2", "Chien")
                        .values("3", "Framboise")
                        .values("4", "Cacao")
                        .values("5", "Caramel")
                        .values("6", "Cerise")
                        .values("7", "Fraise")
                        .build());

            final DbSetup dbSetup = new DbSetup(new DataSourceDestination(dataSource), operation);
            dbSetupTracker.launchIfNecessary(dbSetup);
        }
    };

    @Test
    public void testCompterAvecC() {
        dbSetupTracker.skipNextLaunch();

        // Arrange
        final String critere = "C";
        final int expected = 5;

        // Act
        final int valeur = dao.compter(critere);

        // Assert
        assertEquals(expected, valeur);
    }

    @Test
    public void testCompterAvecCh() {
        dbSetupTracker.skipNextLaunch();

        // Arrange
        final String critere = "Ch";
        final int expected = 2;

        // Act
        final int valeur = dao.compter(critere);

        // Assert
        assertEquals(expected, valeur);
    }
}
