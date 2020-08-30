/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.vegetablews.config;


import org.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@Configuration
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    public String getTrackingId(JSONObject request){
        if(request.has(GlobalVariables.TRACKING_ID))
            return request.getString(GlobalVariables.TRACKING_ID);
        else
            return UUID.randomUUID().toString();
    }

    public  String getTracking(){
        return UUID.randomUUID().toString();
    }
} 