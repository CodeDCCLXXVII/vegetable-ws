package com.ws.vegetablews.dblayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

/**
 * @author dcclxxvii
 */

@Service
public class TransactionAO implements RepoInterface{

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public Object add(Object object) {
        return transactionRepo.save((Transaction) object);
    }

    @Override
    public Object update(Object object) {
        return add(object);
    }

    public List<Transaction> getTransactions(){
        return transactionRepo.findAll();
    }

    public Page<Transaction> getTransactions(int page, int size){
        return transactionRepo.findAll(PageRequest.of(page, size));
    }

    public List<Transaction> getTransactions(String vName){
        return transactionRepo.findByName(vName);
    }

    public Optional<Transaction> getTransactionByID(String id){
        return transactionRepo.findById(id);
    }

    @Override
    public void remove(@NotNull Object object){
        transactionRepo.delete((Transaction) object);
    }



}
