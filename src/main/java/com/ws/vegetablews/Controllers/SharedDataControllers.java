package com.ws.vegetablews.Controllers;


import com.ws.vegetablews.config.ResponsesMarshaller;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SharedDataControllers {

    @Autowired
    private ResponsesMarshaller responses;

    public String getErrors(List errors){
        JSONArray response=new JSONArray();
        errors.forEach(v->response.put(new JSONObject(v).getString("defaultMessage")));
        return responses.error(2,response).toString();
    }
}
