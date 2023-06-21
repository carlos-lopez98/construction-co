package com.solvd.constructionco.service.mybatisimpl;

import com.solvd.constructionco.Main;
import com.solvd.constructionco.dao.ICustomerDAO;
import com.solvd.constructionco.dao.ISupplierDAO;
import com.solvd.constructionco.models.Customer;
import com.solvd.constructionco.models.Supplier;
import com.solvd.constructionco.service.interfaces.ISupplierService;
import com.solvd.constructionco.util.SQLSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

public class SupplierService implements ISupplierService {

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final SQLSessionUtil sessionUtil = new SQLSessionUtil();

    @Override
    public Supplier getById(Integer supplierID) {
        if (supplierID > 0) {
            Supplier supplier = null;

            SqlSession session = sessionUtil.retrieveSqlSession();
            ISupplierDAO supplierDAO = session.getMapper(ISupplierDAO.class);
            supplier = (Supplier) supplierDAO.getById(supplierID);

            //Commits and closes
            session.commit();
            session.close();
            return supplier;
        } else {
            throw new RuntimeException("Invalid SupplierID Entered");
        }
    }

    @Override
    public void save(Supplier supplier) {
        if (supplier != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ISupplierDAO supplierDAO = session.getMapper(ISupplierDAO.class);

            supplierDAO.save(supplier);

            session.commit();
            session.close();

            logger.info("Succesfully saved supplier to database");
        } else {
            throw new NullPointerException("Supplier Entered is null");
        }
    }

    @Override
    public void update(Supplier supplier) {
        if (supplier != null) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ISupplierDAO supplierDAO = session.getMapper(ISupplierDAO.class);

            supplierDAO.update(supplier);

            session.commit();
            session.close();

            logger.info("Succesfully saved supplier to database");
        } else {
            throw new NullPointerException("Supplier Entered is null");
        }
    }

    @Override
    public void delete(Integer supplierID) {
        if (supplierID > 0) {
            SqlSession session = sessionUtil.retrieveSqlSession();
            ISupplierDAO supplierDAO = session.getMapper(ISupplierDAO.class);
            Supplier supplier = (Supplier) supplierDAO.getById(supplierID);
            if (supplier != null) {
                supplierDAO.delete(supplierID);
                session.commit();
                session.close();
                logger.info("Succesfully deleted supplier from database");
            } else {
                throw new RuntimeException("SupplierID is not in database");
            }
        } else {
            throw new RuntimeException("SupplierID given is not valid");
        }
    }

    @Override
    public List<Supplier> getAll() {
        SqlSession session = sessionUtil.retrieveSqlSession();
        ISupplierDAO supplierDAO = session.getMapper(ISupplierDAO.class);

        List<Supplier> suppliers = supplierDAO.getAll();

        if (suppliers.isEmpty()) {
            throw new RuntimeException("No suppliers in Database");
        } else {
            session.commit();
            session.close();
            return suppliers;
        }
    }
}
