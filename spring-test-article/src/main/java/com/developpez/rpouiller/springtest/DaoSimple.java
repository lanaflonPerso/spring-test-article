package com.developpez.rpouiller.springtest;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
@Profile(value="implementation")
public class DaoSimple implements IDaoSimple {

    private static final String REQUETE_COMPTER =
            "SELECT COUNT(*) FROM EXEMPLE WHERE NOM LIKE :nom";
    private static final String REQUETE_SUPPRIMER =
        "DELETE FROM EXEMPLE WHERE IDENTIFIANT = ?";
    private static final String REQUETE_CREER =
        "INSERT INTO EXEMPLE(IDENTIFIANT, NOM) " + 
        "VALUES ((SELECT MAX(IDENTIFIANT) + 1 FROM EXEMPLE), ?)";

    @Autowired
    private DataSource dataSource;

    @Override
    public int compter(final String critere) {
        final NamedParameterJdbcTemplate jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        final Map<String, Object> params = new HashMap<String, Object>();
        params.put("nom", critere + "%");
        return jdbcTemplate.queryForObject(REQUETE_COMPTER, params, Integer.class);
    }

    @Override
    public void supprimer(final int identifiant) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(REQUETE_SUPPRIMER, identifiant);
    }

    @Override
    public void creer(final String nom) {
        final JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTemplate.update(REQUETE_CREER, nom);
    }
}