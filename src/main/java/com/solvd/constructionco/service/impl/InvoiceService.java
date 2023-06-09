package com.solvd.constructionco.service.impl;

import com.solvd.constructionco.dao.impl.InvoiceDAO;
import com.solvd.constructionco.models.Invoice;
import com.solvd.constructionco.service.interfaces.IInvoiceService;

import java.util.List;

public class InvoiceService implements IInvoiceService {

    private static final InvoiceDAO invoiceDAO = new InvoiceDAO();

    public InvoiceService(){

    }

    @Override
    public Invoice getById(Integer invoiceID) {

        if(invoiceID == null || invoiceID < 0){

            throw new IllegalArgumentException("InvoiceId provided is not correct format");

        }

        Invoice invoice = invoiceDAO.getById(invoiceID);

        if(invoice == null){
            throw new RuntimeException("Invoice is not in database");
        }

        return invoice;
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
