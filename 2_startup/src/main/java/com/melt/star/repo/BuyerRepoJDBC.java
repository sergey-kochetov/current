package com.melt.star.repo;

import com.melt.star.model.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.melt.star.aspect.Loggable;

import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class BuyerRepoJDBC implements BuyerRepo {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    @Loggable
    public Iterable<Buyer> findAll() {
        return jdbcTemplate.query("SELECT id, name, country, token FROM buyer", this::rowMapToBuyer);
    }

    @Override
    @Loggable
    public Buyer findById(String id) {
        return jdbcTemplate.queryForObject("SELECT id, name, country, token FROM buyer WHERE id=?", this::rowMapToBuyer, id);
    }

    @Override
    @Loggable
    public Buyer save(Buyer buyer) {
        jdbcTemplate.update("INSERT INTO buyer(id, name, country, token) VALUES (?, ?, ?, ?)",
                buyer.getId(), buyer.getName(), buyer.getCountry(), buyer.getToken());
        return buyer;
    }

    private Buyer rowMapToBuyer(ResultSet rs, int rowNum) throws SQLException {
        return new Buyer().builder()
                .id(rs.getLong("id"))
                .name(rs.getString("name"))
                .country(rs.getString("country"))
                .token(rs.getInt("token"))
                .build();

    }
}
