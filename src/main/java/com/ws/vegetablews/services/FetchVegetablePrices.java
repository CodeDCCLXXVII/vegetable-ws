package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FetchVegetablePrices extends SharedDataService implements Task {

    public FetchVegetablePrices(LogsMgr logsMgr, VegetableAO vegetableAO) {
        super(logsMgr, vegetableAO);
    }

    @Override
    public RequestResponse execute(String trackingId, Vegetable vegetable, String vegetableId) {
        return null;
    }

    @Override
    public RequestResponse execute(String trackingId, String searchTerm, int page, int size) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {
            Page<Vegetable> vegetables = vegetableAO.getVegetables(page, size);
            if(!vegetables.isEmpty()){
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200,GlobalVariables.SUCCESS, vegetables.getContent());
            }else{
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_404, GlobalVariables.NOT_FOUND, null);
            }
        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));
        }
        return requestResponse;
    }
}
