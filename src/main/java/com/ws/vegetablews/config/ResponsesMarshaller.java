/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.vegetablews.config;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

@Service
public class ResponsesMarshaller {
    private final String code="response_code";
    private final String desc="description";
    private final String error="error";
    private final String success="success";
    public JSONObject success(String message){
       return new JSONObject()
               .put(code, 0)
               .put(desc, message)
               .put(success, true);
    }
    public JSONObject error(int cd,JSONArray message){
       return new JSONObject()
               .put(code, cd)
               .put(error, true)
               .put(desc, message);
    }
}
