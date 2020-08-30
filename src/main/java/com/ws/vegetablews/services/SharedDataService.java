package com.ws.vegetablews.services;



import com.ws.vegetablews.config.GlobalVariables;
import com.ws.vegetablews.config.LogsMgr;
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
import java.util.TimeZone;

/**
 * @author dcclxxvii
 */
@Service
public class SharedDataService {

    @Autowired
    private LogsMgr logsMgr;
    private final Logger logger = LoggerFactory.getLogger(SharedDataService.class);

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

    public Date getDateFromLocalDateTimeNow(int lessMonths){
        LocalDateTime localDateTime =LocalDateTime.now();
        ZoneId zoneId = ZoneId.of(GlobalVariables.NAIROBI);
        ZoneOffset zoneOffset = zoneId.getRules().getOffset(localDateTime);
        LocalDateTime less = LocalDateTime.from(localDateTime.toInstant(zoneOffset)).minusMonths(lessMonths);
        return  Date.from(less.atZone(TimeZone.getTimeZone(GlobalVariables.NAIROBI).toZoneId()).toInstant());
    }

    public Date getLessMonths(int lessMonths){
        Date now = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(now);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
        c.add(Calendar.MONTH, -lessMonths);
        return c.getTime();
    }

    public Date getParseDate(String dateStr, String format){
        try{
            return  new SimpleDateFormat(format).parse(dateStr);
        } catch(Exception e){
            logger.error(logsMgr.addlogger("", GlobalVariables.ERROR_CODE_500,GlobalVariables.REQUEST_FOR_SESSION_RESULT,logsMgr.errorMessage(e)));
        }

        return  null;
    }
}
