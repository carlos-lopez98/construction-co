package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.*;
import com.solvd.constructionco.models.Employee;
import com.solvd.constructionco.models.Invoice;
import com.solvd.constructionco.service.interfaces.IInvoiceService;
import com.solvd.constructionco.util.SQLSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InvoiceService implements IInvoiceService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Invoice getById(Integer invoiceId) {
        if (invoiceId > 0) {
            Invoice invoice = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            IInvoiceDAO invoiceDAO = session.getMapper(IInvoiceDAO.class);

            invoice = (Invoice) invoiceDAO.getById(invoiceId);

            //Commits and closes
            session.commit();
            session.close();
            return invoice;
        } else {
            throw new RuntimeException("Invalid InvoiceId Entered");
        }    }

    @Override
    public void save(Invoice invoice) {
        if (invoice != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IInvoiceDAO invoiceDAO = session.getMapper(IInvoiceDAO.class);

            invoiceDAO.save(invoice);

            session.commit();
            session.close();

            logger.info("Successfully saved invoice to database");
        } else {
            throw new NullPointerException("Invoice Entered is null");
        }
    }

    @Override
    public void update(Invoice invoice) {
        if (invoice != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IInvoiceDAO invoiceDAO = session.getMapper(IInvoiceDAO.class);

            invoiceDAO.update(invoice);

            session.commit();
            session.close();

            logger.info("Successfully updated invoice to database");
        } else {
            throw new NullPointerException("Invoice Entered is null");
        }
    }

    @Override
    public void delete(Integer invoiceId) {
        if (invoiceId > 0) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            IInvoiceDAO invoiceDAO = session.getMapper(IInvoiceDAO.class);
            Invoice invoice = (Invoice) invoiceDAO.getById(invoiceId);
            if (invoice != null) {
                invoiceDAO.delete(invoiceId);
                session.commit();
                session.close();
                logger.info("Succesfully deleted invoice from database");
            } else {
                throw new RuntimeException("InvoiceId is not in database");
            }
        } else {
            throw new RuntimeException("InvoiceId given is not valid");
        }
    }

    @Override
    public List<Invoice> getAll() {
        SqlSession session = sessionUtil.retrieveSqlSession();
        IInvoiceDAO invoiceDAO = session.getMapper(IInvoiceDAO.class);

        List<Invoice> invoices = invoiceDAO.getAll();

        if (invoices.isEmpty()) {
            throw new RuntimeException("No Invoices in Database");
        } else {
            session.commit();
            session.close();
            return invoices;
        }
    }
}
