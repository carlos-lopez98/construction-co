package com.solvd.constructionco.models;

public class Project {

    private int purchaseOrderId;
    private String purchaseOrderName;
    private int budget;
    private boolean isClosed;

    public Project(){

    };

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public String getPurchaseOrderName() {
        return purchaseOrderName;
    }

    public void setPurchaseOrderName(String purchaseOrderName) {
        this.purchaseOrderName = purchaseOrderName;
    }

    public int getBudget() {
        return budget;
    }

    public void setBudget(int budget) {
        this.budget = budget;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }
}
