package com.ws.vegetablews.services;



import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.Vegetable;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.TimeZone;

/**
 * @author dcclxxvii
 */
@Service
public class SharedDataService {

    protected final LogsMgr logsMgr;
    protected final Logger logger = LoggerFactory.getLogger(SharedDataService.class);
    protected final
    VegetableAO vegetableAO;

    @Autowired
    public SharedDataService(LogsMgr logsMgr, VegetableAO vegetableAO) {
        this.logsMgr = logsMgr;
        this.vegetableAO = vegetableAO;
    }

    /**
     *
     * @param requestResponse
     * @param code
     * @param message
     * @param object
     * @return
     */
    public RequestResponse getRequestResponse(RequestResponse requestResponse, int code, String message, Object object){
        requestResponse.setCode(code);
        requestResponse.setMessage(message);
        requestResponse.setMetadata(object);
        return requestResponse;
    }

    public Date getDateFromLocalDateTimeNow(){
        LocalDateTime localDateTime =LocalDateTime.now(TimeZone.getTimeZone(GlobalVariables.NAIROBI).toZoneId());
        return  Date.from(localDateTime.atZone(TimeZone.getTimeZone(GlobalVariables.NAIROBI).toZoneId()).toInstant());
    }

    public RequestResponse alterVegetable(String trackingId, Vegetable vegetable, RequestResponse requestResponse){
        try {
            Optional<Vegetable> checkVegetable = vegetableAO.getVegetables(vegetable.getName());
            if(checkVegetable.isPresent()){
                requestResponse = getRequestResponse(requestResponse, GlobalVariables.ERROR_CODE_500, GlobalVariables.EXISTS, vegetable);
            }else{
                if(vegetableAO.add(vegetable) != null)
                    requestResponse = getRequestResponse(requestResponse, GlobalVariables.SUCCESS_CODE_200, GlobalVariables.SUCCESS, vegetable);
            }

        }catch (Exception e){
            requestResponse.setMessage(e.getMessage());
            logger.error(logsMgr.addlogger(trackingId, GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST,logsMgr.errorMessage(e)));
        }
        return requestResponse;
    }
}
