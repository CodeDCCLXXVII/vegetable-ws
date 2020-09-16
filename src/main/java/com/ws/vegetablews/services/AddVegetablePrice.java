package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.TransactionAO;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddVegetablePrice extends SharedDataService implements Task {

    public AddVegetablePrice(LogsMgr logsMgr, VegetableAO vegetableAO, TransactionAO transactionAO) {
        super(logsMgr, vegetableAO, transactionAO);
    }

    @Override
    public RequestResponse execute(String trackingId, TaskRequest taskRequest, String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {
            Vegetable newVegetable = new Vegetable(taskRequest.getName(), taskRequest.getPrice());
            newVegetable.setCreated(getDateFromLocalDateTimeNow());
            newVegetable.setLastUpdate(getDateFromLocalDateTimeNow());

            Optional<Vegetable> checkVegetable = vegetableAO.getVegetables(newVegetable.getName());
            if(checkVegetable.isPresent()){
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_500, GlobalVariables.EXISTS, taskRequest);
            }else{
                if(vegetableAO.add(newVegetable) != null)
                    requestResponse = getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200, GlobalVariables.SUCCESS, newVegetable);
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
