package com.ws.vegetablews.services;

import com.ws.vegetablews.dblayer.Vegetable;

public interface Compute {

    public RequestResponse excuteTask(String trackingId, String taskType, Vegetable vegetable);
    public RequestResponse updateVegetablePrice(String trackingId, Vegetable vegetable,
                                                String vegetableId);
    public RequestResponse deleteVegetablePrice(String trackingId, String vegetableId);
    public RequestResponse addVegetablePriceToCart(String trackingId, String clientId, String transactionId,
                                                   String vegetableId, double quantity);
    public RequestResponse generateVegetablePrice(String trackingId,
                                                  String transactionId);
}
