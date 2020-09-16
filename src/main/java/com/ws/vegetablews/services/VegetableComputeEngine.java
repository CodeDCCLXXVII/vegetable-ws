package com.ws.vegetablews.services;

import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.Vegetable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dcclxxvii
 */

@Service
public class VegetableComputeEngine  implements Compute{

    private final LogsMgr logsMgr;
    private Logger logger = LoggerFactory.getLogger(VegetableComputeEngine.class);
    private final AddVegetablePrice vegetablePrice;
    private final UpdateVegetablePrice updateVegetablePrice;
    private final FetchVegetablePrices fetchVegetablePrices;
    private final SearchVegetablePrice searchVegetablePrice;
    private final DeleteVegetablePrice deleteVegetablePrice;
    private final CalVegetableCost calVegetableCost;

    @Autowired
    public VegetableComputeEngine(LogsMgr logsMgr, AddVegetablePrice vegetablePrice, UpdateVegetablePrice updateVegetablePrice, FetchVegetablePrices fetchVegetablePrices, SearchVegetablePrice searchVegetablePrice, DeleteVegetablePrice deleteVegetablePrice, CalVegetableCost calVegetableCost) {
        this.logsMgr = logsMgr;
        this.vegetablePrice = vegetablePrice;
        this.updateVegetablePrice = updateVegetablePrice;
        this.fetchVegetablePrices = fetchVegetablePrices;
        this.searchVegetablePrice = searchVegetablePrice;
        this.deleteVegetablePrice = deleteVegetablePrice;
        this.calVegetableCost = calVegetableCost;
    }

    @Override
    public RequestResponse excuteTask(String trackingId, String taskType, TaskRequest taskRequest, String vegetableId) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try{
            if(taskType != null){
                taskType = taskType.toUpperCase();
                switch (taskType){
                    case GlobalVariables.ADD_VEG_TASK:
                        requestResponse = vegetablePrice.execute(trackingId, taskRequest, null);
                        break;
                    case GlobalVariables.UPDATE_VEG_TASK:
                        requestResponse = updateVegetablePrice.execute(trackingId, taskRequest, vegetableId);
                        break;
                    case GlobalVariables.DELETE_VEG_TASK:
                        requestResponse = deleteVegetablePrice.execute(trackingId, null, vegetableId);
                        break;
                    case GlobalVariables.CALC_VEG_COST_TASK:
                        requestResponse = calVegetableCost.execute(trackingId, taskRequest, vegetableId);
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
    public RequestResponse excuteTask(String trackingId, String taskType, String searchterm, int page, int size) {
        RequestResponse requestResponse = new RequestResponse(GlobalVariables.ERROR_CODE_500, GlobalVariables.ERROR);
        try{
            if(taskType != null){
                taskType = taskType.toUpperCase();
                switch (taskType){
                    case GlobalVariables.GET_LIST_VEG_TASK:
                        requestResponse = fetchVegetablePrices.execute(trackingId,null, page, size);
                        break;
                    case GlobalVariables.SEARCH_VEG_TASK:
                        requestResponse = searchVegetablePrice.execute(trackingId, searchterm, page, size);
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
    public RequestResponse addVegetablePriceToCart(String trackingId, String clientId, String transactionId, String vegetableId, double quantity) {
        return null;
    }

    @Override
    public RequestResponse generateVegetablePrice(String trackingId, String transactionId) {
        return null;
    }



}
