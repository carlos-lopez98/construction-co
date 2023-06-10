package com.solvd.constructionco.dao.impl;

import com.solvd.constructionco.dao.IInvoiceDAO;
import com.solvd.constructionco.models.Invoice;

import java.util.ArrayList;
import java.util.List;

public class InvoiceDAO implements IInvoiceDAO<Invoice, Integer> {

    private List<Invoice> invoices;

    private static final String getAllQuery = "SELECT invoice_id, purchase_order_id, customer_id, contractor_id, due_date, total_due FROM invoices";
    private static final String getByIdQuery = "SELECT invoice_id, purchase_order_id, customer_id, contractor_id, due_date, total_due FROM invoices WHERE invoice_id = ?";
    private static final String saveQuery = "INSERT INTO invoices VALUES(?,?,?,?,?,?)";
    private static final String deleteQuery = "DELETE FROM invoices WHERE invoice_id = ?";
    private static final String updateQuery = "UPDATE invoices SET purchase_order_id = ?, customer_id = ?, contractor_id = ?, due_date = ?, total_due = ? WHERE invoice_id = ?";

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