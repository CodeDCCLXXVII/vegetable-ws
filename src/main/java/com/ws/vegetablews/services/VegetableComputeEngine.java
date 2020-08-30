package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Optional;

/**
 * @author dcclxxvii
 */

@Service
public class VegetableComputeEngine  implements Compute{

    private final LogsMgr logsMgr;
    private Logger logger = LoggerFactory.getLogger(VegetableComputeEngine.class);
    private final VegetableAO vegetableAO;
    private final SharedDataService sharedDataService;
    private final AddVegetablePrice vegetablePrice;

    @Autowired
    public VegetableComputeEngine(LogsMgr logsMgr, VegetableAO vegetableAO, SharedDataService sharedDataService, AddVegetablePrice vegetablePrice) {
        this.logsMgr = logsMgr;
        this.vegetableAO = vegetableAO;
        this.sharedDataService = sharedDataService;
        this.vegetablePrice = vegetablePrice;
    }

    @Override
    public RequestResponse excuteTask(String trackingId, String taskType, Vegetable vegetable) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try{
            if(taskType != null){
                taskType = taskType.toUpperCase();
                switch (taskType){
                    case GlobalVariables.ADD_VEG_TASK:
                        requestResponse = vegetablePrice.execute(trackingId, vegetable);
                        break;
                    case GlobalVariables.UPDATE_VEG_TASK:
                        break;
                        default:
                            requestResponse.setMessage(GlobalVariables.TASK_ERROR);
                }
            }

        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));
        }
        return requestResponse;
    }

    @Override
    public RequestResponse updateVegetablePrice(String trackingId, Vegetable vegetable, String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {
            Optional<Vegetable> vegetableOptional = vegetableAO.getVegetableByID(vegetableId);
            if(vegetableOptional.isPresent()){
                Vegetable updateVegetable = vegetableOptional.get();
                updateVegetable.setName(vegetable.getName());
                updateVegetable.setPrice(vegetable.getPrice());
                updateVegetable.setLastUpdate(sharedDataService.getDateFromLocalDateTimeNow());
                requestResponse = sharedDataService.alterVegetable(trackingId, updateVegetable, requestResponse);
            }else
                requestResponse = sharedDataService.getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_404,GlobalVariables.NOT_FOUND, vegetable);

        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));
        }
        return requestResponse;
    }

    @Override
    public RequestResponse deleteVegetablePrice(String trackingId, @NotNull String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {

            Optional<Vegetable> vegetableOptional = vegetableAO.getVegetableByID(vegetableId);
            if(vegetableOptional.isPresent()){
                vegetableAO.remove(vegetableOptional.get());
                requestResponse = sharedDataService.getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200,GlobalVariables.SUCCESS, vegetableOptional.get());

            }
            else{
                requestResponse = sharedDataService.getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_404, GlobalVariables.NOT_FOUND, vegetableId);
            }

        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));

        }

        return requestResponse;
    }

    @Override
    public RequestResponse addVegetablePriceToCart(String trackingId, String clientId, String transactionId, String vegetableId, double quantity) {
        return null;
    }

    @Override
    public RequestResponse generateVegetablePrice(String trackingId, String transactionId) {
        return null;
    }

    public RequestResponse getAllVegetables(String trackingId, @NotNull int page, @NotNull int size){

        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try {
            Page<Vegetable> vegetables = vegetableAO.getVegetables(page, size);
            if(!vegetables.isEmpty()){
                requestResponse = sharedDataService.getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200,GlobalVariables.SUCCESS, vegetables.getContent());
            }else{
                requestResponse = sharedDataService.getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_404, GlobalVariables.NOT_FOUND, null);
            }
        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));
        }
        return requestResponse;
    }


}
