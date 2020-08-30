package com.ws.vegetablews.services;

import com.ws.vegetablews.dblayer.Vegetable;

public interface Task {

    public RequestResponse execute(String trackingId, Vegetable vegetable);
}
