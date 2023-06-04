package com.solvd.constructionco.models;

import java.time.LocalDate;

public class Invoice {

    private int invoiceId;
    private int purchaseOrderId;
    private int customerId;
    private int contractorId;
    private LocalDate dueDate;
    private int totalDue;

    public Invoice(){

    };

    public Invoice(int purchaseOrderId, int customerId, int contractorId){
        this.purchaseOrderId = purchaseOrderId;
        this.customerId = customerId;
        this.contractorId = contractorId;
    };


    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public int getPurchaseOrderId() {
        return purchaseOrderId;
    }

    public void setPurchaseOrderId(int purchaseOrderId) {
        this.purchaseOrderId = purchaseOrderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getContractorId() {
        return contractorId;
    }

    public void setContractorId(int contractorId) {
        this.contractorId = contractorId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public int getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(int totalDue) {
        this.totalDue = totalDue;
    }
}
