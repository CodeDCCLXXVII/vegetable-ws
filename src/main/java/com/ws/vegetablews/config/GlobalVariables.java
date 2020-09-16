/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ws.vegetablews.config;

import org.springframework.stereotype.Service;

/**
 * @author dcclxxvii
 */
@Service
public class GlobalVariables {


    public static final String USERDETAILS = "userDetails";
    public static final String APP_INFO ="appInfo" ;
    public static final String USERINFO = "userInfo";

    private GlobalVariables() {
    }
    public static final int SUCCESS_CODE_00 = 0;
    public static final String SUCCESS_CODE_200 = "200";
    public static final String ERROR_CODE_99 = "99";
    public static final String ERROR_CODE_500 = "500";
    public static final  String ERROR_CODE_404 = "404";
    public static String TRACKING_ID = "transactionId";
    public static final String NOT_FOUND = "Record not found";
    public static final String ERROR="error";
    public static final String SUCCESS="success";
    public static final String REQUEST="request";
    public static final String EXISTS = "Record already exists";
    public static final String REQUEST_FOR_CHAT_FLOW = "Recieved chat from social media";
    public static final String REQUEST_FOR_SESSION_RESULT = "Recieved session details from session service";
    public static final String NAIROBI = "Africa/Nairobi" ;

    public static final String ADD_VEG_TASK="ADD_VEG";
    public static final String UPDATE_VEG_TASK="UPDATE_VEG";
    public static final String DELETE_VEG_TASK="DELETE_VEG";
    public static final String CALC_VEG_COST_TASK="VEG_COST";
    public static final String GET_LIST_VEG_TASK="GET_VEG_LIST";
    public static final String SEARCH_VEG_TASK="SEARCH_VEG";
    public static final String TASK_ERROR="Task unknown";

}