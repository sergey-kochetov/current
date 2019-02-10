package com.melt.star.repo;

import com.melt.star.model.Buyer;

public interface BuyerRepo {

    Iterable<Buyer> findAll();

    Buyer findById(String id);

    Buyer save(Buyer buyer);
}
