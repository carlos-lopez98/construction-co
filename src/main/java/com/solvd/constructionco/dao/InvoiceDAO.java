package com.solvd.constructionco.dao;

import com.solvd.constructionco.interfaces.ConstructionDAO;
import com.solvd.constructionco.models.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO implements ConstructionDAO<Invoice, Integer> {

    private List<Invoice> invoices;

    public InvoiceDAO() {
        invoices = new ArrayList<>();
    }

    @Override
    public Invoice getById(Integer id) {
        for (Invoice invoice : invoices) {
            if (invoice.getInvoiceId() == id) {
                return invoice;
            }
        }
        return null;
    }

    @Override
    public void save(Invoice invoice) {
        invoices.add(invoice);
    }

    @Override
    public void update(Invoice invoice) {
        Invoice existingInvoice = getById(invoice.getInvoiceId());
        if (existingInvoice != null) {
            existingInvoice.setPurchaseOrderId(invoice.getPurchaseOrderId());
            existingInvoice.setCustomerId(invoice.getCustomerId());
            existingInvoice.setContractorId(invoice.getContractorId());
            existingInvoice.setDueDate(invoice.getDueDate());
            existingInvoice.setTotalDue(invoice.getTotalDue());
        }
    }

    @Override
    public void delete(Integer id) {
        Invoice invoiceToRemove = getById(id);
        if (invoiceToRemove != null) {
            invoices.remove(invoiceToRemove);
        }
    }

    @Override
    public List<Invoice> getAll() {
        return invoices;
    }
}