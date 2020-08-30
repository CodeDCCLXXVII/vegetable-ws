package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.springframework.stereotype.Service;

@Service
public class CalculateCost extends SharedDataService implements Task {

    public CalculateCost(LogsMgr logsMgr, VegetableAO vegetableAO) {
        super(logsMgr, vegetableAO);
    }

    @Override
    public RequestResponse execute(String trackingId, Vegetable vegetable, String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {


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
