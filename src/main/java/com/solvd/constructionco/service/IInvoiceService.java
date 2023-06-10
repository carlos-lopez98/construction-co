package com.solvd.constructionco.service;

import com.solvd.constructionco.models.Invoice;

import java.util.List;

public interface IInvoiceService extends ConstructionServiceOperations<Invoice, Integer>{

    @Override
    <T> T getById(Integer invoiceID);

    @Override
    void save(Invoice invoice);

    @Override
    void update(Invoice invoice);

    @Override
    void delete(Integer invoiceID);

    @Override
    List<Invoice> getAll();
}
