package com.melt.mongo.repo;

import com.melt.mongo.domain.Image;
import org.springframework.data.repository.CrudRepository;

public interface ImageRepo extends CrudRepository<Image, Long> {

    Image findById(Long id);
}
