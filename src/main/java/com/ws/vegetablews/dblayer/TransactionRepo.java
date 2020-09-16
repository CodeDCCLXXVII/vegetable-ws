package com.ws.vegetablews.dblayer;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransactionRepo extends MongoRepository<Transaction, String>{

    @Query("{$and:[ {'cashierId': ?0}]}")
    List<Transaction> findByName(String vName);

}
