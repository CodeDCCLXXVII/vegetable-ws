package com.ws.vegetablews.dblayer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface VegetableRepo  extends MongoRepository<Vegetable, String>{

    @Query("{$and:[ {'name': ?0}]}")
    Optional<Vegetable> findByName(String vName);
}
