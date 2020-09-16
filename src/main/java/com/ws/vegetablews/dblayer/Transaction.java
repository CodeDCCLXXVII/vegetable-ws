package com.ws.vegetablews.dblayer;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection="transactions")
public class Transaction {
    @Id
    private String id;
    private String cashierId;
    private float total;
    List<VegetableTrans> vegtableTransList;
    private Date created;
    private Date lastUpdate;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCashierId() {
        return cashierId;
    }

    public void setCashierId(String cashierId) {
        this.cashierId = cashierId;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<VegetableTrans> getVegtableTransList() {
        return vegtableTransList;
    }

    public void setVegtableTransList(List<VegetableTrans> vegtableTransList) {
        this.vegtableTransList = vegtableTransList;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id='" + id + '\'' +
                ", cashierId='" + cashierId + '\'' +
                ", vegtableTransList=" + vegtableTransList +
                ", created=" + created +
                ", lastUpdate=" + lastUpdate +
                '}';
    }
}
