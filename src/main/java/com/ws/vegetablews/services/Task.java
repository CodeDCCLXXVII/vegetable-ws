package com.ws.vegetablews.services;


public interface Task {

    public RequestResponse execute(String trackingId, TaskRequest taskRequest, String vegetableId);
    public RequestResponse execute(String trackingId, String searchTerm, int page, int size);
}
