package com.solvd.constructionco.dao;

import com.solvd.constructionco.models.Invoice;

import java.util.List;

public interface iInvoiceDAO<Invoice, Integer> extends ConstructionDAO<Invoice, Integer>{

    @Override
    Invoice getById(Integer invoiceID);

    @Override
    void save(Invoice invoice);

    @Override
    void update(Invoice invoice);

    @Override
    void delete(Integer invoiceID);

    @Override
    List<Invoice> getAll();
}
