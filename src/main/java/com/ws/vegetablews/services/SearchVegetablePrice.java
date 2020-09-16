package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.TransactionAO;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SearchVegetablePrice extends SharedDataService implements Task {

    public SearchVegetablePrice(LogsMgr logsMgr, VegetableAO vegetableAO, TransactionAO transactionAO) {
        super(logsMgr, vegetableAO, transactionAO);
    }

    @Override
    public RequestResponse execute(String trackingId, TaskRequest taskRequest, String vegetableId) {
        return null;
    }

    @Override
    public RequestResponse execute(String trackingId, String searchTerm, int page, int size) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {
            Optional<Vegetable> vegetableOptional = vegetableAO.getVegetables(searchTerm);
            if(vegetableOptional.isPresent()){
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200, GlobalVariables.SUCCESS, vegetableOptional.get());
            }else
                requestResponse.setMessage(GlobalVariables.NOT_FOUND);
        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));
        }
        return requestResponse;
    }
}
