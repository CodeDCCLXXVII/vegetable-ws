package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.TransactionAO;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class FetchVegetablePrices extends SharedDataService implements Task {

    public FetchVegetablePrices(LogsMgr logsMgr, VegetableAO vegetableAO, TransactionAO transactionAO) {
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
            Page<Vegetable> vegetables = vegetableAO.getVegetables(page, size);
            if(!vegetables.isEmpty()){
                requestResponse = getRequestResponse(requestResponse, vegetables.getContent());
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
