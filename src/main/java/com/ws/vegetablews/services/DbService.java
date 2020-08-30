package com.ws.vegetablews.services;

import com.ws.vegetablews.config.LogsMgr;
import com.ws.vegetablews.dblayer.VegetableAO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DbService {

    protected final LogsMgr logsMgr;
    protected Logger logger = LoggerFactory.getLogger(VegetableComputeEngine.class);
    protected final VegetableAO vegetableAO;

    @Autowired
    public DbService(LogsMgr logsMgr, VegetableAO vegetableAO) {
        this.logsMgr = logsMgr;
        this.vegetableAO = vegetableAO;
    }
}
