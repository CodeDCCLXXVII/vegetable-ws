package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.TransactionAO;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteVegetablePrice extends SharedDataService implements Task {

    public DeleteVegetablePrice(LogsMgr logsMgr, VegetableAO vegetableAO, TransactionAO transactionAO) {
        super(logsMgr, vegetableAO, transactionAO);
    }

    @Override
    public RequestResponse execute(String trackingId, TaskRequest taskRequest, String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {
            Optional<Vegetable> vegetableOptional = vegetableAO.getVegetableByID(vegetableId);
            if(vegetableOptional.isPresent()){
                vegetableAO.remove(vegetableOptional.get());
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200,GlobalVariables.SUCCESS, vegetableOptional.get());

            }
            else{
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_404, GlobalVariables.NOT_FOUND, vegetableId);
            }

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
