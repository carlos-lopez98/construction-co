package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.InvoiceDAO;
import com.solvd.constructionco.models.Invoice;
import com.solvd.constructionco.service.interfaces.IInvoiceService;

import java.util.List;

public class InvoiceService implements IInvoiceService {

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
