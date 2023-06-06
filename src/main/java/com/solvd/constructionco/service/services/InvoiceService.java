package com.solvd.constructionco.service.services;

import com.solvd.constructionco.dao.InvoiceDAO;
import com.solvd.constructionco.interfaces.ConstructionServiceOperations;
import com.solvd.constructionco.models.Invoice;

import java.util.List;

public class InvoiceService implements ConstructionServiceOperations<Invoice, Integer> {

    private InvoiceDAO invoiceDAO;

    public InvoiceService(InvoiceDAO invoiceDAO){
        this.invoiceDAO = invoiceDAO;
    }

    @Override
    public Invoice getById(Integer invoiceID) {
        return invoiceDAO.getById(invoiceID);
    }

    @Override
    public void save(Invoice invoice) {
        invoiceDAO.save(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        invoiceDAO.update(invoice);
    }

    @Override
    public void delete(Integer invoiceID) {
        invoiceDAO.delete(invoiceID);
    }

    @Override
    public List getAll() {
        return invoiceDAO.getAll();
    }
}
