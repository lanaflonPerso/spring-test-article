package com.developpez.rpouiller.springtest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServiceSimple implements IServiceSimple {

    @Autowired
    private IDaoSimple dao;

    public void setDao(final IDaoSimple dao) {
        this.dao = dao;
    }

    @Override
    public int compter(final String critere) {
        if (critere == null) {
            throw new NullPointerException("Le critère ne doit pas être null");
        }
        final int valeur = dao.compter(critere);
        return valeur;
    }
}