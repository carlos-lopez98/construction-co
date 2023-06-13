package com.solvd.constructionco.models;

import java.sql.Date;
import java.time.LocalDate;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Invoice")
@XmlAccessorType(XmlAccessType.FIELD)
public class Invoice {

    @XmlElement(name = "invoiceId")
    private int invoiceId;

    @XmlElement(name = "dueDate")
    private java.sql.Date dueDate;

    @XmlElement(name = "totalDue")
    private int totalDue;

    @XmlElement(name = "customer")
    private Customer customer;

    @XmlElement(name = "contractor")
    private Contractor contractor;

    @XmlElement(name = "project")
    private Project project;

    public Invoice() {
    }

    // Getters and Setters

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public java.sql.Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(java.sql.Date dueDate) {
        this.dueDate = dueDate;
    }

    public int getTotalDue() {
        return totalDue;
    }

    public void setTotalDue(int totalDue) {
        this.totalDue = totalDue;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contractor getContractor() {
        return contractor;
    }

    public void setContractor(Contractor contractor) {
        this.contractor = contractor;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}