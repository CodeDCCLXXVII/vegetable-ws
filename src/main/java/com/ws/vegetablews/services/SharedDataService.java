package com.ws.vegetablews.services;



import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.Date;
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
    public RequestResponse getRequestResponse(RequestResponse requestResponse, String code, String message, Object object){
        requestResponse.setCode(code);
        requestResponse.setMessage(message);
        requestResponse.setMetadata(object);
        return requestResponse;
    }
    public RequestResponse getRequestResponse(RequestResponse requestResponse, Object object){
        requestResponse.setCode(null);
        requestResponse.setMessage(null);
        requestResponse.setMetadata(object);
        return requestResponse;
    }


    public Date getDateFromLocalDateTimeNow(){
        LocalDateTime localDateTime =LocalDateTime.now(TimeZone.getTimeZone(GlobalVariables.NAIROBI).toZoneId());
        return  Date.from(localDateTime.atZone(TimeZone.getTimeZone(GlobalVariables.NAIROBI).toZoneId()).toInstant());
    }
}
