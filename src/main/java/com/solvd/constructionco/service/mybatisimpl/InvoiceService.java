package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.models.Invoice;
import com.solvd.constructionco.service.interfaces.IInvoiceService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class InvoiceService implements IInvoiceService {

    private static final Logger logger = LogManager.getLogger(Main.class);

    private static final String BATIS_CONFIG = "src/main/resources/config/mybatis-config.xml";
    private static final String SAVE_PROJECT = "com.solvd.constructionco.mybatis.impl.invoicemapper.save";
    private static final String GET_BY_ID = "com.solvd.constructionco.mybatis.impl.invoicemapper.getById";
    private static final String UPDATE_PROJECT = "com.solvd.constructionco.mybatis.impl.invoicemapper.update";
    private static final String DELETE_PROJECT = "com.solvd.constructionco.mybatis.impl.invoicemapper.delete";
    private static final String GET_ALL = "com.solvd.constructionco.mybatis.impl.invoicemapper.getAll";

    @Override
    public Invoice getById(Integer invoiceId) {
        return null;
    }

    @Override
    public void save(Invoice invoice) {

    }

    @Override
    public void update(Invoice invoice) {

    }

    @Override
    public void delete(Integer invoiceId) {

    }

    @Override
    public List<Invoice> getAll() {
        return null;
    }
}
