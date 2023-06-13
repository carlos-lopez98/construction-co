package com.solvd.constructionco.models;

import java.sql.Date;
import java.time.LocalDate;

public class Invoice {

    private int invoiceId;
    private int purchaseOrderId;
    private int customerId;
    private int contractorId;
    private Date dueDate;
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

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(int totalDue) {
        this.totalDue = totalDue;
    }
}
