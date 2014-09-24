package com.developpez.rpouiller.springtest;

import static org.junit.Assert.fail;

public class DaoSimpleMock implements IDaoSimple {

    private String critere;

    @Override
    public int compter(final String critere) {
        if (this.critere != null) {
            fail("Le service ne doit appeler la DAO qu'une seule fois");
        }
        this.critere = critere;
        return 10;
    }

    public String getCritere() {
        return critere;
    }

    public void initMock() {
        critere = null;
    }

    @Override
    public void supprimer(int identifiant) {
        
    }

    @Override
    public void creer(String nom) {
        
    }
}