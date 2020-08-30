package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UpdateVegetablePrice extends SharedDataService implements Task {

    public UpdateVegetablePrice(LogsMgr logsMgr, VegetableAO vegetableAO) {
        super(logsMgr, vegetableAO);
    }

    @Override
    public RequestResponse execute(String trackingId, Vegetable vegetable) {
        return null;
    }

    @Override
    public RequestResponse execute(String trackingId, Vegetable vegetable, String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {
            Optional<Vegetable> vegetableOptional = vegetableAO.getVegetableByID(vegetableId);
            if(vegetableOptional.isPresent()){
                Vegetable updateVegetable = vegetableOptional.get();
                updateVegetable.setName(vegetable.getName());
                updateVegetable.setPrice(vegetable.getPrice());
                updateVegetable.setLastUpdate(getDateFromLocalDateTimeNow());
                requestResponse = alterVegetable(trackingId, updateVegetable, requestResponse);
            }else
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_404,GlobalVariables.NOT_FOUND, vegetable);

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
