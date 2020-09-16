package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalVegetableCost extends SharedDataService implements Task {

    public CalVegetableCost(LogsMgr logsMgr, VegetableAO vegetableAO, TransactionAO transactionAO) {
        super(logsMgr, vegetableAO, transactionAO);
    }

    @Override
    public RequestResponse execute(String trackingId, TaskRequest taskRequest, String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {

            Optional<Vegetable> vegetableOptional = vegetableAO.getVegetables(vegetableId);
            Transaction transaction = null;
            if(vegetableOptional.isPresent()){
                Vegetable vegetable = vegetableOptional.get();
                if(taskRequest.getTransactionId() != null) {
                    Optional<Transaction> transactionOptional = transactionAO.getTransactionByID(taskRequest.getTransactionId());
                    if (transactionOptional.isPresent()) {
                        transaction = transactionOptional.get();
                    }
                }else
                {
                    transaction = new Transaction();
                    taskRequest.setTransactionId(transaction.getId());
                }

                if(transaction != null) {
                    VegetableTrans vegetableTrans = new VegetableTrans(vegetable.getName(), vegetable.getPrice(),
                            taskRequest.getQuantity(),
                            round((vegetable.getPrice() * taskRequest.getQuantity()), 2));
                    if (transaction.getVegtableTransList() != null)
                        transaction.getVegtableTransList().add(vegetableTrans);
                    else {
                        List<VegetableTrans> vegetableTransList = new ArrayList<>();
                        vegetableTransList.add(vegetableTrans);
                        transaction.setVegtableTransList(vegetableTransList);
                    }

                    if (transactionAO.update(transaction) != null)
                        requestResponse = getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200, GlobalVariables.SUCCESS, transaction);
                }
            }else
                getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_404,GlobalVariables.NOT_FOUND, taskRequest);

        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));
        }
        return requestResponse;
    }

    @Override
    public RequestResponse execute(String trackingId, String searchTerm, int page, int size) {
        return null;
    }
}
