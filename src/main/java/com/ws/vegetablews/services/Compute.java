package com.ws.vegetablews.services;

public interface Compute {

    public RequestResponse excuteTask(String trackingId, String taskType, TaskRequest taskRequest, String vegetableId);
    public RequestResponse excuteTask(String trackingId, String taskType, String searchTerm, int page, int size);
    public RequestResponse addVegetablePriceToCart(String trackingId, String clientId, String transactionId,
                                                   String vegetableId, double quantity);
    public RequestResponse generateVegetablePrice(String trackingId,
                                                  String transactionId);
}
