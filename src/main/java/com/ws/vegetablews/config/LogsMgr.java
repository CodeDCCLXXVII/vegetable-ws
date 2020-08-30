/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.vegetablews.config;

import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.UUID;

@Service
public class LogsMgr {

    public String addlogger(String requestId, int responseCode,String message,String logDetailedMessage){
        StringBuilder sb=new StringBuilder();
        sb
                .append(message)
                .append(" | RequestId =")
                .append(requestId)
                .append(" | ResponseCode =")
                .append(responseCode)
                .append(" | LogDetailedMessage= ")
                .append(logDetailedMessage);
        return sb.toString();
    }

    public  String errorMessage(Exception e){
        StringWriter sw = new StringWriter();
        e.printStackTrace(new PrintWriter(sw));
        return sw.toString();
    }

    public String getTrackingId(){
        return UUID.randomUUID().toString();
    }
    
}
